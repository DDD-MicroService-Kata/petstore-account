package com.ThoughtWorks.DDD.account.domain;

public interface ValueObject<T> {
    boolean sameValueAs(T other);
}
