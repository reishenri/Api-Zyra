package com.zyra.api.service;

import com.zyra.api.dto.AuthRequestDTO;
import com.zyra.api.dto.AuthResponseDTO;
import com.zyra.api.dto.RegisterRequestDTO;
import com.zyra.api.model.User;
import com.zyra.api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthResponseDTO register(RegisterRequestDTO request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(request.password());
        user.setAddress(request.address());
        user.setCity(request.city());
        user.setState(request.state());
        user.setZipCode(request.zipCode());
        user.setPlan(request.plan());

        User saved = userRepository.save(user);

        return new AuthResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                null
        );
    }

    public AuthResponseDTO login(AuthRequestDTO request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if (!user.getPassword().equals(request.password())) {
            throw new IllegalArgumentException("Senha inválida");
        }

        return new AuthResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                null
        );
    }
}