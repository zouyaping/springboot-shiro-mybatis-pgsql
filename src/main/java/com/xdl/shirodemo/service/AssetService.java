package com.xdl.shirodemo.service;

import com.xdl.shirodemo.entity.Asset;
import com.xdl.shirodemo.entity.AssetNoId;

import java.util.List;

public interface AssetService {
    List<Asset> getAssetList();
    int insertAsset(AssetNoId assetNoId);
    void deleteAssetList(List<Integer> ids);
    List<Asset> getAssetsByName(String name);
    int insertAssetBatch(List<AssetNoId> assetNoIds);
}
