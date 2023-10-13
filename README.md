# starterJAVAPoo
Starter JAVA Poo


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




