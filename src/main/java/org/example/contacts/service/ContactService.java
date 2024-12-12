package org.example.contacts.service;

import org.example.contacts.entity.Contact;
import org.example.contacts.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public Contact updateContact(Long id, String fullName) {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isEmpty()) {
            return null;
        }

        Contact existingContact = optionalContact.get();

        existingContact.setFullName(fullName);

        return contactRepository.save(existingContact);
    }

    public boolean deleteContact(Long id) {
        if (!contactRepository.existsById(id)) {
            return false;
        }
        contactRepository.deleteById(id);
        return true;
    }

    public List<Contact> searchContactsByName(String name) {
        return contactRepository.findByFullNameContaining(name);
    }
}