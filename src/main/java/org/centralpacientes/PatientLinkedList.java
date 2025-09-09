package org.centralpacientes;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PatientLinkedList {
    private PatientNode head;
    private int size = 0;

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    /** Inserta al inicio si no existe un paciente con el mismo ID. */
    public void addFirst(Patient p) {
        if (findById(p.getId()) != null)
            throw new IllegalArgumentException("Ya existe un paciente con ID " + p.getId());
        PatientNode n = new PatientNode(p);
        n.next = head;
        head = n;
        size++;
    }

    /** Inserta al final (útil si quieres mantener orden de llegada). */
    public void addLast(Patient p) {
        if (findById(p.getId()) != null)
            throw new IllegalArgumentException("Ya existe un paciente con ID " + p.getId());
        PatientNode n = new PatientNode(p);
        if (head == null) {
            head = n;
        } else {
            PatientNode cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = n;
        }
        size++;
    }

    /** Busca por ID y devuelve la referencia al objeto Patient (o null). */
    public Patient findById(int id) {
        PatientNode cur = head;
        while (cur != null) {
            if (cur.data.getId() == id) return cur.data;
            cur = cur.next;
        }
        return null;
    }

    /** Actualiza campos básicos (nombre, edad, clínica) del paciente con el ID dado. */
    public void update(int id, String newName, Integer newAge, String newClinic) {
        Patient p = findById(id);
        if (p == null) throw new NoSuchElementException("No existe paciente con ID " + id);
        if (newName != null && !newName.isBlank()) p.setName(newName);
        if (newAge != null) p.setAge(newAge);
        if (newClinic != null && !newClinic.isBlank()) p.setClinic(newClinic);
    }

    /** Elimina por ID. Devuelve true si eliminó, false si no existía. */
    public boolean removeById(int id) {
        if (head == null) return false;
        if (head.data.getId() == id) {
            head = head.next;
            size--;
            return true;
        }
        PatientNode prev = head;
        PatientNode cur = head.next;
        while (cur != null) {
            if (cur.data.getId() == id) {
                prev.next = cur.next;
                size--;
                return true;
            }
            prev = cur;
            cur = cur.next;
        }
        return false;
    }

    /** Lista inmutable de pacientes (para imprimir o reportar). */
    public List<Patient> toList() {
        List<Patient> out = new ArrayList<>();
        PatientNode cur = head;
        while (cur != null) {
            out.add(cur.data);
            cur = cur.next;
        }
        return out;
    }
}
