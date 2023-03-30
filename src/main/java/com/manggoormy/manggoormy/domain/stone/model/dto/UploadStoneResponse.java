package com.manggoormy.manggoormy.domain.stone.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UploadStoneResponse {
    private Long stoneId;
    private String dateTime;
    private String stoneType;
    private String stoneName;
    private Long attack;
    private Long defense;
    private Long magicDefence;
    private String address;
}