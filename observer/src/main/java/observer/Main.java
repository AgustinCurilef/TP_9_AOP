package observer;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        var castrilli = new Arbitro("Castrilli");
        var elDiego = new Jugador("Diego", List.of(new Registracion()));
        elDiego.insultarA(castrilli, "lppp...");
    }
}