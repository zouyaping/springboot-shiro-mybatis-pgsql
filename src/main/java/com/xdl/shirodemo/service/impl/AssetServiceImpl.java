package com.xdl.shirodemo.service.impl;

import com.xdl.shirodemo.entity.Asset;
import com.xdl.shirodemo.entity.AssetNoId;
import com.xdl.shirodemo.mapper.AssetMapper;
import com.xdl.shirodemo.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    AssetMapper assetMapper;

    @Override
    @Transactional
    public int insertAssetBatch(List<AssetNoId> assetNoIds) {
        int num = assetMapper.insertAssetBatch(assetNoIds);
        return num;
    }

    @Override
    public List<Asset> getAssetList() {
        List<Asset> assetList = assetMapper.getAssetList();
        if (assetList != null){
            return assetList;
        }
        return null;
    }

    @Override
    public List<Asset> getAssetsByName(String name) {
        List<Asset> assets = assetMapper.getAssetsByName(name);
        if (assets != null){
            return assets;
        }
        return null;
    }

    @Override
    public void deleteAssetList(List<Integer> ids) {
        assetMapper.deleteAssetList(ids);
    }

    @Override
    public int insertAsset(AssetNoId assetNoId) {
        int result = assetMapper.insertAsset(assetNoId);
        if (result > 0){
            return result;
        }
        return 0;
    }
}
