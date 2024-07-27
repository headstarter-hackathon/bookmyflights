package com.excelr.bookmyflights.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager; 
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication; 
import org.springframework.security.core.userdetails.UsernameNotFoundException; 
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import com.excelr.bookmyflights.model.AuthRequest;
import com.excelr.bookmyflights.model.AuthResponse;
import com.excelr.bookmyflights.model.UserInfo;
import com.excelr.bookmyflights.service.JwtService;
import com.excelr.bookmyflights.service.UserInfoService; 
  
@RestController
@RequestMapping("/auth") 
public class UserController { 
  
    @Autowired
    private UserInfoService service; 
  
    @Autowired
    private JwtService jwtService; 
  
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register") 
    public UserInfo addNewUser(@RequestBody UserInfo userInfo) { 
        return service.addUser(userInfo);
    } 

    @PostMapping("/login") 
    public AuthResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) { 
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())); 
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUsername(authRequest.getUsername());
        if (authentication.isAuthenticated()) {
            UserInfo userInfo = service.getUserByUsername(authRequest.getUsername());
            String role = userInfo.getRoles();

            authResponse.setResponse(jwtService.generateToken(authRequest.getUsername(), role));
            return authResponse; 
        } else { 
            throw new UsernameNotFoundException("invalid user request !"); 
        } 
    }

    @GetMapping("/checkLogin")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public String checkLogin() {
        return "Welcome to BookMyFlights";
    }

    @GetMapping("/checkAdmin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String checkAdmin() {
        return "Welcome to BookMyFlights Admin";
    }

    @GetMapping("/getUser")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public UserInfo getUser(@RequestHeader String token) {
        String username = jwtService.extractUsername(token);
        return service.getUserByUsername(username);
    }
}