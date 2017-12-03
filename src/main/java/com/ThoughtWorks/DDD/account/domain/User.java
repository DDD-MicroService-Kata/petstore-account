package com.ThoughtWorks.DDD.account.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@javax.persistence.Entity
public class User implements Entity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private Contacts contacts;

    public User() {
    }

    public User(final String firstName, final String lastName, Contacts contacts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contacts = contacts;
    }

    @Override
    public boolean sameIdentityAs(Long otherId) {
        return id.equals(otherId);
    }

    public final Long getId() {
        return id;
    }

    public final void setId(final Long id) {
        this.id = id;
    }

    public final String getFirstName() {
        return firstName;
    }

    public final void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public final String getLastName() {
        return lastName;
    }

    public final void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public boolean changeEmailAddressTo(String emailAddress) {
        return changeToNewContacts(new Contacts(this.contacts.getPhoneNumber(), emailAddress));
    }

    public Contacts getContacts() {
        return contacts;
    }

    public boolean changePhoneNumberTo(String newPhoneNumber) {
        return changeToNewContacts(new Contacts(newPhoneNumber, this.contacts.getEmailAddress()));
    }

    private boolean changeToNewContacts(Contacts newContacts) {
        if(contacts.sameValueAs(newContacts)) {
            return false;
        }
        contacts = newContacts;
        return true;
    }
}
