package com.crypto.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import com.crypto.Dto.GeoLocationResponseDto;
import com.crypto.Dto.MessariResponseDto;
import com.crypto.Dto.PriceInputDto;
import com.crypto.Dto.PriceOutPutDto;
import com.crypto.client.GeoLocationClient;
import com.crypto.client.MessariClient;
import com.crypto.service.Impl.PriceCalService;
import com.crypto.service.Impl.PriceServiceImpl;
import java.util.Collections;
import java.util.Locale;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class PriceServiceImplTest {

  @Autowired
  private PriceServiceImpl priceServiceImpl;

  @Autowired
  private PriceCalService priceCalService;
  @Mock
  private GeoLocationClient geoClient;
  @Mock
  private MessariClient messariClient;

  @Test
  public void testWithLocal() {
    when(messariClient.getCryptoDetails("XRP"))
        .thenReturn(getMessaiClient());
    when(geoClient.getCryptoDetails(any(), any()))
        .thenReturn(getGeoClient());

    PriceOutPutDto response = priceServiceImpl
        .getPrice(PriceInputDto.builder().CryptoName("XRP").build(), Locale.GERMANY);
    Assertions.assertEquals(response.getCurrencySymbol(), "€");


  }

  @Test
  public void testWithOutLocal() {
    when(messariClient.getCryptoDetails("XRP"))
        .thenReturn(getMessaiClient());
    when(geoClient.getCryptoDetails(any(), any()))
        .thenReturn(getGeoClient());

    PriceOutPutDto response = priceServiceImpl
        .getPrice(PriceInputDto.builder().CryptoName("XRP").ipAddress("161.69.123.10").build(),
            Locale.US);
    Assertions.assertEquals(response.getCurrencySymbol(), "US$");

  }

  public static ResponseEntity getMessaiClient() {
    return ResponseEntity.ok(Collections.singletonList(MessariResponseDto.builder().build()));
  }

  public static ResponseEntity getGeoClient() {
    return ResponseEntity.ok(Collections.singletonList(GeoLocationResponseDto.builder().build()));
  }

}
