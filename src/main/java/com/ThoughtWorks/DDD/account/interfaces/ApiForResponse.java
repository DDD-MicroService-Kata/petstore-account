package com.ThoughtWorks.DDD.account.interfaces;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("data")
public class ApiForResponse<T> {
    private Long id;
    private T attributes;

    public ApiForResponse(Long id, T attributes) {
        this.id = id;
        this.attributes = attributes;
    }

    public Long getId() {
        return id;
    }

    public T getAttributes() {
        return attributes;
    }
}
