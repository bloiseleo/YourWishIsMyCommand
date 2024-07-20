package br.com.yourwishismycommand.infra.security;

import br.com.yourwishismycommand.application.dtos.UserWithRoleDTO;
import br.com.yourwishismycommand.application.services.JwtManagerService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    private final JwtManagerService jwtManagerService;
    private final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    public JwtFilter(JwtManagerService jwtManagerService) {
        this.jwtManagerService = jwtManagerService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var header = request.getHeader("Authorization");
        if(header == null) {
            filterChain.doFilter(request, response);
            return;
        }
        if(header.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }
        var splited = header.split("Bearer ");
        if (splited.length <= 1) {
            filterChain.doFilter(request, response);
            return;
        }
        var token = splited[1];
        try {
            var user = jwtManagerService.decodeJwt(token);
            injectUserInSecurityContext(user);
        } catch (RuntimeException exception) {
            logger.debug(exception.getMessage());
        } finally {
            filterChain.doFilter(request, response);
        }
    }
    private void injectUserInSecurityContext(UserWithRoleDTO userWithRoleDTO) {
        var context = SecurityContextHolder.getContext();
        var userDetails = UserDetailsServiceImpl.adapt(userWithRoleDTO.user(), userWithRoleDTO.role());
        context.setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userWithRoleDTO.user().getPassword(),
                        userDetails.getAuthorities()
                )
        );
    }
}
