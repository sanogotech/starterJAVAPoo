package com.macrosoft.starterjavapoo;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Classe {
    private String professeurPrincipal;
    private List<Eleve> eleves = new ArrayList<>();
    private static final Logger logger = LogManager.getLogger(Classe.class);
    private static final int COEFFICIENT = 1;
    private static final Scanner scanner = new Scanner(System.in);

    public void setProfesseurPrincipal(String professeurPrincipal) {
        this.professeurPrincipal = professeurPrincipal;
    }

    public void ajouterEleve(Eleve eleve) {
        eleves.add(eleve);
    }

    public List<Eleve> getEleves() {
        return eleves;
    }

    public void saisirNotes() {
        Scanner scanner = new Scanner(System.in);
        for (Eleve eleve : eleves) {
            System.out.println("Saisir les notes pour l'élève " + eleve.getNom() + ":");
            System.out.print("Maths: ");
            int maths = scanner.nextInt();
            System.out.print("Physique: ");
            int physique = scanner.nextInt();
            System.out.print("Français: ");
            int francais = scanner.nextInt();
            System.out.print("Anglais: ");
            int anglais = scanner.nextInt();

            try {
                eleve.setNotes(maths, physique, francais, anglais);
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : " + e.getMessage());
                logger.error(" Demandez à l'utilisateur de saisir les notes à nouveau si elles sont incorrectes");
                saisirNotes();
            }
        }
    }
 

    public void genererClassement() {
        // Calculer les moyennes et trier les élèves par ordre décroissant de moyennes
        eleves.sort((e1, e2) -> Double.compare(e2.calculerMoyenne(), e1.calculerMoyenne()));

        // Afficher le classement sur la console
        System.out.println("Classement des élèves :");
        for (Eleve eleve : eleves) {
            System.out.println(eleve.getNom() + " - Moyenne : " + eleve.calculerMoyenne());
        }

        // Enregistrer le classement dans un fichier avec la date dans le nom de fichier
        String nomFichier = "classement-" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomFichier))) {
            writer.println("Classement des élèves :");
            for (Eleve eleve : eleves) {
                writer.println(eleve.getNom() + " - Moyenne : " + eleve.calculerMoyenne());
            }
            writer.flush();
        } catch (Exception e) {
            // Gérer les exceptions liées à l'écriture du fichier
            e.printStackTrace();
        }

        // Enregistrer le classement dans les logs
        logger.info("Classement généré et enregistré dans le fichier : " + nomFichier);
    }

    public void genererBulletinHTML() {
        String nomFichier = "bulletin-" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".html";
    	
    	
        try (PrintWriter writer = new PrintWriter(new FileWriter(nomFichier))) {
            writer.println("<html><head><title>Bulletin</title>");
            writer.println("<style>");
            writer.println("table { width: 50%; border-collapse: collapse; margin-bottom: 20px; }");
            writer.println("th, td { padding: 10px; text-align: left; }");
            writer.println("th { background-color: #f2f2f2; }");
            writer.println("tr:nth-child(even) { background-color: #f2f2f2; }");
            writer.println("tr:nth-child(odd) { background-color: #ffffff; }");
            writer.println("</style>");
            writer.println("</head><body>");

            for (Eleve eleve : eleves) {
                writer.println("<h2>Bulletin de " + eleve.getNom() + "</h2>");
                writer.println("<table border='1'>");
                writer.println("<tr><th>Matière</th><th>Note</th></tr>");
                writer.println("<tr><td>Mathématiques</td><td>" + eleve.getMaths() + "</td></tr>");
                writer.println("<tr><td>Physique</td><td>" + eleve.getPhysique() + "</td></tr>");
                writer.println("<tr><td>Français</td><td>" + eleve.getFrancais() + "</td></tr>");
                writer.println("<tr><td>Anglais</td><td>" + eleve.getAnglais() + "</td></tr>");
                writer.println("<tr><th>Moyenne</th><td>" + eleve.calculerMoyenne() + "</td></tr>");
                writer.println("</table>");
            }

            writer.println("</body></html>");
            writer.flush();

            logger.info("Bulletin généré en HTML et enregistré dans le fichier : " + nomFichier);
        } catch (Exception e) {
            // Gérer les exceptions liées à l'écriture du fichier
            e.printStackTrace();
        }
   }
}



