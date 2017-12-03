package com.ThoughtWorks.DDD.account.domain;

import java.io.Serializable;

public interface Entity<T> extends Serializable {
    T getId();

    boolean sameIdentityAs(T otherId);
}
