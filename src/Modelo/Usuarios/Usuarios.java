/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Usuarios;

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
 
   
 public Usuarios() {
        super();
    }

public Usuarios(int idUsuario, String nombreUsuario, String contraseña, String rol, String estado) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.rol = rol;
        this.estado = estado;
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
  public boolean esAdministrador() {
        return "administrador".equalsIgnoreCase(this.rol);
    }
  
    public boolean esUsuarioNormal() {
        return "usuario".equalsIgnoreCase(this.rol);
    }


}
