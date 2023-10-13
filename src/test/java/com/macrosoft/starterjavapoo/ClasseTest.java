package com.macrosoft.starterjavapoo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ClasseTest {
    private Classe classe;

    @BeforeEach
    void setUp() {
        classe = new Classe();
        Eleve eleve1 = new Eleve("Eleve1");
        Eleve eleve2 = new Eleve("Eleve2");
        eleve1.setNotes(15, 8,6, 14);
       eleve2.setNotes(11, 15, 12, 17);
        classe.ajouterEleve(eleve1);
        classe.ajouterEleve(eleve2);
    }

    @Test
    void testGenererClassement() {
        classe.genererClassement();
        List<Eleve> eleves = classe.getEleves();
        assertEquals("Eleve2", eleves.get(0).getNom());
        assertEquals("Eleve1", eleves.get(1).getNom());
    }

    @Test
    void testGenererBulletinHTML() throws IOException {
        classe.genererBulletinHTML();
        
        // Vérifiez si le fichier bulletin.*html a été créé
        String nomFichier = "bulletin-" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".html";
        File file = new File(nomFichier);
        String filenane = file.getName();
        assertTrue(filenane.startsWith("bulletin-"));

        // Lisez le fichier bulletin-yyyy-MM-dd.html à l'aide de Jsoup
        Document doc = Jsoup.parse(file, "UTF-8");
        
        // Vérifiez le titre du document HTML
        assertEquals("Bulletin", doc.title());

        // Vérifiez le contenu du tableau HTML pour chaque élève
        Element table1 = doc.select("h2:contains(Bulletin de Eleve1) + table").first();
        assertNotNull(table1);
        assertEquals(10.75, Double.parseDouble(table1.select("tr:contains(Moyenne) td").first().text()), 0.01);

        Element table2 = doc.select("h2:contains(Bulletin de Eleve2) + table").first();
        assertNotNull(table2);
        assertEquals(13.75, Double.parseDouble(table2.select("tr:contains(Moyenne) td").first().text()), 0.01);

        // Supprimez le fichier bulletin.html après les tests
        file.delete();
    }

   


}

