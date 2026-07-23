package com.a4b.automation.auth.jwt;

import java.io.IOException;
import org.springframework.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.a4b.automation.auth.service.CustumUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter{
    @Autowired
    private JwtService jwtService;
    @Autowired
    private CustumUserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                  String path=request.getRequestURI();
               
                if(path.startsWith("/api/auth/register")||path.startsWith("/api/auth/login")){
                    filterChain.doFilter(request, response);
                    return;
                }
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authHeader==null||!authHeader.startsWith("Bearer ")){
          filterChain.doFilter(request, response);
          return;
        }
        String jwt=authHeader.substring(7);
        String email=jwtService.extractUsername(jwt);
        if(email==null){
            filterChain.doFilter(request, response);
            return;
        }
        UserDetails user=userDetailsService.loadUserByUsername(email);
        if(jwtService.isTokenValid(email, user)){
          UsernamePasswordAuthenticationToken authtoken=new  UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
          authtoken.setDetails(new WebAuthenticationDetails(request));
           SecurityContextHolder.getContext().setAuthentication(authtoken); 
        }
        filterChain.doFilter(request, response);
    }   
    
    

}
