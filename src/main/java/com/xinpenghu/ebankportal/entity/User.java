package com.xinpenghu.ebankportal.entity;

import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.data.annotation.Id;
//import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
    private String email;
    private String password;

    @Tolerate
    public User() {
    }
//    @PersistenceCreator
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
