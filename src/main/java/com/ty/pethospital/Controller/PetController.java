package com.ty.pethospital.Controller;

import cn.hutool.core.lang.Snowflake;
import com.github.pagehelper.PageInfo;
import com.ty.pethospital.ApiCommon.ApiResponse;
import com.ty.pethospital.Common.Status;
import com.ty.pethospital.Entity.TyPet;
import com.ty.pethospital.Service.TyPetService;
import com.ty.pethospital.Utils.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author fy
 * @Date 2019/11/18 21:43
 */
@Api(tags = "宠物接口")
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    TyPetService petService;

    @Autowired
    Snowflake snowflake;

    @ApiIgnore
    @GetMapping("toList")
    public ModelAndView toList(ModelAndView mv) {
        mv.setViewName("Admin/Pet/list");
        return mv;
    }

    @ApiIgnore
    @GetMapping("toListForUser")
    public ModelAndView toListForUser(ModelAndView mv) {
        mv.setViewName("User/Pet/list");
        return mv;
    }

    @GetMapping("/img/{id}")
    public void photo(@PathVariable String id, HttpServletResponse response) {
        TyPet tyPet = petService.selectByPrimaryKey(id);
        if (tyPet == null) {
            return;
        }
        String photopath = tyPet.getImgpath();
        Optional.ofNullable(photopath).ifPresent(s -> {
            if (Files.exists(Paths.get(s)) && s.length() > 1) {
                response.setContentType("image/jpeg");
                try {
                    ServletOutputStream outputStream = response.getOutputStream();
                    outputStream.write(Files.readAllBytes(Paths.get(s)));
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @PostMapping("/uploadImg")
    public ApiResponse upload(MultipartRequest request, TyPet pet) {
        try {
            List<MultipartFile> files = request.getFiles("file");
            if (files == null || files.size() == 0) {
                return ApiResponse.fail("上传文件列表为空");
            }
            List<String> filePaths = uploadImg(files);
            /*List<String> successList = filePaths.stream()
                    .filter(s -> Optional.ofNullable(s).isPresent())
                    .filter(s -> petService.updateImgpathById(s, pet.getId()) > 0)
                    .collect(Collectors.toList());*/
            if (filePaths.isEmpty()) {
                return ApiResponse.fail("上传失败");
            }
            return ApiResponse.success(filePaths);
        } catch (Exception e) {
            return ApiResponse.fail("上传失败");
        }
    }

    public List<String> uploadImg(List<MultipartFile> files) {
        List<String> collect = files.stream()
                .filter(multipartFile -> multipartFile != null && !multipartFile.isEmpty())
                .map(multipartFile -> {
                    try {
                        Long id = snowflake.nextId();
                        Path path = FileUtils.uploadFile(multipartFile, id);
                        if (Files.exists(path)) {
                            return path.toString();
                        } else {
                            return null;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .filter(s -> Optional.ofNullable(s).isPresent())
                .collect(Collectors.toList());
        return collect;
    }

    @ApiOperation("列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pet", value = "宠物实体"),
            @ApiImplicitParam(name = "currentPage", value = "当前页",required = true),
            @ApiImplicitParam(name = "pageSize", value = "分页大小",required = true),
    })
    @PostMapping("/list")
    public ApiResponse<PageInfo> findByAllwithPage(TyPet pet, @RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "10") int pageSize) {
        try {
            PageInfo<TyPet> byAllwithPage = petService.findByAllwithPage(currentPage, pageSize, pet);
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
            if (petService.selectByPrimaryKey(id) != null) {
                if (petService.deleteByPrimaryKey(id) > 0) {
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
    @ApiImplicitParam(name = "pet", value = "详细实体", required = true, dataType = "TyPet")
    @PostMapping
    public ApiResponse<TyPet> addOrUpdate(TyPet pet) {
        try {
            if (Optional.ofNullable(pet.getId()).isPresent()) {
                if (petService.updateByPrimaryKeySelective(pet) > 0) {
                    return ApiResponse.success("操作成功", pet);
                }
            } else {
                pet.setId(String.valueOf(snowflake.nextId()));
                if (petService.insertSelective(pet) > 0) {
                    return ApiResponse.success("操作成功", pet);
                }
            }
        } catch (Exception e) {
            return ApiResponse.fail("操作失败：" + e.getMessage());
        }
        return ApiResponse.status(Status.ERROR);
    }

}
