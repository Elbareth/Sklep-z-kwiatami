package com.example.sklepZKwiatami.asembler;

import com.example.sklepZKwiatami.dto.FlowerDTO;
import com.example.sklepZKwiatami.dto.FlowerWithoutBarcodeDTO;
import com.example.sklepZKwiatami.entity.Flower;
import com.example.sklepZKwiatami.service.ZxingHelperService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FlowerAsembler {

    public Flower toEntity(FlowerDTO flowerDTO){
        return new Flower(flowerDTO.getName(),
                flowerDTO.getDescription(),
                flowerDTO.getPrice(),
                flowerDTO.getBarcode(),
                flowerDTO.getQuantity());
    }
    public List<Flower> toEntity(List<FlowerDTO> flowerDTO){
        List<Flower> entity = new ArrayList<>();
        flowerDTO.forEach(it->{
            entity.add(toEntity(it));
        });
        return entity;
    }
    public FlowerDTO toDTO(Flower flower){
        return new FlowerDTO(flower.getId(),
                flower.getName(),
                flower.getDescription(),
                flower.getPrice(),
                flower.getBarcode(),
                flower.getQuantity());
    }
    public List<FlowerDTO> toDTO(Iterable<Flower> flowers){
        List<FlowerDTO> dto = new ArrayList<>();
        flowers.forEach(it->{
            dto.add(toDTO(it));
        });
        return dto;
    }
    public void update(FlowerWithoutBarcodeDTO flowerDTO, Flower flower){
        flower.setName(flowerDTO.getName());
        flower.setDescription(flowerDTO.getDescription());
        flower.setPrice(flowerDTO.getPrice());
        flower.setBarcode(ZxingHelperService.getBarCodeImage(flowerDTO.getName(), flowerDTO.getPrice()));
        flower.setQuantity(flowerDTO.getQuantity());
    }
}
