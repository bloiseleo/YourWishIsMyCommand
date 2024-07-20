package br.com.yourwishismycommand.domain.entities;

import br.com.yourwishismycommand.domain.exceptions.ApplicationValidationException;

import java.util.List;
import java.util.regex.Pattern;

public class Address {
    private static Pattern codePattern = Pattern.compile("^\\d{8}$");
    private String address;
    private int addressNumber;
    private String complement = "";
    private String addressCode;
    private String city;
    private String state;
    public Address(
            String address,
            int number,
            String complement,
            String code,
            String city,
            String state
    ) {
        this.address = address;
        setAddressNumber(number);
        setAddressCode(code);
        this.complement = complement;
        this.city = city;
        this.state = state;
    }
    public Address(
            String address,
            int number,
            String code,
            String city,
            String state
    ) {
        this.address = address;
        setAddressNumber(number);
        setAddressCode(code);
        this.city = city;
        this.state = state;
    }
    public void setAddressNumber(int number) {
        if(number <= 0) {
            throw new ApplicationValidationException(
                    List.of("Address Number must be bigger than 0")
            );
        }
        this.addressNumber = number;
    }
    public void setAddressCode(String code) {
        if (code.length() != 8) {
            throw new ApplicationValidationException(
                    List.of("Address Code must have 8 digits")
            );
        }
        var matcher = codePattern.matcher(code);
        if(!matcher.matches()) {
            throw new ApplicationValidationException(
                    List.of("Address Code must have 8 digits")
            );
        }
        this.addressCode = code;
    }

    public int getAddressNumber() {
        return addressNumber;
    }

    public String getComplement() {
        return complement;
    }

    public String getAddressCode() {
        return addressCode;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getAddress() {
        return address;
    }
}
