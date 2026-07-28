package com.a4b.automation.notification.websocket;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.a4b.automation.auth.jwt.JwtService;
@Component
public class WebSocketAuthInterpreter  implements HandshakeInterceptor {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void afterHandshake(ServerHttpRequest arg0, ServerHttpResponse arg1, WebSocketHandler arg2, Exception arg3) {
      if (arg3 == null) {
        System.out.println("WebSocket Connected Successfully");
    } else {
        System.out.println("Handshake Failed : " + arg3.getMessage());
    }
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
            Map<String, Object> attributes) throws Exception {
                String authHeader=request.getHeaders().getFirst("Authorization");
                if(authHeader==null||!authHeader.startsWith("Bearer ")){
                return false;
    }
    String jwt=authHeader.substring(7);
    String username=jwtService.extractUsername(jwt);
    UserDetails user=userDetailsService.loadUserByUsername(username);
    if(!jwtService.isTokenValid(username, user)){
        return false;
    }
    attributes.put(username, user);
    return true;


}

   

}
