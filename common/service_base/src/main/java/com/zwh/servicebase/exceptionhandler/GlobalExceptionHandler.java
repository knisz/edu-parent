package com.zwh.servicebase.exceptionhandler;

import com.zwh.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //指定出现什么异常时执行这个方法
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("出现了全局异常...");
    }

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public R error(MyException e){
        e.printStackTrace();
        log.error(e.getMsg());
        //log.error(e.getMessage()); //要前一个，这个信息不全
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
