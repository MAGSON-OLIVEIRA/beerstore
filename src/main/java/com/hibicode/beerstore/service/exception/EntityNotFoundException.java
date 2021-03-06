package com.hibicode.beerstore.service.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException() {
        super("not-found", HttpStatus.NOT_FOUND);
    }
}
