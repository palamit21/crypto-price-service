package com.crypto.client;

import com.crypto.Dto.CurrencyConversionDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "currencyconversionapi",
    contextId = "currencyconversion",
    url = "https://v6.exchangerate-api.com/v6/341c83182308440b4cebd957/latest/USD")
@Component
public interface CurrencyConversionClient {
  @RequestMapping(method = RequestMethod.GET)
  @Headers("Content-Type: application/json")
  ResponseEntity<CurrencyConversionDto> getCurrencyConversion();


}
