package aop_tpLayers.model;

public record Participante(String nombre, String apellido, String dni, String telefono, String email, int idConcurso) {
    @Override
    public String toString() {
        //apellido, nombre, teléfono, email, idconcurso
        return apellido + ", " + nombre + ", " + telefono + ", " + email + ", " + idConcurso + "\n";
    }
}
