package com.manggoormy.manggoormy.domain.stone.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetStoneResponse {
    private Long stoneId;
    private String address;
    private String imageUrl;
    private Long attack;
    private Long defense;
    private Long magicDefence;
}
