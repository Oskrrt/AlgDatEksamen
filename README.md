# Mappeeksamen i Algoritmer og Datastrukturer Høst 2020

# Krav til innlevering

Se oblig-tekst for alle krav, og husk spesielt på følgende:

* Git er brukt til å dokumentere arbeid (minst 2 commits per oppgave, beskrivende commit-meldinger)	
* git bundle er levert inn
* Hovedklassen ligger i denne path'en i git: src/no/oslomet/cs/algdat/Eksamen/EksamenSBinTre.java
* Ingen debug-utskrifter
* Alle testene i test-programmet kjører og gir null feil (også spesialtilfeller)
* Readme-filen her er fyllt ut som beskrevet


# Beskrivelse av oppgaveløsning (4-8 linjer/setninger per oppgave)

Vi har brukt git til å dokumentere arbeidet vårt. Jeg har 16 commits totalt, og hver logg-melding beskriver det jeg har gjort av endringer.

* Oppgave 1: Løste ved å implementere ...
* Oppgave 2: Løste ved å bruke en while loop lik som den i inneholder, bare at i "else" statementet så plusser den på <br/>
1 på count variabelen og går videre til høyre for å finne flere frem til den ikke lenger finner fler. <br/> 
Går til høyre fordi den leter etter duplicates og tall som er større eller lik verdi ligger alltid til høyre
* Oppgave 3: førstePostorden ble løst ved bruk av rekursjon, denne sender med enten p.høyre eller p.venstre ettersom
hvilke av de som er NULL eller ikke. <br/>nestePostorden ble løst ved hjelp av if-setninger som oppfyller sjekklisten under punkt 5.1.7 i kompendiet.
* Oppgave 4: <strong>I postOrden</strong> metoden ble det brukt en while-loop som kjørte så lenge p != null. Inne i loopen
ble oppgaven utført og p ersatttet med nestePostorden(p).<br/> <strong>I postOrdenRecursive</strong> hentet jeg først den førstePostOrden.
Deretter ble postOrdenRecursive(p, oppgave) kalt på med denne verdien. Denne forstatte å kalle på seg selv med postOrdenRecursive(nestePostOrden(p)),
helt frem til p == null.
* Oppgave 6: <strong>I fjern</strong> metoden la jeg merke til at ved tilfelle 2 (p har eksakt et barn) så pekte barnet til den fjernede noden
sin foreldre peker til den fjernede noden. Her la jeg til if(b!=null) b.forelder = q; Jeg måtte også legge til if (b != null) b.foreler = null, inne i if (p == rot) Dette fikset problemet med foreldre pekerne.
<br/><strong>I fjernAlle</strong> metoden brukte jeg antall(T verdi) metoden for å finne antall forekomster av verdi
Deretter ble det brukt en while løkke som loopet så lenge antall slettede var færre enn antallForekomster. Inne i while loopen
brukte jeg fjern metoden med verdi, dersom denne returnerte true ble antallSlettede inkrementert med 1.<br/>
<strong>I nullstill</strong> metoden ble det brukt en while loop som fjerner en og en node i post orden. Til slutt setter den rotnoden til null.
Den minker også antall variabelen for hver node som nulles.