# Mappeeksamen i Algoritmer og Datastrukturer Høst 2020
# Beskrivelse av oppgaveløsning

* Oppgave 1: Løste ved å kopiere kildekode fra kompendie, programkode 5.2.3 a). Endringen som ble gjort for å få riktig
foreldre peker var å sende med q i konstruktøren til New Node();
* Oppgave 2: Løste ved å bruke en while loop lik som den i inneholder, bare at i "else" statementet så plusser den på <br/>
1 på count variabelen og går videre til høyre for å finne flere frem til den ikke lenger finner fler. <br/> 
Går til høyre fordi den leter etter duplicates og tall som er større eller lik verdi ligger alltid til høyre
* Oppgave 3: førstePostorden ble løst ved bruk av rekursjon, denne sender med enten p.høyre eller p.venstre ettersom
hvilke av de som er NULL eller ikke. <br/>nestePostorden ble løst ved hjelp av if-setninger som oppfyller sjekklisten under punkt 5.1.7 i kompendiet.
* Oppgave 4: <strong>I postOrden</strong> metoden ble det brukt en while-loop som kjørte så lenge p != null. Inne i loopen
ble oppgaven utført og p ersatttet med nestePostorden(p).<br/> <strong>I postOrdenRecursive</strong> hentet jeg først den førstePostOrden.
Deretter ble postOrdenRecursive(p, oppgave) kalt på med denne verdien. Denne forstatte å kalle på seg selv med postOrdenRecursive(nestePostOrden(p)),
helt frem til p == null.
* Oppgave 5: <strong>I serialize</strong> metoden kopierte jeg kode fra kompendie, Programkode 5.1.6 a) og gjorde nødvendige
endringer for at det skulle funke med ArrayDeque<> istedenfor TabbellKø<>. Istedenfor å skrive ut p.verdi, la jeg p.verdi inn i et ArrayList<> og til slutt returnerte arraylisten.
<br/> <strong>I deserialize</strong> brukte jeg en enkel for-each loop til å legge hver verdi fra ArrayListen inn i treet.
* Oppgave 6: <strong>I fjern</strong> metoden la jeg merke til at ved tilfelle 2 (p har eksakt et barn) så pekte barnet til den fjernede noden
sin foreldre peker til den fjernede noden. Her la jeg til if(b!=null) b.forelder = q; Jeg måtte også legge til if (b != null) b.foreler = null, inne i if (p == rot) Dette fikset problemet med foreldre pekerne.
<br/><strong>I fjernAlle</strong> metoden brukte jeg antall(T verdi) metoden for å finne antall forekomster av verdi
Deretter ble det brukt en while løkke som loopet så lenge antall slettede var færre enn antallForekomster. Inne i while loopen
brukte jeg fjern metoden med verdi, dersom denne returnerte true ble antallSlettede inkrementert med 1.<br/>
<strong>I nullstill</strong> metoden ble det brukt en while loop som fjerner en og en node i post orden. Til slutt setter den rotnoden til null.
Den minker også antall variabelen for hver node som nulles.

# Warnings
Alle warnings som prosjektet har er der pga. hvordan prosjektet ble satt opp. Jeg har valgt å ikke endre noe på prosjektets
struktur ut ifra sånn det var når vi fikk oppgaven. Warnings inkluderer: 
* Access can be package private, denne warningen står på alle metoder som er public.
* Non-ASCII characters is an identifier, dette er pga. right_child pekeren heter høyre og førstePreOrden metoden.
* Private field 'endringer' is assigned but never used. Denne variablen blir ikke nevnt i noen av oppgavene.
* På linje 200 i EksamenSBinTre.java måtte jeg legge til if (current != null) for å ikke få warning om at current might be null.
Sånn jeg har bygget opp metoden vil den egentlig aldri være null, fordi eneste måten nestePostOrden returnerer null er om rot noden er neste.
og dette blir allerede håndtert av if (current == rot). 