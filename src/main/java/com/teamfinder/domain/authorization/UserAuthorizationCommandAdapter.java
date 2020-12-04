package com.teamfinder.domain.authorization;


import com.teamfinder.client.response.TfValidationListDto;
import com.teamfinder.client.transfer.RegistrationCommand;
import com.teamfinder.domain.authorization.ports.incoming.UserAuthorizationCommandPort;
import com.teamfinder.domain.authorization.ports.outcoming.ConfirmationTokenRepositoryPort;
import com.teamfinder.domain.authorization.ports.outcoming.EmailSenderPort;
import com.teamfinder.domain.authorization.ports.outcoming.RoleRepositoryPort;
import com.teamfinder.domain.authorization.ports.outcoming.UserRepositoryPort;
import com.teamfinder.validators.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class UserAuthorizationCommandAdapter implements UserAuthorizationCommandPort {

    private final UserRepositoryPort userRepositoryPort;
    private final RoleRepositoryPort roleRepositoryPort;
    private final EmailSenderPort emailSenderPort;
    private final ConfirmationTokenRepositoryPort confirmationTokenRepositoryPort;
    private final AccountValidator accountValidator;

    @Autowired
    UserAuthorizationCommandAdapter(UserRepositoryPort userRepositoryPort, RoleRepositoryPort roleRepositoryPort, EmailSenderPort emailSenderPort, ConfirmationTokenRepositoryPort confirmationTokenRepositoryPort, AccountValidator accountValidator) {
        this.userRepositoryPort = userRepositoryPort;
        this.roleRepositoryPort = roleRepositoryPort;
        this.emailSenderPort = emailSenderPort;
        this.confirmationTokenRepositoryPort = confirmationTokenRepositoryPort;
        this.accountValidator = accountValidator;
    }

    @Override
    public TfValidationListDto createNotConfirmedUser(RegistrationCommand registrationCommand) {
        Optional<Account> account = userRepositoryPort.findByEmail(registrationCommand.getEmail());
        TfValidationListDto tfValidationListDto = accountValidator.validateAccount(account,registrationCommand);

        if (!tfValidationListDto.getErrors().isEmpty())
            return tfValidationListDto;

        Account newUser = createNewUser(registrationCommand);
        userRepositoryPort.save(newUser);
        emailSenderPort.sendEmailWithConfirmationToken(newUser.getConfirmationToken());

        return tfValidationListDto;
    }

    private Account createNewUser(RegistrationCommand registrationCommand) {
        Optional<Role> role = roleRepositoryPort.findByRole(registrationCommand.getSystemRole());
        if (role.isPresent())
            return new Account(registrationCommand, role.get());
        else
            throw new IllegalStateException(String.format("Cannot find role %s", registrationCommand.getSystemRole()));
    }

    @Override
    public void confirmUser(String confirmationToken) {
        Optional<ConfirmationToken> token = confirmationTokenRepositoryPort.findByConfirmationToken(confirmationToken);
        token.ifPresentOrElse(this::activeUserAccount, () -> {
            throw new IllegalStateException(String.format("Invalid confirmation token : %s", confirmationToken));
        });
    }

    private void activeUserAccount(ConfirmationToken foundedToken) {
        Account user = foundedToken.getAccount();
        user.activeUser();
        userRepositoryPort.save(user);
    }

    @Override
    public boolean checkEmailExist(String email) {
        Optional<Account> account = userRepositoryPort.findByEmail(email);
        return account.isPresent();
    }

    @Override
    public boolean checkUserIsEnabled(String email) {
        Optional<Account> account = userRepositoryPort.findByEmail(email);

        if(!account.isPresent())
            throw new IllegalStateException(String.format("User by email: %s don't exist",email));
        else
            return account.get().getIsEnabled();

    }
}
