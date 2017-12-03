package com.ThoughtWorks.DDD.account.repositories;

import com.ThoughtWorks.DDD.account.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
        extends CrudRepository<User, Long> {
}
