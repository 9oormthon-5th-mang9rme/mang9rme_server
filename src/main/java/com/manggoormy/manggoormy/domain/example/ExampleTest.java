package com.manggoormy.manggoormy.domain.example;

import com.manggoormy.manggoormy.domain.common.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleTest {
    @GetMapping("/api/test")
    public ApiResponse<String> testApi() {
        return ApiResponse.OK;
    }

    @GetMapping("/api/test2")
    public ApiResponse<String> test2Api() {
        return ApiResponse.OK;
    }
}
