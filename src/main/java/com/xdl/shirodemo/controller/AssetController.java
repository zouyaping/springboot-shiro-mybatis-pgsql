package com.xdl.shirodemo.controller;


import com.xdl.shirodemo.entity.Asset;
import com.xdl.shirodemo.entity.AssetNoId;
import com.xdl.shirodemo.entity.Result;
import com.xdl.shirodemo.service.AssetService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Controller
public class AssetController {

    @Autowired
    AssetService assetService;

    // 查询所有数据
    @RequiresPermissions(value = "query")
    @RequestMapping(value = "/api/asset/list", method = RequestMethod.GET )
    @ResponseBody
    public Result getAssetList(){
        List<Asset> assetList = assetService.getAssetList();
        if (assetList != null){
            return Result.successWithData(assetList);
        }else {
            return Result.error("加载数据失败");
        }
    }

    // 根据资产名字模糊查询数据
    @RequiresPermissions(value = "query")
    @RequestMapping(value = "/api/asset/getDataByName", method = RequestMethod.GET )
    @ResponseBody
    public Result getAssetsByName(@RequestParam("name") String name ,HttpServletRequest request){
        List<Asset> assetList = assetService.getAssetsByName(name);
        request.getParameter("name");
        if (assetList != null){
            return Result.successWithData(assetList);
        }else {
            return Result.error("根据资产名称查询数据失败");
        }
    }

    // 删除数据
    @RequiresPermissions(value = "insert")
    @RequestMapping(value = "/api/asset/delete", method = RequestMethod.POST )
    @ResponseBody
    public Result deleteAssetList(@RequestBody List<Integer> ids){
        try {
            assetService.deleteAssetList(ids);
            return Result.successWithMsg("删除数据成功！！！");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除数据失败！！！");
        }

    }


    // 批量插入数据
    @RequiresPermissions(value = "insert")
    @RequestMapping(value = "/api/asset/addBatch", method = RequestMethod.POST)
    @ResponseBody
    public Result insertData(@RequestBody List<AssetNoId> assetNoIds){
        int num = assetService.insertAssetBatch(assetNoIds);
        if (num > 0){
            return Result.successWithData(num);
        }else {
            return Result.error("批量导入数据失败！！！");
        }
    }


    // 新增数据
    @RequiresPermissions(value = "insert")
    @RequestMapping(value = "/api/asset/add", method = RequestMethod.POST)
    @ResponseBody
    public Result insertData(@RequestBody AssetNoId assetNoId, HttpServletResponse response){
        int result = assetService.insertAsset(assetNoId);
        if (result > 0){
            return Result.successWithData(result);

        }else {
            return Result.error("插入数据失败！！！");
        }
    }

}
