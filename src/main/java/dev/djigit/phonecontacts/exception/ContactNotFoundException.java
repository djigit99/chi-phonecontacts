package dev.djigit.phonecontacts.exception;

public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException(Integer id) {
        super("Contact with id " + id + " not found.");
    }

    public ContactNotFoundException(String name) {
        super("Contact with name " + name + " not found.");
    }
}
