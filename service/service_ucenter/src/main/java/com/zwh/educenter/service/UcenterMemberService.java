package com.zwh.educenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zwh.educenter.entity.UcenterMember;
import com.zwh.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-05-30
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    //登录的方法
    String login(UcenterMember member);

    //注册的方法
    void register(RegisterVo registerVo);
}
