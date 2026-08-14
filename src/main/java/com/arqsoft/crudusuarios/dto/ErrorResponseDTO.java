package com.arqsoft.crudusuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Map;

@Schema(description = "Molde estándar para devolver errores de la API, ocultando trazas internas del servidor")
public record ErrorResponseDTO(
        @Schema(description = "Momento exacto en que ocurrió el error", example = "2026-08-14T12:00:00")
        LocalDateTime timestamp,

        @Schema(description = "Código de estado HTTP", example = "400")
        int status,

        @Schema(description = "Tipo de error HTTP", example = "Bad Request")
        String error,

        @Schema(description = "Mensaje principal del error", example = "Uno o más campos tienen errores")
        String message,

        @Schema(description = "Mapa con los detalles de los campos que fallaron la validación (si aplica)",
                example = "{\"nombre\": \"El nombre solo debe contener letras y espacios\"}")
        Map<String, String> validaciones
) {
}
