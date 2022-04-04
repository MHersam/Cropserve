# Product Backlog

Hier werden **alle** Anforderungen in Form von **User Stories** geordnet aufgelistet.

<!-- TOC command doesn't seem to be supported in gitlab view of this pb, so no TOC generated, the words remain though -->

[TOC]

## Epic 1 Verwaltung

> Als Benutzer und Gutachter möchte ich alle meine Daten bezüglich Schadensmeldungen, Verträgen und Feldern verwalten (erfassen/bearbeiten/löschen) können.

###### *Ausführliche Beschreibung*
Die für die App fundamentalen Bestandteile wie Felder, Schadensmeldungen und Verträge sollen einfach und intuitiv in der App zu verwalten sein. Die Verwaltung von Feldern, Verträgen oder Schadensfällen ist in Ansichten verfügbar, die benutzerfreundlich durch die einzelnen Schritte und Vorgänge leiten. Über das Menü, welches in der Visuellen Darstellung weiter beschrieben ist, lässt sich strukturiert durch diese Funktionen der App navigieren. Eine Suchfunktion erleichtert zusätzlich die Benutzung der Verwaltungsoptionen und deren Daten, welche gespeichert und abgerufen werden können.

### Feature 1.1 Verwaltung der Schadensfälle

> Als Gutachter möchte ich Schadensfälle verwalten (erfassen/bearbeiten/löschen) können.

- Aufwandsschätzung: L
- Akzeptanztests:
    - [ ] Schadensfälle können mit der Angabe des Versicherungsobjekts (Name des Versicherungsnehmers, Fläche und Koordinaten des Objekts, Region (mind. Landkreis)), Schadensinformationen (Schadensfläche, Schadensposition, Schadens-Koordinaten/-Polygon, Datum, Bilder, standardisierte Schadensbeschreibung wie Hagelschaden) und Name des Gutachters erfasst werden.
    - [ ] Die Erfassung von Schadensfällen/-Koordinaten verwendet tatsächliche Sensorwerte eines Positionssensors im Gerät.
    - [ ] Die Erfassung von Schadensfällen/-Koordinaten kann manuell per Hand geschehen
    - [ ] Schadensfälle sind nach dem vollständigen Schließen der App und Starten der App wieder im gleichen Zustand verfügbar.
    - [ ] Schadensfälle können während des Erfassens in der Kartenansicht (*siehe Visuelle Darstellung der Schadensfälle*) dargestellt werden.
    - [ ] Schadensfälle können während der Bearbeitung in der Kartenansicht (*siehe Visuelle Darstellung der Schadensfälle*) dargestellt werden.
    - [ ] Schadensfälle können nach Name des Versicherungsnehmers gesucht werden.
    - [ ] Schadensfälle können gelöscht werden.
    - [ ] Schadensfälle können bearbeitet werden.
    - [ ] Schadensfälle haben einen Bearbeitungsstatus.
    - [ ] Die Verwaltung ist ohne Internetverbindung möglich.

#### Implementable Story 1.1.1 Schadensfall erfassen
> Als Gutachter möchte ich den Schaden mit der Angabe des Versicherungsobjekts (Name des Versicherungsnehmers, Fläche und Koordinaten des Objekts, Region (mind. Landkreis)), Schadensinformationen (Schadensfläche, Schadensposition, Schadens-Koordinaten/-Polygon, Datum, Bilder, standardisierte Schadensbeschreibung wie Hagelschaden) und Name des Gutachters erfassen können.

- Aufwandsschätzung: 10 Story Points
- Akzeptanztests:
    - [ ] Daten des Versicherungsobjekts werden ausgelesen.
    - [ ] Schadensbeschreibung wird korrekt ausgelesen.
    - [ ] Name des Gutachters wird korrekt ausgelesen.
    - [ ] Daten werden korrekt lokal abgespeichert.

##### Task 1.1.1.1 Betroffenes Feld auswählen

- Aufwandsschätzung: 0,2 Stunden

##### Task 1.1.1.2 Ansicht für Schadensmeldungen aufrufen

- Aufwandsschätzung: 0,2 Stunden

##### Task 1.1.1.3 Daten des ausgewählten Versicherungsobjekts zwischenspeichern

- Aufwandsschätzung: 1 Stunde

##### Task 1.1.1.4 Koordinaten des Schadens erfassen

- Aufwandsschätzung: 1 Stunde

##### Task 1.1.1.5 Schadensinformationen in Formular eintragen

- Aufwandsschätzung: 0,5 Stunden

##### Task 1.1.1.6 Option zur graphischen Erfassung des Schadens aufrufen

- Aufwandsschätzung: 1 Stunde

##### Task 1.1.1.7 Alle erfassten Informationen in Schadensbericht abspeichern

- Aufwandsschätzung: 1 Stunde

##### Task 1.1.1.8 Schadensbericht lokal abspeichern

- Aufwandsschätzung: 0,5 Stunden

#### Implementable Story 1.1.2 Schadensfälle/-Koordinaten mit Sensorwerten eines Positionssensor im Gerät erfassen
> Als Gutachter oder Benutzer möchte ich den Schaden mit einem Positionssensors im Gerät erfassen, um die Position und die Größe des Schadens darzustellen.

- Aufwandsschätzung: 20 Story Points
- Akzeptanztests:
    - [ ] Die Eckpunkte eines Feldes werden mit dem Sensor erfasst.
    - [ ] Die Fläche des Schadens kann mit dem Positionssensor erfasst werden.
    - [ ] Fläche des Feldes wird korrekt berechnet.

##### Task 1.1.2.1 Koordinaten mit dem Positionssensor über einen Button setzen

- Aufwandsschätzung: 1 Stunden

##### Task 1.1.2.2 Koordinaten in chronologischer Reihenfolge speichern

- Aufwandsschätzung: 0,5 Stunden

##### Task 1.1.2.3 Polygon aus den Koordinaten generieren

- Aufwandsschätzung: 1 Stunde

##### Task 1.1.2.4 Fläche des Polygons berechnen

- Aufwandsschätzung: 2 Stunden

#### Implementable Story 1.1.3 Schadensfällen/-Koordinaten manuell erfassen
> Als Gutachter oder Benutzer möchte ich den Schaden manuell in die Kartenansicht einzeichnen können, um ein Feld mit dem Schaden darzustellen.

- Aufwandsschätzung: 25 Story Points
- Akzeptanztests:
    - [ ] Die Polygone der Felder werden korrekt angezeigt.
    - [ ] Der Ort des Schadens kann in der Kartenansicht eingezeichnet werden.
    - [ ] Die Fläche des Schadens wird korrekt berechnet.

##### Task 1.1.3.1 Koordinaten manuell in Kartenansicht einzeichnen

- Aufwandsschätzung: 2 Stunden

##### Task 1.1.3.2 Koordinaten in chronologischer Reihenfolge speichern

- Aufwandsschätzung: 0,5 Stunden

##### Task 1.1.3.3 Polygon aus den Koordinaten generieren

- Aufwandsschätzung: 1 Stunde

##### Task 1.1.3.4 Fläche des Polygons berechnen

- Aufwandsschätzung: 2 Stunden

#### Implementable Story 1.1.4 Zustand der App beim Schließen speichern und beim Starten vorherigen Zustand laden
> Als Gutachter und Benutzer der App möchte ich, dass die Schadensfälle auch nach dem Schließen der App (Beenden, Neustart oder als Hintergrundprozess) wieder abrufbar sind, um keine Daten zu verlieren und um genau dort weitermachen zu können, wo ich aufgehört habe.

- Aufwandsschätzung: 35 Story Points
- Akzeptanztests:
    - [ ] Der Schadensfall ist nach dem Schließen (Beenden, Neustart oder als Hintergrundprozess)und erneutem Öffnen der App im gleichen Zustand.

##### Task 1.1.4.1 Speichern aller Daten zugehörig zum Schadensfall beim Schließen (Beenden, Neustart oder als Hintergrundprozess) der App

- Aufwandsschätzung: 3 Stunden

##### Task 1.1.4.2 Beim Öffnen der App die zuvor gespeicherten Daten wieder auslesen und den alten Zustand wiederherstellen.

- Aufwandsschätzung: 3 Stunden

#### Implementable Story 1.1.5 Schadensfälle während dem Bearbeiten in einer Kartenansicht darstellen
> Als Benutzer möchte ich den Schadensfall, während ich ihn bearbeite, in einer Kartenansicht angezeigt bekommen, um ihn mir vorstellen zu können.

- Aufwandsschätzung: 4 Story Points
- Akzeptanztests:
    - [ ] Kartenansicht des Schadensfalles in der Bearbeitungsansicht ist richtig.
    - [ ] Alle betroffenen Informationen sind aktualisiert.

##### Task 1.1.5.1 Ansicht zur Bearbeitung von Schadensfällen bereitstellen

- Aufwandsschätzung: 1 Stunde

##### Task 1.1.5.2 Karte mit Polygon der Schadensfläche in einer Teilansicht laden

- Aufwandsschätzung: 1,5 Stunden

##### Task 1.1.5.3 Beschreibung des Schadens über Eingabefelder mit neuen Informationen aktualisieren

- Aufwandsschätzung: 0,2 Stunden

##### Task 1.1.5.4 Status des Schadensfalls aktualisieren

- Aufwandsschätzung: 0,2 Stunden

#### Implementable Story 1.1.6 Schadensfälle löschen
> Als Benutzer möchte ich den Schadensfall löschen können.

- Aufwandsschätzung: 6 Story Points
- Akzeptanztests:
    - [ ] Schadensfall nicht mehr abrufbar.
    - [ ] Schadensfall ist  mit allen Informationen aus dem System entfernt.

##### Task 1.1.6.1 Schadensfall aus der Übersicht auswählen und entfernen

- Aufwandsschätzung: 0,2 Stunden

##### Task 1.1.6.2 Informationen des Schadensfalls aus dem Speicher entfernen

- Aufwandsschätzung: 1 Stunde

#### Implementable Story 1.1.7 Schadensfälle bearbeiten
> Als Gutachter möchte ich Schadensfälle bearbeiten können.

- Aufwandsschätzung: 4 Story Points
- Akzeptanztests:
    - [ ] Informationen zum Schaden können geändert werden.
    - [ ] Koordinaten der Schadensposition können angepasst werden.
    - [ ] Status des Schadensfalls kann geändert werden.

##### Task 1.1.7.1 Informationen zum Schaden bearbeiten und aktualisieren

- Aufwandsschätzung: 0,5 Stunden

##### Task 1.1.7.2 Koordinaten der Schadensfläche bearbeiten und aktualisieren

- Aufwandsschätzung: 0,5 Stunden

##### Task 1.1.7.3 Status des Schadensfalls aktualisieren

- Aufwandsschätzung: 0,5 Stunden

#### Implementable Story 1.1.8 Der Status eines Schadensfalls kann angezeigt werden
> Als Benutzer möchte ich, dass es Statusinformationen zu einem Schadensfall gibt, um den aktuellen Stand der Bearbeitung zu sehen.

- Aufwandsschätzung: 3 Story Points
- Akzeptanztests:
    - [ ] Der Status eines Schadensfall wird aktualisiert.
    - [ ] Die aktuellsten Daten werden abgerufen.

##### Task 1.1.8.1 Menü aufrufen

- Aufwandsschätzung: 0,2 Stunden

##### Task 1.1.8.2 Schadensmeldungen aus Verwaltungsoption auswählen

- Aufwandsschätzung: 0,2 Stunden

##### Task 1.1.8.3 Daten abrufen und Anzeige aktualisieren

- Aufwandsschätzung: 0,5 Stunden

#### Implementable Story 1.1.9 Suchfunktion für Schadensfälle
> Als Gutachter möchte ich nach Name des Versicherungsnehmers und Versicherungsnummer suchen können, um dessen registrierte Schadensfälle angezeigt bekommen zu lassen.

- Aufwandsschätzung: 20 Story Points
- Akzeptanztests:
    - [ ] Suchleiste ist in der Verwaltungsansicht der Schadensmeldungen vorhanden.
    - [ ] Es werden alle Namen und Verträge angezeigt, die mit der Suche übereinstimmen.
    - [ ] Es werden keine Duplikate angezeigt.
    - [ ] Nur Einträge des gesuchten Versicherungsnehmers werden angezeigt.

##### Task 1.1.9.1 Suchleiste erstellen

- Aufwandsschätzung: 0,2 Stunden

##### Task 1.1.9.2 Suchleiste in der Verwaltungsansicht für Schadensfälle bereitstellen

- Aufwandsschätzung: 0,2 Stunden

##### Task 1.1.9.3 Eingegebene Suchdaten mit Namen und Versicherungsnummern vergleichen
- Aufwandsschätzung: 1,5 Stunde

##### Task 1.1.9.4 Schadensfälle des gesuchten Versicherungsnehmers anzeigen
- Aufwandsschätzung: 1 Stunde

### Feature 1.2 Verwaltung der Verträge

> Als Gutachter möchte ich Verträge verwalten können.

- Aufwandsschätzung: M
- Akzeptanztests:
    - [ ] Felder können einem Vertrag zugewiesen werden.
    - [ ] Felder können aus einem Vertrag gelöscht werden.
    - [ ] Verträge können nach Name des Versicherungsnehmers und Versicherungsnummer gesucht werden.
    - [ ] Die Felder eines Vertrags können in der Kartenansicht dargestellt werden ("siehe Visuelle Darstellung der Verträge").

#### Implementable Story 1.2.1 Feld einem Vertrag zuordnen
> Als Gutachter und Benutzer möchte ich ein Feld einem Vertrag zuweisen können.

- Aufwandsschätzung: 4 Story Points
- Akzeptanztests:
    - [ ] Alle Felder sind zur Auswahl bereitgestellt.
    - [ ] Feld ist bei Abschluss einer Zuordnung einem Vertrag zugeordnet.
    - [ ] Alle verfügbaren Verträge werden zur anfänglichen Auswahl bereitgestellt.
    - [ ] Felder sind vor Vertragszuordnung registriert (erfasst) worden.

##### Task 1.2.1.1  Vertrag aus Liste in der Verwaltungsansicht auswählen

- Aufwandsschätzung: 0,5 Stunden

##### Task 1.2.1.2 Option zur Vertragszuordnung aufrufen

- Aufwandsschätzung: 0,2 Stunden

##### Task 1.2.1.3 Kartenansicht der Felder aufrufen

- Aufwandsschätzung: 0,5 Stunden

##### Task 1.2.1.4 Feld(er) auf der Karte auswählen

- Aufwandsschätzung: 0,5 Stunden

##### Task 1.2.1.5 Vertragsdaten zu Felddaten hinzufügen

- Aufwandsschätzung: 1 Stunde

##### Task 1.2.1.6  Zur Vertragsübersicht zurückkehren

- Aufwandsschätzung: 0,1 Stunden


#### Implementable Story 1.2.2 Feld aus einem Vertrag löschen
> Als Benutzer möchte ich ein Feld aus einem Vertrag löschen können.

- Aufwandsschätzung: 4 Story Points
- Akzeptanztests:
    - [ ] Der Vertrag ist aus den Felddaten gelöscht.
    - [ ] Über die Vertragsverwaltung wird das Feld nicht mehr zugehörig zu einem gewählten Vertrag angezeigt.

##### Task 1.2.2.1 Vertrag aus Liste in der Verwaltungsansicht auswählen

- Aufwandsschätzung: 0,5 Stunden

##### Task 1.2.2.2 Liste der zugeordneten Felder anzeigen

- Aufwandsschätzung: 0,2 Stunden

##### Task 1.2.2.3 Zu löschendes Feld auswählen

- Aufwandsschätzung: 0,5 Stunden

##### Task 1.2.2.4 Feld aus dem Vertrag löschen

- Aufwandsschätzung: 0,5 Stunden

##### Task 1.2.2.5 Feld- und Vertragsdaten aktualisieren

- Aufwandsschätzung: 1 Stunde

#### Implementable Story 1.2.3 Suchfunktion für Verträge
> Als Gutachter möchte ich nach Name eines Vertrages  suchen können, um eine Übersicht über alle Verträge zu haben.

- Aufwandsschätzung: 30 Story Points
- Akzeptanztests:
    - [ ] Suchleiste ist in der Verwaltungsansicht der Verträge ist vorhanden.
    - [ ] Es werden alle Verträge angezeigt, die mit der Suche übereinstimmen.
    - [ ] Es werden keine Duplikate angezeigt.

##### Task 1.2.3.1 Suchleiste erstellen

- Aufwandsschätzung: 1 Stunde

##### Task 1.2.3.2 Suchleiste in der Verwaltungsansicht für Verträge bereitstellen

- Aufwandsschätzung: 1 Stunde

##### Task 1.2.3.3 Eingegebene Suchdaten mit existierenden Verträgen vergleichen

- Aufwandsschätzung: 0,5 Stunde

##### Task 1.2.3.4 Vorhandene Verträge anzeigen

- Aufwandsschätzung: 0,5 Stunde

### Feature 1.3 Verwaltung der Felder
> Als Benutzer möchte ich meine Felder verwalten (erfassen/bearbeiten/löschen) können.

- Aufwandsschätzung: L
- Akzeptanztests:
    - [ ] Felder werden mit Fläche, Koordinaten des Objekts, Region (mind. Landkreis)), Art, und Name des Versicherungsnehmers erfasst.
    - [ ] Die Erfassung von Feldern verwendet tatsächliche Sensorwerte eines Positionssensors im Gerät.
    - [ ] Die Erfassung von Feldern kann manuell per Hand geschehen
    - [ ] Felder sind nach dem vollständigen Schließen der App und Starten der App wieder im gleichen Zustand verfügbar.
    - [ ] Felder können während der Bearbeitung in der Kartenansicht dargestellt werden.
    - [ ] Felder können gelöscht werden.
    - [ ] Felder können bearbeitet werden.
    - [ ] Die Verwaltung ist ohne Internetverbindung möglich.

#### Implementable Story 1.3.1 Feld erfassen
> Als Benutzer möchte ich Felder mit Informationen über Größe/Fläche, Art, Region und Namen des Benutzers erfassen.

- Aufwandsschätzung: 5 Story Points
- Akzeptanztests:
    - [ ] Feld wird auf der Kartenansicht angezeigt.
    - [ ] Daten sind gespeichert.

##### Task 1.3.1.1 Menü aufrufen

- Aufwandsschätzung: 0,2 Stunden

##### Task 1.3.1.2 Option für das Erfassen eines Feldes auswählen

- Aufwandsschätzung: 0,2 Stunden

##### Task 1.3.1.3 Kartenansicht aufrufen

- Aufwandsschätzung: 0,2 Stunden

##### Task 1.3.1.4 Koordinaten des Feldes erfassen

- Aufwandsschätzung: 1 Stunde

##### Task 1.3.1.5 Daten des Feldes in Formular eintragen

- Aufwandsschätzung: 0,5 Stunden

##### Task 1.3.1.6 Option zur graphischen Erfassung des Feldes aufrufen

- Aufwandsschätzung: 1 Stunde

##### Task 1.3.1.7 Alle erfassten Informationen abspeichern

- Aufwandsschätzung: 1 Stunde

##### Task 1.3.1.8  Daten lokal abspeichern

- Aufwandsschätzung: 0,5 Stunden

#### Implementable Story 1.3.2 Feldkoordinaten mit Sensorwerten eines Positionssensors im Gerät erfassen
> Als Gutachter oder Benutzer möchte ich ein Feld mit einem Positionssensors im Gerät erfassen, um die Position und die Größe des Guts genau darzustellen.

- Aufwandsschätzung: 20 Story Points
- Akzeptanztests:
    - [ ] Die Eckpunkte eines Feldes werden mit dem Sensor erfasst.
    - [ ] Die Fläche des Feldes wird korrekt berechnet.

##### Task 1.3.2.1 Koordinaten mit dem Positionssensor über Button auf der Karte setzen

- Aufwandsschätzung: 1 Stunden

##### Task 1.3.2.2 Koordinaten in chronologischer Reihenfolge speichern

- Aufwandsschätzung: 0,5 Stunden

##### Task 1.3.2.3 Polygon aus den Koordinaten berechnen

- Aufwandsschätzung: 1 Stunde

##### Task 1.3.2.4 Fläche des Polygons berechnen

- Aufwandsschätzung: 2 Stunden

#### Implementable Story 1.3.3 Feldkoordinaten manuell erfassen
> Als Gutachter oder Benutzer möchte ich ein Feld manuell in die Kartenansicht einzeichnen können, um die Position und die Größe des Guts genau darzustellen.

- Aufwandsschätzung: 25 Story Points
- Akzeptanztests:
    - [ ] Die Polygone der Felder werden nach der Erfassung korrekt generiert.
    - [ ] Das Feld kann in der Kartenansicht eingezeichnet werden.
    - [ ] Die Fläche des Feldes wird korrekt berechnet.

##### Task 1.3.3.1 Koordinaten manuell über Screen in Kartenansicht einzeichnen

- Aufwandsschätzung: 1,5 Stunden

##### Task 1.3.3.2 Koordinaten in chronologischer Reihenfolge speichern

- Aufwandsschätzung: 0,5 Stunden

##### Task 1.3.3.3 Polygon aus den Koordinaten generieren

- Aufwandsschätzung: 1 Stunde

##### Task 1.3.3.4 Fläche des Polygons berechnen

- Aufwandsschätzung: 2 Stunden

#### Implementable Story 1.3.4 Zustand der App beim Schließen speichern
> Als Gutachter und Benutzer der App möchte ich, dass die erfassten Felder auch nach dem Schließen (Beenden, Neustart oder als Hintergrundprozess) der App wieder abrufbar sind, um die diese Daten nicht zu verlieren und weiter bearbeiten zu können.

- Aufwandsschätzung: 35 Story Points
- Akzeptanztests:
    - [ ] Die Daten der Felder sind nach dem Schließen (Beenden, Neustart oder als Hintergrundprozess) und erneutem Öffnen der App im gleichen Zustand.

##### Task 1.3.4.1 Speichern aller Daten der Felder beim Schließen (Beenden, Neustart oder als Hintergrundprozess) der App

- Aufwandsschätzung: 1,5 Stunden

##### Task 1.3.4.2 Beim Öffnen der App die zuvor gespeicherten Daten wieder auslesen und den alten Zustand wiederherstellen.

- Aufwandsschätzung: 2 Stunden

#### Implementable Story 1.3.5 Feldinformationen aus Kartenansicht aufrufbar
> Als Gutachter und Benutzer möchte ich Felder in einer Kartenansicht angezeigt bekommen und darüber auch ihre Informationen erreichen können.

- Aufwandsschätzung: 6 Story Points
- Akzeptanztests:
    - [ ] Felder werden in der Kartenansicht dargestellt.
    - [ ] Die Polygone der Felder sind sichtbar und auswählbar.
    - [ ] Die zusätzlichen Informationen zu den Felder werden geladen und angezeigt.

##### Task 1.3.5.1 Felder auswählbar machen

- Aufwandsschätzung: 1,5 Stunden

##### Task 1.3.5.2 Informationen über das Feld aufrufen

- Aufwandsschätzung: 0,5 Stunden

##### Task 1.3.5.3 Informationen über Klick auf das Feld aufrufbar machen

- Aufwandsschätzung: 0,5 Stunde

#### Implementable Story 1.3.6 Felder bearbeiten
> Als Benutzer möchte ich ein Feld und seine Informationen bearbeiten können.

- Aufwandsschätzung: 4 Story Points
- Akzeptanztests:
    - [ ] Positionen der Felder sind angepasst worden.
    - [ ] Alle Informationen wurden aktualisiert.

##### Task 1.3.6.1 Felderverwaltung aufrufen

- Aufwandsschätzung: 0,2 Stunden

##### Task 1.3.6.2 Kartenansicht aufrufen

- Aufwandsschätzung: 0,5 Stunden

##### Task 1.3.6.3 Position der Koordinaten anpassen

- Aufwandsschätzung: 0,2 Stunden

##### Task 1.3.6.4 Polygon der Felder neu zeichnen lassen

- Aufwandsschätzung: 0,2 Stunden

##### Task 1.3.6.5 Beschreibung der Felder mit neuen Informationen aktualisieren

- Aufwandsschätzung: 0,2 Stunden

##### Task 1.3.6.6 Aktualisierte Daten abspeichern

- Aufwandsschätzung: 0,2 Stunden

#### Implementable Story 1.3.7 Feld löschen
> Als Benutzer möchte ich ein Feld löschen können.

- Aufwandsschätzung: 6 Story Points
- Akzeptanztests:
    - [ ] Polygon des Feldes ist nicht mehr auf der Karte sichtbar
    - [ ] Feld ist mit allen Informationen aus dem System entfernt.

##### Task 1.3.7.1 Felderverwaltung aufrufen

- Aufwandsschätzung: 0,2 Stunden

##### Task 1.3.7.2 Option zur Löschung eines Feldes

- Aufwandsschätzung: 0,2 Stunden

##### Task 1.3.7.3 Bestätigungsfenster öffnen

- Aufwandsschätzung: 0,2 Stunden

##### Task 1.3.7.4 Felddaten aus dem System löschen

- Aufwandsschätzung: 1 Stunde


### Feature 1.4 Benutzer- und Datenverwaltung

> Als Benutzer und Gutachter möchte ich Daten speichern und abrufen können. Als Gutachter  möchte die Benutzer verwalten (erfassen, bearbeiten, löschen) können.

- Aufwandsschätzung: M
- Akzeptanztests:
    - [ ] Bei der Anmeldung kann zwischen Versicherungsnehmer und Gutachter unterschieden werden.
    - [ ] Aktionen des Users können aufgezeichnet werden.
    - [ ] User werden mit Namen, Passwort, Telefonnummer, Email und Adresse erstellt.
    - [ ] Die Datenbank stellt Daten bereit und dient zur Speicherung von Daten.
    - [ ] Userdaten können bearbeitet werden.

#### Implementable Story 1.4.1 Anmeldungsunterscheidung
> Als Benutzer und Gutachter möchte ich, dass es zwei verschiedene User Interfaces und Anmeldemöglichkeiten für Benutzer und Gutachter gibt.

- Aufwandsschätzung: 3 Story Points
- Akzeptanztests:
    - [ ] Unterscheidung von Versicherungsnehmer und Gutachter bei der Anmeldung.

##### Task 1.4.1.1 Startbildschirm bereitstellen

- Aufwandsschätzung: 0,5 Stunden

##### Task 1.4.1.2 Anmeldung für Versicherungsnehmer aufrufen

- Aufwandsschätzung: 0,5 Stunden

##### Task 1.4.1.3 Anmeldung für Gutachter aufrufen

- Aufwandsschätzung: 0,5 Stunden

#### Implementable Story 1.4.2 Aktionen des Users aufzeichnen
> Als Benutzer und Gutachter möchte ich, dass meine Aktionen aufgezeichnet werden, um später nachvollziehen zu können, welche Aktionen vorgenommen wurden.

- Aufwandsschätzung: 10 Story Points
- Akzeptanztests:
    - [ ] Log-Daten werden erstellt.
    - [ ] Log-Daten werden gespeichert.

##### Task 1.4.2.1 Log-Daten erstellen

- Aufwandsschätzung: 4 Stunden

##### Task 1.4.2.2 Log-Daten speichern

- Aufwandsschätzung: 0,5 Stunden

#### Implementable Story 1.4.3 User erstellen
> Als Benutzer und Gutachter möchte ich die Option haben, einen User anzulegen.

- Aufwandsschätzung: 5 Story Points
- Akzeptanztests:
    - [ ] Userdaten können aufgenommen werden.
    - [ ] Userprofil wird korrekt angelegt und ein Login ist möglich.
    - [ ] Userdaten werden gespeichert.

##### Task 1.4.3.1 Userdaten aufnehmen

- Aufwandsschätzung: 1 Stunde

##### Task 1.4.3.2 Userdaten abspeichern

- Aufwandsschätzung: 1 Stunde

#### Implementable Story 1.4.4 Eigene Userdaten löschen
> Als Benutzer möchte ich Userdaten löschen können.

- Aufwandsschätzung: 7 Story Points
- Akzeptanztests:
    - [ ] User und das damit verbundene Konto ist gelöscht worden.
    - [ ] Gespeicherte Daten sind gelöscht.
    - [ ] Abruf der Daten nicht mehr möglich.
    - [ ] Anmeldung über Startbildschirm ist nicht mehr möglich.

##### Task 1.4.4.1 Account/User-Profil über Menü aufrufen

- Aufwandsschätzung: 0,2 Stunden

##### Task 1.4.4.2 Userdaten und damit verbundenes Konto über Auswahl mit mehrfacher Bestätigung löschen

- Aufwandsschätzung: 1 Stunde

#### Implementable Story 1.4.5 Datenspeicherung über Datenbank
> Als Gutachter möchte ich die Verträge und Schadensfälle aller Versicherungsnehmer speichern und herunterladen können, um diese zu einem späteren Zeitpunkt noch bearbeiten zu können.

- Aufwandsschätzung: 40 Story Points
- Akzeptanztests:
    - [ ] Datenbank kann Daten lokal speichern.
    - [ ] Daten sind offline und online verfügbar.
    - [ ] Daten können aus Datenbank abgerufen werden.

##### Task 1.4.5.1 Datenbank erstellen

- Aufwandsschätzung: 3 Stunden

##### Task 1.4.5.2 Datenbank und App verbinden

- Aufwandsschätzung: 3 Stunden

##### Task 1.4.5.3 Datenabfrage off-/online erstellen

- Aufwandsschätzung: 1,5 Stunden

##### Task 1.4.5.4 Datenspeicherung erstellen

- Aufwandsschätzung: 2 Stunden


## Epic 2 Visuelle Darstellung

> Als Benutzer und Gutachter möchte ich meine Felder, Verträge und Schadensfälle in einer Kartenansicht betrachten und betrachten können und durch diese visuelle Unterstützung gezielt durch die App navigieren.

###### *Ausführliche Beschreibung*
Dem Benutzer steht für die grundlegenden Funktionen der App eine Karte zur Verfügung, die je nach Bedarf skaliert ist und  eine Zoom-Funktion unterstützt, um die gewünschte Ansicht zu erhalten. Die Kartenansicht für Felder und Schadensfälle wird durch visuelle Abhebung - in Form von verstärkten, gefärbten Rändern und Eckpunkten - unterstützt, die es dem Benutzer erleichtert seine eingetragenen Polygone leichter auf der Karte zu erkennen. Die verschiedenen Funktionen der App sind über ein Menü zu erreichen, welches einfach über den Startbildschirm aufgerufen werden kann und in den Vordergrund geschoben wird; die Unterteilung des Menüs lässt schnell zu den Punkten navigieren, die man braucht. 
Alle registrierten Felder und Schadensfälle aber auch die Verträge werden übersichtlich in einer Listenform dargestellt, wobei die einzelnen Teile dieser Listen ausklappbar sind, um alle Informationen kompakt darzustellen und dem Benutzer diese in einem Blick zu liefern.
Für die Benutzer der App steht ein Login-Screen zur Verfügung, welcher den Benutzer beim Starten der App begrüßt.

### Feature 2.1 Visuelle Darstellung des Schadens

> Als Benutzer möchte ich, dass Schadensfälle, auch während der Erfassung, auf einer Karte angezeigt werden können. Die Schadensflächen sind visuell hervorgehoben.

- Aufwandsschätzung: L
- Akzeptanztests:
    - [ ] Schadensfälle können in einer Kartenansicht dargestellt werden.
    - [ ] Die Kartenansicht des Schadens zeigt Polygone der versicherten Objekte.
    - [ ] Die Kartenansicht des Schadens zeigt den Schaden als Polygon/Fläche innerhalb der versicherten Objekte.
    - [ ] Die Kartenansicht inkl. der Schadensdarstellung ermöglicht mehrere Maßstäbe.
    - [ ] Die Ansicht der Polygone ist ohne Internetverbindung möglich.
    - [ ] Die Schadensfälle können in einem Menü ausgewählt werden

#### Implementable Story 2.1.1 Schadensfälle können in einer Kartenansicht dargestellt werden
> Als Gutachter und Benutzer möchte ich Schadensfälle in einer Kartenansicht dargestellt bekommen, um sie mir vorstellen zu können.

- Aufwandsschätzung: 40 Story Points
- Akzeptanztests:
    - [ ] Schadensfalldaten können ausgelesen werden
    - [ ] Koordinaten werden in die Kartenansicht übertragen.
    - [ ] Koordinaten werden zu einem Polygon verbunden.
    - [ ] Die Fläche des Polygon wird eingefärbt.
    - [ ] Zusätzliche Informationen zum Polygon können angezeigt werden.
    - [ ] Standort des Schadens kann in die Kartenansicht eingetragen werden.
    - [ ] Die notwendigen Daten sind auch offline abrufbar.

##### Task 2.1.1.1 Kartenansicht erstellen

- Aufwandsschätzung: 2 Stunden

##### Task 2.1.1.2 Koordinaten in die Kartenansicht eintragen

- Aufwandsschätzung: 1 Stunde

##### Task 2.1.1.3 Polygon erstellen

- Aufwandsschätzung: 2 Stunden

##### Task 2.1.1.4 Polygonflächeninhalt einfärben

- Aufwandsschätzung: 0,5 Stunden

##### Task 2.1.1.5 Informationen zum Polygon hinzufügen

- Aufwandsschätzung: 1 Stunde

##### Task 2.1.1.6 Standort der Schäden in die Karteneinsicht eintragen

- Aufwandsschätzung: 0,5 Stunden

#### Implementable Story 2.1.2 Die Kartenansicht des Schadens zeigt Polygone der versicherten Objekte
> Als Gutachter und Benutzer möchte ich Polygone der versicherten Objekte in der Kartenansicht angezeigt bekommen.

- Aufwandsschätzung: 35 Story Points
- Akzeptanztests:
    - [ ] Daten des Schadens können ausgelesen werden.
    - [ ] Koordinaten können in die Kartenansicht übertragen werden.
    - [ ] Koordinaten können zu einem Polygon verbunden werden.
    - [ ] Die Fläche des Polygon kann eingefärbt werden.
    - [ ] Zusätzliche Informationen zum Polygon können angezeigt werden.

##### Task 2.1.2.1 Kartenansicht erstellen

- Aufwandsschätzung: 2 Stunden

##### Task 2.1.2.2 Schadensfalldaten auslesen

- Aufwandsschätzung: 0,5 Stunden

##### Task 2.1.2.3 Koordinaten in die Kartenansicht eintragen

- Aufwandsschätzung: 1 Stunde

##### Task 2.1.2.4 Polygon erstellen

- Aufwandsschätzung: 2 Stunden

##### Task 2.1.2.5 Polygonflächeninhalt einfärben

- Aufwandsschätzung: 0,5 Stunden

##### Task 2.1.2.6 Informationen zum Polygon hinzufügen

- Aufwandsschätzung: 1 Stunde

#### Implementable Story 2.1.3 Die Kartenansicht der Schäden zeigt einen Schaden als Polygon innerhalb der versicherten Objekte
> Als Gutachter und Benutzer möchte ich Schäden als Polygone in den versicherten Objekte angezeigt bekommen, um zu wissen welche Teile der versicherten Objekte beschädigt sind.

- Aufwandsschätzung: 15 Story Points
- Akzeptanztests:
    - [ ] Versicherungsobjekt werden in der Kartenansicht angezeigt.
    - [ ] Im Versicherungsobjekt können Schäden als Polygone angezeigt werden.
    - [ ] Die Schadensflächen sind visuell hervorgehoben.

##### Task 2.1.3.1 Daten des Versicherungsobjekts auslesen

- Aufwandsschätzung: 0,5 Stunden

##### Task 2.1.3.2 Versicherungsobjektkoordinaten in die Kartenansicht eintragen

- Aufwandsschätzung: 0,5 Stunden

##### Task 2.1.3.3 Versicherungsobjektkoordinaten verbinden

- Aufwandsschätzung: 0,5 Stunde

##### Task 2.1.3.4 Schadensdaten auslesen

- Aufwandsschätzung: 0,5 Stunden

##### Task 2.1.3.5 Schadenskoordinaten in der Kartenansicht eintragen

- Aufwandsschätzung: 1 Stunde

##### Task 2.1.3.6 Schadenskoordinaten verbinden

- Aufwandsschätzung: 1 Stunde

#### Implementable Story 2.1.4 Die Schadensfälle können in einer Verwaltungsansicht ausgewählt werden
> Als Gutachter und Benutzer möchte ich Schadensfälle in einem Menü auswählen können, um einen Überblick über die gemeldeten Schäden zu bekommen.

- Aufwandsschätzung: 20 Story Points
- Akzeptanztests:
    - [ ] Schadensfälle können ausgewählt werden.
    - [ ] Alle Schadensdaten werden korrekt angezeigt.
    - [ ] Die Schadensfälle werden in dem Menü angezeigt.

##### Task 2.1.4.1 Menü erstellen

- Aufwandsschätzung: 2 Stunden

##### Task 2.1.4.2 Schadensfalldaten auslesen

- Aufwandsschätzung: 1 Stunde

##### Task 2.1.4.3 Schadensfälle dem Menü hinzufügen

- Aufwandsschätzung: 0,5 Stunde

##### Task 2.1.4.4 Schadensfälle in dem Menü auswählbar machen

- Aufwandsschätzung: 1 Stunde

##### Task 2.1.4.5 Informationen über den Schadensfall einblenden

- Aufwandschätzung: 0,5 Stunde

### Feature 2.2 Darstellung der Vertragsübersichten

> Als Benutzer möchte ich, dass Verträge, die einem Feld zugeordnet wurden, übersichtlich in einer Ansicht dargestellt werden.

- Aufwandsschätzung: L
- Akzeptanztests:
    - [ ] Verträge können auf einer Kartenansicht über die Feldinformationen dargestellt werden.
    - [ ] Verträge können in einem Menü eingesehen und ausgewählt werden.
    - [ ] Die Anzahl der Verträge die angezeigt werden soll, kann verändert werden.

#### Implementable Story 2.2.1 Die Verträge können auf einer Kartenansicht über die Feldinformationen dargestellt werden
> Als Gutachter oder Benutzer möchte ich auf Verträge über die Kartenansicht zugreifen können, um sie besser zuordnen und einsehen zu können.

- Aufwandsschätzung: 40 Story Points
- Akzeptanztests:
    - [ ] Verträge können ausgewählt werden.
    - [ ] Verträge werden bei den Feldinformationen angezeigt

##### Task 2.2.1.1 Kartenansicht erstellen

- Aufwandsschätzung: 2 Stunden

##### Task 2.2.1.2 Felddaten inklusive Vertragsdaten laden

- Aufwandsschätzung: 0,5 Stunden

##### Task 2.2.1.3 Polygone erstellen

- Aufwandsschätzung: 2 Stunden

##### Task 2.2.1.4 Informationen zum Polygon hinzufügen

- Aufwandsschätzung: 1 Stunde

##### Task 2.2.1.5 Informationen zum Polygon aufrufen

- Aufwandsschätzung: 1 Stunde

#### Implementable Story 2.2.2 Verträge können in einer Verwaltungsansicht ausgewählt und eingesehen werden
> Als Gutachter oder Benutzer möchte ich Verträge geordnet in einem Menü angezeigt bekommen und sie auswählen können, um mehr Informationen über sie zu erhalten.

- Aufwandsschätzung: 20 Story Points
- Akzeptanztests:
    - [ ] Verträge können ausgewählt werden.
    - [ ] Verträge werden in der Verwaltungsansicht in einer Liste angezeigt.
    - [ ] Zugriff auf Vertragsübersicht über Menü (*siehe  Feature 2.5 Menü*) bereitgestellt.

##### Task 2.2.2.1 Menü erstellen

- Aufwandsschätzung: 2 Stunden

##### Task 2.2.2.2 Vertragsverwaltung aufrufen

- Aufwandsschätzung: 0,5 Stunden

##### Task 2.2.2.3 Verträge in einer Liste anzeigen

- Aufwandsschätzung: 1 Stunde

##### Task 2.2.2.4 Zusätzliche Informationen ausklappbar machen

- Aufwandsschätzung: 0,5 Stunden

#### Implementable Story 2.2.3 Die Anzahl der Verträge die im Menü angezeigt wird, kann verändert werden
> Als Gutachter oder Benutzer möchte ich die Anzahl der Verträge die mir angezeigt werden verändern können, um eine übersichtlichere Darstellung zu bekommen.

- Aufwandsschätzung: 10 Story Points
- Akzeptanztests:
    - [ ] Die Anzahl der Verträge die angezeigt werden kann verändert werden.

##### Task 2.2.3.1 Ansicht erstellen

- Aufwandsschätzung: 2 Stunden

##### Task 2.2.3.2 Verträge der Ansicht hinzufügen

- Aufwandsschätzung: 1 Stunde

##### Task 2.2.3.3 Kapazität der Ansicht festlegen

- Aufwandsschätzung: 2 Stunden

### Feature 2.3 Visuelle Darstellung der Felder

> Als Benutzer möchte ich eine Übersicht über meine Felder haben.

- Aufwandsschätzung: L
- Akzeptanztests:
    - [ ] Die Felder werden auf einer Kartenansicht dargestellt.
    - [ ] Die Felder sind durch Markierungen visuell hervorgehoben.
    - [ ] Registrierte Felder können in einem Menü ausgewählt und angeschaut werden.
    - [ ] Die Kartenansicht der Felder ist in ihrer Anzeigegröße veränderbar.

#### Implementable Story 2.3.1 Felder werden auf einer verfügbaren Karte eingezeichnet
> Als Benutzer möchte ich meine Felder sichtbar auf einer Karte einzeichnen und betrachten können.

- Aufwandsschätzung: 15 Story Points
- Akzeptanztests:
    - [ ] Die Polygone der Felder sind deutlich erkennbar.
    - [ ] Die Polygone der Felder wurden erstellt.

##### Task 2.3.1.1 Kartenansicht laden

- Aufwandsschätzung: 0,5 Stunden

##### Task 2.3.1.2 Polygone durch Koordinaten generieren

- Aufwandsschätzung: 1 Stunde

##### Task 2.3.1.3 Kanten und Eckpunkte der Polygone hervorheben

- Aufwandsschätzung: 0,5 Stunden


#### Implementable Story 2.3.2 Felder werden auf einer verfügbaren Karte dargestellt
> Als Benutzer möchte ich meine Felder auf einer Karte angezeigt bekommen, um eine Übersicht zu bekommen.

- Aufwandsschätzung: 15 Story Points
- Akzeptanztests:
    - [ ] Die bereits erstellten Felder werden korrekt auf der Karte angezeigt.

##### Task 2.3.2.1 Kartenansicht erstellen

- Aufwandsschätzung: 2 Stunden

##### Task 2.3.2.2 Daten der Felder abrufen

- Aufwandsschätzung: 0,5 Stunden

##### Task 2.3.2.3 Polygone generieren

- Aufwandsschätzung: 1 Stunde


#### Implementable Story 2.3.3 Felder sind durch ihre Markierungen hervorgehoben
> Als Benutzer will ich, dass meine Felder gut deutlich auf der Karte erkennbar sind.

- Aufwandsschätzung: 10 Story Points
- Akzeptanztests:
    - [ ] Die Polygone der Felder heben sich durch explizite Farbgebung vom Rest der Karte ab.

##### Task 2.3.3.1 Positionsdaten der Felder lesen

- Aufwandsschätzung: 0,3 Stunden

##### Task 2.3.3.2 Die Polygonseiten der Felder werden eingefärbt

- Aufwandsschätzung: 0,5 Stunden

#### Implementable Story 2.3.4 Felder können in einer Verwaltungsansicht betrachtet und ausgewählt werden
> Als Benutzer möchte ich über ein Menü einen geordneten Zugriff auf alle meine Felder haben.

- Aufwandsschätzung: 30 Story Points
- Akzeptanztests:
    - [ ] Alle Felder sind im Menü enthalten.
    - [ ] Alle Felder können ausgewählt werden.
    - [ ] Für jedes Feld werden die entsprechenden Informationen nach der Auswahl angezeigt.

##### Task 2.3.4.1 Verwaltungsansicht erstellen

- Aufwandsschätzung:  1 Stunde

##### Task 2.3.4.2 Daten der Felder auslesen

- Aufwandsschätzung:  0,2 Stunden

##### Task 2.3.4.3 Felder dem Ansicht hinzufügen

- Aufwandsschätzung:  0,5 Stunde

##### Task 2.3.4.4 Felder können aus einer Liste ausgewählt werden

- Aufwandsschätzung:  0,5 Stunden

##### Task 2.3.4.5 Feldinformation können über Feldauswahl eingeblendet werden

- Aufwandsschätzung:  0,5 Stunden

### Feature 2.4 Visuelle Darstellungen der Funktionen für den Benutzer

> Als Benutzer möchte ich eine Oberfläche für den Zugriff auf die App und meine persönlichen Daten haben.

- Aufwandsschätzung: M
- Akzeptanztests:
    - [ ] Der Benutzer hat einen Startbildschirm mit Login-Funktion.
    - [ ] Der Benutzer kann über ein Menü auf die Funktionen der App zugreifen.
    - [ ] Der Benutzer kann seine persönlichen Daten einsehen.

#### Implementable Story 2.4.1 Zugriff auf Funktionen über Menü
> Als Benutzer möchte ich persönlichen, hinterlegten Daten und bereitgestellte Funktionen über ein Menü aufrufen können.

- Aufwandsschätzung: 25 Story Points
- Akzeptanztests:
    - [ ] Es besteht im Menü ein Zugriff auf alle angezeigten Punkte.
    - [ ] Die hinterlegten Punkte sind auswählbar und rufen die korrekten Informationen auf.

##### Task 2.4.1.1 Menü über Button aus Startansicht heraus aufrufen

- Aufwandsschätzung:  1 Stunde

##### Task 2.4.1.2 Menü in den Vordergrund schieben

- Aufwandsschätzung:  1 Stunde

##### Task 2.4.1.3 Menüpunkte laden und anzeigen

- Aufwandsschätzung:  0,5 Stunden

##### Task 2.4.1.4 Sichtbare Unterteilung der Menüpunkte

- Aufwandsschätzung:  0,3 Stunden

##### Task 2.4.1.5 Menü-Unterpunkte auswählbar machen

- Aufwandsschätzung:  0,3 Stunden


#### Implementable Story 2.4.2 Zugriff auf Account über Startbildschirm
> Als Gutachter und Benutzer möchte ich über ein gesondertes Fenster Zugriff auf meine Daten haben.

- Aufwandsschätzung: 15 Story Points
- Akzeptanztests:
    - [ ] Die App startet mit einem Startbildschirm (*siehe Implementable Story 1.4.2 Anmeldungsunterschied*).
    - [ ] Es wird die Login-Funktion angezeigt.

##### Task 2.4.2.1 Startbildschirm erstellen

- Aufwandsschätzung:  1 Stunde

##### Task 2.4.2.2 Login-Maske bereitstellen

- Aufwandsschätzung: 1 Stunde

### Feature 2.5 Menü

> Als Gutachter und Benutzer möchte ich über ein Menü Zugriff auf alle möglichen Funktionen innerhalb der App bekommen und gezielt durch die App navigieren.

- Aufwandsschätzung: L
- Akzeptanztests:
    - [ ] Das Menü kann aufgerufen werden.
    - [ ] Über das Menü sind alle Unterpunkte zu erreichen.
    - [ ] Im Menü sind alle Verwaltungsoptionen (Verträge, Felder, Schadensmeldungen, Account, Infos) aufgelistet und auswählbar.
    - [ ] Alle auswählbaren Menüpunkte erfüllen die erwartete Funktion.

#### Implementable Story 2.5.1 Menü erstellen
> Als Gutachter und Benutzer möchte ich über ein Menü meine gewünschten Funktionen aufrufen.

- Aufwandsschätzung: 18 Story Points
- Akzeptanztests:
    - [ ] Das Menü kann aufgerufen werden.
    - [ ] Das Menü enthält alle angelegten Menüpunkte.

##### Task 2.5.1.1 Menü für die App erstellen

- Aufwandsschätzung: 1,5 Stunden

##### Task 2.5.1.2 Menü über Button aufrufen

- Aufwandsschätzung: 1 Stunde

#### Implementable Story 2.5.2 Menü für Verträge
> Als Gutachter und Benutzer möchte ich einen Menüpunkt für Verträge haben.

- Aufwandsschätzung: 10 Story Points
- Akzeptanztests:
    - [ ] Das Menü kann vollständig aufgerufen werden.
    - [ ] Der Menüpunkt ruft die Verwaltung für Verträge auf.

##### Task 2.5.2.1 Menüpunkt für Verträge erstellen

-  Aufwandsschätzung: 0,2 Stunden

##### Task 2.5.2.2 Menüpunkt auswählbar machen

-  Aufwandsschätzung: 0,2 Stunden

##### Task 2.5.2.3 Verwaltungsansicht für Verträge über Menüpunkt aufrufen

- Aufwandsschätzung: 0,2 Stunden

#### Implementable Story 2.5.3 Menü für Felder
> Als Gutachter und Benutzer möchte ich einen Menüpunkt für Felder haben.

- Aufwandsschätzung: 10 Story Points
- Akzeptanztests: 
    - [ ] Das Menü kann vollständig aufgerufen werden.
    - [ ] Der Menüpunkt ruft die Verwaltung für die Felder des Benutzers auf.

##### Task 2.5.3.1 Menüpunkt für Felder erstellen

-  Aufwandsschätzung: 0,2 Stunden

##### Task 2.5.3.2 Menüpunkt auswählbar machen

-  Aufwandsschätzung: 0,2 Stunden

##### Task 2.5.3.3 Verwaltungsansicht für Felder über Menüpunkt aufrufen

- Aufwandsschätzung: 0,2 Stunden

#### Implementable Story 2.5.4 Menü für Schadensmeldungen
> Als Gutachter und Benutzer möchte ich einen Menüpunkt für meine gemeldeten Schadensfälle haben, um eine Übersicht über diese zu erhalten und deren Status einzusehen.

- Aufwandsschätzung: 10 Story Points
- Akzeptanztests:
    - [ ] Die registrierten Schadensfälle werden in einer Ansicht in einer Liste dargestellt.
    - [ ] Das Menü kann vollständig aufgerufen werden.
    - [ ] Der Menüpunkt ruft die Verwaltung für die Schadensmeldungen auf.

##### Task 2.5.4.1 Menüpunkt für Accountdetails erstellen

-  Aufwandsschätzung: 0,2 Stunden

##### Task 2.5.4.2 Menüpunkt auswählbar machen

-  Aufwandsschätzung: 0,2 Stunden

##### Task 2.5.4.3 Ansicht für Details über Menüpunkt aufrufen

- Aufwandsschätzung: 0,2 Stunden

#### Implementable Story 2.5.5 Menü für Account-Details
> Als Gutachter und Benutzer möchte ich einen Menüpunkt für meinen Account haben, um dessen Details anzuschauen.

- Aufwandsschätzung: 10 Story Points
- Akzeptanztests: 
    - [ ] Das Menü kann vollständig aufgerufen werden.
    - [ ] Der Menüpunkt ruft eine Übersicht der Benutzerdaten auf.

##### Task 2.5.5.1 Menüpunkt für Accountdetails erstellen

-  Aufwandsschätzung: 0,2 Stunden

##### Task 2.5.5.2 Menüpunkt auswählbar machen

-  Aufwandsschätzung: 0,2 Stunden

##### Task 2.5.5.3 Ansicht für Details über Menüpunkt aufrufen

- Aufwandsschätzung: 0,2 Stunden

#### Implementable Story 2.5.6 Menü für App-Informationen
> Als Gutachter und Benutzer möchte ich allgemeine Infos über die App einsehen.

- Aufwandsschätzung: 8 Story Points
- Akzeptanztests: 
    - [ ] Das Menü kann vollständig aufgerufen werden.
    - [ ] Es werden Informationen (Version, Lizenz, Name, Entwickler) der App abgerufen und angezeigt.

##### Task 2.5.6.1 Menüpunkt für App-Informationen erstellen

-  Aufwandsschätzung: 0,2 Stunden

##### Task 2.5.6.2 Menüpunkt auswählbar machen

-  Aufwandsschätzung: 0,2 Stunden

##### Task 2.5.6.3 Infofenster aufrufen

- Aufwandsschätzung: 0,2 Stunden

### Feature 2.6 Kartenfunktion
> Als Gutachter und Benutzer möchte ich immer eine Karte zur Verfügung gestellt bekommen, wenn ich Schäden, Felder und Verträge betrachten und bearbeiten will.

- Aufwandsschätzung: L
- Akzeptanztests:
    - [ ] Die Kartenansicht wird korrekt dargestellt.
    - [ ] Die Karten sind hinterlegt und somit offline verfügbar.
    - [ ] Die Karten werden bei jedem Aufruf generiert.
    - [ ] Die auf der Karte eingezeichneten Polygone sind immer sichtbar.
    - [ ] Karten sind skalierbar und besitzen eine Zoom-Funktion.

#### Implementable Story 2.6.1 Polygone sind immer sichtbar
> Als Gutachter und Benutzer möchte ich die Polygone der Felder und Schadensfälle sehen können, selbst wenn keine Internet-Verbindung besteht und damit womöglich keine Karte verfügbar ist.

- Aufwandsschätzung: 20 Story Points
- Akzeptanztests:
    - [ ] Die Daten der Polygone sind lokal abrufbar.
    - [ ] Die auf den Karten angezeigten Informationen bezüglich der Polygone werden aktualisiert und angezeigt.
    - [ ] Die Polygone werden richtig geladen und angezeigt.

##### Task 2.6.1.1 Daten der Polygone laden
-  Aufwandsschätzung: 0,5 Stunden

##### Task 2.6.1.2 Polygone und zugehörige Informationen auf Karte laden

-  Aufwandsschätzung: 1 Stunde

#### Implementable Story 2.6.2 Karten sind skalierbar und vergrößerbar (Zoom)
> Als Gutachter und Benutzer möchte ich die Karte in einem angemessenen Format sehen, um einen besseren Überblick zu bekommen.

- Aufwandsschätzung: 16 Story Points
- Akzeptanztests:
    - [ ] Die Karten sind erfolgreich heruntergeladen.
    - [ ] Die Karten sind offline lokal abrufbar.
    - [ ] Die angezeigten Karten sind auf die richtige Größe angepasst.
    - [ ] Die Karten sind vergrößerbar.

##### Task 2.6.2.1 Karten laden

-  Aufwandsschätzung: 0,5 Stunden

##### Task 2.6.2.2 Karten-Skalierung verfügbar machen

-  Aufwandsschätzung: 2 Stunden

##### Task 2.6.2.3 Karten ermöglichen Zoom-Funktion

-  Aufwandsschätzung: 2 Stunden

#### Implementable Story 2.6.3 Karten sind offline verfügbar
> Als Gutachter und Benutzer möchte ich die Karte sehen können, selbst wenn keine Internet-Verbindung besteht.

- Aufwandsschätzung: 18 Story Points
- Akzeptanztests:
    - [ ] Die Karten sind erfolgreich heruntergeladen.
    - [ ] Die Karten sind offline lokal abrufbar.
    - [ ] Die auf Karten angezeigten Informationen werden aktualisiert.

##### Task 2.6.3.1 Lokal gespeicherte Karten laden
-  Aufwandsschätzung: 0,7 Stunden

##### Task 2.6.3.2 Polygone und Informationen auf Karten laden

-  Aufwandsschätzung: 1,2 Stunden