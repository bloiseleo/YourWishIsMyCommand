package br.com.yourwishismycommand.infra.schemas.converters;

import br.com.yourwishismycommand.domain.entities.OrderMedias;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

public class OrderMediaConverter implements AttributeConverter<OrderMedias, String> {
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(OrderMedias orderMedias) {
        try {
            return mapper.writeValueAsString(orderMedias);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
    @Override
    public OrderMedias convertToEntityAttribute(String s) {
        try {
            return mapper.readValue(s, OrderMedias.class);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
