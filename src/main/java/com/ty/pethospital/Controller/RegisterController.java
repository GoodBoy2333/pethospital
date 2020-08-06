package com.ty.pethospital.Controller;

import cn.hutool.core.lang.Snowflake;
import com.github.pagehelper.PageInfo;
import com.ty.pethospital.ApiCommon.ApiResponse;
import com.ty.pethospital.Common.Status;
import com.ty.pethospital.Entity.TyDiagnosis;
import com.ty.pethospital.Entity.TyRegister;
import com.ty.pethospital.Service.TyDiagnosisService;
import com.ty.pethospital.Service.TyRegisterService;
import com.ty.pethospital.Service.TySeqService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.Optional;

/**
 * @author fy
 * @Date 2019/11/20 21:43
 */
@Api(tags = "预约挂号接口")
@RestController
@RequestMapping("/reg")
public class RegisterController {

    @Autowired
    TyRegisterService registerService;

    @Autowired
    TySeqService seqService;

    @Autowired
    TyDiagnosisService diagnosisService;

    @Autowired
    Snowflake snowflake;

    @ApiIgnore
    @GetMapping("toList")
    public ModelAndView toList(ModelAndView mv) {
        mv.setViewName("Admin/Reg/list");
        return mv;
    }

    @ApiIgnore
    @GetMapping("toListForUser")
    public ModelAndView toListForUser(ModelAndView mv) {
        mv.setViewName("User/Reg/list");
        return mv;
    }

    @ApiOperation("列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "register", value = "预约挂号实体"),
            @ApiImplicitParam(name = "currentPage", value = "当前页",required = true),
            @ApiImplicitParam(name = "pageSize", value = "分页大小",required = true),
    })
    @PostMapping("/list")
    public ApiResponse<PageInfo> findByAllwithPage(TyRegister register, @RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "10") int pageSize) {
        try {
            PageInfo<TyRegister> byAllwithPage = registerService.findByAllwithPage(currentPage,pageSize,register);;
            return ApiResponse.success(byAllwithPage);
        } catch (Exception e) {
            return ApiResponse.fail("操作失败：" + e.getMessage());
        }
    }

    @ApiOperation("删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id"),
    })
    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable String id) {
        try {
            if (registerService.selectByPrimaryKey(id) != null) {
                if (registerService.deleteByPrimaryKey(id) > 0) {
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
    @ApiImplicitParam(name = "register", value = "详细实体", required = true, dataType = "TyRegister")
    @PostMapping
    public ApiResponse<TyRegister> addOrUpdate(TyRegister register) {
        try {
            if (Optional.ofNullable(register.getId()).isPresent()) {

                if (registerService.updateByPrimaryKeySelective(register) > 0) {

                    if("regsuccess".equals(register.getStatus())){
                        if(diagnosisService.findAllByRegisterid(register.getId()).isEmpty()){
                            TyDiagnosis diagnosis= TyDiagnosis
                                    .builder()
                                    .id(snowflake.nextIdStr())
                                    .registerid(register.getId())
                                    .status("diaing")
                                    .createtime(new Date())
                                    .build();
                            diagnosisService.insertSelective(diagnosis);
                        }
                    }
                    return ApiResponse.success("操作成功", register);
                }
            } else {
                register.setId(seqService.getNextIdStr("resister"));
                register.setCreatetime(new Date());
                if (registerService.insertSelective(register) > 0) {
                    return ApiResponse.success("操作成功", register);
                }
            }
        } catch (Exception e) {
            return ApiResponse.fail("操作失败：" + e.getMessage());
        }
        return ApiResponse.status(Status.ERROR);
    }

}
