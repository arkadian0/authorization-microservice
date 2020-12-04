package com.teamfinder.client.transfer;

import com.teamfinder.domain.authorization.enums.SystemRole;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class RegistrationCommand {
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private SystemRole systemRole;
}
