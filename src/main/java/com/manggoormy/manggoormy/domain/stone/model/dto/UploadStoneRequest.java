package com.manggoormy.manggoormy.domain.stone.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UploadStoneRequest {
    private LocalDateTime dateTime;
    private String address;
    private String lat;
    private String lng;
    private String stoneType;
}
