package com.zwh.staservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zwh.staservice.entity.StatisticsDaily;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-06-03
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {
    //统计每天注册人数，生成统计数据
    void registerCount(String day);
}
