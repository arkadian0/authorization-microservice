package com.teamfinder.client;

import com.teamfinder.client.response.TfValidationListDto;
import com.teamfinder.client.transfer.RegistrationCommand;
import com.teamfinder.domain.authorization.ports.incoming.UserAuthorizationCommandPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/register")
@RequiredArgsConstructor
public class UserRegistrationController {

    private final UserAuthorizationCommandPort userAuthorizationCommandPort;

    @PostMapping
    public ResponseEntity<TfValidationListDto> registerNotConfirmedUser(@RequestBody RegistrationCommand registrationCommand) {
        TfValidationListDto tfValidationListDto = userAuthorizationCommandPort.createNotConfirmedUser(registrationCommand);
        return TfValidationListDto.getPostResponse(tfValidationListDto);
    }

    @GetMapping(value = "/confirm-account")
    public ResponseEntity<String> confirmUserAccount(@RequestParam("token") String confirmationToken) {
        userAuthorizationCommandPort.confirmUser(confirmationToken);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("User confirmed");

    }
    @GetMapping(value = "/check-user-exist/{email}")
    public ResponseEntity<Boolean> checkEmailExists(@PathVariable String email) {
        boolean isExist = userAuthorizationCommandPort.checkEmailExist(email);
        return ResponseEntity.ok(isExist);

    }
    @GetMapping(value = "/check-user-enabled/{email}")
    public ResponseEntity<Boolean> checkUserEnabled(@PathVariable String email) {
        boolean isEnabled = userAuthorizationCommandPort.checkUserIsEnabled(email);
        return ResponseEntity.ok(isEnabled);

    }
}
