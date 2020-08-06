package com.ty.pethospital.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping("/admin")
public class AdminController {

    @ApiIgnore
    @GetMapping("/index")
    public ModelAndView admin(ModelAndView mv) {
        mv.setViewName("Admin/index");
        return mv;
    }

    @ApiIgnore
    @GetMapping("/info")
    public ModelAndView info(ModelAndView mv) {
        mv.setViewName("Admin/sysInfo");
        return mv;
    }

}
