package org.example.contacts.ui;

import org.example.contacts.entity.Contact;
import org.example.contacts.entity.Phone;
import org.example.contacts.service.ContactService;
import org.example.contacts.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/ui/phones")
public class PhoneUIController {
    private final ContactService contactService;
    private final PhoneService phoneService;

    @Autowired
    public PhoneUIController(ContactService contactService, PhoneService phoneService) {
        this.contactService = contactService;
        this.phoneService = phoneService;
    }

    @PostMapping("/add/{contactId}")
    public String addPhone(@PathVariable Long contactId, @ModelAttribute Phone phone) {
        Optional<Contact> optionalContact = contactService.getContactById(contactId);

        phone.setContact(optionalContact.get());

        phoneService.createPhone(phone);

        return "redirect:/ui/contacts/details/" + contactId;
    }

    @GetMapping("/delete/{id}/{contactId}")
    public String deletePhone(@PathVariable Long id, @PathVariable Long contactId) {
        phoneService.deletePhone(id);
        return "redirect:/ui/contacts/details/" + contactId;
    }
}
