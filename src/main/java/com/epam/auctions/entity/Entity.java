package com.epam.auctions.entity;

import lombok.Builder;

import java.io.Serializable;
import java.util.Objects;

public abstract class Entity<T> implements Serializable {
    T id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity)) return false;
        Entity<?> entity = (Entity<?>) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
