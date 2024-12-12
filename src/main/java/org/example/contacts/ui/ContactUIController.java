package org.example.contacts.ui;

import org.example.contacts.entity.Contact;
import org.example.contacts.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/ui/contacts")
public class ContactUIController {

    private final ContactService contactService;

    public ContactUIController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public String getAllContacts(Model model) {
        List<Contact> contacts = contactService.getAllContacts();
        model.addAttribute("contacts", contacts);
        return "contacts";
    }

    @GetMapping("/search")
    public String searchContacts(@RequestParam String name, Model model) {
        List<Contact> contacts = contactService.searchContactsByName(name);
        model.addAttribute("contacts", contacts);
        model.addAttribute("searchActive", true); // Флаг для отображения кнопки "Показать все"
        return "contacts";
    }

    @GetMapping("/details/{id}")
    public String getContactDetails(@PathVariable Long id, Model model) {
        Contact contact = contactService.getContactById(id).orElse(null);
        model.addAttribute("contact", contact);
        return "contact-details";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact-form";
    }

    @PostMapping
    public String createContact(@ModelAttribute Contact contact) {
        contactService.createContact(contact);
        return "redirect:/ui/contacts";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Contact contact = contactService.getContactById(id).orElse(null);
        model.addAttribute("contact", contact);
        return "contact-form";
    }

    @PostMapping("/{id}")
    public String updateContact(@PathVariable Long id, @ModelAttribute Contact contact) {
        contactService.updateContact(id, contact.getFullName());
        return "redirect:/ui/contacts";
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return "redirect:/ui/contacts";
    }
}