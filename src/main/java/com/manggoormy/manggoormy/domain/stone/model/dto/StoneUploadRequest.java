package com.manggoormy.manggoormy.domain.stone.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoneUploadRequest {
    private String address;
    private String lat;
    private String lng;
    private String stoneType;
}
