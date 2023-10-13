
# Projet Java d'École

Ce projet Java est un système de gestion pour une école, qui permet de gérer les élèves, les classes, les notes et générer des rapports de classement et des bulletins en HTML.

## Prérequis

- Java JDK 11 ou supérieur
- Apache Maven (pour la gestion des dépendances et la compilation)

## Configuration du Projet

Le projet utilise Maven comme gestionnaire de dépendances. Assurez-vous d'avoir Maven installé sur votre système.

## Maven  POM.xml

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <!-- Informations générales sur le projet -->
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.macrosoft</groupId> <!-- Identifiant du groupe (organisation) -->
    <artifactId>starterjavapoo</artifactId> <!-- Nom du projet -->
    <version>0.0.1-SNAPSHOT</version> <!-- Version du projet -->

    <!-- Propriétés pour spécifier la version source et la version cible du compilateur Java -->
    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
    </properties>

    <build>
        <plugins>
            <!-- Plugin pour créer un JAR exécutable avec les dépendances incluses -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId> <!-- Plugin Maven Shade -->
                <version>3.2.4</version> <!-- Version du plugin Shade -->
                <executions>
                    <execution>
                        <phase>package</phase> <!-- Phase Maven à laquelle le plugin sera exécuté -->
                        <goals>
                            <goal>shade</goal> <!-- Objectif du plugin : créer un JAR exécutable -->
                        </goals>
                        <configuration>
                            <transformers>
                                <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                    <mainClass>com.macrosoft.starterjavapoo.ProgrammeMain</mainClass> <!-- Classe principale du JAR -->
                                </transformer>
                            </transformers>
                        </configuration>
                    </execution>
                </executions>
            </plugin>

            <!-- Plugin pour exécuter les tests JUnit 5 -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId> <!-- Plugin Maven Surefire -->
                <version>3.0.0-M5</version> <!-- Version du plugin Surefire -->
                <dependencies>
                    <!-- Dépendance pour exécuter les tests JUnit 5 -->
                    <dependency>
                        <groupId>org.junit.jupiter</groupId>
                        <artifactId>junit-jupiter-engine</artifactId>
                        <version>5.8.1</version> <!-- Version de JUnit 5 -->
                    </dependency>
                </dependencies>
            </plugin>
        </plugins>
    </build>

    <dependencies>
        <!-- Log4j pour la journalisation -->
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-api</artifactId>
            <version>2.14.1</version> <!-- Version de Log4j -->
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
            <version>2.14.1</version> <!-- Version de Log4j -->
        </dependency>

        <!-- JUnit 5 pour les tests unitaires -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.8.1</version> <!-- Version de JUnit 5 -->
            <scope>test</scope> <!-- Utilisé uniquement pour les tests -->
        </dependency>

        <!-- Jsoup pour l'analyse HTML -->
        <dependency>
            <groupId>org.jsoup</groupId>
            <artifactId>jsoup</artifactId>
            <version>1.14.3</version> <!-- Version de Jsoup -->
        </dependency>
    </dependencies>
</project>
```

Dans ce fichier pom.xml :

<build> : Cette section contient les configurations pour les plugins Maven. Le plugin maven-shade-plugin est configuré ici pour créer un JAR exécutable avec les dépendances incluses. Le plugin maven-surefire-plugin est utilisé pour exécuter les tests unitaires.

<dependencies> : Cette section contient les dépendances du projet. Les bibliothèques Log4j, JUnit 5 et Jsoup sont spécifiées ici. Maven se charge de télécharger ces bibliothèques et de les inclure dans le projet lors de la construction.

Chaque balise et élément dans ce fichier pom.xml est essentiel pour configurer votre projet Maven, du compilateur Java aux dépendances en passant par les plugins pour l'emballage et les tests. Assurez-vous que ces configurations correspondent à vos besoins spécifiques et à la structure de votre projet.
 

## Compilation du Projet

Pour compiler le projet, exécutez la commande suivante à la racine du projet :

```bash
mvn clean install
```

Cela téléchargera les dépendances, compilera le code et exécutera les tests unitaires.

Exécution des Tests
Pour exécuter les tests unitaires, utilisez la commande suivante :

```bash

mvn test
```

Cela exécutera tous les tests unitaires et affichera les résultats dans la console.

**Exécution du Projet**

Pour exécuter le projet, utilisez la commande suivante :

```bash
mvn package
cd target
java -jar starterjavapoo-0.0.1-SNAPSHOT.jar
```

Assurez-vous de remplacer nom-du-jar.jar par le nom réel du fichier JAR généré.

Auteur

[SANOGO  Souleymane Formateur JAVA JEE]

## Fichier log4j2.xml avec rotation quotidienne des logs


Pour utiliser la configuration de Log4j, vous pouvez créer un fichier log4j2.xml (ou log4j.xml, selon la version de Log4j que vous utilisez) et le placer dans le répertoire src/main/resources de votre projet Maven. Voici un exemple de configuration de base pour log4j2.xml :


```xml

<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="INFO">
    <Appenders>
        <!-- Rotation quotidienne des logs -->
        <RollingFile name="RollingFile" fileName="logs/monecole.log"
                     filePattern="logs/monecole-%d{MM-dd-yyyy}-%i.log.gz">
            <PatternLayout pattern="%d %-5p [%c{1.}] %m%n" />
            <Policies>
                <TimeBasedTriggeringPolicy interval="1" modulate="true" />
                <SizeBasedTriggeringPolicy size="100 MB" />
            </Policies>
            <DefaultRolloverStrategy max="20"/>
        </RollingFile>

        <!-- Affichage des logs dans la console -->
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d %-5p [%c{1.}] %m%n" />
        </Console>
    </Appenders>
    <Loggers>
        <!-- Configuration du niveau de log pour différentes classes ou packages -->
        <Root level="info">
            <AppenderRef ref="RollingFile" />
            <AppenderRef ref="Console" />
        </Root>
    </Loggers>
</Configuration>
```

Dans ce fichier de configuration, l'appender RollingFile est configuré avec filePattern contenant le modèle de nom de fichier incluant la date (%d{MM-dd-yyyy}) et un numéro d'index (%i) pour garantir la rotation quotidienne. Les fichiers de log seront compressés (.gz) pour économiser de l'espace disque.

De plus, le TimeBasedTriggeringPolicy est configuré avec interval="1" pour définir la rotation quotidienne, et modulate="true" pour garantir que la rotation a lieu à minuit chaque jour, quelle que soit l'heure à laquelle l'application a été démarrée. La politique SizeBasedTriggeringPolicy est également ajoutée pour effectuer la rotation des fichiers lorsque leur taille atteint 100 MB.

Avec cette configuration, un nouveau fichier de log sera créé chaque jour, portant la date dans son nom de fichier, et les fichiers de log plus anciens seront compressés et conservés en fonction du nombre maximal de fichiers configuré (max="20" dans cet exemple). N'oubliez pas d'ajuster ces paramètres selon vos besoins spécifiques.




