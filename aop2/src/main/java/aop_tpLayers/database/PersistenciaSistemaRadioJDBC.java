package aop_tpLayers.database;

import aop_tpLayers.model.*;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaSistemaRadioJDBC implements InscripcionParticipante, ConcursoManager {
    private final String url;
    private final String user;
    private final String password;

    public PersistenciaSistemaRadioJDBC() {
        url = "jdbc:mysql://localhost:3306/tp4-ejercicio3-objetos2";
        user = "root";
        password = "";
    }

    @Override
    @Log
    public List<Concurso> todosLosConcursos() {
        List<Concurso> concursos = new ArrayList<>();
        java.sql.Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(url, user, password);
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM concurso");

            while (rs.next()) {
                int id = rs.getInt("idConcurso");
                String nombre = rs.getString("nombre");
                Date fechaInicio = rs.getDate("fechaInicio");
                Date fechaFin = rs.getDate("fechaFin");

                Concurso concurso = new Concurso(id, nombre, fechaInicio.toLocalDate(), fechaFin.toLocalDate());
                concursos.add(concurso);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al recuperar concursos de la base de datos", e);
        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return concursos;
    }

    @Override
    public void saveConcurso(Concurso concurso) {
        java.sql.Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(url, user, password);//String nombre, String apellido, String dni, String telfono, String email, int idConcurso)
            PreparedStatement st = conexion.prepareStatement("insert into concurso(idConcurso,nombre,fechaInicio,fechaFin) values(?,?,?,?)");
            try {
                st.setInt(1, concurso.obtenerID());
                st.setString(2, concurso.obtenerNombre());
                st.setDate(3, Date.valueOf(concurso.obtenerFechaInicio()));
                st.setDate(4, Date.valueOf(concurso.obtenerFechaFin()));

                st.executeUpdate();
            } finally {
                st.close();

            }
        } catch (SQLException e) {
            throw new RuntimeException("Error de conexion", e);
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    @Log
    public void saveInscription(Participante participante) {
        java.sql.Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(url, user, password);//String nombre, String apellido, String dni, String telfono, String email, int idConcurso)
            PreparedStatement st = conexion.prepareStatement("insert into participante(nombre,apellido,dni,telefono,email,idConcurso) values(?,?,?,?,?,?)");
            try {
                st.setString(1, participante.nombre());
                st.setString(2, participante.apellido());
                st.setString(3, participante.dni());
                st.setString(4, participante.telefono());
                st.setString(5, participante.email());
                st.setInt(6, participante.idConcurso());

                st.executeUpdate();
            } finally {
                st.close();

            }
        } catch (SQLException e) {
            throw new RuntimeException("Error de conexion", e);
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
