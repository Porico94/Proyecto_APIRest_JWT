package com.proyecto3.ImplJWT.service;

import com.proyecto3.ImplJWT.controller.models.AuthResponse;
import com.proyecto3.ImplJWT.controller.models.AuthenticationRequest;
import com.proyecto3.ImplJWT.controller.models.RegisterRequest;


public interface AuthService {

    AuthResponse register (RegisterRequest request);

    AuthResponse authenticate (AuthenticationRequest request);

    AuthResponse update(RegisterRequest request);

    void delete(Long userId);


}
