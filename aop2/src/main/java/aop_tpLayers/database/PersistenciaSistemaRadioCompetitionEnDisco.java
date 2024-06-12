package aop_tpLayers.database;

import aop_tpLayers.model.Concurso;
import aop_tpLayers.model.ConcursoManager;
import aop_tpLayers.model.InscripcionParticipante;
import aop_tpLayers.model.Participante;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PersistenciaSistemaRadioCompetitionEnDisco implements InscripcionParticipante, ConcursoManager {
    private final String urlInscripcion;
    private final String urlConcurso;

    public PersistenciaSistemaRadioCompetitionEnDisco(String urlInscripcion, String urlConcurso) {

        this.urlInscripcion = Objects.requireNonNull(urlInscripcion);
        this.urlConcurso = Objects.requireNonNull(urlConcurso);
    }

    @Override
    public void saveInscription(Participante participante) {
        if (!Files.exists(Paths.get(this.urlInscripcion))) {
            throw new RuntimeException("No se puedo encontrar el archivo en disco");
        }
        try {
            Files.write(Paths.get(this.urlInscripcion), participante.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("No se puedo Inscribir en disco", e);
        }


    }

    @Override
    public List<Concurso> todosLosConcursos() {
        List<Concurso> concursos = new ArrayList<>();
        try {
            // Leer todas las líneas del archivo
            List<String> lineas = Files.readAllLines(Paths.get(this.urlConcurso));
            for (String linea : lineas) {
                String[] partes = linea.split(", ");
                String idConcurso = partes[0];
                String nombre = partes[1];
                String fechaInicio = partes[2];
                String fechaFin = partes[3];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                var concurso = new Concurso(Integer.parseInt(idConcurso), nombre, LocalDate.parse(fechaInicio, formatter), LocalDate.parse(fechaFin, formatter));
                concursos.add(concurso);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo de empleados", e);
        }
        return concursos;
    }

    @Override
    public void saveConcurso(Concurso concurso) {
        if (!Files.exists(Paths.get(this.urlConcurso))) {
            throw new RuntimeException("No se puedo encontrar el archivo en disco");
        }
        try {
            Files.write(Paths.get(this.urlConcurso), concurso.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("No se puedo Inscribir en disco", e);
        }
    }

}
