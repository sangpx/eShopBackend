package com.base.springsecurity.models.dto.catalog.category;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    private String name;

    private int level;
}
