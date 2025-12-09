/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Mapper;

import Modelo.Dto.UsuarioDTO;
import Modelo.Usuarios.Usuarios; 
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author isaac
 */
public class UsuariosMapper {
    public static UsuarioDTO toDTO(Usuarios entidad) {
        if (entidad == null) return null;
        
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(entidad.getIdUsuario());
        dto.setNombreUsuario(entidad.getNombreUsuario());
        dto.setRol(entidad.getRol());
        dto.setEstado(entidad.getEstado());
        return dto;
    }

    public static List<UsuarioDTO> toDTOList(List<Usuarios> entidades) {
        return entidades.stream()
                .map(UsuariosMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static Usuarios toEntidad(UsuarioDTO dto, String contrasenaHash) {
        if (dto == null) return null;

        Usuarios entidad = new Usuarios(
            dto.getIdUsuario(),
            dto.getNombreUsuario(),
            contrasenaHash, 
            dto.getRol(),
            dto.getEstado()
        );

        return entidad;
    }
}