package org.example.contacts.ui;

import org.example.contacts.entity.Contact;
import org.example.contacts.service.ContactService;
import org.example.contacts.service.EmailService;
import org.example.contacts.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/ui/emails")
public class EmailUIController {
    private final ContactService contactService;
    private final EmailService emailService;

    @Autowired
    public EmailUIController(ContactService contactService, EmailService emailService) {
        this.contactService = contactService;
        this.emailService = emailService;
    }

    @PostMapping("/add/{contactId}")
    public String addEmail(@PathVariable Long contactId, @ModelAttribute Email email) {
        Optional<Contact> optionalContact = contactService.getContactById(contactId);

        email.setContact(optionalContact.get());

        emailService.createEmail(email);

        return "redirect:/ui/contacts/details/" + contactId;
    }

    @GetMapping("/delete/{id}/{contactId}")
    public String deleteEmail(@PathVariable Long id, @PathVariable Long contactId) {
        emailService.deleteEmail(id);
        return "redirect:/ui/contacts/details/" + contactId;
    }
}
