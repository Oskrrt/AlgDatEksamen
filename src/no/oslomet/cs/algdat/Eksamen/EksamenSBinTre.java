package no.oslomet.cs.algdat.Eksamen;


import java.util.*;

public class EksamenSBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public EksamenSBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden

        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    // kopiert fra kompendie 5.2.3 a)
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");
        Node<T> p = rot, q = null;               // p starter i roten
        int cmp = 0;                             // hjelpevariabel

        while (p != null)       // fortsetter til p er ute av treet
        {
            q = p;                                 // q er forelder til p
            cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
            p = cmp < 0 ? p.venstre : p.høyre;     // flytter p
        }

        // p er nå null, dvs. ute av treet, q er den siste vi passerte

        p = new Node<>(verdi, q);                   // oppretter en ny node

        if (q == null) rot = p;                  // p blir rotnode
        else if (cmp < 0) q.venstre = p;         // venstre barn til q
        else q.høyre = p;                        // høyre barn til q

        antall++;                                // én verdi mer i treet
        return true;
    }
    // kode kopiert fra kompendie, seksjon 5.2.8 d)
    public boolean fjern(T verdi) {
        if (verdi == null) return false;  // treet har ingen nullverdier

        Node<T> p = rot, q = null;   // q skal være forelder til p

        while (p != null)            // leter etter verdi
        {
            int cmp = comp.compare(verdi,p.verdi);      // sammenligner
            if (cmp < 0) { q = p; p = p.venstre; }      // går til venstre
            else if (cmp > 0) { q = p; p = p.høyre; }   // går til høyre
            else break;    // den søkte verdien ligger i p
        }
        if (p == null) return false;   // finner ikke verdi

        if (p.venstre == null || p.høyre == null)  // Tilfelle 1) og 2)
        {
            Node<T> b = p.venstre != null ? p.venstre : p.høyre;  // b for barn
            if (p == rot) {
                if (b != null) b.forelder = null;
                rot = b;
            }
            else if (p == q.venstre) {
                q.venstre = b;
                if (b != null) b.forelder = q;             // legge til riktig foreldre peker
            }
            else {
                q.høyre = b;
                if (b != null) b.forelder = q;             // legge til riktig foreldre peker
            }
        }
        else  // Tilfelle 3)
        {
            Node<T> s = p, r = p.høyre;   // finner neste i inorden
            while (r.venstre != null)
            {
                s = r;    // s er forelder til r
                r = r.venstre;
            }

            p.verdi = r.verdi;   // kopierer verdien i r til p

            if (s != p) s.venstre = r.høyre;
            else s.høyre = r.høyre;
        }

        antall--;   // det er nå én node mindre i treet
        return true;
    }

    public int fjernAlle(T verdi) {
        if (tom()) return 0; // Hvis treet er tomt, skal 0 returneres.
        int antallForekomster = antall(verdi);
        int antallSlettede = 0;
        while (antallSlettede < antallForekomster) {
            if (fjern(verdi)) antallSlettede++;
        }
        return antallSlettede;
    }

    public int antall(T verdi) {
        Objects.requireNonNull(verdi, "Null verdi ikke tillat");
        if(!inneholder(verdi)) {
            return 0;
        }
        Node<T> p = rot;

        int count = 0;
        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else {  // dersom den finner verdi plusser den på count og beveger seg til høyre
                count++;
                p = p.høyre;
            }
        }
        return count;
    }
    // bruker en while loop som kjører gjennom så lenge treet ikke er tomt
    // Deretter setter den current = nestePostOrden og minker antall med 1.
    // Dersom current = rot så setter den rot = null, antall-- og går ut av løkken og metoden.
    public void nullstill() {
        if (tom()) return;
        Node<T> current = førstePostorden(rot);
        while (!tom()) {
            if (current == rot) {
                rot = null;
                antall--;
                break;
            }
            Node<T> neste = nestePostorden(current);
            current = neste;
            antall--;
        }
    }

    public boolean amIrightChild(Node<T> p, Node<T> f) {
        int cmp = comp.compare(p.verdi, f.verdi);
        return cmp >= 0;
    }

    private static <T> Node<T> førstePostorden(Node<T> p) {
        if (p.venstre != null) {
            p = førstePostorden(p.venstre);
        } else if (p.høyre != null) {
            p = førstePostorden(p.høyre);
        }
        return p;
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {
        /* Kopiert inn sjekklisten fra kompendiet, denne ble fulgt under utvikling av metoden
        1. Hvis p ikke har en forelder ( p er rotnoden), så er p den siste i postorden.
        2. Hvis p er høyre barn til sin forelder f, er forelderen f den neste.
        3. Hvis p er venstre barn til sin forelder f, gjelder:
        3.1. Hvis p er enebarn (f.høyre er null), er forelderen f den neste.
        3.2 .Hvis p ikke er enebarn (dvs. f.høyre er ikke null), så er den neste den noden som kommer først i postorden i subtreet med f.høyre som rot.*/

        if (p.forelder == null) return null; // 1.
        if (p == p.forelder.høyre) {
            return p.forelder; // 2.
        } else if (p == p.forelder.venstre) { // 3.
            if (p.forelder.høyre == null) {
                return p.forelder; // 3.1.
            } else {
                return førstePostorden(p.forelder.høyre); // 3.2.
            }
        }
        return p;
    }
    // Henter noden som kommer først i postorden og looper gjennom. Inne i loopen blir oppgaven utført og p blir til neste node i postorden
    public void postorden(Oppgave<? super T> oppgave) {
        if(tom()) return;
        Node<T> p = førstePostorden(rot);
        while (p != null) {
            oppgave.utførOppgave(p.verdi);
            p = nestePostorden(p);
        }
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        if(tom()) return;
        Node<T> p = førstePostorden(rot);
        postordenRecursive(p, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        if (p == null) {
            return;
        }
        oppgave.utførOppgave(p.verdi);
        postordenRecursive(nestePostorden(p), oppgave);
    }
    // kode kopiert fra kompendie Programkode 5.1.6 a). Endringer gjort for å fungere med ArrayDeque istedenfor TabbellKø.
    public ArrayList<T> serialize() {
        ArrayDeque<Node<T>> queue = new ArrayDeque<>();
        ArrayList<T> serialized = new ArrayList<>();
        queue.add(rot);
        while (!queue.isEmpty()) {
            Node<T> p = queue.pop();
            serialized.add(p.verdi);
            if (p.venstre != null) queue.add(p.venstre);
            if (p.høyre != null) queue.add( p.høyre);
        }
        return serialized;
    }
    // oppretter et tre, looper gjennom verdiene i arraylisten og legger inn en og en verdi i treet.
    static <K> EksamenSBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        EksamenSBinTre<K> tree = new EksamenSBinTre<>(c);
        for (K value : data) {
            tree.leggInn(value);
        }
        return tree;
    }


} // ObligSBinTre
