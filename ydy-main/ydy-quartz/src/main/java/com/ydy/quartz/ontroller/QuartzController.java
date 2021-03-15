package com.ydy.quartz.ontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class QuartzController {

    @GetMapping
    public String index(ModelAndView model){
        List<Map<String,Object>> list = new ArrayList<>();
        return "index";
    }
}
