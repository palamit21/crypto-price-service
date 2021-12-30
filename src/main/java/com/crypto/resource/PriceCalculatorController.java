package com.crypto.resource;

import com.crypto.Dto.PriceInputDto;
import com.crypto.Dto.PriceOutPutDto;
import com.crypto.service.Impl.PriceCalService;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PriceCalculatorController {

  @Autowired
  private PriceCalService priceCalService;

  @RequestMapping("/")
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

  @PostMapping(value = "/getPrice")
  public ModelAndView getCurrentCryptoPrice(@ModelAttribute PriceInputDto priceInputDto,
      HttpServletRequest request) {
    PriceOutPutDto priceOutPutDto = priceCalService.getPrice(priceInputDto, request.getLocale());
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("outputdisplay");
    modelAndView.addObject("priceOutPutDto", priceOutPutDto);
    return modelAndView;
  }

}
