package aop_tpLayers.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Concurso {
    private final int idConcurso;
    private final String nombre;
    private final LocalDate fechaInicioInscripcion;
    private final LocalDate fechaFinInscripcion;

    public Concurso(int idConcurso, String nombre, LocalDate fechaInicioInscripcion, LocalDate fechaFinInscripcion) {
        this.idConcurso = idConcurso;
        this.nombre = nombre;
        this.fechaInicioInscripcion = fechaInicioInscripcion;
        this.fechaFinInscripcion = fechaFinInscripcion;
    }

    public boolean verificarFechasConcurso(LocalDate fecha) {
        return ((this.fechaInicioInscripcion.isBefore(fecha) || this.fechaInicioInscripcion.isEqual(fecha)) && (this.fechaFinInscripcion.isAfter(fecha) || this.fechaFinInscripcion.isEqual(fecha)));
    }

    public String obtenerNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return idConcurso + ", " + nombre + ", " + fechaInicioInscripcion.format(formato) +
                ", " + fechaFinInscripcion.format(formato) + "\n";
    }

    public int obtenerID() {
        return idConcurso;
    }

    public LocalDate obtenerFechaInicio() {
        return this.fechaInicioInscripcion;
    }

    public LocalDate obtenerFechaFin() {
        return this.fechaFinInscripcion;
    }
}
