# Change Log
All notable changes to this project will be documented in this file.
This project adheres currently NOT REALLY to [Semantic Versioning](http://semver.org/) despite the formatting of the version numbers.

## Unreleased

### Added

- Login Screen ohne Funktionalität
- Hauptmenü mit dem zu den Unterpunkten navigieren kann
- Untermenü Felder mit dem man Felder erstellen, bearbeiten, löschen kann
- Untermenü Schadensfälle mit dem Schadensfälle erstellen, bearbeiten, löschen kann
- Suchleiste mit der man eine Suche durchführen kann

### Changed

- keine Veränderung

### Deprecated

- nichts veraltetes

### Removed

- nichts wurde entfernt

### Fixed

- nichts wurde gefixt

### Security

- keine Sicherheit vorhanden

## [0.0.5] - 2018-01-24

### Added

- Critical Features:
    - Felder und Schäden werden beim Erfassen live gezeichnet.
    - User-Warnhinweis für Gebrauch der App bezüglich Internetverbindung
    - Auswahl eines gewünschten Versicherungsnehmers bei Erstellung von Feldern

    
- Additional Features: 
    - Unterstützung für verschieden User-Profile, inklusive Login/Logout-Funktion
    - Cursor in der Mitte des Bildschirms in der Kartenansicht als visueller Hinweis für Markerposition
    - Schadensmeldungen können Fotos hinzugefügt werden
    - Anzeige der Schadensfälle enthält das zuvor aufgenommene Bild
    - Menü Unterpunkt Vertrag implementiert
        - Liste mit allen Verträgen und detaillierten Information und zugeordneten Feldern
    - Menü Unterpunkt Hilfe implementiert
        - Hilfestellungen zu allen wichtigen Funktionen der App 
    - Menü Unterpunkt Über Cropserve implementiert
        - Details über App, Lokalisierung über Auswahl für Anzeigesprache (Deutsch, English)
    - Menü Unterpunkt Account implementiert
        - Benutzerdetails und Passwortänderung
    - Logo und App-Name
    
### Changed

- Critical Features: 
    - keine Unterscheidung mehr zwischen Felder mit und ohne Vertrag
    - Schadensfälle enthalten nun Namen des Gutachters, des Users und das korrekte Datum
    - Suche unterstützt unterschiedliche Sprachen und zeigt Hinweise für das Finden der Suchergebnisse in der App
    
- Additional Features:
    - Header im Menü zeigt nun die ID und den Namen des eingeloggten Benutzers
    - Login Screen hat nun volle Funktionalität


### Deprecated

- nichts Veraltetes

### Removed

- Felder Ohne Vertrag-Klasse und Code-Implementierung; Entfernung design-bedingt und zur Verbesserung der Feld-Funktionalitäten.

### Fixed

- nichts wurde gefixt.

### Security

- Beim Login werden ID und Passwort abgefragt.
- Benutzern können nicht ohne diesen beiden Angaben in die App gelangen und Daten einsehen.

## [0.0.6] - 2018-01-29 

### Added

- Suche nach Schadensmeldungen über den Namen (Vor-, Nach- und ganzer Name) des Versicherungsnehmers
- Suche nach Felder über den Namen (Vor-, Nach- und ganzer Name) des Versicherungsnehmers
- Versicherungsnehmer können von Gutachter unter 'Account' erstellt werden
- Versicherungsnehmer können von Gutachter unter 'Account' gelöscht werden
- Übersichtsliste für Gutachter von allen Versicherungsnehmern (mit User-ID, Feldern, Schäden)
- Landscape-Layouts für den Landscape Mode
- Hilfe-Texte zur User-Erstellung und -Löschung

### Changed

- Es werden nur die Suchergebnisse für Felder und Schadensmeldungen angezeigt, die dem eingeloggten Versicherungsnehmer gehören
- Bilder für Schadensmeldungen rotieren um 90°, um im Portraitmodus besser angezeigt zu werden

### Deprecated

 - nichts ist veraltet

### Removed

 - nichts wurde entfernt

### Fixed

- Flächenberechnung in EditFeld und ListItemDetail, damit diese gleich berechnen wie bei der Erstellung von Felder und Schäden
- Cursor-Farbe bei Eingabefeldern (Suchleiste) angepasst, damit dieser überhaupt sichtbar ist


### Security

- keine weiteren Sicherheiten eingebaut


## [X.Y.Z] - XXXX-XX-XX (TEMPLATE for new versions)

### Added

- added something
- added something else

### Changed

- changed something
- changed something else

### Deprecated

- deprecated something
- deprecated something else

### Removed

- removed something
- removed something else

### Fixed

- fixed something
- fixed something else

### Security

- made some security relevant changes
- made other security relevant changes

