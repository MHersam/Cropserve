# Sprint Report ([1,4])

In diesem Sprint wurden alle die Issues abgearbeitet, die bis spätestens [Meilenstein 6](https://sopra.informatik.uni-stuttgart.de/sopra-ws1718/sopra-team-33/milestones/6) zu erledigen waren. Dazu gehören auch der Großteil der Issues für [Meilenstein 5] (https://sopra.informatik.uni-stuttgart.de/sopra-ws1718/sopra-team-33/milestones/5).
Die übrig gebliebene Implementable Story wurden als Idee verworfen und auch aus dem Product Backlog entfernt.
Im Verlaufe des Sprints wurden weitere, von uns gesetzte [Additional Features] (https://sopra.informatik.uni-stuttgart.de/sopra-ws1718/sopra-team-33/issues?scope=all&utf8=%E2%9C%93&state=closed&milestone_title=Meilenstein%205%20Additional%20Features) implementiert und die vorhandenen Critical Features verbessert.

###### Critical Features:

 - Schadensmeldungen enthalten nun zusätzlich die Information über den "Besitzer" (Versicherungsnehmer)
 - Die Suchfunktion unterstützt nun die Suche nach Schadensfällen über den Namen des Versicherungsnehmers
 - Die Suchfunktion unterstützt nun die Suche nach Feldern über den Namen des Versicherungsnehmers.


######  Additional Features:
 - Von Gutachtern können neue Versicherungsnehmer angelegt werden
 - Von Gutachtern können existierende Versicherungsnehmer gelöscht werden
 - Unter Account haben Gutachter nun eine Liste mit allen Versicherungsnehmer, die Informationen über diese anzeigt
 - Die Flächenberechnung für das Editieren von Schäden und Feldern wurde angepasst an die der Erstellung
 - Suchergebnisse werden je nach eingeloggtem User anders angezeigt
    -> Versicherungsnehmer können nur ihre eigenen Felder und Schäden suchen
    -> Gutachter haben weiterhin uneingeschränkte Suchergebnisse
 - Landscape-Layouts wurden erstellt, wo es benötigt wurde
 - Hilfestellungen wurden angepasst und aktualisiert.

## Verbesserte Dokumente

Folgende Dokumente wurden aktualisiert:

- [Read Me] (https://sopra.informatik.uni-stuttgart.de/sopra-ws1718/sopra-team-33/blob/master/Readme.md) 
- [Changelog] (https://sopra.informatik.uni-stuttgart.de/sopra-ws1718/sopra-team-33/blob/master/CHANGELOG.md)
- [Zeitabrechnung] (https://sopra.informatik.uni-stuttgart.de/sopra-ws1718/sopra-team-33/blob/master/doc/Zeitabrechnung.ods)
- [Merge Request Template der Definition of Done] (https://sopra.informatik.uni-stuttgart.de/sopra-ws1718/sopra-team-33/blob/master/.gitlab/merge_request_templates/General.md)
- [Product Backlog - Begriffe und Zeiteinschätzungen] (https://sopra.informatik.uni-stuttgart.de/sopra-ws1718/sopra-team-33/blob/master/doc/Product.Backlog.md)
- [Entwurf - Klassendiagramm] (https://sopra.informatik.uni-stuttgart.de/sopra-ws1718/sopra-team-33/blob/master/doc/Entwurf.md)


## Tests/Testprotokolle/Nachweis der Testabdeckung

Die Tests bzw. Testklassen für diesen Sprint befinden sich [*hier*] (https://sopra.informatik.uni-stuttgart.de/sopra-ws1718/sopra-team-33/tree/master/app/src/androidTest/java/de/uni_stuttgart/informatik/sopra/sopraapp).

Die Dateien für Testprotokolle bzw. Testabdeckung sind unter [doc/coverage/..] (https://sopra.informatik.uni-stuttgart.de/sopra-ws1718/sopra-team-33/tree/master/doc/coverage) zu finden.
Die Datei [index.html] (https://sopra.informatik.uni-stuttgart.de/sopra-ws1718/sopra-team-33/blob/master/doc/coverage/index.html) steht dabei für das Testabdeckungsprotokoll, welches einmal die gesamte Abdeckung anzeigt, und aufgeklappt werden kann, um sich die Prozentsätze der einzelnen Testklassen anzuschauen.
Nach dem Herunterladen kann die die Datei (so wie alle anderen Test-relevanten Dateien) im Browser geöffnet werden.

Im Unterordner [de.uni_stuttgart.informatik.sopra.sopraapp] (https://sopra.informatik.uni-stuttgart.de/sopra-ws1718/sopra-team-33/tree/master/doc/coverage/de.uni_stuttgart.informatik.sopra.sopraapp) befinden sich die Testprotokolle der einzelnen Testklassen, die nochmals detailliertere Informationen über den Aufbau und die Abdeckung liefern.
