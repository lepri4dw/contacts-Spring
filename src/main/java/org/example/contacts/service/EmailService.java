package org.example.contacts.service;

import org.example.contacts.entity.Contact;
import org.example.contacts.entity.Email;
import org.example.contacts.repository.EmailRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailService {

    private final EmailRepository emailRepository;

    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public List<Email> getAllEmails() {
        return emailRepository.findAll();
    }

    public Optional<Email> getEmailById(Long id) {
        return emailRepository.findById(id);
    }

    public Email createEmail(Email email) {
        Contact contact = email.getContact();
        contact.getEmails().add(email);
        return emailRepository.save(email);
    }

    public Email updateEmail(Long id, Email email) {
        if (!emailRepository.existsById(id)) {
            return null;
        }
        email.setId(id);
        return emailRepository.save(email);
    }

    public boolean deleteEmail(Long id) {
        if (!emailRepository.existsById(id)) {
            return false;
        }
        emailRepository.deleteById(id);
        return true;
    }
}
