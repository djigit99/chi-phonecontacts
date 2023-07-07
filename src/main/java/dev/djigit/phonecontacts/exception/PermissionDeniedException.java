package dev.djigit.phonecontacts.exception;

public class PermissionDeniedException extends RuntimeException {
    public PermissionDeniedException(String msg) {
        super(msg);
    }
}
