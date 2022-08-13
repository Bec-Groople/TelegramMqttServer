package com.bec.mqttserver.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.bec.mqttserver.response.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {


    ErrorResponse illegalArgumentException = new ErrorResponse(new IllegalArgumentException("请求参数错误，请稍后重试!"));
    ErrorResponse NullPointerException = new ErrorResponse(new NullPointerException("请求发生了空指针异常，请稍后再试"));


    @ExceptionHandler(value = Exception.class)
    public ModelAndView exceptionHandler(Exception e) {
        System.out.println("发生了异常:" + e.getMessage());
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", e.getMessage());
        mv.addObject("type", NullPointerException.getErrorTypeName());
        mv.setViewName("error");
        return mv;
    }


}