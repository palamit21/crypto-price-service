package com.crypto.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PriceInputDto {
  private String CryptoName;
  private String ipAddress;

}
