package com.base.springsecurity.exception;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
	private String error;
	private String details;
	private LocalDateTime timestamp;
}
