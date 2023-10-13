
# Projet Java d'�cole

Ce projet Java est un syst�me de gestion pour une �cole, qui permet de g�rer les �l�ves, les classes, les notes et g�n�rer des rapports de classement et des bulletins en HTML.

## Pr�requis

- Java JDK 11 ou sup�rieur
- Apache Maven (pour la gestion des d�pendances et la compilation)

## Configuration du Projet

Le projet utilise Maven comme gestionnaire de d�pendances. Assurez-vous d'avoir Maven install� sur votre syst�me.

## Maven  POM.xml

```xml

<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <!-- Informations g�n�rales sur le projet -->
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.macrosoft</groupId> <!-- Identifiant du groupe (organisation) -->
    <artifactId>starterjavapoo</artifactId> <!-- Nom du projet -->
    <version>0.0.1-SNAPSHOT</version> <!-- Version du projet -->

    <!-- Propri�t�s pour sp�cifier la version source et la version cible du compilateur Java -->
    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
    </properties>

    <build>
        <plugins>
            <!-- Plugin pour cr�er un JAR ex�cutable avec les d�pendances incluses -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId> <!-- Plugin Maven Shade -->
                <version>3.2.4</version> <!-- Version du plugin Shade -->
                <executions>
                    <execution>
                        <phase>package</phase> <!-- Phase Maven � laquelle le plugin sera ex�cut� -->
                        <goals>
                            <goal>shade</goal> <!-- Objectif du plugin : cr�er un JAR ex�cutable -->
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

            <!-- Plugin pour ex�cuter les tests JUnit 5 -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId> <!-- Plugin Maven Surefire -->
                <version>3.0.0-M5</version> <!-- Version du plugin Surefire -->
                <dependencies>
                    <!-- D�pendance pour ex�cuter les tests JUnit 5 -->
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
            <scope>test</scope> <!-- Utilis� uniquement pour les tests -->
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

<build> : Cette section contient les configurations pour les plugins Maven. Le plugin maven-shade-plugin est configur� ici pour cr�er un JAR ex�cutable avec les d�pendances incluses. Le plugin maven-surefire-plugin est utilis� pour ex�cuter les tests unitaires.

<dependencies> : Cette section contient les d�pendances du projet. Les biblioth�ques Log4j, JUnit 5 et Jsoup sont sp�cifi�es ici. Maven se charge de t�l�charger ces biblioth�ques et de les inclure dans le projet lors de la construction.

Chaque balise et �l�ment dans ce fichier pom.xml est essentiel pour configurer votre projet Maven, du compilateur Java aux d�pendances en passant par les plugins pour l'emballage et les tests. Assurez-vous que ces configurations correspondent � vos besoins sp�cifiques et � la structure de votre projet.
 

## Compilation du Projet

Pour compiler le projet, ex�cutez la commande suivante � la racine du projet :

```bash
mvn clean install
```

Cela t�l�chargera les d�pendances, compilera le code et ex�cutera les tests unitaires.

Ex�cution des Tests
Pour ex�cuter les tests unitaires, utilisez la commande suivante :

```bash

mvn test
```

Cela ex�cutera tous les tests unitaires et affichera les r�sultats dans la console.

**Ex�cution du Projet**

Pour ex�cuter le projet, utilisez la commande suivante :

```bash
mvn package
cd target
java -jar starterjavapoo-0.0.1-SNAPSHOT.jar
```

Assurez-vous de remplacer nom-du-jar.jar par le nom r�el du fichier JAR g�n�r�.

Auteur

[SANOGO  Souleymane Formateur JAVA JEE]

## Fichier log4j2.xml avec rotation quotidienne des logs


Pour utiliser la configuration de Log4j, vous pouvez cr�er un fichier log4j2.xml (ou log4j.xml, selon la version de Log4j que vous utilisez) et le placer dans le r�pertoire src/main/resources de votre projet Maven. Voici un exemple de configuration de base pour log4j2.xml :


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
        <!-- Configuration du niveau de log pour diff�rentes classes ou packages -->
        <Root level="info">
            <AppenderRef ref="RollingFile" />
            <AppenderRef ref="Console" />
        </Root>
    </Loggers>
</Configuration>
```

Dans ce fichier de configuration, l'appender RollingFile est configur� avec filePattern contenant le mod�le de nom de fichier incluant la date (%d{MM-dd-yyyy}) et un num�ro d'index (%i) pour garantir la rotation quotidienne. Les fichiers de log seront compress�s (.gz) pour �conomiser de l'espace disque.

De plus, le TimeBasedTriggeringPolicy est configur� avec interval="1" pour d�finir la rotation quotidienne, et modulate="true" pour garantir que la rotation a lieu � minuit chaque jour, quelle que soit l'heure � laquelle l'application a �t� d�marr�e. La politique SizeBasedTriggeringPolicy est �galement ajout�e pour effectuer la rotation des fichiers lorsque leur taille atteint 100 MB.

Avec cette configuration, un nouveau fichier de log sera cr�� chaque jour, portant la date dans son nom de fichier, et les fichiers de log plus anciens seront compress�s et conserv�s en fonction du nombre maximal de fichiers configur� (max="20" dans cet exemple). N'oubliez pas d'ajuster ces param�tres selon vos besoins sp�cifiques.




