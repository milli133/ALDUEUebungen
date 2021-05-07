package A04_TraverseTree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class W�rterbuch {

    /**
     * Wurzel des Baums (Startknoten)
     */
    private Wort root;
    private ArrayList<Wort> woerter;

    public Wort getRoot() {
        return root;
    }

    void collectViaPreorder(Wort wort) {

        // Preorder macht immer zuerst alles links fertig bis zum Ende
        // Danach wird der n�chste Rechte Baum wieder links angefangen und zum
        // Ende durchsucht
        if (wort == null)
            return;

        // Wort zur Liste hinzuf�gen
        woerter.add(wort);

        // linken Baum abarbeiten
        collectViaPreorder(wort.getLeft());

        // rechten Baum abarbeiten
        collectViaPreorder(wort.getRight());
    }

    /**
     * Z�hlt alle W�rter des Teilbaums ab einem bestimmten Wort
     *
     * @param w Wort
     * @return Zahl der W�rter (=Anzahl der Elemente)
     */
    public int countWordsInSubTree(Wort w) {

        if (w == null)
            return 0;

        woerter = new ArrayList<>();

        int i = 0;

        Wort erstesWort = find(w.getWort());

        collectViaPreorder(erstesWort);

        return woerter.size();
    }

    /**
     * Liefert die Menge aller W�rter retour, die ein spezifisches Pr�fix haben.
     *
     * @param prefix W�rter m�ssen diesen Pr�fix haben
     * @return Menge aller zutreffenden W�rter
     */
    public Set<String> getWordsWithPrefix(String prefix) {

        Set<String> gefundeneWoerter = new HashSet<String>();

        woerter = new ArrayList<>();

        collectViaPreorder(root);

        for (Wort w :
                woerter) {
            if (w.getWort().startsWith(prefix, 0)) {
                gefundeneWoerter.add(w.getWort());
                //System.out.println("Wort gefunden: " + w.getWort());
            }
        }

        return gefundeneWoerter;
    }


    /**
     * Neues Wort hinzuf�gen
     *
     * @param wort Hinzuzuf�gendes Wort
     */
    public void add(String wort) {
        Wort neu = new Wort(wort);
        if (root == null) {            // Fall 1: Baum ist leer
            root = neu;
            return;
        }
        Wort w = root;                // Fall 2: Baum ist nicht leer
        while (true) {
            int vgl = wort.compareTo(w.getWort());
            if (vgl < 0) {            // Neues Wort ist lexikographisch kleiner
                if (w.getLeft() == null) {
                    w.setLeft(neu);
                    neu.setParent(w);
                    return;
                }
                w = w.getLeft();
            } else if (vgl > 0) {        // Neues Wort ist lexikographisch gr��er
                if (w.getRight() == null) {
                    w.setRight(neu);
                    neu.setParent(w);
                    return;
                }
                w = w.getRight();
            } else {                    // Neues Wort ist lexikographisch gleich
                return;
            }
        }
    }

    public Wort find(String s) {
        return find(root, s);
    }

    private Wort find(Wort current, String s) {
        if (current == null) {
            return null;
        }
        int vgl = s.compareTo(current.getWort());
        if (vgl == 0) {        // Gefunden
            return current;
        } else if (vgl < 0) {    // Links
            return find(current.getLeft(), s);
        } else {                // Rechts
            return find(current.getRight(), s);
        }
    }

}
