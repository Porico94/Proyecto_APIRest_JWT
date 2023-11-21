package com.proyecto3.ImplJWT.controller;

import com.proyecto3.ImplJWT.controller.models.AuthResponse;
import com.proyecto3.ImplJWT.controller.models.AuthenticationRequest;
import com.proyecto3.ImplJWT.controller.models.RegisterRequest;
import com.proyecto3.ImplJWT.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PutMapping("/update")
    public ResponseEntity<AuthResponse> update(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.update(request));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> delete(@PathVariable Long userId){
        authService.delete(userId);
        return ResponseEntity.ok("Usuario eliminado exitosamente");
    }
}
