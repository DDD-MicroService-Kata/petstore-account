package com.ThoughtWorks.DDD.account.interfaces;

public class UserDTO {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String emailAddress;
        private String phoneNumber;

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserDTO build() {
            UserDTO dto = new UserDTO();
            dto.firstName = this.firstName;
            dto.lastName = this.lastName;
            dto.emailAddress = this.emailAddress;
            dto.phoneNumber = this.phoneNumber;
            return dto;
        }
    }


}
