package com.example.sklepZKwiatami.contoller;

import com.example.sklepZKwiatami.dto.FlowerDTO;
import com.example.sklepZKwiatami.dto.FlowerWithoutBarcodeDTO;
import com.example.sklepZKwiatami.service.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flower")
public class FlowerController {
    @Autowired
    private FlowerService flowerService;

    @GetMapping("/asc")
    public List<FlowerDTO> findPageAsc(@RequestParam("page") Integer page){
        List<FlowerDTO> flower = flowerService.findAllByOrderByPriceAsc();
        return pagination(page -1, flower);
    }
    @GetMapping("/desc")
    public List<FlowerDTO> findPageDesc(@RequestParam("page") Integer page){
        List<FlowerDTO> flower = flowerService.findAllByOrderByPriceDesc();
        return pagination(page -1, flower);
    }
    @GetMapping("/not_null")
    public List<FlowerDTO> findNotNull(@RequestParam("page") Integer page){
        List<FlowerDTO> flower = flowerService.findByQuantityGreaterThan(0);
        return pagination(page -1, flower);
    }
    @GetMapping("/{id}")
    public FlowerDTO findById(@PathVariable("id") Integer id){
        return flowerService.findById(id);
    }
    @PutMapping("/{id}")
    public FlowerDTO update(@RequestBody FlowerWithoutBarcodeDTO flowerDTO, @PathVariable("id") Integer id){
        return flowerService.update(flowerDTO, id);
    }
    @PostMapping
    public FlowerDTO create(@RequestBody FlowerWithoutBarcodeDTO flowerDTO){
        return flowerService.create(flowerDTO);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        flowerService.delete(id);
    }
    @GetMapping
    public FlowerDTO findByName(@RequestParam("name") String name){
        return flowerService.findByName(name);
    }
    private List<FlowerDTO> pagination(Integer page, List<FlowerDTO> flower){
        PagedListHolder<FlowerDTO> pagedListHolder = new PagedListHolder<>(flower);
        pagedListHolder.setPage(page);
        pagedListHolder.setPageSize(10);
        return pagedListHolder.getPageList();
    }

}
