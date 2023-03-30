package com.manggoormy.manggoormy.domain.stone.service;

import com.manggoormy.manggoormy.domain.stone.model.Stone;
import com.manggoormy.manggoormy.domain.stone.model.dto.FileUploadResponse;
import com.manggoormy.manggoormy.domain.stone.model.dto.GetStoneResponse;
import com.manggoormy.manggoormy.domain.stone.model.dto.UploadStoneRequest;
import com.manggoormy.manggoormy.domain.stone.model.dto.UploadStoneResponse;
import com.manggoormy.manggoormy.domain.stone.repository.StoneRepository;
import com.manggoormy.manggoormy.util.FileUploadUtil;
import com.manggoormy.manggoormy.util.MeasureStoneUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class StoneService {
    private final StoneRepository stoneRepository;
    private final FileUploadUtil fileUploadUtil;

    @Transactional
    public UploadStoneResponse createStone(UploadStoneRequest request, MultipartFile image) throws IOException {

        byte[] bytes = image.getBytes();
        File file = new File("example.png");
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(bytes);

        BufferedImage read = ImageIO.read(file);
        double texture = MeasureStoneUtil.detectTexture(read);
        Random random = new Random();
        file.delete();

        // attack
        int min = (int) Math.floor(texture * 100);
        int max = 100;
        long atk = random.nextInt(max - min + 1) + min;


        // defense
        // 방어력 ⇒ 석회암 < 화강암 < 현무암(화산송이)
        //0 ~33, 34~66, 67~100

        // magicDefense
        //마법저항력 ⇒ 석회암 < 현무암(화산송이) < 화강암
        // 0 ~33, 34~66, 67~100
//        석회암 : C
//        현무암 : B
//        화강암 : A
//        화산송이 : S
        long defense;
        long magicDefense;
        String rarity;
        if (request.getStoneType() == "석회암") {
            defense = random.nextInt(34);
            magicDefense = random.nextInt(34);
            rarity = "C";
        } else if (request.getStoneType() == " 화강암") {
            defense = random.nextInt(33) + 34;
            magicDefense = random.nextInt(34) + 67;
            rarity = "A";
        } else {
            defense = random.nextInt(34) + 67;
            magicDefense = random.nextInt(33) + 34;
            if (request.getStoneType() == "현무암") {
                rarity = "B";
            } else {
                rarity = "S";
            }
        }

        FileUploadResponse fileUploadResponse = fileUploadUtil.uploadFile("image", image);

        Stone stone = new Stone(request.getDateTime(), fileUploadResponse.getFileUrl(), fileUploadResponse.getFilePath(), request.getAddress(), request.getLat(), request.getLng(), request.getStoneType(), request.getStoneType(), rarity, atk, defense, magicDefense);
        stoneRepository.save(stone);

        return new UploadStoneResponse(stone.getId(), stone.getDateTime().format(DateTimeFormatter.ofPattern("MM월 dd일 E요일").withLocale(Locale.KOREA)), stone.getStoneType(), stone.getStoneName(), stone.getAttack(), stone.getDefense(), stone.getMagicDefence(), stone.getAddress());
    }

    @Transactional
    public List<GetStoneResponse> getAllStone() {
        List<Stone> stones = stoneRepository.findAll();
        List<GetStoneResponse> responses = new ArrayList<>();

        for (Stone stone : stones) {
            GetStoneResponse stoneResponse = new GetStoneResponse(stone.getId(), stone.getStoneType(), stone.getStoneName(), stone.getDateTime().format(DateTimeFormatter.ofPattern("MM월 dd일 E요일").withLocale(Locale.KOREA)), stone.getAddress(), stone.getLat(), stone.getLng(), stone.getImageUrl(), stone.getRarity(), stone.getAttack(), stone.getDefense(), stone.getMagicDefence());
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

    @Transactional
    public GetStoneResponse getStone(Long stoneId) {
        Optional<Stone> optionalStone = stoneRepository.findById(stoneId);
        Stone stone = optionalStone.get();
        GetStoneResponse response = new GetStoneResponse(stone.getId(), stone.getStoneType(), stone.getStoneName(), stone.getDateTime().format(DateTimeFormatter.ofPattern("MM월 dd일 E요일").withLocale(Locale.KOREA)), stone.getAddress(), stone.getImageUrl(), stone.getRarity(), stone.getLat(), stone.getLng(), stone.getAttack(), stone.getDefense(), stone.getMagicDefence());
        return response;
    }
}
