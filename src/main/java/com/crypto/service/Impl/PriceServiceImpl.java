package com.crypto.service.Impl;

import com.crypto.Dto.CurrencyConversionDto;
import com.crypto.Dto.GeoLocationResponseDto;
import com.crypto.Dto.MessariResponseDto;
import com.crypto.Dto.PriceInputDto;
import com.crypto.Dto.PriceOutPutDto;
import com.crypto.Dto.PriceOutPutDto.PriceOutPutDtoBuilder;
import com.crypto.client.CurrencyConversionClient;
import com.crypto.client.GeoLocationClient;
import com.crypto.client.MessariClient;
import java.text.DecimalFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

@Service
public class PriceServiceImpl implements PriceCalService {

  @Autowired
  private MessariClient messariClient;
  @Autowired
  private GeoLocationClient geoLocationClient;

  @Autowired
  private CurrencyConversionClient currencyClient;
  private static final DecimalFormat df = new DecimalFormat("0.00");


  @Override
  public PriceOutPutDto getPrice(PriceInputDto priceInputDto,
      Locale request) {
    Currency currencySymbol = null;
    ResponseEntity<GeoLocationResponseDto> ipResponse = null;
    CompletableFuture<CurrencyConversionDto> conversionResponse = null;
    PriceOutPutDtoBuilder priceOutPutDto=PriceOutPutDto.builder();
    try {
      conversionResponse = executeConversionAPi();
      ResponseEntity<MessariResponseDto> response = messariClient
          .getCryptoDetails(priceInputDto.getCryptoName());

      currencySymbol = getCurrencySymbol(priceInputDto, request);
      String conversionRates = null;
      conversionRates = getConversionRate(currencySymbol, conversionResponse);
      double priceFromApi = response.getBody().data.getMarket_data()
          .getPrice_usd();
      Double finalPrice = priceFromApi * Double.valueOf(conversionRates);
       priceOutPutDto
          .cryptoPrice(Double.valueOf(df.format(finalPrice)))
          .currencySymbol(currencySymbol.getSymbol()).currencyName(priceInputDto.getCryptoName()).build();
    } catch (ExecutionException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }


    return priceOutPutDto.build();

  }

  private String getConversionRate(Currency currencySymbol,
      CompletableFuture<CurrencyConversionDto> response12)
      throws ExecutionException, InterruptedException {
    return response12.get().conversion_rates
        .get(currencySymbol.getCurrencyCode());
  }

  private Currency getCurrencySymbol(PriceInputDto priceInputDto, Locale request) {
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
    return cur;
  }

  @Async("asyncExecutor")
  public CompletableFuture<CurrencyConversionDto> executeConversionAPi()
      throws InterruptedException {
    ResponseEntity<CurrencyConversionDto> conversionResponse = currencyClient
        .getCurrencyConversion();

    return CompletableFuture.completedFuture(conversionResponse.getBody());
  }
}
