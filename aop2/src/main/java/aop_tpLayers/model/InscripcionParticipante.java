package aop_tpLayers.model;
public interface InscripcionParticipante {
    @Log
    void saveInscription(Participante participante);
}
