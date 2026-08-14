package com.arqsoft.crudusuarios.service;

import com.arqsoft.crudusuarios.dto.UsuarioPatchDTO;
import com.arqsoft.crudusuarios.dto.UsuarioRequestDTO;
import com.arqsoft.crudusuarios.dto.UsuarioResponseDTO;
import com.arqsoft.crudusuarios.entity.Usuario;
import com.arqsoft.crudusuarios.exception.ResourceAlreadyExistsException;
import com.arqsoft.crudusuarios.exception.ResourceNotFoundException;
import com.arqsoft.crudusuarios.mapper.UsuarioMapper;
import com.arqsoft.crudusuarios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UsuarioResponseDTO crear(UsuarioRequestDTO request) {
        if (usuarioRepository.existsByEmail(request.email())) {
            throw new ResourceAlreadyExistsException("El email ya está registrado");
        }
        Usuario usuario = usuarioMapper.toEntity(request);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponseDTO obtenerPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));
        return usuarioMapper.toDto(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> obtenerTodos() {
        return usuarioRepository.findAll().stream()
                .map(usuarioMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public UsuarioResponseDTO actualizar(Long id, UsuarioRequestDTO request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));

        if (!usuario.getEmail().equals(request.email()) && usuarioRepository.existsByEmail(request.email())) {
            throw new ResourceAlreadyExistsException("El email ya está en uso por otro usuario");
        }

        usuario.setNombre(request.nombre());
        usuario.setApellido(request.apellido());
        usuario.setEmail(request.email());
        usuario.setTelefono(request.telefono());
        usuario.setPassword(passwordEncoder.encode(request.password()));

        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO actualizarParcial(Long id, UsuarioPatchDTO request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + id));

        if (request.nombre() != null) {
            usuario.setNombre(request.nombre());
        }

        if (request.apellido() != null) {
            usuario.setApellido(request.apellido());
        }

        if (request.email() != null) {
            if (!usuario.getEmail().equals(request.email()) && usuarioRepository.existsByEmail(request.email())) {
                throw new ResourceAlreadyExistsException("El email ya está en uso por otro usuario");
            }
            usuario.setEmail(request.email());
        }

        if (request.telefono() != null) {
            usuario.setTelefono(request.telefono());
        }

        if (request.password() != null) {
            usuario.setPassword(passwordEncoder.encode(request.password()));
        }

        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }
}

