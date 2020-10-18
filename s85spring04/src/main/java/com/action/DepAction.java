package com.action;


import com.pojo.Dep;
import com.service.DepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/dep.do")
public class DepAction {

    @Autowired
    private DepService depService;

    @RequestMapping(params = "p=findall")
    public String findAll(HttpServletRequest request){
        List<Dep> list=depService.findall();
        request.setAttribute("list",list);
        return "show.jsp";
    }


}
