package com.example.sklepZKwiatami.contoller;

import com.example.sklepZKwiatami.dto.FlowerDTO;
import com.example.sklepZKwiatami.dto.HistoryDTO;
import com.example.sklepZKwiatami.dto.UserDTO;
import com.example.sklepZKwiatami.service.FlowerService;
import com.example.sklepZKwiatami.service.HistoryService;
import com.example.sklepZKwiatami.service.UserService;
import com.example.sklepZKwiatami.service.ZxingHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/shop")
public class ShopContoller {
    @Autowired
    private HistoryService historyService;
    @Autowired
    private UserService userService;
    @Autowired
    private FlowerService flowerService;

    @GetMapping
    private Float allPrice(HttpSession session){
        checkIfSessionHasPrice(0f, session);
        return (Float) session.getAttribute("price");
    }
    @PostMapping
    public HistoryDTO create(@RequestBody Byte[] byteArray, HttpSession session){
        String product = ZxingHelperService.getInformationFromBarCode(byteArray);
        String[] information = product.split(" ");
        FlowerDTO flowerDTO = flowerService.findByName(information[0]);
        UserDTO userDTO = userService.findByLogin((String) session.getAttribute("login"));
        checkIfSessionHasPrice(Float.parseFloat(information[1]), session);
        HistoryDTO historyDTO = new HistoryDTO(0,
              userDTO.getId(),
              flowerDTO.getId(),
              1,
              LocalDateTime.now());
        return historyService.create(historyDTO);
    }
    private void checkIfSessionHasPrice(Float price, HttpSession session){
        if(session.getAttribute("price") == null) session.setAttribute("price", price);
        else session.setAttribute("price", (Float) session.getAttribute("price") + price);
    }
}
