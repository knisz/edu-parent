package com.zwh.eduservice.controller;

import com.zwh.commonutils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("eduservice/user")
@CrossOrigin
public class EduLoginController {

    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }
    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://pic4.zhimg.com/da8e974dc_xs.jpg?source=06d4cd63");
    }
}
