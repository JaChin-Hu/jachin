package com.jachin.blog.handler;

import com.jachin.common.constant.HttpStatus;
import com.jachin.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.stream.Collectors;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/07/02 14:54
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public Result handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        log.error("请求地址'{}', 权限校验失败'{}'", request.getRequestURI(), e.getMessage());
        return Result.error(HttpStatus.FORBIDDEN, "没有权限，请联系管理员授权");
    }

    @ExceptionHandler({BindException.class, ValidationException.class, MethodArgumentNotValidException.class})
    public Result handleValidatedException(Exception e) {
        log.error(e.getMessage());
        if (e instanceof MethodArgumentNotValidException ex) {
            return Result.error(ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining("; ")));
        } else if (e instanceof ConstraintViolationException ex) {
            return Result.error(ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("; ")));
        } else if (e instanceof BindException ex) {
            return Result.error(ex.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining("; ")));
        }
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        log.error("请求地址'{}',不支持'{}'请求", request.getRequestURI(), e.getMethod());
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        log.error("请求地址'{}',发生未知异常.", request.getRequestURI(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e, HttpServletRequest request) {
        log.error("请求地址'{}',发生系统异常.", request.getRequestURI(), e);
        return Result.error(e.getMessage());
    }
}
