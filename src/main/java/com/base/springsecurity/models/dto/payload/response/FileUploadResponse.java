package com.base.springsecurity.models.dto.payload.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadResponse {
    private String name;
    private String url;
    private String type;
    private long size;
}
