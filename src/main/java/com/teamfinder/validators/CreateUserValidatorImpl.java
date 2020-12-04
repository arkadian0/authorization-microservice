package com.teamfinder.validators;

import com.teamfinder.client.response.TfValidationListDto;
import com.teamfinder.client.transfer.RegistrationCommand;
import com.teamfinder.domain.authorization.Account;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateUserValidatorImpl extends ValidatorsMessages implements AccountValidator {

    private final PasswordValidator passwordValidator;

    CreateUserValidatorImpl(MessageSource messageSource,PasswordValidator passwordValidator) {
        super(messageSource);
        this.passwordValidator = passwordValidator;
    }

    @Override
    public TfValidationListDto validateAccount(Optional<Account> account, RegistrationCommand registrationCommand) {
        TfValidationListDto tfValidationListDto = new TfValidationListDto();
        validateEmailExists(account, tfValidationListDto);
        validateEmailSyntax(registrationCommand.getEmail(), tfValidationListDto);
        //validatePassword(registrationCommand.getPassword(),tfValidationListDto); //TODO do analizy
        return tfValidationListDto;
    }

    private void validateEmailExists(Optional<Account> account, TfValidationListDto tfValidationListDto) {
        if (account.isPresent()) {
            addErrors(EMAIL_ALREADY_EXIST, tfValidationListDto);
        }
    }

    private void validateEmailSyntax(String email, TfValidationListDto tfValidationListDto) {
        boolean isValid = EmailValidator.getInstance().isValid(email);
        if (!isValid)
            addErrors(EMAIL_ERROR_SYNTAX, tfValidationListDto);
    }

    private void validatePassword(String password,TfValidationListDto tfValidationListDto) {
        boolean isCorrectSyntax = passwordValidator.validate(password);
        if(!isCorrectSyntax)
            addErrors(PASSWORD_ERROR_SYNTAX, tfValidationListDto);
    }
}
