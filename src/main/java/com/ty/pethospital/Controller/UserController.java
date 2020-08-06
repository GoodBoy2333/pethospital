package com.ty.pethospital.Controller;

import cn.hutool.core.lang.Snowflake;
import com.github.pagehelper.PageInfo;
import com.ty.pethospital.ApiCommon.ApiResponse;
import com.ty.pethospital.Common.Status;
import com.ty.pethospital.Entity.TyUser;
import com.ty.pethospital.Service.TyUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "用户接口")
public class UserController {

    @Autowired
    TyUserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    Snowflake snowflake;

    @ApiOperation("用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名",required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true)
    })
    @PostMapping("/login")
    public ApiResponse login(@RequestParam String userName, @RequestParam String password, HttpServletRequest request) {
        try {
            Map<String, Object> res = new HashMap<>();
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            HttpSession session = request.getSession();
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            if (authentication.getPrincipal() instanceof TyUser) {
                TyUser user = (TyUser) authentication.getPrincipal();
                user.setLastlogintime(new Date());
                userService.updateByUsername(user, userName);
                switch (user.getUserType()) {
                    case "admin":
                        res.put("path", "/admin/index");
                        break;
                    case "user":
                        res.put("path", "/user/index");
                        break;
                }
                res.put("user", user);
            }
            return ApiResponse.success(res);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
            return ApiResponse.status(Status.USERNAME_PASSWORD_ERROR);
        } catch (Exception e) {
            log.warn("登录异常:{}", e.getMessage());
            return ApiResponse.fail(e.getMessage());
        }
    }

    @ApiIgnore
    @GetMapping
    public ModelAndView toReg(ModelAndView mv) {
        mv.setViewName("User/userReg");
        return mv;
    }

    @ApiIgnore
    @GetMapping("/forget")
    public ModelAndView toForget(ModelAndView mv) {
        mv.setViewName("User/userForget");
        return mv;
    }

    @ApiIgnore
    @GetMapping("/index")
    public ModelAndView admin(ModelAndView mv) {
        mv.setViewName("User/index");
        return mv;
    }

    @ApiIgnore
    @GetMapping("/info")
    public ModelAndView info(ModelAndView mv) {
        mv.setViewName("User/sysInfo");
        return mv;
    }

    @ApiOperation(value = "添加或更新用户",notes = "传入id则代表更新操作，未传入id则代表新增操作")
    @ApiImplicitParam(name = "user", value = "用户详细实体", required = true, dataType = "TyUser")
    @PostMapping
    public ApiResponse<TyUser> addOrUpdate(TyUser user) {
        try {
            if (user == null) {
                return ApiResponse.fail(Status.PARAM_NOT_NULL);
            }
            if (StringUtils.isEmpty(user.getId())) {
                if (userService.findOneByUsername(user.getUsername()) != null) {
                    return ApiResponse.fail("用户已注册");
                }
                if (StringUtils.isEmpty(user.getPassword())) {
                    return ApiResponse.fail("密码不能为空");
                }
                if (StringUtils.isEmpty(user.getUserType())) {
                    return ApiResponse.fail("用户类型不能为空");
                }
                user.setId(String.valueOf(snowflake.nextId()));
                //将密码进行加密操作
                String encodePassword = passwordEncoder.encode(user.getPassword());
                user.setPassword(encodePassword);
                user.setStatus("1");
                user.setCreatetime(new Date());
                user.setUpdatetime(new Date());
                int insert = userService.insert(user);
                if (insert > 0) {
                    return ApiResponse.success("操作成功", user);
                }
            } else {
                String id = user.getId();
                if (userService.selectByPrimaryKey(id) == null) {
                    return ApiResponse.fail("用户不存在");
                }
                TyUser one = userService.findOneByUsername(user.getUsername());
                if (one != null) {
                    if (!one.getId().equals(user.getId())) {
                        return ApiResponse.fail("用户重复");
                    }
                }
                String encodePassword = passwordEncoder.encode(user.getPassword());
                user.setPassword(encodePassword);
                user.setUpdatetime(new Date());
                int i = userService.updateByPrimaryKeySelective(user);
                if (i > 0) {
                    return ApiResponse.success("操作成功", user);
                }
            }
        } catch (Exception e) {
            return ApiResponse.status(Status.ERROR);
        }
        return ApiResponse.status(Status.ERROR);
    }

    @ApiOperation("用户是否存在")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名",required = true),
    })
    @PostMapping("/exist")
    public ApiResponse<String> checkExist(@RequestParam String userName) {
        try {
            if (userService.findOneByUsername(userName) == null) {
                return ApiResponse.success("用户名未重复");
            } else {
                return ApiResponse.fail("用户名重复");
            }
        } catch (Exception e) {
            return ApiResponse.status(Status.ERROR);
        }
    }

    @ApiOperation("用户是否存在")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名",required = true),
            @ApiImplicitParam(name = "email", value = "邮箱",required = true),
            @ApiImplicitParam(name = "phone", value = "手机号",required = true),
    })
    @PostMapping("/existByParm")
    public ApiResponse<String> existByParm(@RequestParam String userName, @RequestParam String email, @RequestParam String phone) {
        try {
            TyUser oneUsernameAndEmail = userService.findOneUserByparm(userName, email, phone);
            if (oneUsernameAndEmail != null) {
                return ApiResponse.success("校验正确", oneUsernameAndEmail);
            } else {
                return ApiResponse.fail("校验失败");
            }
        } catch (Exception e) {
            return ApiResponse.status(Status.ERROR);
        }
    }

    @ApiIgnore
    @GetMapping("toList")
    public ModelAndView toList(ModelAndView mv) {
        mv.setViewName("Admin/User/list");
        return mv;
    }

    @ApiIgnore
    @GetMapping("toListForUser")
    public ModelAndView toListForUser(ModelAndView mv) {
        mv.setViewName("User/User/list");
        return mv;
    }

    @ApiOperation("列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户实体"),
            @ApiImplicitParam(name = "currentPage", value = "当前页",required = true),
            @ApiImplicitParam(name = "pageSize", value = "分页大小",required = true),
    })
    @PostMapping("/list")
    public ApiResponse<PageInfo> list(TyUser user, @RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "10") int pageSize) {
        try {
            PageInfo<TyUser> byAllwithPage = userService.findByAllwithPage(currentPage, pageSize, user);
            return ApiResponse.success(byAllwithPage);
        } catch (Exception e) {
            return ApiResponse.fail("查询失败:" + e.getMessage());
        }
    }

    @ApiOperation("删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id"),
    })
    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable String id) {
        try {
            if (userService.selectByPrimaryKey(id) != null) {
                if (userService.deleteByPrimaryKey(id) > 0) {
                    return ApiResponse.success(Status.SUCCESS);
                }
            } else {
                return ApiResponse.fail("用户不存在");
            }
        } catch (Exception e) {
            return ApiResponse.fail("操作失败：" + e.getMessage());
        }
        return ApiResponse.fail(Status.ERROR);
    }

}
