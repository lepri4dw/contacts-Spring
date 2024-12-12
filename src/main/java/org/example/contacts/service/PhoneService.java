package org.example.contacts.service;

import org.example.contacts.entity.Contact;
import org.example.contacts.entity.Phone;
import org.example.contacts.repository.PhoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneService {

    private final PhoneRepository phoneRepository;

    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public List<Phone> getAllPhones() {
        return phoneRepository.findAll();
    }

    public Optional<Phone> getPhoneById(Long id) {
        return phoneRepository.findById(id);
    }

    public Phone createPhone(Phone phone) {
        Contact contact = phone.getContact();
        contact.getPhones().add(phone);
        return phoneRepository.save(phone);
    }

    public Phone updatePhone(Long id, Phone phone) {
        if (!phoneRepository.existsById(id)) {
            return null;
        }
        phone.setId(id);
        return phoneRepository.save(phone);
    }

    public boolean deletePhone(Long id) {
        if (!phoneRepository.existsById(id)) {
            return false;
        }
        phoneRepository.deleteById(id);
        return true;
    }
}
