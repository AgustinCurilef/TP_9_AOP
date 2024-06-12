package aop_tpLayers.model;

import java.time.LocalDate;
import java.util.List;

public interface SistemaRadioCompetition {
    void inscribirParticipante(String nombre, String apellido, String dni, String mail, String telefono, int idconcurso);

    List<Concurso> listarConcurso();//obtiene los concursos disponibles//invertir dependencias

    void generarConcurso(int idConcurso, String nombre, LocalDate fechaInicio, LocalDate fechaFin);// crea concursos

    int obtenerIDPorNombre(String nombre);//sacar el object a string¿usar record para combo box?
}
