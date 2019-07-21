package com.up.platform.controller;

import com.up.platform.entity.ColumnInfo;
import com.up.platform.service.ColumnInfoService;
import com.up.platform.vo.ResultVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/column")
public class ColumnInfoController {

    @Autowired
    private ColumnInfoService columnInfoService;

    @ApiOperation(value="获取数据库表的字段信息", notes="根据传入数据库ID和表ID读取")
    @PostMapping(value = "/list", produces = {"application/json;charset=UTF-8"})
    @CrossOrigin(origins = "*", maxAge = 3600)
    public List<ColumnInfo> list(@RequestBody ResultVO resultVO) {
        List<ColumnInfo> columnInfoList = columnInfoService.getColumnInfoBySchemaAndTable("tool_platform","db_table");
        return columnInfoList;
    }
}
