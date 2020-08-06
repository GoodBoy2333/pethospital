package com.ty.pethospital.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
public class ErroePageController {
    @RequestMapping("/error/404")
    public ModelAndView NOT_FOUND(){
        ModelAndView mv =new ModelAndView("/error/404");
        return mv;
    }
    @RequestMapping("/error/403")
    public ModelAndView FORBIDDEN(){
        ModelAndView mv =new ModelAndView("/error/403");
        return mv;
    }
    @RequestMapping("/error/500")
    public ModelAndView INTERNAL_SERVER_ERROR(){
        ModelAndView mv =new ModelAndView("/error/500");
        return mv;
    }
}
