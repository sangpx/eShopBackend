package com.base.springsecurity.models.dto.catalog.rating;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RatingDTO {
    private Long productId;
    private double rating;
}
