package com.manggoormy.manggoormy.domain.stone.controller;

import com.manggoormy.manggoormy.domain.common.dto.ApiResponse;
import com.manggoormy.manggoormy.domain.stone.model.dto.*;
import com.manggoormy.manggoormy.domain.stone.service.StoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StoneController {
    private final StoneService stoneService;

    @PostMapping("/api/stone")
    public ApiResponse<UploadStoneResponse> createStone(
            @RequestPart MultipartFile image,
            @RequestPart UploadStoneRequest uploadStoneRequest
    ) throws IOException {
        UploadStoneResponse response = stoneService.createStone(uploadStoneRequest, image);
        return ApiResponse.success(response);
    }

    @GetMapping("/api/stone")
    public ApiResponse<List<GetStoneResponse>> getAllStones() {
        List<GetStoneResponse> response = stoneService.getAllStone();
        return ApiResponse.success(response);
    }

    @PatchMapping("/api/stone")
    public ApiResponse<UpdateStoneNameResponse> updateStoneName(
            @RequestBody UpdateStoneNameRequest updateStoneNameRequest
    ) {
        Long stoneId = stoneService.updateStoneName(updateStoneNameRequest.getStoneId(), updateStoneNameRequest.getStoneName());
        UpdateStoneNameResponse response = new UpdateStoneNameResponse(stoneId);
        return ApiResponse.success(response);
    }

    @GetMapping("/api/{stoneId}")
    public ApiResponse<GetStoneResponse> getStone(
            @PathVariable Long stoneId
    ) {
        GetStoneResponse response = stoneService.getStone(stoneId);
        return ApiResponse.success(response);
    }
}
