/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Usuarios;

/**
 *
 * @author UTN
 */
public class Trabajador {
    private String cedula;
    private String puesto;
    private String horario;
    private double salario;
    private String Nombre;
    private String Correo;
    private String Telefono;

    public Trabajador(String cedula, String puesto, String horario, double salario, String Nombre, String Correo, String Telefono) {
        this.cedula = cedula;
        this.puesto = puesto;
        this.horario = horario;
        this.salario = salario;
        this.Nombre = Nombre;
        this.Correo = Correo;
        this.Telefono = Telefono;
    }


   public Trabajador() {
        super();
    }
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
        return Nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
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

    
    
}
