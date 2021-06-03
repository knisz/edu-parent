package com.zwh.staservice.controller;


import com.zwh.commonutils.R;
import com.zwh.staservice.service.StatisticsDailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-03
 */
@RestController
@RequestMapping("/staservice/sta")
@Api(tags="数据统计模块")
@CrossOrigin
public class StatisticsDailyController {


    @Autowired
    private StatisticsDailyService statisticsDailyService;

    //统计每天注册人数，生成统计数据
    @ApiOperation("统计每天注册人数，生成统计数据")
    @PostMapping("/registerCount/{day}")
    public R registerCount(@PathVariable String day){
        statisticsDailyService.registerCount(day);
        return R.ok();
    }
}

