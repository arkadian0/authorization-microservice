package com.teamfinder.domain.authorization.ports.incoming;

import com.teamfinder.client.response.TfValidationListDto;
import com.teamfinder.client.transfer.RegistrationCommand;

public interface UserAuthorizationCommandPort {
    TfValidationListDto createNotConfirmedUser(RegistrationCommand authorizationCommand);

    void confirmUser(String confirmationToken);

    boolean checkEmailExist(String email);

    boolean checkUserIsEnabled(String email);
}
