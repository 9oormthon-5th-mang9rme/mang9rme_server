package com.manggoormy.manggoormy.domain.stone.model;

import com.manggoormy.manggoormy.domain.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stone extends BaseEntity {
    private LocalDateTime dateTime;
    private String imageUrl;
    private String address;
    private String lat;
    private String lng;
    private String stoneType;
    private String stoneName;
    private String rarity;
    private Long attack;
    private Long defense;
    private Long magicDefence;
}