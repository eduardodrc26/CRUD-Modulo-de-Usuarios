package com.arqsoft.crudusuarios.mapper;

import com.arqsoft.crudusuarios.dto.UsuarioRequestDTO;
import com.arqsoft.crudusuarios.dto.UsuarioResponseDTO;
import com.arqsoft.crudusuarios.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public Usuario toEntity(UsuarioRequestDTO dto) {
        return Usuario.builder()
                .nombre(dto.nombre())
                .email(dto.email())
                .password(dto.password())
                .build();
    }

    public UsuarioResponseDTO toDto(Usuario entity) {
        return new UsuarioResponseDTO(
                entity.getId(),
                entity.getNombre(),
                entity.getEmail(),
                entity.getFechaCreacion()
        );
    }
}
