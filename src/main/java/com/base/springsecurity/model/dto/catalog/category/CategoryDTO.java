package com.base.springsecurity.model.dto.catalog.category;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoryDTO {

    private String name;

    private int level;
}
