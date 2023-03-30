package com.manggoormy.manggoormy.domain.stone.model;

import com.manggoormy.manggoormy.domain.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Stone extends BaseEntity {
    private String imageUrl;
    private String path;
    private String address;
    private String lat;
    private String lng;
    private String stoneType;
    private String level;
    private String rarity;
    private Long attack;
    private Long defense;
    private Long magicDefence;
}