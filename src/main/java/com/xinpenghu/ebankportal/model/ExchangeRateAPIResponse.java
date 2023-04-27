package com.xinpenghu.ebankportal.model;

import com.xinpenghu.ebankportal.entity.ExchangeRate;
import lombok.Data;

@Data
public class ExchangeRateAPIResponse {
    public String result;
    public String documentation;
    public String terms_of_use;
    public String time_last_update_unix;
    public String time_last_update_utc;
    public String time_next_update_unix;
    public String time_next_update_utc;
    public String base_code;
    public ExchangeRate conversion_rates;
}
