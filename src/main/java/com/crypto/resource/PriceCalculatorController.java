package com.crypto.resource;

import com.crypto.resource.Dto.PriceInputDto;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PriceCalculatorController {
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
    System.out.println("User from UI =");
    /*
     * Here you can write the code to save the user information in database
     */
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("index1");
    //modelAndView.addObject("index1", "user");
    return modelAndView;
  }

}
