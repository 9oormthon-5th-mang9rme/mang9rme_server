package com.manggoormy.manggoormy.domain.stone.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadResponse {
    private String fileUrl; // 파일 접근 URL
    private String filePath;  // 삭제 시 필요한 path
}