/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.Usuarios;

/**
 *
 * @author UTN
 */
public class Trabajador extends Persona{
    private int cedula;
    private String puesto;
    private String horario;
    private double salario;

    public Trabajador(String puesto, String horario, double salario, int cedula, String Nombre, String Correo, int Telefono) {
        super(Nombre, Correo, Telefono);
        this.puesto = puesto;
        this.horario = horario;
        this.salario = salario;
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
