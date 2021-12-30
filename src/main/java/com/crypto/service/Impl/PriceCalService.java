package com.crypto.service.Impl;

import com.crypto.Dto.MessariResponseDto;
import com.crypto.Dto.PriceInputDto;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface PriceCalService {
  abstract ResponseEntity<MessariResponseDto> getPrice(PriceInputDto priceInputDto,
      HttpServletRequest request);

}
