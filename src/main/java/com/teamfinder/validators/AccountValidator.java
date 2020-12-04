package com.teamfinder.validators;

import com.teamfinder.client.response.TfValidationListDto;
import com.teamfinder.client.transfer.RegistrationCommand;
import com.teamfinder.domain.authorization.Account;
import java.util.Optional;

public interface AccountValidator {
    TfValidationListDto validateAccount(Optional<Account> account, RegistrationCommand registrationCommand);
}
