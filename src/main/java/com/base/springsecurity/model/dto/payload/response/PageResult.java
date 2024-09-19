package com.base.springsecurity.model.dto.payload.response;


import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T>{
    int recordCount;
    T dataResponse;
}
