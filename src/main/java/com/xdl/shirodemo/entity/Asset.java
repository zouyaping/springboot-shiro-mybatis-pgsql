package com.xdl.shirodemo.entity;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
public class Asset {
    private int assetId;
    private String assetNo;
    private String assetName;
    private String assetType;
    private String assetStatus;
    private String assetNature;
    private String borrowPerson;
    private String borrowTime;
    private float assetValue;
    private String buyTime;
    private String storedSection;
}
