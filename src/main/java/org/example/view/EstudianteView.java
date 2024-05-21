package org.example.view;

import org.example.controller.EstudiantesController;
import org.example.model.Estudiante;
import org.example.utils.FileReader;

import javax.imageio.stream.FileImageInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class EstudianteView {

    EstudiantesController estudiantesController = new EstudiantesController();

    public void launchMenu() {
        System.out.println("Bienvenido a la gestión de estudiantes");
        estudiantesController.setEstudiantes(FileReader.loadFile());
        System.out.println(estudiantesController.getEstudiantes().toString());
        Consumer<Estudiante> estudianteConsumer = estudiante -> System.out.println(estudiante.getNombre());
        Consumer<Estudiante> estudianteynotasConsumer = estudiante -> System.out.println(estudiante.getNombre() + estudiante.getNotas().toString());
        Scanner in = new Scanner(System.in);
        int opcion =0;
        do {

            mostrarMenu();
            opcion = in.nextInt();
            switch (opcion) {
                case 0:
                    estudiantesController.getEstudiantes().stream().forEach(x -> estudianteynotasConsumer.accept(x));
                    break;
                case 1:
                    System.out.println("Digite el nombre");
                    Estudiante est =  new Estudiante(in.next());
                    estudiantesController.getEstudiantes().add(est);
                    System.out.println("Estudiante Añadido");
                    break;
                case 2:
                    System.out.println("Digite el nombre del estudiante");
                    estudiantesController.getEstudiantes().stream().forEach(x -> estudianteConsumer.accept(x));
                    String nombre = in.next();
                    if(estudiantesController.hayEstudianteByName(nombre)){
                        System.out.println("Digite las notas separadas por ';' (Ejemplo: 5;4,5;3)");
                        String notas = in.next();
                        List<Float> notasNumericas = Arrays.stream(notas.split(";")).map(x -> Float.parseFloat(x)).collect(Collectors.toList());
                        estudiantesController.addNotas(nombre,notasNumericas);
                    }else{
                        System.out.println("Estudiante no encontrado");
                    }
                    break;
                case 3:
                    in.nextLine();
                    System.out.println("Digite el nombre del estudiante");
                    estudiantesController.getEstudiantes().stream().forEach(x -> estudianteConsumer.accept(x));
                    String nombreEst = in.nextLine();
                    if(estudiantesController.hayEstudianteByName(nombreEst)){
                        System.out.println(estudiantesController.calcularPromedio(nombreEst));
                    }else{
                        System.out.println("Estudiante no encontrado");
                    }
                    break;
                case 4:
                    in.nextLine();
                    System.out.println("Digite el nombre del estudiante");
                    estudiantesController.getEstudiantes().stream().forEach(x -> estudianteConsumer.accept(x));
                    String nombreDel = in.nextLine();
                    if(estudiantesController.hayEstudianteByName(nombreDel)){
                        System.out.println(estudiantesController.eliminar(nombreDel));
                    }else{
                        System.out.println("Estudiante no encontrado");
                    }
                    break;
                case -1:
                    System.out.println("Muchas gracias!");
                    break;
                default:
                    System.out.println("La opción elegida no es válida.");
            }
        }
        while (opcion!=-1);
        FileReader.saveData(estudiantesController.getEstudiantes());

    }

    private void mostrarMenu() {

        System.out.println("Seleccione una opción: ");
        System.out.println("0. Ver Estudiantes");
        System.out.println("1. Añadir estudiante");
        System.out.println("2. Añadir nota a estudiante");
        System.out.println("3. Calcular promedio de estudiante");
        System.out.println("4. Eliminar estudiante");
        System.out.println("-1. Salir");
    }
}
