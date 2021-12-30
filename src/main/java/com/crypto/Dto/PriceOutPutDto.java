package com.crypto.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceOutPutDto {

  public Double cryptoPrice;

  public   String currencySymbol;


}
