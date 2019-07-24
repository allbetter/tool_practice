package com.up.platform.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.up.platform.dto.BooleanDTO;
import com.up.platform.dto.IdDTO;
import com.up.platform.dto.ResultDTO;
import com.up.platform.entity.DBManage;
import com.up.platform.entity.TableInfo;
import com.up.platform.enums.ResultEnum;
import com.up.platform.exception.ToolException;
import com.up.platform.manager.DataSourceContextHolder;
import com.up.platform.manager.DynamicDataSource;
import com.up.platform.service.DBManageService;
import com.up.platform.service.TableInfoService;
import com.up.platform.utils.BooleanDTOUtil;
import com.up.platform.utils.IdDTOUtil;
import com.up.platform.utils.ResultDTOUtil;
import com.up.platform.validation.DBManageValidation;
import com.up.platform.validation.IdValidation;
import com.up.platform.vo.IdVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/api/db")
public class DBManageController {
    
    @Autowired
    private DBManageService dbManageService;

    @Autowired
    private TableInfoService tableInfoService;

    @ApiOperation(value="获取数据库详情")
    @PostMapping(value = "/detail", produces = {"application/json;charset=UTF-8"})
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResultDTO list(@RequestBody IdVO idVO) {
        DBManage dbManage = dbManageService.findDBManageById(idVO.getId());
        return ResultDTOUtil.success(dbManage);
    }

    @ApiOperation(value="添加数据库")
    @PostMapping(path = "/add",
            consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResultDTO add(@RequestBody @Valid DBManageValidation dbManageValidation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("创建数据库失败，参数不正确，dbManage={}", dbManageValidation);
            throw new ToolException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        DBManage dbManage = new DBManage();
        BeanUtils.copyProperties(dbManageValidation, dbManage);
        dbManageService.addDBManage(dbManage);
        IdDTO idDTO = IdDTOUtil.setId(dbManage.getId());
        return ResultDTOUtil.success(idDTO);
    }

    @ApiOperation(value="编辑数据库")
    @PostMapping(path = "/edit",
            consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResultDTO edit(@RequestBody @Valid DBManageValidation dbManageValidation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("编辑数据库失败，参数不正确，dbManage={}", dbManageValidation);
            throw new ToolException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        DBManage dbManage = new DBManage();
        BeanUtils.copyProperties(dbManageValidation, dbManage);
        dbManageService.editDBManage(dbManage);
        IdDTO idDTO = IdDTOUtil.setId(dbManage.getId());
        return ResultDTOUtil.success(idDTO);
    }

    @ApiOperation(value="删除数据库")
    @PostMapping(path = "/delete",
            consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResultDTO delete(@RequestBody @Valid IdValidation idValidation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("删除数据库失败，参数不正确，dbManage={}", idValidation);
            throw new ToolException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        Integer result = dbManageService.deleteDBManage(idValidation.getId());
        Boolean status = (result == 1 ? true : false);
        BooleanDTO booleanDTO = BooleanDTOUtil.setStatus(status);
        return ResultDTOUtil.success(booleanDTO);
    }

    @ApiOperation(value="测试动态库")
    @PostMapping(path = "/test",
            consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public List<TableInfo> test(@RequestBody IdVO idVO) {
        /**
         * 获取maste数据库信息
         */
        DataSourceContextHolder.setDBType("master");
        DBManage dbManage = dbManageService.findDBManageById(idVO.getId());

//        /**
//         * 根据slave数据源获取目标数据库信息
//         */
//        DataSourceContextHolder.setDBType("default");
//        DBManage dbManage2 = dbManageService.findDBManageById(idVO.getId());
//        log.info("dbName is -> " + dbManage.getDbName() + "; dbIP is  -> " + dbManage.getDbHost() + "; dbUser is  -> " + dbManage.getDbUsername() + "; dbPasswd is -> " + dbManage.getDbPassword() + "; dbSchema is -> " + dbManage.getDbSchemaName());
//
//        DruidDataSource dynamicDataSource = new DruidDataSource();
//        dynamicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dynamicDataSource.setUrl("jdbc:mysql://" + dbManage.getDbHost() + ":" + dbManage.getDbPort() + "/" + dbManage.getDbSchemaName() + "?characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull");
//        dynamicDataSource.setUsername(dbManage.getDbUsername());
//        dynamicDataSource.setPassword(dbManage.getDbPassword());
//
//        /**
//         * 创建动态数据源
//         */
//        Map<Object, Object> dataSourceMap = DynamicDataSource.getInstance().getDataSourceMap();
//        dataSourceMap.put("dynamic-slave", dynamicDataSource);
//        DynamicDataSource.getInstance().setTargetDataSources(dataSourceMap);
//        /**
//         * 切换为动态数据源实例
//         */
//        DataSourceContextHolder.setDBType("dynamic-slave");
        List<TableInfo> tableInfoList = tableInfoService.getTableInfoBySchema(dbManage.getDbSchemaName());
        return tableInfoList;

    }
}
