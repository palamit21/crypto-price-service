package com.crypto.service.Impl;

import com.crypto.Dto.PriceInputDto;
import com.crypto.Dto.PriceOutPutDto;
import java.util.Locale;

public interface PriceCalService {
   PriceOutPutDto getPrice(PriceInputDto priceInputDto,
      Locale request);

}
