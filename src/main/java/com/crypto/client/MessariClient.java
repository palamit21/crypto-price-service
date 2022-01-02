package com.crypto.client;

import com.crypto.Dto.MessariResponseDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "payload-api",
    contextId = "payload",
    url = "https://data.messari.io/api/v1/assets")
@Component
public interface MessariClient {
  @RequestMapping(value = "/{cryptoName}/metrics",method = RequestMethod.GET)
  @Headers("Content-Type: application/json")
  ResponseEntity<MessariResponseDto> getCryptoDetails(@PathVariable("cryptoName") String cryptoName);

}
