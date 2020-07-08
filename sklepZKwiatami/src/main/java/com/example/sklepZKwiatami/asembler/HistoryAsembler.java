package com.example.sklepZKwiatami.asembler;

import com.example.sklepZKwiatami.dto.HistoryDTO;
import com.example.sklepZKwiatami.entity.History;
import com.example.sklepZKwiatami.repositories.FlowerRepository;
import com.example.sklepZKwiatami.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HistoryAsembler {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FlowerRepository flowerRepository;

    public History toEntity(HistoryDTO historyDTO){
        return new History(historyDTO.getId(),
                userRepository.findById(historyDTO.getUser()).get(),
                flowerRepository.findById(historyDTO.getFlower()).get(),
                historyDTO.getQuantity(),
                historyDTO.getLocalDateTime());
    }
    public List<History> toEntity(List<HistoryDTO> historyDTOS){
        List<History> entity = new ArrayList<>();
        historyDTOS.forEach(it->{
            entity.add(toEntity(it));
        });
        return entity;
    }
    public HistoryDTO toDto(History history){
        return new HistoryDTO(history.getId(),
                history.getUser().getId(),
                history.getFlower().getId(),
                history.getQuantity(),
                history.getLocalDateTime());
    }
    public List<HistoryDTO> toDto(List<History> histories){
        List<HistoryDTO> dto = new ArrayList<>();
        histories.forEach(it->{
            dto.add(toDto(it));
        });
        return dto;
    }
}
