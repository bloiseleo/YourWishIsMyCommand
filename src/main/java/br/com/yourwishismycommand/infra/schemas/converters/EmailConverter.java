package br.com.yourwishismycommand.infra.schemas.converters;

import br.com.yourwishismycommand.domain.entities.Email;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter
public class EmailConverter implements AttributeConverter<Email, String> {
    @Override
    public String convertToDatabaseColumn(Email email) {
        return email.toString();
    }
    @Override
    public Email convertToEntityAttribute(String s) {
        return new Email(s);
    }
}
