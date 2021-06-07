package com.zwh.staservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zwh.staservice.entity.StatisticsDaily;

import java.util.Map;

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

    //查询统计数据表格
    Map<String, Object> getShowData(String type, String begin, String end);
}
