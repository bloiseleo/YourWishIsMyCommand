package br.com.yourwishismycommand.domain.entities;

import br.com.yourwishismycommand.domain.exceptions.ApplicationValidationException;
import br.com.yourwishismycommand.domain.exceptions.InvalidClientAndProfessional;

import java.util.List;

public class Order {
    private int id;
    private User client;
    private User professional;
    private String description;
    private List<String> medias;
    private OrderStatus status;
    public Order(
            User client,
            User professional,
            String description,
            List<String> medias
    ) {
        this.id = 0;
        setClientAndProfessional(client, professional);
        setDescription(description);
        setMedias(medias);
        status = OrderStatus.OPEN;
    }
    public Order(
            int id,
            User client,
            User professional,
            String description,
            List<String> medias,
            OrderStatus status
    ) {
        this.id = 0;
        this.client = client;
        this.professional = professional;
        this.description = description;
        this.medias = medias;
        this.status = status;
    }
    public int getId() {
        return id;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public String getDescription() {
        return description;
    }
    public User getClient() {
        return client;
    }
    public User getProfessional() {
        return professional;
    }
    public List<String> getMedias() {
        return medias;
    }
    public void setMedias(List<String> medias) {
        if(medias == null) {
            throw new ApplicationValidationException(
                    List.of("Order must have medias attached to it")
            );
        }
        if(medias.isEmpty()) {
            throw new ApplicationValidationException(
                    List.of("Order must have medias attached to it")
            );
        }
        this.medias = medias;
    }
    public void setClientAndProfessional(User client, User professional) {
        if (client.getId() == professional.getId()) {
            throw new InvalidClientAndProfessional();
        }
        this.client = client;
        this.professional = professional;
    }
    public void setDescription(String description) {
        if(description == null) {
            throw new ApplicationValidationException(
                    List.of("Description must be provided for all orders")
            );
        }
        if(description.isEmpty() || description.replace(" ", "").isEmpty()) {
            throw new ApplicationValidationException(
                    List.of("Description must be provided for all orders")
            );
        }
        this.description = description;
    }
}
