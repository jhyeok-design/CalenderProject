package com.example.calenderproject.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

// Lv7 전역 예외 처리
public class ExceptionAdvice {

    /**
     *  시간이 부족해서 범용으로 사용하는 IllegalArgumentException으로 모든 예외처리를 했음
     *  다음 프로젝트 할 땐 상황에 맞는 예외처리를 하고 dto와 http status를 이용하기
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handlerIllegalArgumentException(IllegalArgumentException e ){
        return ResponseEntity.badRequest().body(e.getMessage());
    }


}
