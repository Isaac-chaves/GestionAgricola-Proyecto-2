/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author UTN
 */
public abstract class Persona {
    private String Nombre;
    private String Correo;
    private int  Telefono;

    public Persona(String Nombre, String Correo, int Telefono) {
        this.Nombre = Nombre;
        this.Correo = Correo;
        this.Telefono = Telefono;
    }
    
    
    public Persona() {
        super();
    }

    public String getNombre() {
        return Nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public int getTelefono() {
        return Telefono;
    }


    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public void setTelefono(int Telefono) {
        this.Telefono = Telefono;
    }
    
    
}
