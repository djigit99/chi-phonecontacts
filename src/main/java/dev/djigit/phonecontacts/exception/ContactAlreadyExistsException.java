package dev.djigit.phonecontacts.exception;

public class ContactAlreadyExistsException extends RuntimeException {
    public ContactAlreadyExistsException(String contactName) {
        super("Contact with name " + contactName + " already exists.");
    }

}
