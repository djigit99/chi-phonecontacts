package dev.djigit.phonecontacts.service;

import dev.djigit.phonecontacts.dao.UserRepository;
import dev.djigit.phonecontacts.entity.Contact;
import dev.djigit.phonecontacts.dto.ContactDto;
import dev.djigit.phonecontacts.dao.ContactRepository;
import dev.djigit.phonecontacts.entity.PhoneContactUser;
import dev.djigit.phonecontacts.exception.ContactAlreadyExistsException;
import dev.djigit.phonecontacts.exception.ContactNotFoundException;
import dev.djigit.phonecontacts.exception.PermissionDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private UserRepository userRepository;

    public Contact addContact(ContactDto contactDto, PhoneContactUser loggedInUser) {

        Optional<Contact> existingContact = contactRepository.findByName(contactDto.getName());
        existingContact.ifPresent(c -> {throw new ContactAlreadyExistsException(c.getName());});

        Contact contact = new Contact();
        contact.setName(contactDto.getName());
        contact.setContactEmails(contactDto.getEmails());
        contact.setPhoneNumbers(contactDto.getPhones());
        contact.setUser(loggedInUser);

        contactRepository.save(contact);
        return contact;
    }

    public Optional<Contact> getContact(Integer id) {
        return contactRepository.findById(id);
    }

    public List<ContactDto> getContacts(String username) {
        Optional<PhoneContactUser> loggedInUser = userRepository.findByLogin(username);
        List<Contact> userContacts = loggedInUser.map(PhoneContactUser::getContacts)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        return userContacts.stream().map(contact -> {
            ContactDto contactDto = new ContactDto();
            contactDto.setName(contact.getName());
            contactDto.setEmails(contact.getContactEmails());
            contactDto.setPhones(contact.getPhoneNumbers());
            return contactDto;
        }).collect(Collectors.toList());
    }

    public void deleteContact(Integer id) {
        contactRepository.deleteById(id);
    }

    @Transactional
    public void updateContact(ContactDto contactDto, Authentication authentication) {
        Optional<Contact> contactOpt = contactRepository.findByName(contactDto.getName());
        if (contactOpt.isEmpty())
            throw new ContactNotFoundException(contactDto.getName());
        Contact contact = contactOpt.get();

        PhoneContactUser contactOwner = contact.getUser();
        PhoneContactUser loggedInUser = userRepository.findByLogin(authentication.getName()).get();

        if (!contactOwner.getId().equals(loggedInUser.getId()))
            throw new PermissionDeniedException("You have no right to update the contact.");

        contact.setContactEmails(contactDto.getEmails());
        contact.setPhoneNumbers(contactDto.getPhones());
        contactRepository.save(contact);
    }
}
