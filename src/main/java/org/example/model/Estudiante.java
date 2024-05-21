package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Estudiante {
    private String nombre;
    private List<Float> notas;

    public Estudiante(String nombre) {
        this.nombre = nombre;
        this.notas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Float> getNotas() {
        return notas;
    }

    public void setNotas(List<Float> notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "nombre='" + nombre + '\'' +
                ", notas=" + notas +
                '}';
    }
}
