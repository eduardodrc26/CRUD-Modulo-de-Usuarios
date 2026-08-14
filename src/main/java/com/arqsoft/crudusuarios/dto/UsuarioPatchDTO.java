package com.arqsoft.crudusuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Objeto de transferencia para la actualización parcial de usuarios (los campos nulos se ignoran)")
public record UsuarioPatchDTO (
        @Schema(description = "Nombre(s) del usuario", example = "Eduardo", nullable = true)
        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre solo debe contener letras y espacios")
        String nombre,

        @Schema(description = "Apellido(s) del usuario", example = "De Rosas", nullable = true)
        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El apellido solo debe contener letras y espacios")
        String apellido,

        @Schema(description = "Correo electrónico corporativo o personal", example = "eduardo.nuevo@gmail.com", nullable = true)
        @Email(message = "Formato de email inválido")
        String email,

        @Schema(description = "Número telefónico de contacto a 10 dígitos", example = "5559998888", nullable = true)
        @Pattern(regexp = "^\\d{10}$", message = "El teléfono debe tener exactamente 10 dígitos numéricos")
        String telefono,

        @Schema(description = "Nueva contraseña segura", example = "NuevoPass456!", nullable = true)
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
        String password
){
}
