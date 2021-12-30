package com.crypto.resource;

import com.crypto.Dto.MessariResponseDto;
import com.crypto.Dto.PriceInputDto;
import com.crypto.Dto.PriceOutPutDto;
import com.crypto.service.Impl.PriceCalService;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PriceCalculatorController {

  @Autowired
  private PriceCalService priceCalService;

  @RequestMapping("/price")
  public ModelAndView index() {
    System.out.println("User from UI =");
    ModelAndView modelAndView = new ModelAndView();
    List<String> list = new ArrayList<>();
    list.add("BTC");
    list.add("XRP");

    modelAndView.setViewName("inputform");
    modelAndView.addObject("list", list);
    return modelAndView;
  }

  @PostMapping(value = "/save")
  public ModelAndView getCurrentCryptoPrice(@ModelAttribute PriceInputDto priceInputDto, Model model,
      HttpServletRequest request) {
  ResponseEntity<MessariResponseDto> response=  priceCalService.getPrice(priceInputDto,request);;
    Currency cur = Currency.getInstance("INR");
    // Get and print the symbol of the currency
    String symbol = cur.getSymbol();
    PriceOutPutDto priceOutPutDto = PriceOutPutDto.builder()
        .cryptoPrice(response.getBody().data.getMarket_data()
            .getPrice_usd())
        .currencySymbol(symbol).build();

    System.out.println("currecncy Symbol =" + priceOutPutDto+""+request.getRemoteAddr());
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("outputdisplay");
    modelAndView.addObject("priceOutPutDto", priceOutPutDto);
    return modelAndView;
  }

}
