package org.centralpacientes;

public class Patient {
    private final int id;     // Identificador único
    private String name;
    private int age;
    private String clinic;

    public Patient(int id, String name, int age, String clinic) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.clinic = clinic;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getClinic() { return clinic; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setClinic(String clinic) { this.clinic = clinic; }

    @Override
    public String toString() {
        return "Patient{id=" + id + ", name='" + name + '\'' +
                ", age=" + age + ", clinic='" + clinic + '\'' + '}';
    }
}
