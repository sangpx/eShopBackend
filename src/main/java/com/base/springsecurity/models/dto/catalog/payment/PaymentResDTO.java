package com.base.springsecurity.models.dto.catalog.payment;

import lombok.*;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentResDTO implements Serializable {
    private String status;
    private String message;
    private String URL;
}
