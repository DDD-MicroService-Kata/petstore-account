package com.ThoughtWorks.DDD.account.domain;

import javax.persistence.Embeddable;

import static java.util.Objects.nonNull;

@Embeddable
public class Contacts implements ValueObject<Contacts> {
    private String phoneNumber;
    private String emailAddress;

    public Contacts() {
    }

    public Contacts(String phoneNumber, String emailAddress) {
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public boolean sameValueAs(Contacts other) {
        return nonNull(other)
                && other.emailAddress.equals(emailAddress)
                && other.phoneNumber.equals(phoneNumber);
    }
}
