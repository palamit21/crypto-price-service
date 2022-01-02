package com.crypto.resource;

import com.crypto.Dto.PriceInputDto;
import com.crypto.Dto.PriceOutPutDto;
import com.crypto.service.Impl.PriceCalService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PriceCalculatorController {

  @Autowired
  private PriceCalService priceCalService;

  @Value("#{'${crypto-list}'.split(',')}")
  private List<String> cryptoList;
  /**
   * (This method is returning crypto list for landing page.
   * @param
   * @return inputfrom view
   */
  @RequestMapping("/")
  public ModelAndView index() {

    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("inputform");
    modelAndView.addObject("list", cryptoList);
    return modelAndView;
  }
  /**
   * (This method will take the priceinputDto and HttpServlet request.
   * @param (HttpServletRequest)  (request)
   * @param (PriceInputDto) (priceInputDto)
   * @return (outputdisplay) (view)
   * @return (PriceOutPutDto) (priceOutPutDto)
   */
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
