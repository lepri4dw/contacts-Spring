package org.example.contacts.service;

import org.example.contacts.entity.Address;
import org.example.contacts.entity.Contact;
import org.example.contacts.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    public Address createAddress(Address address) {
        Contact contact = address.getContact();
        contact.getAddresses().add(address);
        return addressRepository.save(address);
    }

    public Address updateAddress(Long id, Address address) {
        if (!addressRepository.existsById(id)) {
            return null;
        }
        address.setId(id);
        return addressRepository.save(address);
    }

    public boolean deleteAddress(Long id) {
        if (!addressRepository.existsById(id)) {
            return false;
        }
        addressRepository.deleteById(id);
        return true;
    }
}
