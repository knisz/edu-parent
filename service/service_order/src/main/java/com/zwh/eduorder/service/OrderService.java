package com.zwh.eduorder.service;

import com.zwh.eduorder.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-06-02
 */
public interface OrderService extends IService<Order> {

    //生成订单方法
    String createOrders(String courseId, String memberIdByJwtToken);
}
