package com.macrosoft.starterjavapoo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class  ProgrammeMain {
    private static final Logger logger = LogManager.getLogger(ProgrammeMain.class);

    public static void main(String[] args) {
        logger.info("Démarrage de l'application");
        Menu menu = new Menu();
        menu.displayMenu();
        logger.info("Fermeture de l'application");
    }
}
