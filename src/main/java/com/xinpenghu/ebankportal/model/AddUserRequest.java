package com.xinpenghu.ebankportal.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddUserRequest {
    @JsonProperty
    public String email;
    @JsonProperty
    public String password;
}
