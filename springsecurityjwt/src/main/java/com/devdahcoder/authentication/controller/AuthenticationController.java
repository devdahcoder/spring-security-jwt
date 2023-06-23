package com.devdahcoder.authentication.controller;

import com.devdahcoder.authentication.model.AuthenticateUserModel;
import com.devdahcoder.authentication.model.AuthenticationResponseModel;
import com.devdahcoder.authentication.service.AuthenticationService;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {

        this.authenticationService = authenticationService;

    }

    @PostMapping("/authenticate")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<AuthenticationResponseModel> authenticateUser(@RequestBody @Valid @NotNull AuthenticateUserModel authenticateUserModel) {

        return new ResponseEntity<>(authenticationService.authenticateUser(authenticateUserModel), HttpStatus.OK);

    }

}
