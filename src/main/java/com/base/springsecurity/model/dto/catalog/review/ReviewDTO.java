package com.base.springsecurity.model.dto.catalog.review;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDTO {
    private Long productId;
    private String review;
}
