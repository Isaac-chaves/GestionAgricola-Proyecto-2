/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Usuarios;

import java.util.HashMap;

/**
 *
 * @author UTN
 */
public class Usuarios {
   private int idUsuario;
    private String nombreUsuario;
    private String contraseña;
    private String rol; 
    private String estado;
    private static HashMap<String, String> contraseñasMap = new HashMap<>();
   
 public Usuarios() {
        super();
    }

public Usuarios(int idUsuario, String nombreUsuario, String contraseña, String rol, String estado) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.rol = rol;
        this.estado = estado;
        contraseñasMap.put(nombreUsuario, contraseña);
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getRol() {
        return rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public void setContraseña(String nuevaContraseña) {
        this.contraseña = nuevaContraseña;
        contraseñasMap.put(this.nombreUsuario, nuevaContraseña);
    }
public boolean esAdministrador() {
        return "administrador".equalsIgnoreCase(this.rol);
    }
  
public boolean esUsuarioNormal() {
    return "usuario".equalsIgnoreCase(this.rol);
} 
    
    
public static void guardarContraseña(String nombreUsuario, String contraseña) {
        contraseñasMap.put(nombreUsuario, contraseña);
    }

 public static String obtenerContraseña(String nombreUsuario) {
        return contraseñasMap.get(nombreUsuario);
    }
 
  public static boolean existeUsuario(String nombreUsuario) {
        return contraseñasMap.containsKey(nombreUsuario);
    }
  
  
    public static boolean verificarContraseña(String nombreUsuario, String contraseña) {
        String contraseñaAlmacenada = contraseñasMap.get(nombreUsuario);
        return contraseñaAlmacenada != null && contraseñaAlmacenada.equals(contraseña);
    }
    
public static HashMap<String, String> getContraseñasMap() {
        return new HashMap<>(contraseñasMap);
    }
}
