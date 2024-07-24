package br.com.yourwishismycommand.application.dtos.inbound;

import java.util.List;

public interface NewOrderDTO {
    int professional();
    String description();
    List<String> medias();
}
