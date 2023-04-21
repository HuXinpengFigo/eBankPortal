package com.xinpenghu.ebankportal.entity;

import com.xinpenghu.ebankportal.model.AddUserRequest;
import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class User {
    @Getter
    @Setter
    @Id
    private String id;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;


    public User(AddUserRequest request) {
        this.email = request.email;
        this.password = request.password;
    }

    @PersistenceCreator
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
