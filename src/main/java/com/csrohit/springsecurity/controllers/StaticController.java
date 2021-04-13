package com.csrohit.springsecurity.controllers;

import com.csrohit.springsecurity.auth.JwtUtil;
import com.csrohit.springsecurity.models.AuthenticationRequest;
import com.csrohit.springsecurity.models.AuthenticationResponse;
import com.csrohit.springsecurity.services.MyUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class StaticController {

    private final JwtUtil jwtUtil;
    private final MyUserDetailsService myUserDetailsService;
    private final AuthenticationManager authenticationManager;

    public StaticController(JwtUtil jwtUtil, MyUserDetailsService myUserDetailsService, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.myUserDetailsService = myUserDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("")
    public String home() {
        return "Hello world";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username and password");
        }

        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
