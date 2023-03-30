package com.manggoormy.manggoormy.util;

import com.manggoormy.manggoormy.domain.common.exception.InvalidException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.manggoormy.manggoormy.domain.common.exception.ErrorCode.E400_INVALID_UPLOAD_FILE_EXTENSION;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUploadUtilHelper {

    public static void validateFileExtension(String category, String fileContentType) {
        System.out.println(fileContentType);
        if (category.equals("image") && !fileContentType.startsWith("image")){
            throw new InvalidException("파일 확장자를 확인해주세요.", E400_INVALID_UPLOAD_FILE_EXTENSION);
        }
    }
}
