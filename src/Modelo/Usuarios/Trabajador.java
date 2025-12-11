/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Usuarios;

public class Trabajador {
    private String cedula;
    private String puesto;
    private String horario;
    private double salario;
    private String nombre; 
    private String correo; 
    private String telefono; 
    private String contrasena; 

   
    public Trabajador(String cedula, String puesto, String horario, double salario, 
                     String nombre, String correo, String telefono) {
        this.cedula = cedula;
        this.puesto = puesto;
        this.horario = horario;
        this.salario = salario;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        // No asignamos contraseña aquí
    }

    // Constructor con contraseña (solo para inserción/autenticación)
    public Trabajador(String cedula, String puesto, String horario, double salario,
                     String nombre, String correo, String telefono, String contrasena) {
        this.cedula = cedula;
        this.puesto = puesto;
        this.horario = horario;
        this.salario = salario;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.contrasena = contrasena;
    }

    public Trabajador() {
        super();
    }

    // Getters y Setters (actualizar nombres)
    public String getPuesto() {
        return puesto;
    }

    public String getHorario() {
        return horario;
    }

    public double getSalario() {
        return salario;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getContrasena() {
        return contrasena; // Solo usar internamente, nunca exponer
    }
}
