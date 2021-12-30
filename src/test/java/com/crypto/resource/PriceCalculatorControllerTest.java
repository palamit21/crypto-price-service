package com.crypto.resource;

import com.crypto.Dto.PriceInputDto;
import com.crypto.Dto.PriceOutPutDto;
import com.crypto.service.Impl.PriceCalService;
import java.util.Collection;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PriceCalculatorControllerTest {


  @Autowired
  private PriceCalculatorController controller;

  @MockBean
  private PriceCalService priceCalService;
  @Mock
  HttpServletRequest request;


  @Test
  public void contextLoads() throws Exception {
    assertThat(controller).isNotNull();
  }

  @Test
  public void getCurrentCryptoPrice_Test() throws Exception {
    PriceOutPutDto packReportResponse = new PriceOutPutDto();
    MockHttpServletRequest request = new MockHttpServletRequest();
    when(priceCalService.getPrice(PriceInputDto.builder().build(), Locale.US))
        .thenReturn(packReportResponse);
    ModelAndView response = controller
        .getCurrentCryptoPrice(PriceInputDto.builder().CryptoName("XRP").build(), request);
    assertEquals(response.getViewName(), "outputdisplay");

  }

  @Test
  public void getIndex_Test() throws Exception {
    ModelAndView response = controller.index();
    Collection<Object> s = response.getModel().values();
    assertEquals(response.getViewName(), "inputform");

  }

}
