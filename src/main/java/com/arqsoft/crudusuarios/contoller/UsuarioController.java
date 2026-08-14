package com.arqsoft.crudusuarios.contoller;

import com.arqsoft.crudusuarios.dto.UsuarioPatchDTO;
import com.arqsoft.crudusuarios.dto.UsuarioRequestDTO;
import com.arqsoft.crudusuarios.dto.UsuarioResponseDTO;
import com.arqsoft.crudusuarios.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crear(@Valid @RequestBody UsuarioRequestDTO request) {
        UsuarioResponseDTO nuevoUsuario = usuarioService.crear(request);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(usuarioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequestDTO request) {
        return ResponseEntity.ok(usuarioService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actulizarParcial(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioPatchDTO request
            ){
        return ResponseEntity.ok(usuarioService.actualizarParcial(id, request));
    }
}
