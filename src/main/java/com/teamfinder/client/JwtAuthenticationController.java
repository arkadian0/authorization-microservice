package com.teamfinder.client;

import com.teamfinder.application.ports.JwtAuthenticationPort;
import com.teamfinder.client.transfer.JwtRequest;
import com.teamfinder.client.transfer.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/authenticate")
public class JwtAuthenticationController {

    private final JwtAuthenticationPort jwtAuthenticationPort;

    @PostMapping
    public ResponseEntity<Object> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        JwtResponse jwtResponse = jwtAuthenticationPort.createAuthenticationToken(authenticationRequest);
        return ResponseEntity.ok(jwtResponse);
    }


}