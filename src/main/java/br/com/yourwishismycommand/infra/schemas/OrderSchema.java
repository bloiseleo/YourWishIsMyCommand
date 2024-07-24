package br.com.yourwishismycommand.infra.schemas;

import br.com.yourwishismycommand.domain.entities.OrderMedias;
import br.com.yourwishismycommand.domain.entities.OrderStatus;
import br.com.yourwishismycommand.infra.schemas.converters.OrderMediaConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class OrderSchema {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_id_seq")
    @SequenceGenerator(name = "orders_id_seq", allocationSize = 1)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private ProfileSchema client;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professional_id", nullable = false)
    private ProfileSchema professional;
    @Column(name = "description", nullable = false)
    private String description;
    @Convert(converter = OrderMediaConverter.class, attributeName = "medias")
    @Column(name = "medias", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private OrderMedias medias;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus statusa = OrderStatus.OPEN;
}
