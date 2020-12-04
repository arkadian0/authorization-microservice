package com.teamfinder.domain.authorization.ports.outcoming;

import com.teamfinder.domain.authorization.ConfirmationToken;

public interface EmailSenderPort {
    void sendEmailWithConfirmationToken(ConfirmationToken confirmationToken);
}

