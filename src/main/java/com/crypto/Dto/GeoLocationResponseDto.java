package com.crypto.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class GeoLocationResponseDto {
  public String ip;
  public String type;
  public String continent_code;
  public String continent_name;
  public String country_code;
  public String country_name;
  public String region_code;
  public String region_name;
  public String city;
  public Object zip;
  public double latitude;
  public double longitude;
  public Location location;
  @Builder
  @lombok.Data
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Language{
    public String code;
    public String name;
  }
  @Builder
  @lombok.Data
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Location{
    public List<Language> languages;
  }

}
