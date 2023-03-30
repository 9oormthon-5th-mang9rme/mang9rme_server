package com.manggoormy.manggoormy.domain.stone.service;

import com.manggoormy.manggoormy.domain.stone.model.Stone;
import com.manggoormy.manggoormy.domain.stone.model.dto.FileUploadResponse;
import com.manggoormy.manggoormy.domain.stone.model.dto.GetStoneResponse;
import com.manggoormy.manggoormy.domain.stone.model.dto.StoneUploadRequest;
import com.manggoormy.manggoormy.domain.stone.model.dto.StoneUploadResponse;
import com.manggoormy.manggoormy.domain.stone.repository.StoneRepository;
import com.manggoormy.manggoormy.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoneService {
    private final StoneRepository stoneRepository;
    private final FileUploadUtil fileUploadUtil;

    @Transactional
    public StoneUploadResponse createStone(StoneUploadRequest request, MultipartFile image) throws IOException {
        FileUploadResponse fileUploadResponse = fileUploadUtil.uploadFile("image", image);

        Stone stone = new Stone(fileUploadResponse.getFileUrl(), fileUploadResponse.getFilePath(), request.getAddress(), request.getLat(), request.getLng()
                , request.getStoneType(), "level", "rarity", 100L, 100L, 100L);
        stoneRepository.save(stone);

        return new StoneUploadResponse(stone.getId());
    }

    @Transactional
    public List<GetStoneResponse> getAllStone() {
        List<Stone> stones = stoneRepository.findAll();
        List<GetStoneResponse> responses = new ArrayList<>();

        for (Stone stone : stones) {
            GetStoneResponse stoneResponse = new GetStoneResponse(stone.getId(), stone.getAddress(), stone.getImageUrl(),
                    100L,100L,100L);
            responses.add(stoneResponse);
        }

        return responses;
    }

}
