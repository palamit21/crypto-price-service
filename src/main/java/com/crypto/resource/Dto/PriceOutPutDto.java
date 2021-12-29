package com.crypto.resource.Dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PriceOutPutDto {

  public Double cryptoPrice;

  public   String currencySymbol;


}
