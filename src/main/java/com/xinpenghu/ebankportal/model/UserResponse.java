package com.xinpenghu.ebankportal.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xinpenghu.ebankportal.entity.User;
import com.xinpenghu.ebankportal.interfaces.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

public class UserResponse {
    @JsonProperty
    @Getter
    @Setter
    @Id
    private String id;
    @JsonProperty
    @Getter
    @Setter
    private String email;
    @JsonProperty
    @Getter
    @Setter
    private String password;

    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }


}
