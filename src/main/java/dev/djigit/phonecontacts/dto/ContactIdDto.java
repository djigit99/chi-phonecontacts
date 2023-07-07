package dev.djigit.phonecontacts.dto;

import javax.validation.constraints.NotNull;

public class ContactIdDto {
    @NotNull
    private Integer contactId;

    public ContactIdDto(Integer contactId) {
        this.contactId = contactId;
    }

    public ContactIdDto() {
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public Integer getContactId() {
        return contactId;
    }
}