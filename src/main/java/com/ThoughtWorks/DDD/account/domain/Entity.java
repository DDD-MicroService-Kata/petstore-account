package com.ThoughtWorks.DDD.account.domain;

public interface Entity<T> {
    T getId();
    boolean sameIdentityAs(T otherId);
}
