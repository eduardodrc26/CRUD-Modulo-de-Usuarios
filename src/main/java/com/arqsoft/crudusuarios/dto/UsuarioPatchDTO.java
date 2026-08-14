package com.arqsoft.crudusuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UsuarioPatchDTO (
        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El nombre solo debe contener letras y espacios")
        String nombre,

        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", message = "El apellido solo debe contener letras y espacios")
        String apellido,

        @Email(message = "Formato de email inválido")
        String email,

        @Pattern(regexp = "^\\d{10}$", message = "El teléfono debe tener exactamente 10 dígitos numéricos")
        String telefono,

        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
        String password
){
}
