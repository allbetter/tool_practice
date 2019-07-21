package com.up.platform.controller;

import com.up.platform.entity.TableInfo;
import com.up.platform.service.TableInfoService;
import com.up.platform.vo.ResultVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/table")
public class TableInfoController {

    @Autowired
    private  TableInfoService tableInfoService;

    @ApiOperation(value="获取数据库的表信息", notes="根据传入数据库ID读取")
    @PostMapping(value = "/list", produces = {"application/json;charset=UTF-8"})
    @CrossOrigin(origins = "*", maxAge = 3600)
    public List<TableInfo> list(@RequestBody ResultVO resultVO) {
        List<TableInfo> tableInfoList = tableInfoService.getTableInfoBySchema("tool_platform");
        return tableInfoList;
    }
}
