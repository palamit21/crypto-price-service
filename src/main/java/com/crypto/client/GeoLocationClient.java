package com.crypto.client;

import com.crypto.Dto.GeoLocationResponseDto;
import com.crypto.Dto.MessariResponseDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "payload-api",
    contextId = "ipaddress",
    url = "http://api.ipstack.com/")
@Component
public interface GeoLocationClient {
  @RequestMapping(value = "/{ipAddress}",method = RequestMethod.GET)
  @Headers("Content-Type: application/json")
  ResponseEntity<GeoLocationResponseDto> getCryptoDetails(@PathVariable("ipAddress") String cryptoName,@RequestParam("access_key") String apiKey);


}
