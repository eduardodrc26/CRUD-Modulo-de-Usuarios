package com.arqsoft.crudusuarios.service;

import com.arqsoft.crudusuarios.dto.UsuarioPatchDTO;
import com.arqsoft.crudusuarios.dto.UsuarioRequestDTO;
import com.arqsoft.crudusuarios.dto.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {
    UsuarioResponseDTO crear(UsuarioRequestDTO request);
    UsuarioResponseDTO obtenerPorId(Long id);
    List<UsuarioResponseDTO> obtenerTodos();
    UsuarioResponseDTO actualizar(Long id, UsuarioRequestDTO request);
    void eliminar(Long id);
    UsuarioResponseDTO actualizarParcial(Long id, UsuarioPatchDTO request);
}
