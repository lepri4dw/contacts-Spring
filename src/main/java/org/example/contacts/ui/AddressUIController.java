package org.example.contacts.ui;

import org.example.contacts.entity.Address;
import org.example.contacts.entity.Contact;
import org.example.contacts.service.AddressService;
import org.example.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/ui/addresses")
public class AddressUIController {
    private final ContactService contactService;
    private final AddressService addressService;

    @Autowired
    public AddressUIController(ContactService contactService, AddressService addressService) {
        this.contactService = contactService;
        this.addressService = addressService;
    }

    @PostMapping("/add/{contactId}")
    public String addAddress(@PathVariable Long contactId, @ModelAttribute Address address) {
        Optional<Contact> optionalContact = contactService.getContactById(contactId);

        address.setContact(optionalContact.get());

        addressService.createAddress(address);

        return "redirect:/ui/contacts/details/" + contactId;
    }

    @GetMapping("/delete/{id}/{contactId}")
    public String deleteAddress(@PathVariable Long id, @PathVariable Long contactId) {
        addressService.deleteAddress(id);
        return "redirect:/ui/contacts/details/" + contactId;
    }
}
