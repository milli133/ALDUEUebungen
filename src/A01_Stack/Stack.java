package A01_Stack;


public class Stack<T> {
    private Node<T> first;
    private int count = 0;


    /**
     * Oberstes Element entfernen und zurückliefern.
     * Existiert kein Element, wird eine Exception ausgelöst.
     *
     * @throws StackEmptyException
     */
    public T pop() throws StackEmptyException {

        if (first == null)
            throw new StackEmptyException();

        T toReturn = first.getData();

        first = first.getNext();

        count --;

        return toReturn;
    }

    /**
     * Übergebenen T auf Stack (als oberstes Element) speichern.
     *
     * @param i data
     */
    public void push(T i) {
        Node<T> newElement = new Node<T>(i);

        newElement.setNext(first);

        first = newElement;

        count ++;
    }

    /**
     * Liefert die Anzahl der Elemente im Stack
     *
     * @return
     */
    public int getCount() {
        return count;
    }
}
