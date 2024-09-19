package com.base.springsecurity.model.dto.catalog.rating;

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
