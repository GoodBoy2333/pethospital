package com.ty.pethospital.Controller;

import cn.hutool.core.lang.Snowflake;
import com.github.pagehelper.PageInfo;
import com.ty.pethospital.ApiCommon.ApiResponse;
import com.ty.pethospital.Common.Status;
import com.ty.pethospital.Entity.TyDiagnosis;
import com.ty.pethospital.Entity.TyRegister;
import com.ty.pethospital.Service.TyDiagnosisService;
import com.ty.pethospital.Service.TyRegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Optional;

/**
 * @author fy
 * @Date 2019/11/21 20:30
 */
@Api(tags = "就诊接口")
@RestController
@RequestMapping("/dia")
public class DiagnosisController {

    @Autowired
    TyDiagnosisService diagnosisService;

    @Autowired
    TyRegisterService registerService;

    @Autowired
    Snowflake snowflake;

    @ApiIgnore
    @GetMapping("toList")
    public ModelAndView toList(ModelAndView mv) {
        mv.setViewName("Admin/Dia/list");
        return mv;
    }

    @ApiIgnore
    @GetMapping("toListForUser")
    public ModelAndView toListForUser(ModelAndView mv) {
        mv.setViewName("User/Dia/list");
        return mv;
    }

    @ApiOperation("列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "diagnosis", value = "就诊实体"),
            @ApiImplicitParam(name = "currentPage", value = "当前页",required = true),
            @ApiImplicitParam(name = "pageSize", value = "分页大小",required = true),
    })
    @PostMapping("/list")
    public ApiResponse<PageInfo> findByAllwithPage(TyDiagnosis diagnosis, @RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "10") int pageSize) {
        try {
            PageInfo<TyDiagnosis> byAllwithPage = diagnosisService.findByAllwithPage(currentPage,pageSize,diagnosis);;
            return ApiResponse.success(byAllwithPage);
        } catch (Exception e) {
            return ApiResponse.fail("操作失败：" + e.getMessage());
        }
    }

    @ApiOperation("删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "就诊id"),
    })
    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable String id) {
        try {
            if (diagnosisService.selectByPrimaryKey(id) != null) {
                if (diagnosisService.deleteByPrimaryKey(id) > 0) {
                    return ApiResponse.success("操作成功");
                }
            } else {
                return ApiResponse.fail("宠物不存在");
            }
        } catch (Exception e) {
            return ApiResponse.fail("操作失败：" + e.getMessage());
        }
        return ApiResponse.status(Status.ERROR);
    }

    @ApiOperation(value = "添加或更新",notes = "传入id则代表更新操作，未传入id则代表新增操作")
    @ApiImplicitParam(name = "diagnosis", value = "就诊详细实体", required = true, dataType = "TyDiagnosis")
    @PostMapping
    public ApiResponse<TyDiagnosis> addOrUpdate(TyDiagnosis diagnosis) {
        try {
            if (Optional.ofNullable(diagnosis.getId()).isPresent()) {
                if (diagnosisService.updateByPrimaryKeySelective(diagnosis) > 0) {
                    return ApiResponse.success("操作成功", diagnosis);
                }
            } else {
                if(StringUtils.isEmpty(diagnosis.getRegisterid())){
                    return ApiResponse.fail("挂号编号不能为空");
                }
                TyRegister tyRegister = registerService.selectByPrimaryKey(diagnosis.getRegisterid());
                if(!"regsuccess".equals(tyRegister.getStatus())){
                    return ApiResponse.fail("未预约成功");
                }
                if(!diagnosisService.findAllByRegisterid(diagnosis.getRegisterid()).isEmpty()){
                    return ApiResponse.fail("该挂号编号已添加就诊信息");
                }
                diagnosis.setId(String.valueOf(snowflake.nextId()));
                if (diagnosisService.insertSelective(diagnosis) > 0) {
                    return ApiResponse.success("操作成功", diagnosis);
                }
            }
        } catch (Exception e) {
            return ApiResponse.fail("操作失败：" + e.getMessage());
        }
        return ApiResponse.status(Status.ERROR);
    }

}
