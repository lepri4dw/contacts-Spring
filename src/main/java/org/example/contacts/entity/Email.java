package org.example.contacts.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "emails")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String emailAddress;

    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false, referencedColumnName = "id")
    @JsonIgnoreProperties("emails")
    private Contact contact;
}
