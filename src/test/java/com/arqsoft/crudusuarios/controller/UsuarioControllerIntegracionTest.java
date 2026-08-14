package com.arqsoft.crudusuarios.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UsuarioControllerIntegracionTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void deberiaRechazarCreacionConDatosInvalidos() throws Exception {
        String jsonRequest = """
                {
                    "nombre": "Eduardo123",
                    "email": "eduardo@arqsoft.com",
                    "telefono": "123",
                    "password": "passwordSeguro123"
                }
                """;

        mockMvc.perform(post("/api/v1/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.validaciones.nombre").exists())
                .andExpect(jsonPath("$.validaciones.apellido").exists())
                .andExpect(jsonPath("$.validaciones.telefono").exists());
    }
}
