package com.teamfinder.validators;

import com.teamfinder.client.response.TfValidationDto;
import com.teamfinder.client.response.TfValidationListDto;
import org.springframework.context.MessageSource;

import java.util.Locale;

public abstract class ValidatorsMessages {
    final Locale LOCALE = Locale.forLanguageTag("pl_PL");
    final String EMAIL_ALREADY_EXIST = "validation.email.exist";
    final String EMAIL_ERROR_SYNTAX = "validation.email.syntax";
    final String PASSWORD_ERROR_SYNTAX = "validation.password.syntax";

    private final MessageSource messageSource;

    ValidatorsMessages(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    void addErrors(String code, TfValidationListDto tfValidationListDto) {
        String errorMessage = messageSource.getMessage(code,null,LOCALE);
        tfValidationListDto.addError(new TfValidationDto(code,errorMessage));
    }

}
