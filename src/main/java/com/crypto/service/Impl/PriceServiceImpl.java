package com.crypto.service.Impl;

import com.crypto.Dto.GeoLocationResponseDto;
import com.crypto.Dto.MessariResponseDto;
import com.crypto.Dto.PriceInputDto;
import com.crypto.client.GeoLocationClient;
import com.crypto.client.MessariClient;
import java.util.Currency;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImpl implements PriceCalService{
  @Autowired
  private MessariClient messariClient;
  @Autowired
 private GeoLocationClient geoLocationClient;

  @Override
  public ResponseEntity<MessariResponseDto> getPrice(PriceInputDto priceInputDto,
      HttpServletRequest request) {

    ResponseEntity<MessariResponseDto> response = messariClient
        .getCryptoDetails(priceInputDto.getCryptoName());
    System.out.println("User from UI =" + priceInputDto.getCryptoName());
    ResponseEntity<GeoLocationResponseDto> ipResponse=  geoLocationClient.getCryptoDetails("2401:4900:1f38:7592:91b6:ca6f:a301:3aeb","6dc13f5a2d39241a092421cc9fe0a1d0");
    Locale locale = new Locale(ipResponse.getBody().getLocation().getLanguages().get(0).getCode(),ipResponse.getBody().getCountry_code());

    Currency cur = Currency.getInstance(locale);
    // Get and print the symbol of the currency
    String symbol = cur.getSymbol();
    System.out.println(ipResponse);
    return response;

  }

  private static void displayCurrency(String languageCode,
      String countryCode) {

    Locale locale = new Locale(languageCode, countryCode);
    Currency currency = Currency.getInstance(locale);
    String code = currency.getCurrencyCode();
    String symbol = currency.getSymbol();

    System.out.printf("Loading currency info for CountryCode=%s,LanguageCode=%s",
        countryCode, languageCode);
    System.out.println();
    System.out.printf("Currency Code=%s, Symbol=%s",code, symbol );

    System.out.println();
    System.out.println();
  }
}
