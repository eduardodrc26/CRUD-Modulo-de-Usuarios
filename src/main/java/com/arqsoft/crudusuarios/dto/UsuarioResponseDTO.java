package com.arqsoft.crudusuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Objeto de transferencia que devuelve los datos del usuario omitiendo información sensible (contraseña)")
public record UsuarioResponseDTO(
        @Schema(description = "ID único del usuario generado por la base de datos", example = "1")
        Long id,

        @Schema(description = "Nombre(s) del usuario", example = "Eduardo")
        String nombre,

        @Schema(description = "Apellido(s) del usuario", example = "De Rosas")
        String apellido,

        @Schema(description = "Correo electrónico", example = "eduardo@gmail.com")
        String email,

        @Schema(description = "Número telefónico a 10 dígitos", example = "5551234567")
        String telefono,

        @Schema(description = "Fecha y hora en que se registró el usuario", example = "2026-08-14T10:30:00")
        LocalDateTime fechaCreacion
) {
}
