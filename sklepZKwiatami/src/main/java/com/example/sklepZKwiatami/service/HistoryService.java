package com.example.sklepZKwiatami.service;

import com.example.sklepZKwiatami.asembler.HistoryAsembler;
import com.example.sklepZKwiatami.dto.HistoryDTO;
import com.example.sklepZKwiatami.repositories.HistoryRepository;
import com.example.sklepZKwiatami.repositories.UserRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoryService {
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private HistoryAsembler historyAsembler;
    @Autowired
    private UserRepository userRepository;

    public List<HistoryDTO> findByUser(Integer user){ // to jest w ramach findAll
        if(!userRepository.findById(user).isPresent()) throw new EntityNotFoundException("Cant find history for this user");
        return historyAsembler.toDto(historyRepository.findByUser(userRepository.findById(user).get()));
    }
    public void delete(Integer id){
        if(!historyRepository.findById(id).isPresent()) throw new EntityNotFoundException("Cant find history with that id");
        historyRepository.deleteById(id);
    }
    public HistoryDTO findById(Integer id){
        if(!historyRepository.findById(id).isPresent()) throw new EntityNotFoundException("Cant find history with that id");
        return historyAsembler.toDto(historyRepository.findById(id).get());
    }
    public HistoryDTO create(HistoryDTO historyDTO){
        return historyAsembler.toDto(historyRepository.save(historyAsembler.toEntity(historyDTO)));
    }
    public HistoryDTO findByLocalDateTime(LocalDateTime localDateTime){
        if(!historyRepository.findByLocalDateTime(localDateTime).isPresent()) throw new EntityNotFoundException("Cant find history at that time");
        return historyAsembler.toDto(historyRepository.findByLocalDateTime(localDateTime).get());
    }
    public List<HistoryDTO> findByLocalDateTimeBetween(LocalDateTime start, LocalDateTime end){
        return historyAsembler.toDto(historyRepository.findByLocalDateTimeBetween(start, end));
    }
}
