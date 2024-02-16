package com.base.springsecurity.models.dto.catalog.review;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Long productId;
    private String review;
}
