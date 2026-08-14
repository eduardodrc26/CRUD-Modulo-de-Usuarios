package com.arqsoft.crudusuarios.dto;

import java.time.LocalDateTime;

public record UsuarioResponseDTO(
        Long id,
        String nombre,
        String email,
        LocalDateTime fechaCreacion
) {
}
