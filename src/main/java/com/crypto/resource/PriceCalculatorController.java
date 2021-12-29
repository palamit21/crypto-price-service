package com.crypto.resource;

import com.crypto.client.MessariClient;
import com.crypto.resource.Dto.MessariResponseDto;
import com.crypto.resource.Dto.PriceInputDto;
import com.crypto.resource.Dto.PriceOutPutDto;
import java.util.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PriceCalculatorController {
  @Autowired
  private MessariClient messariClient;
  @RequestMapping("/")
  public ModelAndView index()
  {
    System.out.println("User from UI =");
    /*
     * Here you can write the code to save the user information in database
     */
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("index");
    //modelAndView.addObject("index1", "user");
    return modelAndView;
  }

  @PostMapping(value = "/saveUser")
  public ModelAndView save( @ModelAttribute PriceInputDto s)
  {
    ResponseEntity<MessariResponseDto> response = messariClient.getCryptoDetails(s.getCryptoName());
    System.out.println("User from UI ="+s.getCryptoName());
    Currency cur = Currency.getInstance("USD");
    // Get and print the symbol of the currency
    String symbol = cur.getSymbol();
  PriceOutPutDto priceOutPutDto=  PriceOutPutDto.builder().cryptoPrice(response.getBody().data.getMarket_data()
      .getPrice_usd())
        .currencySymbol(symbol).build();

    System.out.println("currecncy Symbol ="+priceOutPutDto);
    /*
     * Here you can write the code to save the user information in database
     */
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("index1");
    modelAndView.addObject("priceOutPutDto", priceOutPutDto);
    return modelAndView;
  }

}
