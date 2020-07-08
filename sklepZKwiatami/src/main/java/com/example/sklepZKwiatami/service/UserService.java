package com.example.sklepZKwiatami.service;

import com.example.sklepZKwiatami.asembler.UserAsembler;
import com.example.sklepZKwiatami.dto.UserDTO;
import com.example.sklepZKwiatami.entity.User;
import com.example.sklepZKwiatami.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAsembler userAsembler;

    public UserDTO findByLoginAndPassword(String login, String password){
        if(!userRepository.findByLoginAndPassword(login, password).isPresent()) throw new UsernameNotFoundException("There is no user with that login");
        return userAsembler.toDto(userRepository.findByLoginAndPassword(login, password).get());
    }
    public UserDTO create(UserDTO userDTO){
        return userAsembler.toDto(userRepository.save(userAsembler.toEntity(userDTO)));
    }
    public UserDTO findById(Integer id){
        if(!userRepository.findById(id).isPresent()) throw new EntityNotFoundException("There is no user with that id");
        return userAsembler.toDto(userRepository.findById(id).get());
    }
    public List<UserDTO> findAll(){
        return userAsembler.toDto(userRepository.findAll());
    }
    @Transactional
    public UserDTO update(UserDTO userDTO, Integer id){
        if(!userRepository.findById(id).isPresent()) throw new EntityNotFoundException("There is no user with that id");
        User entity = userRepository.findById(id).get();
        userAsembler.update(entity, userDTO);
        return userDTO;
    }
    public void delete(Integer id){
        if(!userRepository.findById(id).isPresent()) throw new EntityNotFoundException("There is no user with that id");
        userRepository.deleteById(id);
    }
    public UserDTO findByLogin(String login){
        if(!userRepository.findByLogin(login).isPresent()) return null;
        return userAsembler.toDto(userRepository.findByLogin(login).get());
    }
}
