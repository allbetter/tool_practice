package com.up.platform.controller;

import com.up.platform.dto.AddDTO;
import com.up.platform.dto.BusinessUnitDTO;
import com.up.platform.dto.ResultDTO;
import com.up.platform.entity.BusinessUnit;
import com.up.platform.enums.ResultEnum;
import com.up.platform.exception.ToolException;
import com.up.platform.service.BusinessUnitService;
import com.up.platform.utils.AddDTOUtil;
import com.up.platform.utils.ResultDTOUtil;
import com.up.platform.validation.BusinessUnitValidation;
import com.up.platform.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/bu")
public class BusinessUnitController {

    @Autowired
    private BusinessUnitService businessUnitService;

    @PostMapping(value = "/list", produces = {"application/json;charset=UTF-8"})
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResultDTO list(@RequestBody ResultVO resultVO) {
        List<BusinessUnit> businessUnitList = businessUnitService.findAllBU(resultVO.getPage());
        List<BusinessUnitDTO> businessUnitDTOList = new ArrayList<>();
        for (BusinessUnit businessUnit: businessUnitList) {
            BusinessUnitDTO businessUnitDTO = new BusinessUnitDTO();
            BeanUtils.copyProperties(businessUnit, businessUnitDTO);
            businessUnitDTOList.add(businessUnitDTO);
        }
        return ResultDTOUtil.success(businessUnitDTOList);
    }

    @PostMapping(path = "/add",
            consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResultDTO add(@RequestBody @Valid BusinessUnitValidation businessUnitValidation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("创建BU失败，参数不正确，businessUnit={}", businessUnitValidation);
            throw new ToolException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        BusinessUnit businessUnit = new BusinessUnit();
        BeanUtils.copyProperties(businessUnitValidation, businessUnit);
        Integer buId = businessUnitService.addBU(businessUnit);
        AddDTO addDTO = AddDTOUtil.setId(buId);
        return ResultDTOUtil.success(addDTO);
    }
}
