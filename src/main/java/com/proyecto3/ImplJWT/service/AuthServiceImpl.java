package com.proyecto3.ImplJWT.service;

import com.proyecto3.ImplJWT.config.JwtService;
import com.proyecto3.ImplJWT.controller.models.AuthResponse;
import com.proyecto3.ImplJWT.controller.models.AuthenticationRequest;
import com.proyecto3.ImplJWT.controller.models.RegisterRequest;
import com.proyecto3.ImplJWT.entity.Role;
import com.proyecto3.ImplJWT.entity.User;
import com.proyecto3.ImplJWT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken).build();
    }

    @Override
    public AuthResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findUserByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthResponse update(RegisterRequest request) {

        //Obtenemos el usuario autenticado actual
        User authenticatedUser = getCurrentAuthenticatedUser();

        //Verificamos que el usuario que intenta actualizar sea el mismo que el autenticado o sea un ADMIN
        if (!authenticatedUser.getEmail().equals(request.getEmail()) && !authenticatedUser.getRole().equals(Role.ADMIN)) {
            throw new RuntimeException("No tienes permisos para actualizar este usuario");
        }

        //Buscamos al user, si no lo encuentra se realiza una exception con el mensaje "Usuario no encontrado"
        var user = userRepository.findUserByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Actualizamos los campos del usuario según la solicitud
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getLastName()));

        userRepository.save(user);

        // Generamos un nuevo token JWT después de la actualización
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwtToken).build();
    }

    private User getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }
        throw new RuntimeException("Usuario no autenticado");
    }

    @Override
    public void delete(Long userId) {
        //Obtenemos el usuario autenticado actual
        User authenticatedUser = getCurrentAuthenticatedUser();

        //Verificamos que el usuario que intenta eliminar sea un ADMIN
        if (!authenticatedUser.getRole().equals(Role.ADMIN)) {
            throw new RuntimeException("No tienes permisos para eliminar este usuario");
        }

        // Verificar si el usuario no existe lanza una excepción, si no se guarda en userToDelete
        User userToDelete = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("Usuario no encontrado"));

        //Si existe y esta almacenado en userToDelete lo borramos
        userRepository.delete(userToDelete);
    }
}
