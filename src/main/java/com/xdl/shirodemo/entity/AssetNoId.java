package com.xdl.shirodemo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Getter
public class AssetNoId {
    private String assetNo;
    private String assetName;
    private String assetType;
    private String assetStatus;
    private String assetNature;
    private String assetClass;
    private String borrowPerson;
    private String borrowTime;
    private float assetValue;
    private String buyTime;
    private String storedSection;
}
