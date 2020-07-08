package com.example.sklepZKwiatami.asembler;

import com.example.sklepZKwiatami.dto.UserDTO;
import com.example.sklepZKwiatami.entity.User;
import com.example.sklepZKwiatami.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAsembler {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User toEntity(UserDTO userDTO){
        return new User(userDTO.getUsername(),
                passwordEncoder.encode(userDTO.getPassword()),
                userDTO.getGrantedAuthority(),
                userDTO.getName(),
                userDTO.getSurname(),
                userDTO.isAccountNonExpired(),
                userDTO.isAccountNonLocked(),
                userDTO.isCredentialsNonExpired(),
                userDTO.isEnabled());
    }
    public List<User> toEntity(List<UserDTO> userDTOS){
        List<User> entity = new ArrayList<>();
        userDTOS.forEach(it->{
            entity.add(toEntity(it));
        });
        return entity;
    }
    public UserDTO toDto(User user){
        return new UserDTO(user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getGrantedAuthority(),
                user.getName(),
                user.getSurname(),
                user.isAccountNonExpired(),
                user.isAccountNonLocked(),
                user.isCredentialsNonExpired(),
                user.isEnabled());
    }
    public List<UserDTO> toDto(Iterable<User> users){
        List<UserDTO> dto = new ArrayList<>();
        users.forEach(it->{
            dto.add(toDto(it));
        });
        return dto;
    }
    public void update(User user, UserDTO userDTO){
        user.setLogin(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setGrantedAuthority(userDTO.getGrantedAuthority());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setAccountNonExpired(userDTO.isAccountNonExpired());
        user.setAccountNonLocked(userDTO.isAccountNonLocked());
        user.setCredentialsNonExpired(userDTO.isCredentialsNonExpired());
        user.setEnabled(userDTO.isEnabled());
    }
}
