package com.zwh.msmservice.controller;

import com.zwh.commonutils.JwtUtils;
import com.zwh.commonutils.R;
import com.zwh.msmservice.service.MsmService;
import com.zwh.msmservice.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //发送短信的方法
    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone) {
        //1 从redis获取验证码，如果获取到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            return R.ok();
        }
        //2 如果redis获取 不到，进行阿里云发送
        //生成随机值，传递阿里云进行发送
        code = RandomUtil.getFourBitRandom();
        System.out.println("\n\n\n--------------------------------------------\ncode: "+code);
        Map<String, Object> param = new HashMap<>(); //map是为了方便后面直接转换为json
        param.put("code", code);
        //调用service发送短信的方法
        boolean isSend = msmService.send(param, phone);
        if (isSend) {
            //发送成功，把发送成功验证码放到redis里面
            //设置有效时间
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return R.ok();
        } else {
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);  //等短信功能上线后注释此句 TODO
            return R.error().message("短信发送失败");
        }
    }


    @GetMapping("hello")
    public R hello() {
        JwtUtils.getJwtToken("张三","小三");
        return R.ok();
    }


}
