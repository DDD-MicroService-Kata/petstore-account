package com.ThoughtWorks.DDD.account.interfaces;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("data")
public class ApiForRequest<T> {
    private String type;
    private T attributes;

    public T getAttributes() {
        return attributes;
    }

    public void setAttributes(T attributes) {
        this.attributes = attributes;
    }
}
