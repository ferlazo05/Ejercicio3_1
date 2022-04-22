package com.example.ejercicio3_1;

public class ListEmployees {
    public String nombre;
    public String puesto;
    public String apellido;

    public ListEmployees(String apellido, String nombre, String puesto) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.puesto = puesto;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }


}
