package com.springmongo.Model;

import com.springmongo.Base.BaseModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;

/**
 * Created by Mohammad on 7/17/2015.
 */
@Document(collection = "user")
public class User extends BaseModel<Long>{

    private String name;
    private String family;
    private String email;

    @Id
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
