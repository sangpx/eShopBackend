package com.base.springsecurity.model.dto.payload.response;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageResponse {
  private String message;
  private boolean status;
}
