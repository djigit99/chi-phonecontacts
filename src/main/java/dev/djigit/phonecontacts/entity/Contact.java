package dev.djigit.phonecontacts.entity;


import dev.djigit.phonecontacts.validation.EmailCollection;
import dev.djigit.phonecontacts.validation.PhoneCollection;

import javax.persistence.*;
import java.util.List;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "contact_name")
    private String name;

    @EmailCollection
    @ElementCollection
    @CollectionTable(name = "emails", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "contact_emails")
    private List<String> contactEmails;

    @PhoneCollection
    @ElementCollection
    @CollectionTable(name = "phone_numbers", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "contact_phone_numbers")
    private List<String> phoneNumbers;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private PhoneContactUser user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getContactEmails() {
        return contactEmails;
    }

    public void setContactEmails(List<String> contactEmails) {
        this.contactEmails = contactEmails;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public PhoneContactUser getUser() {
        return user;
    }

    public void setUser(PhoneContactUser user) {
        this.user = user;
    }
}
