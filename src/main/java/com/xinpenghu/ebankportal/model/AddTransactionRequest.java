package com.xinpenghu.ebankportal.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddTransactionRequest {
    @JsonProperty
    public Float amount;
    @JsonProperty
    public String type;
    @JsonProperty
    public String email;
    @JsonProperty
    public String currency;
    @JsonProperty
    public String description;
}
