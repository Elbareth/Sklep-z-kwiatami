package com.example.sklepZKwiatami.contoller;

import com.example.sklepZKwiatami.dto.LoginUserDTO;
import com.example.sklepZKwiatami.dto.UserDTO;
import com.example.sklepZKwiatami.security.JwtTokenUtilImpl;
import com.example.sklepZKwiatami.service.CaptchaService;
import com.example.sklepZKwiatami.service.UserDTODetailsService;
import com.example.sklepZKwiatami.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserContoller {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDTODetailsService userDTODetailsService;
    @Autowired
    private CaptchaService captchaService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtilImpl util;

    @GetMapping
    public String captcha(HttpServletResponse response, HttpServletRequest request, HttpSession session){
        String captcha = null;
        try {
            captcha = captchaService.createCaptcha(request, response);
            session.setAttribute("captcha", captcha);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return captcha;
    }
    @PostMapping("/register")
    public UserDTO register(@RequestBody UserDTO userDTO, @RequestParam("captcha")String captcha, @RequestParam(name = "param", required = false)String param, HttpSession session){
        String cap = (String) session.getAttribute("captcha");
        if(cap.equals(captcha)
                && (param == null || userDTO.getGrantedAuthority().equals(param))
                && userService.findByLogin(userDTO.getUsername()) == null){
            return userService.create(userDTO);
        }else{
            return null;
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserDTO loginUser, HttpSession session){
        authenticate(loginUser.getLogin(), loginUser.getPassword());
        final UserDetails userDetails = userDTODetailsService.loadUserByUsername(loginUser.getLogin());
        final String token = util.generateToken(userDetails);
        session.setAttribute("login", loginUser.getLogin());
        return ResponseEntity.ok(token);
    }
    @PutMapping("/{id}")
    public UserDTO update(@RequestBody UserDTO userDTO, @PathVariable("id") Integer id){
        return userService.update(userDTO, id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        userService.delete(id);
    }
    @GetMapping("/{login}")
    public UserDTO findByLogin(@PathVariable("login") String login){
        return userService.findByLogin(login);
    }
    private void authenticate(String username, String password){
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (DisabledException | BadCredentialsException e){
            e.printStackTrace();
        }
    }

}
