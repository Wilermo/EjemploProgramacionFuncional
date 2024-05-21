package org.example.controller;

import org.example.model.Estudiante;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class EstudiantesController {
    List<Estudiante> estudiantes = new ArrayList<>();

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    @Override
    public String toString() {
        return "EstudiantesController{" +
                "estudiantes=" + estudiantes +
                '}';
    }

    public Boolean hayEstudianteByName(String nombre) {
        return estudiantes.stream().anyMatch(x -> x.getNombre().equals(nombre));
    }

    public void addNotas(String nombre, List<Float> notasNumericas) {
        estudiantes.stream().filter(estudiante -> estudiante.getNombre().equals(nombre)).findFirst().ifPresent(estudiante -> estudiante.getNotas().addAll(notasNumericas));
    }

    public Double calcularPromedio(String nombreEst) {
        return estudiantes.stream().filter(estudiante -> estudiante.getNombre().equals(nombreEst)).findFirst().map(estudiante -> estudiante.getNotas().stream().map(nota -> nota.toString()).mapToDouble(Double::parseDouble).average().getAsDouble()).get();
    }

    public boolean eliminar(String nombreDel) {
        try{
            estudiantes.stream().filter(estudiante -> estudiante.getNombre().equals(nombreDel)).findFirst().ifPresent(estudiante -> estudiantes.remove(estudiante));
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
