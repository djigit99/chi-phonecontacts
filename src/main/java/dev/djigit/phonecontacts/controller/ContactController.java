package dev.djigit.phonecontacts.controller;

import dev.djigit.phonecontacts.dto.ContactDto;
import dev.djigit.phonecontacts.dto.ContactIdDto;
import dev.djigit.phonecontacts.entity.Contact;
import dev.djigit.phonecontacts.entity.PhoneContactUser;
import dev.djigit.phonecontacts.exception.ContactNotFoundException;
import dev.djigit.phonecontacts.exception.PermissionDeniedException;
import dev.djigit.phonecontacts.service.ContactService;
import dev.djigit.phonecontacts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ContactDto> getContacts(Authentication authentication) {
        return contactService.getContacts(authentication.getName());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ContactIdDto addContact(@Valid @RequestBody ContactDto contactDto,
                                   Authentication authentication) {
        String login = authentication.getName();
        PhoneContactUser loggedInUser = userService.findUserByLogin(login);
        Contact contact = contactService.addContact(contactDto, loggedInUser);
        return new ContactIdDto(contact.getId());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ContactDto updateContact(@Valid @RequestBody ContactDto contactDto,
                                      Authentication authentication) {
        contactService.updateContact(contactDto, authentication);
        return contactDto;
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ContactIdDto deleteContact(@Valid @RequestBody ContactIdDto contactIdDto,
                                      Authentication authentication) {
        Integer contactId = contactIdDto.getContactId();
        Optional<Contact> contact = contactService.getContact(contactId);
        if (contact.isEmpty())
            throw new ContactNotFoundException(contactId);

        String loggedInUserLogin = authentication.getName();
        String contactOwnerLogin = contact.get().getUser().getLogin();
        if (!contactOwnerLogin.equals(loggedInUserLogin))
            throw new PermissionDeniedException("You have no right to delete this user.");

        contactService.deleteContact(contactId);
        return contactIdDto;
    }
}
