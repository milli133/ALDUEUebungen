package A03_DoubleLinkedList;

public class DoubleLinkedList<T> {

    // der "Anfang" der Liste
    private Node<T> first;
    // das "Ende" der Liste
    private Node<T> last;
    // der "Iterator"
    private Node<T> current;

    /**
     * Einf�gen einer neuen <T>
     *
     * @param a <T>
     */
    public void add(T a) {

        Node<T> newElement = new Node<>(a);
        // temp merkt sich beim Anf�gen das "damals letzte Element", welches man sonst �berschreiben w�rde
        Node<T> temp = null;

        // wenn Liste leer, dann ist das neue erste und letzte Element gleich dem neuen Element
        if (first == null)
            first = newElement;
        // wenn nicht, dann wird das Eleent nur angef�gt
        else {
            last.setNext(newElement);
            temp = last;
        }
        last = newElement;
        last.setPrevious(temp);
    }

    /**
     * Internen Zeiger f�r next() zur�cksetzen
     */
    public void reset() {

        current = first;
    }

    /**
     * analog zur Funktion reset()
     */
    public void resetToLast() {

        current = last;
    }

    /**
     * Liefert erste Node der Liste retour oder null, wenn Liste leer
     *
     * @return Node|null
     */
    public Node<T> getFirst() {

        return first;
    }

    /**
     * Liefert letzte Node der Liste retour oder null, wenn Liste leer
     *
     * @return Node|null
     */
    public Node<T> getLast() {

        return last;
    }

    /**
     * Gibt aktuelle <T> zur�ck und setzt internen Zeiger weiter.
     * Falls current nicht gesetzt, wird null retourniert.
     *
     * @return <T>|null
     */
    public T next() {

        T returnData;

        if (current == null)
            return null;

        returnData = current.getData();
        current = current.getNext();

        return returnData;
    }

    /**
     * analog zur Funktion next()
     *
     * @return <T>|null
     */
    public T previous() {

        T returnData;

        if (current == null)
            return null;

        returnData = current.getData();
        current = current.getPrevious();

        return returnData;
    }

    /**
     * Current-Pointer auf n�chste <T> setzen (aber nicht auslesen).
     * Ignoriert still, dass current nicht gesetzt ist.
     */
    public void moveNext() {

        if (current != null)
            current = current.getNext();
    }

    /**
     * Analog zur Funktion moveNext()
     */
    public void movePrevious() {

        if (current != null)
            current = current.getPrevious();
    }

    /**
     * Retourniert aktuelle (current) <T>, ohne Zeiger zu �ndern
     *
     * @return <T>
     * @throws CurrentNotSetException
     */
    public T getCurrent() throws CurrentNotSetException {

        if (current == null)
            throw new CurrentNotSetException();

        return current.getData();
    }

    /**
     * Gibt <T> an bestimmter Position zur�ck
     *
     * @param pos Position, Nummerierung startet mit 1
     * @return <T>|null
     */
    public T get(int pos) {

        Node<T> n = first;
        int i = 1;

        if (n == null)
            return null;

        while (i != pos && n != null) {
            i++;
            n = n.getNext();
        }

        return n.getData();
    }

    /**
     * Entfernen des Elements an der angegebenen Position.
     * Falls das entfernte Element das aktuelle Element ist, wird current auf null gesetzt.
     *
     * @param pos
     */
    public void remove(int pos) {

        Node<T> toRemove = first;
        int cnt = 1;

        // zur Stelle von pos iterieren
        while (cnt != pos && toRemove != null) {
            cnt++;
            toRemove = toRemove.getNext();
        }

        if (toRemove == null)
            return;

        // siehe Methodenbeschreibung
        if (toRemove == current)
            current = null;

        // wenn das erste Element gel�scht wird, gibts kein previous und first wird eins weiter geschoben
        if (toRemove == first) {
            first = first.getNext();
            first.setPrevious(null);
            return;
        }

        // wenn das letzte Element gel�scht wird, gibts kein next und last wird eins nach vorne geschoben
        if (toRemove == last) {
            last = last.getPrevious();
            last.setNext(null);
            return;
        }

        // "else"
        // zwischendrinnen wird gel�scht
        toRemove.getPrevious().setNext(toRemove.getNext());
        toRemove.getNext().setPrevious(toRemove.getPrevious());
    }

    /**
     * Entfernt das aktuelle Element.
     * Als neues aktuelles Element wird der Nachfolger gesetzt oder
     * (falls kein Nachfolger) das vorhergehende Element
     *
     * @throws CurrentNotSetException
     */
    public void removeCurrent() throws CurrentNotSetException {

        if (current == null)
            throw new CurrentNotSetException();

        Node<T> toRemove = current;

        // meine Vorgehensweise:
        // immer zuerst mit den Extremf�llen anfangen und diese ausprogrammieren
        // alles andere in "else" packen

        // wenn das einzige Element gel�scht wird wird alles auf null gesetzt
        if (current == first && current == last) {
            current = null;
            first = null;
            last = null;
            return;
        }

        // wenn das erste Element gel�scht wird: nach vorne verlinken und first neu zuweisen
        if (current == first) {
            first = first.getNext();
            first.setPrevious(null);
            current = first;
            // return > elseif (auch laufzeittechnisch besser)
            return;
        }

        // wenn das letzte Element gel�scht wird: nach hinten verlinken und last neu zuweisen
        if (current == last) {
            last = last.getPrevious();
            last.setNext(null);
            current = last;
            // return > elseif (auch laufzeittechnisch besser)
            return;
        }

        // "else"
        // wenn zwischendrinnen gel�scht wird, vorne und hinten neu verlinken, indem man
        // vom toRemove die Verlinkungen vorne und hinten ans current und seinen Nachbarn �bertr�gt
        current = current.getNext();
        toRemove.getPrevious().setNext(current);
        toRemove.getNext().setPrevious(current);
        current.setNext(toRemove.getNext().getNext());
        current.setPrevious(toRemove.getPrevious());
    }

    /**
     * Die Methode f�gt die �bergebene <T> nach der aktuellen (current) ein
     * und setzt dann die neu eingef�gte <T> als aktuelle (current) <T>.
     *
     * @throws CurrentNotSetException
     */
    public void insertAfterCurrentAndMove(T a) throws CurrentNotSetException {

        if (current == null)
            throw new CurrentNotSetException();

        Node<T> toInsert = new Node<>(a);

        // an der letzten Stelle einf�gen (nach hinten verlinken und last setzen)
        if (current == last) {
            current.setNext(toInsert);
            toInsert.setPrevious(current);
            last = toInsert;
        } else {
            // zwischendrinnen einf�gen (beide Seiten verlinken)
            toInsert.setNext(current.getNext());
            current.setNext(toInsert);
            toInsert.setPrevious(current);
        }
        // in jedem Fall wird der Zeiger auf das gerade eingef�gte Element gesetzt
        current = toInsert;
    }
}