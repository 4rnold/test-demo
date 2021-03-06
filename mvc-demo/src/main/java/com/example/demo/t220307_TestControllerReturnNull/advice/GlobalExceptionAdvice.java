package com.example.demo.t220307_TestControllerReturnNull.advice;

import com.example.demo.t220307_TestControllerReturnNull.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = Exception.class)
    public CommonResponse<String> handlerCommerceException(HttpServletRequest request, Exception ex) {
        CommonResponse<String> resp = new CommonResponse<>(-1,"business error");
        resp.setData(ex.getMessage());
        log.error("commerce service error:[{}]",ex.getMessage(),ex);
        return resp;
    }
}
