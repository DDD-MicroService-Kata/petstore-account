package com.ThoughtWorks.DDD.account.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    private User customer;
    private String initEmailAddress;
    private String initPhoneNumber;

    @Before
    public void setUp() throws Exception {
        initEmailAddress = "james@gmail.com";
        initPhoneNumber = "13200001111";
        customer = new User("James", "Merson", new Contacts(initPhoneNumber, initEmailAddress));
    }

    @Test
    public void should_be_able_to_change_the_user_email_address() throws Exception {
        String newEmailAddress = "james.merson@gmail.com";

        assertThat(customer.changeEmailAddressTo(newEmailAddress)).isTrue();
        assertThat(customer.getContacts().getEmailAddress()).isEqualTo(newEmailAddress);
    }

    @Test
    public void should_not_be_allowed_to_change_given_email_address_same_as_before() throws Exception {
        assertThat(customer.changeEmailAddressTo(initEmailAddress)).isFalse();
        assertThat(customer.getContacts().getEmailAddress()).isEqualTo(initEmailAddress);
    }

    @Test
    public void should_be_able_to_change_the_user_phone_number() throws Exception {
        String newPhoneNumber = "12311110000";

        assertThat(customer.changePhoneNumberTo(newPhoneNumber)).isTrue();
        assertThat(customer.getContacts().getPhoneNumber()).isEqualTo(newPhoneNumber);
    }

    @Test
    public void should_not_be_allowed_to_change_given_phone_number_same_as_before() throws Exception {
        assertThat(customer.changePhoneNumberTo(initPhoneNumber)).isFalse();
        assertThat(customer.getContacts().getPhoneNumber()).isEqualTo(initPhoneNumber);
    }
}