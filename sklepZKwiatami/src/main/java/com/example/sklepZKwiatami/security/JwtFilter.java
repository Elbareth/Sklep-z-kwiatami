package com.example.sklepZKwiatami.security;

import com.example.sklepZKwiatami.service.UserDTODetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter{
    @Autowired
    private JwtTokenUtilImpl jwtTokenUtil;
    @Autowired
    private UserDTODetailsService userDTODetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String requestHeader = request.getHeader("Authorization");//pobieramy nagłówek o takim tytule
        String token = null;
        String username = null;
        if(requestHeader != null && requestHeader.startsWith("Bearer ")){
            token = requestHeader.substring(7);
            try{
                username = jwtTokenUtil.getUsernameFromToken(token);
            }
            catch (IllegalArgumentException | ExpiredJwtException e){
                e.printStackTrace();
            }
        }
        else System.out.println("Sth wrong with token");
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDTODetailsService.loadUserByUsername(username);
            if(jwtTokenUtil.validateToken(token, userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
