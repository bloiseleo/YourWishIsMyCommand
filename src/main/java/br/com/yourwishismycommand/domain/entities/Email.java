package br.com.yourwishismycommand.domain.entities;

import br.com.yourwishismycommand.domain.exceptions.InvalidEmailException;

import java.util.regex.Pattern;

public final class Email {
    private static final Pattern validEmailPattern = Pattern.compile("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    public static void validate(String data) {
        if(data == null) throw new InvalidEmailException();
        if(!validEmailPattern.matcher(data).matches()) {
            throw new InvalidEmailException(data);
        }
    }
    private final String data;
    public Email(String data) {
        validate(data);
        this.data = data;
    }
    @Override
    public String toString() {
        return data;
    }
}
