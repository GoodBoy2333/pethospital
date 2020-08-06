package com.ty.pethospital.Controller;

import cn.hutool.core.lang.Snowflake;
import com.github.pagehelper.PageInfo;
import com.ty.pethospital.ApiCommon.ApiResponse;
import com.ty.pethospital.Common.Status;
import com.ty.pethospital.Entity.TyBulletin;
import com.ty.pethospital.Entity.TyMessage;
import com.ty.pethospital.Service.TyMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Optional;

/**
 * @author fy
 * @Date 2019/11/20 21:43
 */
@Api(tags = "留言接口")
@RestController
@RequestMapping("/mes")
public class MesController {

    @Autowired
    TyMessageService messageService;

    @Autowired
    Snowflake snowflake;

    @ApiIgnore
    @GetMapping("toList")
    public ModelAndView toList(ModelAndView mv) {
        mv.setViewName("Admin/Mes/list");
        return mv;
    }

    @ApiIgnore
    @GetMapping("toListForUser")
    public ModelAndView toListForUser(ModelAndView mv) {
        mv.setViewName("User/Mes/list");
        return mv;
    }

    @ApiOperation("列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "message", value = "留言实体"),
            @ApiImplicitParam(name = "currentPage", value = "当前页",required = true),
            @ApiImplicitParam(name = "pageSize", value = "分页大小",required = true),
    })
    @PostMapping("/list")
    public ApiResponse<PageInfo> findByAllwithPage(TyMessage message, @RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "10") int pageSize) {
        try {
            PageInfo<TyMessage> byAllwithPage = messageService.findByAllwithPage(currentPage,pageSize,message);;
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
            if (messageService.selectByPrimaryKey(id) != null) {
                if (messageService.deleteByPrimaryKey(id) > 0) {
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
    @ApiImplicitParam(name = "message", value = "详细实体", required = true, dataType = "TyMessage")
    @PostMapping
    public ApiResponse<TyBulletin> addOrUpdate(TyMessage message) {
        try {
            if (Optional.ofNullable(message.getId()).isPresent()) {
                if (messageService.updateByPrimaryKeySelective(message) > 0) {
                    return ApiResponse.success("操作成功", message);
                }
            } else {
                message.setId(String.valueOf(snowflake.nextId()));
                if (messageService.insertSelective(message) > 0) {
                    return ApiResponse.success("操作成功", message);
                }
            }
        } catch (Exception e) {
            return ApiResponse.fail("操作失败：" + e.getMessage());
        }
        return ApiResponse.status(Status.ERROR);
    }

}
