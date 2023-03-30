package com.manggoormy.manggoormy.domain.stone.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetStoneResponse {
    private Long stoneId;
    private String stoneType;
    private String stoneName;
    private String dateTime;
    private String address;
    private String lat;
    private String lng;
    private String imageUrl;
    private String rarity;
    private Long attack;
    private Long defense;
    private Long magicDefense;
}
