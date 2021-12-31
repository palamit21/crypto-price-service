package com.crypto.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyConversionDto {
  public String result;
  public String documentation;
  public String terms_of_use;
  public int time_last_update_unix;
  public String time_last_update_utc;
  public int time_next_update_unix;
  public String time_next_update_utc;
  public String base_code;
  public Map<String,String> conversion_rates;
}
