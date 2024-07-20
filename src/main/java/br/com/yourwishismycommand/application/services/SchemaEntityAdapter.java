package br.com.yourwishismycommand.application.services;

public interface SchemaEntityAdapter<S, E> {
    S from(E entity);
    E to(S schema);
}
