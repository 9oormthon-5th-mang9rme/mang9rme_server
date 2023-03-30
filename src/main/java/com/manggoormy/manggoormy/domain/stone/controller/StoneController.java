package com.manggoormy.manggoormy.domain.stone.controller;

import com.manggoormy.manggoormy.domain.common.dto.ApiResponse;
import com.manggoormy.manggoormy.domain.stone.model.dto.GetStoneResponse;
import com.manggoormy.manggoormy.domain.stone.model.dto.StoneUploadRequest;
import com.manggoormy.manggoormy.domain.stone.model.dto.StoneUploadResponse;
import com.manggoormy.manggoormy.domain.stone.service.StoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StoneController {
    private final StoneService stoneService;

    @PostMapping("/api/stone")
    public ApiResponse<StoneUploadResponse> createStone(
            @RequestPart MultipartFile image,
            @RequestPart StoneUploadRequest stoneUploadRequest
    ) throws IOException {
        StoneUploadResponse response = stoneService.createStone(stoneUploadRequest, image);
        return ApiResponse.success(response);
    }

    @GetMapping("/api/stone")
    public ApiResponse<List<GetStoneResponse>> getAllStones() {
        List<GetStoneResponse> response = stoneService.getAllStone();
        return ApiResponse.success(response);
    }
}
