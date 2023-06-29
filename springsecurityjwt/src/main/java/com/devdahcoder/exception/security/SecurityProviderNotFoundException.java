package com.devdahcoder.exception.security;

import java.nio.file.ProviderNotFoundException;

public class SecurityProviderNotFoundException extends ProviderNotFoundException {

    public SecurityProviderNotFoundException(String msg) {
        super(msg);
    }

}
