package com.manggoormy.manggoormy.domain.stone.service;

import com.manggoormy.manggoormy.domain.stone.model.Stone;
import com.manggoormy.manggoormy.domain.stone.model.dto.FileUploadResponse;
import com.manggoormy.manggoormy.domain.stone.model.dto.GetStoneResponse;
import com.manggoormy.manggoormy.domain.stone.model.dto.UploadStoneRequest;
import com.manggoormy.manggoormy.domain.stone.model.dto.UploadStoneResponse;
import com.manggoormy.manggoormy.domain.stone.repository.StoneRepository;
import com.manggoormy.manggoormy.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoneService {
    private final StoneRepository stoneRepository;
    private final FileUploadUtil fileUploadUtil;

    @Transactional
    public UploadStoneResponse createStone(UploadStoneRequest request, MultipartFile image) throws IOException {
        FileUploadResponse fileUploadResponse = fileUploadUtil.uploadFile("image", image);

        Stone stone = new Stone(request.getDateTime(), fileUploadResponse.getFileUrl(), fileUploadResponse.getFilePath(), request.getAddress(), request.getLat(), request.getLng()
                , request.getStoneType(), request.getStoneType(), "level", "rarity", 100L, 100L, 100L);
        stoneRepository.save(stone);

        return new UploadStoneResponse(stone.getId(), stone.getDateTime().format(DateTimeFormatter.ofPattern("MM월 dd일 E요일").withLocale(Locale.KOREA)), stone.getStoneType(), stone.getStoneName(), stone.getAttack(),
                stone.getDefense(), stone.getMagicDefence(), stone.getAddress());
    }

    @Transactional
    public List<GetStoneResponse> getAllStone() {
        List<Stone> stones = stoneRepository.findAll();
        List<GetStoneResponse> responses = new ArrayList<>();

        for (Stone stone : stones) {
            GetStoneResponse stoneResponse = new GetStoneResponse(stone.getId(), stone.getDateTime().format(DateTimeFormatter.ofPattern("MM월 dd일 E요일").withLocale(Locale.KOREA)), stone.getAddress(), stone.getImageUrl(),
                    100L, 100L, 100L);
            responses.add(stoneResponse);
        }

        return responses;
    }

    @Transactional
    public Long updateStoneName(Long stoneId, String stoneName) {
        Optional<Stone> stone = stoneRepository.findById(stoneId);
        stone.get().setStoneName(stoneName);
        return stoneId;
    }
}
