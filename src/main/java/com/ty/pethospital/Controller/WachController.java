package com.ty.pethospital.Controller;

import cn.hutool.core.lang.Snowflake;
import com.github.pagehelper.PageInfo;
import com.ty.pethospital.ApiCommon.ApiResponse;
import com.ty.pethospital.Common.Status;
import com.ty.pethospital.Entity.TyWatch;
import com.ty.pethospital.Service.TyWatchService;
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
 * @Date 2019/11/21 22:15
 */
@Api(tags = "领养寄养接口")
@RestController
@RequestMapping("/wach")
public class WachController {

    @Autowired
    TyWatchService watchService;

    @Autowired
    Snowflake snowflake;

    @ApiIgnore
    @GetMapping("toList")
    public ModelAndView toList(ModelAndView mv) {
        mv.setViewName("Admin/Wach/list");
        return mv;
    }

    @ApiIgnore
    @GetMapping("toListForUser")
    public ModelAndView toListForUser(ModelAndView mv) {
        mv.setViewName("User/Wach/list");
        return mv;
    }

    @ApiOperation("列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "watch", value = "领养寄养实体"),
            @ApiImplicitParam(name = "currentPage", value = "当前页",required = true),
            @ApiImplicitParam(name = "pageSize", value = "分页大小",required = true),
    })
    @PostMapping("/list")
    public ApiResponse<PageInfo> findByAllwithPage(TyWatch watch, @RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "10") int pageSize) {
        try {
            PageInfo<TyWatch> byAllwithPage = watchService.findByAllwithPage(currentPage,pageSize,watch);;
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
            if (watchService.selectByPrimaryKey(id) != null) {
                if (watchService.deleteByPrimaryKey(id) > 0) {
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
    @ApiImplicitParam(name = "watch", value = "详细实体", required = true, dataType = "TyWatch")
    @PostMapping
    public ApiResponse<TyWatch> addOrUpdate(TyWatch watch) {
        try {
            if (Optional.ofNullable(watch.getId()).isPresent()) {
                if (watchService.updateByPrimaryKeySelective(watch) > 0) {
                    return ApiResponse.success("操作成功", watch);
                }
            } else {
                watch.setId(String.valueOf(snowflake.nextId()));
                if (watchService.insertSelective(watch) > 0) {
                    return ApiResponse.success("操作成功", watch);
                }
            }
        } catch (Exception e) {
            return ApiResponse.fail("操作失败：" + e.getMessage());
        }
        return ApiResponse.status(Status.ERROR);
    }
}
