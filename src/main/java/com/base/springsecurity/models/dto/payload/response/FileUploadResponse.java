package com.base.springsecurity.models.dto.payload.response;


import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadResponse {
    private String name;
    private String url;
    private String type;
    private long size;
}
