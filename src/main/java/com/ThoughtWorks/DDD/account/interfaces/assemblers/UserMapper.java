package com.ThoughtWorks.DDD.account.interfaces.assemblers;

import com.ThoughtWorks.DDD.account.domain.User;
import com.ThoughtWorks.DDD.account.interfaces.UserDTO;

public class UserMapper {
    public static UserDTO toUserDTO(User user) {
        return UserDTO.builder()
                .withFirstName(user.getFirstName())
                .withLastName(user.getLastName())
                .withPhoneNumber(user.getContacts().getPhoneNumber())
                .withEmailAddress(user.getContacts().getEmailAddress())
                .build();
    }
}
