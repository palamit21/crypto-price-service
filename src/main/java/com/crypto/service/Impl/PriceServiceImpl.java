package com.crypto.service.Impl;

import com.crypto.Dto.GeoLocationResponseDto;
import com.crypto.Dto.MessariResponseDto;
import com.crypto.Dto.PriceInputDto;
import com.crypto.Dto.PriceOutPutDto;
import com.crypto.client.GeoLocationClient;
import com.crypto.client.MessariClient;
import java.util.Currency;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

@Service
public class PriceServiceImpl implements PriceCalService {

  @Autowired
  private MessariClient messariClient;
  @Autowired
  private GeoLocationClient geoLocationClient;

  @Override
  public PriceOutPutDto getPrice(PriceInputDto priceInputDto,
      Locale request) {
    String currencySymbol = null;
    ResponseEntity<GeoLocationResponseDto> ipResponse = null;

    ResponseEntity<MessariResponseDto> response = messariClient
        .getCryptoDetails(priceInputDto.getCryptoName());
    currencySymbol = getCurrencySymbol(priceInputDto, request);
    PriceOutPutDto priceOutPutDto = PriceOutPutDto.builder()
        .cryptoPrice(response.getBody().data.getMarket_data()
            .getPrice_usd())
        .currencySymbol(currencySymbol).build();
    return priceOutPutDto;

  }

  private String getCurrencySymbol(PriceInputDto priceInputDto, Locale request) {
    Currency cur;
    ResponseEntity<GeoLocationResponseDto> ipResponse;
    Locale locale;
    if (StringUtils.isEmpty(priceInputDto.getIpAddress())) {
      cur = Currency.getInstance(request);

    } else {
      ipResponse = geoLocationClient
          .getCryptoDetails(priceInputDto.getIpAddress(), "6dc13f5a2d39241a092421cc9fe0a1d0");
      locale = new Locale(ipResponse.getBody().getLocation().getLanguages().get(0).getCode(),
          ipResponse.getBody().getCountry_code());
      cur = Currency.getInstance(locale);


    }
    return cur.getSymbol();
  }
}
