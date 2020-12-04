package com.teamfinder.client.response;

import com.google.common.collect.Lists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TfValidationListDto {
    private List<TfValidationDto> errors = Lists.newArrayList();

    public TfValidationListDto() {
    }

    public TfValidationListDto(List<TfValidationDto> errors) {
        this.errors = errors;
    }

    public List<TfValidationDto> getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return !getErrors().isEmpty();
    }

    public void addError(TfValidationDto error) {
        if (!containsMessage(error.getMessage())) {
            getErrors().add(error);
        }
    }

    public void sortErrors(){
        this.errors = this.errors.stream()
                .sorted(Comparator.comparing(TfValidationDto::getMessage))
                .collect(Collectors.toList());
    }

    private boolean containsMessage(String message) {
        Set<String> messages = getErrors().stream().map(TfValidationDto::getMessage).collect(Collectors.toSet());
        return messages.contains(message);
    }

    public static ResponseEntity<TfValidationListDto> getPostResponse(TfValidationListDto result) {
        return getResponse(result, HttpStatus.CREATED);
    }

    public static ResponseEntity<TfValidationListDto> getPutResponse(TfValidationListDto result) {
        return getResponse(result, HttpStatus.OK);
    }

    private static ResponseEntity<TfValidationListDto> getResponse(TfValidationListDto result, HttpStatus status) {
        if (result.getErrors().isEmpty()) {
            return new ResponseEntity<>(result, status);
        }

        return new ResponseEntity<>(result, HttpStatus.NOT_ACCEPTABLE);
    }
}



