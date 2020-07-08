package com.example.sklepZKwiatami.service;

import com.example.sklepZKwiatami.asembler.FlowerAsembler;
import com.example.sklepZKwiatami.asembler.FlowerWithoutBarcodeAsembler;
import com.example.sklepZKwiatami.dto.FlowerDTO;
import com.example.sklepZKwiatami.dto.FlowerWithoutBarcodeDTO;
import com.example.sklepZKwiatami.entity.Flower;
import com.example.sklepZKwiatami.repositories.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

//TODO podział na strony
@Service
public class FlowerService {
    @Autowired
    private FlowerAsembler flowerAsembler;
    @Autowired
    private FlowerRepository flowerRepository;
    @Autowired
    private FlowerWithoutBarcodeAsembler flowerWithoutBarcodeAsembler;

    public FlowerDTO findById(Integer id){
        if(!flowerRepository.findById(id).isPresent()) throw new EntityNotFoundException("There is no flower with that id");
        return flowerAsembler.toDTO(flowerRepository.findById(id).get());
    }
    public List<FlowerDTO> findAll(){
        return flowerAsembler.toDTO(flowerRepository.findAll());
    }
    public FlowerDTO create(FlowerWithoutBarcodeDTO flowerDTO){
        return flowerAsembler.toDTO(flowerRepository.save(flowerWithoutBarcodeAsembler.toEntity(flowerDTO)));
    }
    @Transactional
    public FlowerDTO update(FlowerWithoutBarcodeDTO flowerDTO, Integer id){
        if(!flowerRepository.findById(id).isPresent()) throw new EntityNotFoundException("There is no flower with that id");
        Flower flower = flowerRepository.findById(id).get();
        flowerAsembler.update(flowerDTO, flower);
        return flowerAsembler.toDTO(flower);
    }
    public void delete(Integer id){
        if(!flowerRepository.findById(id).isPresent()) throw new EntityNotFoundException("There is no flower with that id");
        flowerRepository.deleteById(id);
    }
    public FlowerDTO findByName(String name){
        if(!flowerRepository.findByName(name).isPresent()) throw new EntityNotFoundException("There is no flower with that name");
        return flowerAsembler.toDTO(flowerRepository.findByName(name).get());
    }
    public List<FlowerDTO> findByQuantityGreaterThan(Integer quantity){
       return flowerAsembler.toDTO(flowerRepository.findByQuantityGreaterThan(quantity));
    }
    public List<FlowerDTO> findAllByOrderByPriceAsc(){
        return flowerAsembler.toDTO(flowerRepository.findAllByOrderByPriceAsc());
    }
    public List<FlowerDTO> findAllByOrderByPriceDesc(){
        return flowerAsembler.toDTO(flowerRepository.findAllByOrderByPriceDesc());
    }
}
