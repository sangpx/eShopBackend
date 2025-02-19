package com.base.springsecurity.model.dto.payload.response;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentLinkResponse {
    private String payment_link_url;
    private String payment_link_id;
}
