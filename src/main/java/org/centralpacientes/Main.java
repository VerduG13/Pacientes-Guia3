package org.centralpacientes;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final PatientLinkedList patients = new PatientLinkedList();

    public static void main(String[] args) {
        boolean run = true;
        while (run) {
            printMenu();
            String op = sc.nextLine().trim();
            try {
                switch (op) {
                    case "1" -> addPatient();
                    case "2" -> findPatient();
                    case "3" -> updatePatient();
                    case "4" -> deletePatient();
                    case "5" -> listPatients();
                    case "0" -> run = false;
                    default -> System.out.println("Opción no válida.");
                }
            } catch (IllegalArgumentException | NoSuchElementException e) {
                System.out.println("⚠️ " + e.getMessage());
            } catch (Exception e) {
                System.out.println("⚠️ Error inesperado: " + e);
            }
            System.out.println();
        }
        System.out.println("Hasta pronto.");
    }

    private static void printMenu() {
        System.out.println("=== Central de Pacientes ===");
        System.out.println("1. Registrar paciente");
        System.out.println("2. Buscar paciente por ID");
        System.out.println("3. Actualizar paciente");
        System.out.println("4. Eliminar paciente");
        System.out.println("5. Listar pacientes");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
    }

    private static void addPatient() {
        System.out.print("ID: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Nombre: ");
        String name = sc.nextLine().trim();
        System.out.print("Edad: ");
        int age = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Clínica: ");
        String clinic = sc.nextLine().trim();

        // Puedes alternar addFirst/addLast según prefieras
        patients.addLast(new Patient(id, name, age, clinic));
        System.out.println("✅ Paciente registrado.");
    }

    private static void findPatient() {
        System.out.print("ID a buscar: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        Patient p = patients.findById(id);
        if (p == null) System.out.println("No se encontró el paciente.");
        else System.out.println(p);
    }

    private static void updatePatient() {
        System.out.print("ID a actualizar: ");
        int id = Integer.parseInt(sc.nextLine().trim());

        Patient p = patients.findById(id);
        if (p == null) throw new NoSuchElementException("No existe paciente con ID " + id);


        System.out.print("Nuevo nombre (enter para omitir): ");
        String name = sc.nextLine();
        if (name.isBlank()) name = null;

        System.out.print("Nueva edad (enter para omitir): ");
        String ageStr = sc.nextLine();
        Integer age = ageStr.isBlank() ? null : Integer.parseInt(ageStr.trim());

        System.out.print("Nueva clínica (enter para omitir): ");
        String clinic = sc.nextLine();
        if (clinic.isBlank()) clinic = null;

        patients.update(id, name, age, clinic);
        System.out.println("✅ Paciente actualizado.");
    }

    private static void deletePatient() {
        System.out.print("ID a eliminar: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        boolean ok = patients.removeById(id);
        System.out.println(ok ? "🗑️ Eliminado." : "No existía un paciente con ese ID.");
    }

    private static void listPatients() {
        List<Patient> list = patients.toList();
        if (list.isEmpty()) {
            System.out.println("(sin pacientes)");
        } else {
            list.forEach(System.out::println);
            System.out.println("Total: " + list.size());
        }
    }
}
