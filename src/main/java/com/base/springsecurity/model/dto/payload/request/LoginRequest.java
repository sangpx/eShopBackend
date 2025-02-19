package com.base.springsecurity.model.dto.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {
	@NotBlank
  private String username;
	@NotBlank
	private String password;
}
