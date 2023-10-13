package com.macrosoft.starterjavapoo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EleveTest {

    @Test
    void testCalculerMoyenne() {
        Eleve eleve = new Eleve("Eleve1");
        eleve.setNotes(18, 15, 16, 17);
        assertEquals(16.5, eleve.calculerMoyenne(), 0.01);
    }

    @Test
    void testSetNotesAvecException() {
        Eleve eleve = new Eleve("Eleve1");

        // Teste le cas où les notes sont en dehors de la plage de 0 à 20
        assertThrows(IllegalArgumentException.class, () -> {
            eleve.setNotes(25, -5, 22, 18);
        });

        // Teste le cas où les notes sont correctes
        assertDoesNotThrow(() -> {
            eleve.setNotes(18, 15, 16, 17);
        });
    }
}
