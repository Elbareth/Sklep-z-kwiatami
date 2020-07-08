package com.example.sklepZKwiatami.asembler;

import com.example.sklepZKwiatami.dto.FlowerWithoutBarcodeDTO;
import com.example.sklepZKwiatami.entity.Flower;
import com.example.sklepZKwiatami.service.ZxingHelperService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FlowerWithoutBarcodeAsembler {

    public Flower toEntity(FlowerWithoutBarcodeDTO dto){
        return new Flower(dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                ZxingHelperService.getBarCodeImage(dto.getName(), dto.getPrice()),
                dto.getQuantity());
    }
    public List<Flower> toEntity(List<FlowerWithoutBarcodeDTO> dto){
        List<Flower> entity = new ArrayList<>();
        dto.forEach(it->{
            entity.add(toEntity(it));
        });
        return entity;
    }
    public FlowerWithoutBarcodeDTO toDto(Flower flower){
        return new FlowerWithoutBarcodeDTO(flower.getId(),
                flower.getName(),
                flower.getDescription(),
                flower.getPrice(),
                flower.getQuantity());
    }
    public List<FlowerWithoutBarcodeDTO> toDto(List<Flower> flowers){
        List<FlowerWithoutBarcodeDTO> dto = new ArrayList<>();
        flowers.forEach(it->{
            dto.add(toDto(it));
        });
        return dto;
    }
}
