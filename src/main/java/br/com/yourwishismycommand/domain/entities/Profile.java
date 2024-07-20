package br.com.yourwishismycommand.domain.entities;

import br.com.yourwishismycommand.domain.exceptions.ApplicationValidationException;

import java.util.List;

public class Profile {
    private User user;
    private Address address;
    private String tradingName;
    private UserRole userRole;
    public Profile(
            User user,
            Address address,
            String tradingName,
            UserRole role
    ) {
        this.user = user;
        this.address = address;
        userRole = role;
        setTradingName(tradingName);
    }
    public void setTradingName(String name) {
        if(userRole.equals(UserRole.PROFESSIONAL)) {
            if(name == null) {
                throw new ApplicationValidationException(
                        List.of("Professional profile must have a trading name")
                );
            }
            if(name.isEmpty()) {
                throw new ApplicationValidationException(
                        List.of("Professional profile must have a trading name")
                );
            }
            if(name.length() < 3) {
                throw new ApplicationValidationException(
                        List.of("Trading name must have more than 3 characters")
                );
            }
        }
        this.tradingName = name;
    }
    public UserRole getUserRole() {
        return userRole;
    }
    public Address getAddress() {
        return address;
    }
    public String getTradingName() {
        return tradingName;
    }
    public User getUser() {
        return user;
    }
}
