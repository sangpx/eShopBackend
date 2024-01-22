package com.base.springsecurity.models.dto.payload.response;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentLinkResponse {
    private String payment_link_url;
    private String payment_link_id;
}
