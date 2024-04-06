package com.base.springsecurity.models.dto.payload.response;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {
  private String message;
  private boolean status;
}
