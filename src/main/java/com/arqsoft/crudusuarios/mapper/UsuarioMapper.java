package com.arqsoft.crudusuarios.mapper;

import com.arqsoft.crudusuarios.dto.UsuarioRequestDTO;
import com.arqsoft.crudusuarios.dto.UsuarioResponseDTO;
import com.arqsoft.crudusuarios.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioMapper {

    private final PasswordEncoder passwordEncoder;

    public Usuario toEntity(UsuarioRequestDTO dto) {
        return Usuario.builder()
                .nombre(dto.nombre())
                .apellido(dto.apellido())
                .email(dto.email())
                .telefono(dto.telefono())
                .password(passwordEncoder.encode(dto.password()))
                .build();
    }

    public UsuarioResponseDTO toDto(Usuario entity) {
        return new UsuarioResponseDTO(
                entity.getId(),
                entity.getNombre(),
                entity.getApellido(),
                entity.getEmail(),
                entity.getTelefono(),
                entity.getFechaCreacion()
        );
    }
}
