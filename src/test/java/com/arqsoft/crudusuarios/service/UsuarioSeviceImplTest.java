package com.arqsoft.crudusuarios.service;

import com.arqsoft.crudusuarios.dto.UsuarioPatchDTO;
import com.arqsoft.crudusuarios.dto.UsuarioRequestDTO;
import com.arqsoft.crudusuarios.dto.UsuarioResponseDTO;
import com.arqsoft.crudusuarios.entity.Usuario;
import com.arqsoft.crudusuarios.exception.ResourceAlreadyExistsException;
import com.arqsoft.crudusuarios.mapper.UsuarioMapper;
import com.arqsoft.crudusuarios.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioSeviceImplTest {
    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    private Usuario usuario;
    private UsuarioRequestDTO requestDTO;
    private UsuarioResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        usuario = Usuario.builder()
                .id(1L)
                .nombre("Eduardo")
                .apellido("De Rosas")
                .email("eduardo@gmail.com")
                .telefono("5551234567")
                .password("hashedPassword")
                .fechaCreacion(LocalDateTime.now())
                .build();

        requestDTO = new UsuarioRequestDTO("Eduardo", "De Rosas", "eduardo@gmail.com", "5551234567", "password123");
        responseDTO = new UsuarioResponseDTO(1L, "Eduardo", "De Rosas", "eduardo@gmail.com", "5551234567", LocalDateTime.now());
    }

    @Test
    void crear_UsuarioExitoso() {
        when(usuarioRepository.existsByEmail(requestDTO.email())).thenReturn(false);
        when(usuarioMapper.toEntity(requestDTO)).thenReturn(usuario);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        when(usuarioMapper.toDto(usuario)).thenReturn(responseDTO);

        UsuarioResponseDTO resultado = usuarioService.crear(requestDTO);

        assertNotNull(resultado);
        assertEquals("eduardo@gmail.com", resultado.email());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void actualizarParcial_SoloTelefonoExitoso() {
        UsuarioPatchDTO patchDTO = new UsuarioPatchDTO(null, null, null, "5559998888", null);
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        when(usuarioMapper.toDto(usuario)).thenReturn(responseDTO);

        UsuarioResponseDTO resultado = usuarioService.actualizarParcial(1L, patchDTO);

        assertNotNull(resultado);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
        verify(passwordEncoder, never()).encode(anyString());
    }

    @Test
    void actualizar_LanzaExcepcionCuandoEmailYaExiste() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.existsByEmail("nuevo@correo.com")).thenReturn(true);

        UsuarioRequestDTO requestActualizacion = new UsuarioRequestDTO("Edu", "Rosas", "nuevo@correo.com", "5551234567", "pass12345");

        assertThrows(ResourceAlreadyExistsException.class, () -> usuarioService.actualizar(1L, requestActualizacion));
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }
}
