package com.crypto.resource.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class Data {
  public String id;
  public String symbol;
  public String name;
  public String slug;
  public MarketData market_data;

  @Builder
  @lombok.Data
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @NoArgsConstructor
  @AllArgsConstructor
  public static class MarketData {
    public double price_usd;
    public int price_btc;
    public double volume_last_24_hours;
    public double real_volume_last_24_hours;
    public double volume_last_24_hours_overstatement_multiple;
    public double percent_change_usd_last_24_hours;
    public int percent_change_btc_last_24_hours;
    public OhlcvLast1Hour ohlcv_last_1_hour;

  }
  @Builder
  @lombok.Data
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @NoArgsConstructor
  @AllArgsConstructor
  public static class OhlcvLast1Hour {

    public double open;
    public double high;
    public double low;
    public double close;
    public double volume;


  }


}
