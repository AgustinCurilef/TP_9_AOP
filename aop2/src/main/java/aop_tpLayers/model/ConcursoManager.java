package aop_tpLayers.model;

import java.util.List;

public interface ConcursoManager {
    @Log
    List<Concurso> todosLosConcursos();

    void saveConcurso(Concurso concurso);
}
