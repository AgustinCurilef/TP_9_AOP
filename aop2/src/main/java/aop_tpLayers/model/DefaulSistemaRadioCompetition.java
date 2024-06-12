package aop_tpLayers.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DefaulSistemaRadioCompetition implements SistemaRadioCompetition {
    InscripcionParticipante inscripcionParticipante;
    ConcursoManager concursoManager;

    public DefaulSistemaRadioCompetition(InscripcionParticipante inscripcionParticipante, ConcursoManager concursoManager) {
        this.inscripcionParticipante = inscripcionParticipante;
        this.concursoManager = concursoManager;
    }

    @Override
    public void inscribirParticipante(String nombre, String apellido, String dni, String mail, String telefono, int idconcurso) {
        if (validaciones(nombre, apellido, dni, mail, telefono, idconcurso)) {
            var participante = new Participante(nombre, apellido, dni, mail, telefono, idconcurso);
            inscripcionParticipante.saveInscription(participante);
        }

    }

    ///modifcar
    private boolean validaciones(String nombre, String apellido, String dni, String mail, String telefono, int idconcurso) {
        if (nombre.isEmpty()) {
            throw new RuntimeException("Nombre no puede ser vacio");
        }
        if (apellido.isEmpty()) {
            throw new RuntimeException("apellido no puede ser vacio");
        }
        if (dni.isEmpty()) {
            throw new RuntimeException("dni no puede ser vacio");
        }
        if (!checkEmail(mail)) {
            throw new RuntimeException("email debe ser válido debe ingresarse de la siguiente forma, por ejemplo: ejemplo@email.com");
        }
        if (!checkPhone(telefono)) {
            throw new RuntimeException("El teléfono debe ingresarse de la siguiente forma: NNNN-NNNNNN");
        }
        if (idconcurso < 0) {
            throw new RuntimeException("Debe elegir un Concurso");
        }
        return true;
    }

    private boolean checkEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    private boolean checkPhone(String telefono) {
        String regex = "\\d{4}-\\d{6}";
        return telefono.matches(regex);
    }

    @Override
    public List<Concurso> listarConcurso() {
        List<Concurso> todosConcursos = concursoManager.todosLosConcursos();
        List<Concurso> concursosActuales = new ArrayList<>();
        for (Concurso concurso : todosConcursos) {
            if (concurso.verificarFechasConcurso(LocalDate.now())) {
                concursosActuales.add(concurso);
            }
        }
        return concursosActuales;
    }

    @Override
    public void generarConcurso(int idConcurso, String nombre, LocalDate fechaInicio, LocalDate fechaFin) {
        var concurso = new Concurso(idConcurso, nombre, fechaInicio, fechaFin);
        concursoManager.saveConcurso(concurso);

    }

    public int obtenerIDPorNombre(String nombre) {
        List<Concurso> todosConcursos = concursoManager.todosLosConcursos();
        for (Concurso concurso : todosConcursos) {
            if (concurso.obtenerNombre().equals(nombre)) {
                return concurso.obtenerID();
            }
        }
        return -1;
    }

}
