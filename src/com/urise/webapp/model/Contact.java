package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Contact {
    private final String address;
    private final String email;
    private final String phone;
    private final String homepage;
    private List<String> profiles = new ArrayList<>();

    public Contact(String address, String email, String phone, String homepage, List<String> profiles) {
        Objects.requireNonNull(address, "Address shouldn't be null!");
        Objects.requireNonNull(email, "Email shouldn't be null!");
        Objects.requireNonNull(phone, "Phone shouldn't be null!");

        this.address = address;
        this.email = email;
        this.phone = phone;
        this.homepage = homepage;
        this.profiles = profiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (!address.equals(contact.address)) return false;
        if (!email.equals(contact.email)) return false;
        return phone.equals(contact.phone);
    }

    @Override
    public int hashCode() {
        int result = address.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + phone.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", homepage='" + homepage + '\'' +
                ", profiles=" + profiles +
                '}';
    }
}
