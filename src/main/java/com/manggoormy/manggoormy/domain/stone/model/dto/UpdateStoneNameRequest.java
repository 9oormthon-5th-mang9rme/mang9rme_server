package com.manggoormy.manggoormy.domain.stone.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStoneNameRequest {
    private Long stoneId;
    private String stoneName;
}
