package com.manggoormy.manggoormy.domain.stone.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadStoneRequest {
    private String imgUrl;
    private LocalDateTime dateTime;
    private String address;
    private String lat;
    private String lng;
    private String stoneType;
}
