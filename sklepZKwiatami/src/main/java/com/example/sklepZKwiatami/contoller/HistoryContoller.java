package com.example.sklepZKwiatami.contoller;

import com.example.sklepZKwiatami.dto.DateBetweenDTO;
import com.example.sklepZKwiatami.dto.HistoryDTO;
import com.example.sklepZKwiatami.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryContoller {
    @Autowired
    private HistoryService historyService;

    @GetMapping("/user/{id}")
    private List<HistoryDTO> findByUser(@PathVariable("id") Integer user){
        return historyService.findByUser(user);
    }
    @DeleteMapping("/{id}")
    private void delete(@PathVariable("id") Integer id){
        historyService.delete(id);
    }
    @GetMapping("/findByDate")
    private HistoryDTO findByDate(@RequestBody LocalDateTime time){
        return historyService.findByLocalDateTime(time);
    }
    @GetMapping("/findByDateBetween")
    private List<HistoryDTO> findByDateBetween(@RequestBody DateBetweenDTO dateBetweenDTO){
        return historyService.findByLocalDateTimeBetween(dateBetweenDTO.getDateStart(), dateBetweenDTO.getDateEnd());
    }

}
