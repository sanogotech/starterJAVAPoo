package com.macrosoft.starterjavapoo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;

public class Menu {
    private Classe classe = new Classe();
    private Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LogManager.getLogger(Menu.class);

    public void displayMenu() {
        int choice;
        do {
            System.out.println("1. Ajouter un professeur principal à la classe");
            System.out.println("2. Ajouter un élève à la classe");
            System.out.println("3. Saisir les notes des élèves");
            System.out.println("4. Générer le classement");
            System.out.println("5. Générer un bulletin en HTML");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Pour consommer la nouvelle ligne

            switch (choice) {
                case 1:
                    System.out.print("Nom du professeur principal : ");
                    String professeur = scanner.nextLine();
                    classe.setProfesseurPrincipal(professeur);
                    logger.info("Professeur principal ajouté : " + professeur);
                    break;
                case 2:
                    System.out.print("Nom de l'élève : ");
                    String nomEleve = scanner.nextLine();
                    Eleve eleve = new Eleve(nomEleve);
                    classe.ajouterEleve(eleve);
                    logger.info("Élève ajouté : " + nomEleve);
                    break;
                case 3:
                    classe.saisirNotes();
                    logger.info("Notes saisies pour tous les élèves");
                    break;
                case 4:
                    classe.genererClassement();
                    logger.info("Classement généré");
                    break;
                case 5:
                    classe.genererBulletinHTML();
                    logger.info("Bulletin généré en HTML");
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    logger.info("Application quittée");
                    break;
                default:
                    System.out.println("Choix invalide. Réessayez.");
                    logger.warn("Choix invalide : " + choice);
            }
        } while (choice != 0);
    }
}
