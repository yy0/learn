package com.jriver.web;

import com.jriver.model.ServiceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@ControllerAdvice(basePackages = "com.jriver.web")
public class ControllerExceptionAdvice extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionAdvice.class);

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity<ServiceResult> handleControllerException(HttpServletRequest request, Throwable ex) {
        LOGGER.error("服务异常：", ex);
        HttpStatus status = getStatus(request);
        ServiceResult result = new ServiceResult(String.valueOf(status.value()), ex.getMessage(), null);
        return new ResponseEntity(result, null, status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}