package com.xdl.shirodemo.mapper;


import com.xdl.shirodemo.entity.Asset;
import com.xdl.shirodemo.entity.AssetNoId;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetMapper {
    List<Asset> getAssetList();
    int insertAsset(AssetNoId assetNoId);
    void deleteAssetList(List<Integer> ids);
    List<Asset> getAssetsByName(String name);
    int insertAssetBatch(List<AssetNoId> assetNoIds);
}
