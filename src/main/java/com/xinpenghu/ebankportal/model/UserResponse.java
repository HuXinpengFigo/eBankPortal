package com.xinpenghu.ebankportal.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xinpenghu.ebankportal.entity.User;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserResponse {
    @JsonProperty
    @Id
    private String id;
    @JsonProperty
    private String email;
    @JsonProperty
    private String password;

    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }


}
