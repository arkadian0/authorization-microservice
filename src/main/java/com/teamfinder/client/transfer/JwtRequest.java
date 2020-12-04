package com.teamfinder.client.transfer;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class JwtRequest {
    @NonNull
    private String email;
    @NonNull
    private String password;
}
