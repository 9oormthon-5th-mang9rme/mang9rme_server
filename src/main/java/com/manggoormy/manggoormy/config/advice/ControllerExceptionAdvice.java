package com.manggoormy.manggoormy.config.advice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ControllerExceptionAdvice extends ResponseEntityExceptionHandler {

}
