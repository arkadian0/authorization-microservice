package com.teamfinder.application.ports;

import com.teamfinder.client.transfer.JwtRequest;
import com.teamfinder.client.transfer.JwtResponse;

public interface JwtAuthenticationPort {
    JwtResponse createAuthenticationToken(JwtRequest authenticationRequest) throws Exception;
}
