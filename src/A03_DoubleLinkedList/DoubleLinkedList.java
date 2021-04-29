package A03_DoubleLinkedList;

public class DoubleLinkedList<T> {
    private Node<T> first;
    private Node<T> last;
    private Node<T> current;


    /**
     * Einfügen einer neuen <T>
     *
     * @param a <T>
     */
    public void add(T a) {
        Node<T> newElement = new Node<T>(a);
        Node<T> temp = null;

        if (first == null)
            first = newElement;
        else {
            last.setNext(newElement);
            temp = last;
        }
        last = newElement;
        last.setPrevious(temp);
        //current = newElement;
    }

    /**
     * Internen Zeiger für next() zurücksetzen
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
     * Gibt aktuelle <T> zurück und setzt internen Zeiger weiter.
     * Falls current nicht gesetzt, wird null retourniert.
     *
     * @return <T>|null
     */
    public T next() {

        T returnData = null;

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

        T returnData = null;

        if (current == null)
            return null;

        returnData = current.getData();
        current = current.getPrevious();

        return returnData;
    }

    /**
     * Current-Pointer auf nächste <T> setzen (aber nicht auslesen).
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
     * Retourniert aktuelle (current) <T>, ohne Zeiger zu ändern
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
     * Gibt <T> an bestimmter Position zurück
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

        while (cnt != pos && toRemove != null) {
            cnt++;
            toRemove = toRemove.getNext();
        }

        if (toRemove == null)
            return;

        if (toRemove == current)
            current = null;

        if (toRemove == first) {
            first = first.getNext();
            first.setPrevious(null);
            return;
        }

        if (toRemove == last) {
            last = last.getPrevious();
            last.setNext(null);
            return;
        }

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

        if (current == first && current == last) {
            current = null;
            first = null;
            last = null;
            return;
        }

        if (current == first) {
            current = null;
            first = first.getNext();
            first.setPrevious(null);
            current = first;
            return;
        }

        if (current == last) {
            current = null;
            last = last.getPrevious();
            last.setNext(null);
            current = last;
            return;
        }

        current = current.getNext();
        toRemove.getPrevious().setNext(current);
        toRemove.getNext().setPrevious(current);
        current.setNext(toRemove.getNext().getNext());
        current.setPrevious(toRemove.getPrevious());
    }

    /**
     * Die Methode fügt die übergebene <T> nach der aktuellen (current) ein
     * und setzt dann die neu eingefügte <T> als aktuelle (current) <T>.
     *
     * @throws CurrentNotSetException
     */
    public void insertAfterCurrentAndMove(T a) throws CurrentNotSetException {

        if (current == null)
            throw new CurrentNotSetException();

        Node<T> toInsert = new Node<T>(a);
        Node<T> n = first;

        // zu current navigieren
        while (n != current && n != null) {
            n = n.getNext();
        }

        if (current == last) {
            current.setNext(toInsert);
            toInsert.setPrevious(current);
            last = toInsert;
        } else {
            toInsert.setNext(current.getNext());
            current.setNext(toInsert);
            toInsert.setPrevious(current);
        }
        current = toInsert;
    }
}