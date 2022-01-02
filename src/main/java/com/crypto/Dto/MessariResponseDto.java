package com.crypto.Dto;

import lombok.Builder;

@Builder
@lombok.Data
public class MessariResponseDto {
  public Status status;
  public Data data;

}
