package com.arqsoft.crudusuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Objeto de transferencia para la creación y actualización completa de usuarios")
public record UsuarioRequestDTO(
        @Schema(description = "Nombre(s) del usuario", example="Eduardo")
        @NotBlank(message = "El nombre no puede estar vacío")
        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre solo debe contener letras y espacios")
        String nombre,

        @Schema(description = "Apellido(s) del usuario", example = "De Rosas Carlos")
        @NotBlank(message = "El apellido no puede estar vacío")
        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El apellido solo debe contener letras y espacios")
        String apellido,

        @Schema(description = "Correo electrónico corporativo o personal", example = "eduardo@gmail.com")
        @NotBlank(message = "El email es obligatorio")
        @Email(message = "Formato de email inválido")
        String email,

        @Schema(description = "Número telefónico de contacto a 10 dígitos", example = "5551234567")
        @NotBlank(message = "El teléfono es obligatorio")
        @Pattern(regexp = "^\\d{10}$", message = "El teléfono debe tener exactamente 10 dígitos numéricos")
        String telefono,

        @Schema(description = "Contraseña segura (mínimo 8 caracteres)", example = "PasswordSeguro123!")
        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
        String password
) {
}
