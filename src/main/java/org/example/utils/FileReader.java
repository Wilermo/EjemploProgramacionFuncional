package org.example.utils;

import org.example.model.Estudiante;

import java.io.*;
import java.net.CookieManager;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileReader {

    private final static String filePath = "src/main/resources/StudentsInfo.txt";

    public static List<Estudiante> loadFile(){
        List<Estudiante> estudiantes = new ArrayList<>();
        File file = new File(filePath);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals("<Estudiante>")){
                    Estudiante estudiante = new Estudiante(scanner.nextLine());
                    scanner.nextLine();
                    String noteLine = scanner.nextLine();
                    while(!noteLine.equals("</Notas>")){
                        estudiante.getNotas().add(Float.parseFloat(noteLine));
                        noteLine = scanner.nextLine();
                    }
                    estudiantes.add(estudiante);
                }
                scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + e.getMessage());
        }

        return estudiantes;
    }

    public static void saveData(List<Estudiante> estudiantes){
        File file = new File(filePath);
        List<String> lineas = new ArrayList<>();
        estudiantes.stream().flatMap(x -> {
           lineas.add("<Estudiante>");
           lineas.add(x.getNombre());
           lineas.add("<Notas>");
           lineas.addAll(x.getNotas().stream().map(Object::toString).collect(Collectors.toList()));
           lineas.add("</Notas>");
           lineas.add("</Estudiante>");
           return lineas.stream();
        }).collect(Collectors.toList());

        try{
            Files.write(Paths.get(filePath),lineas);
            //System.out.println("El archivo ha sido sobrescrito exitosamente línea por línea.");
        } catch (IOException e) {
            System.err.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
        }

    }



}
