package com.chinasofti.blog.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.ws.RequestWrapper;
import java.io.File;

@Controller
public class ViewController {
    @RequestMapping("/toView/{firstView}/{secondView}/{thirdView}")
public String toView(@PathVariable("firstView") String firstView,
                   @PathVariable("secondView")String secondView,
                   @PathVariable("thirdView")String thirdView){
        return firstView+ File.separator+secondView+File.separator+thirdView;

}
}
