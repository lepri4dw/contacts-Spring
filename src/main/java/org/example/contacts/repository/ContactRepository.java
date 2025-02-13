package org.example.contacts.repository;

import org.example.contacts.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByFullNameContaining(String fullName);
}
