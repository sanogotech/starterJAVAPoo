# starterJAVAPoo
Starter JAVA Poo


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




