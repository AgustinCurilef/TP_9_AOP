package aop_tpLayers.main;
import aop_tpLayers.database.PersistenciaSistemaRadioJDBC;
import aop_tpLayers.model.DefaulSistemaRadioCompetition;
import aop_tpLayers.model.SistemaRadioCompetition;
import aop_tpLayers.ui.VentanaInscripcionRadioCompetition;

import javax.swing.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    SistemaRadioCompetition sistema = new DefaulSistemaRadioCompetition(new PersistenciaSistemaRadioJDBC(), new PersistenciaSistemaRadioJDBC());
                    new VentanaInscripcionRadioCompetition(sistema);
                    //Main.cargarConcursos(sistema); //para cargar los concursos una unica vez

                } catch (Exception e) {
// log exception...
                    System.out.println(e);
                }
            }
        });
    }

    private static void cargarConcursos(SistemaRadioCompetition sistema) {
        sistema.generarConcurso(4, "concurso4", LocalDate.of(2024, 1, 1), LocalDate.now());
        sistema.generarConcurso(5, "concurso5", LocalDate.of(2022, 12, 3), LocalDate.of(2023, 1, 3));
        sistema.generarConcurso(6, "concurso6", LocalDate.now(), LocalDate.of(2032, 12, 12));
    }
}