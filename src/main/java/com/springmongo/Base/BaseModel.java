package com.springmongo.Base;

/**
 * Created by Mohammad on 7/18/2015.
 */
public class BaseModel<T> {
    public T id;
    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
