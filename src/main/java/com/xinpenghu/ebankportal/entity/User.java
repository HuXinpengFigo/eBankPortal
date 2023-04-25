package com.xinpenghu.ebankportal.entity;

import com.xinpenghu.ebankportal.model.AddUserRequest;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

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
