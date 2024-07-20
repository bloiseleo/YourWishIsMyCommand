package br.com.yourwishismycommand.infra.schemas.embeddables;

import br.com.yourwishismycommand.domain.entities.Address;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AddressSchema {
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "address_number", nullable = false)
    private int addressNumber;
    @Column(name = "complement", nullable = true)
    private String complement;
    @Column(name = "address_code", nullable = false)
    private String addressCode;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "state", nullable = false)
    private String state;
    private AddressSchema(
            String address,
            int number,
            String complement,
            String code,
            String city,
            String state
    ) {
        this.address = address;
        this.addressNumber = number;
        this.complement = complement;
        this.addressCode = code;
        this.city = city;
        this.state = state;
    }
    public AddressSchema() {}
    public static AddressSchema create(Address address) {
        return new AddressSchema(
                address.getAddress(),
                address.getAddressNumber(),
                address.getComplement(),
                address.getAddressCode(),
                address.getCity(),
                address.getState()
        );
    }
    public Address toDomain() {
        return new Address(
                address,
                addressNumber,
                complement,
                addressCode,
                city,
                state
        );
    }
}
