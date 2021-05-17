package A05_Breitensuche;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Breitensuche extends BaseTree<Integer> {

    ArrayList<Integer> nodes;

    @Override
    protected int compare(Integer a, Integer b) {
        return a.compareTo(b);
    }

    int calculateHeight(Node root) {

        // Hilfsmethode, damit die Anzahl der Ebenen des Baums
        if (root == null)
            return 0;
        else {
            // Via Rekursion die Höhe aller Subbäume berechnen
            int leftHeight = calculateHeight(root.getLeft());
            int rightHeight = calculateHeight(root.getRight());

            // Der höhere Baum gewinnt
            if (leftHeight > rightHeight)
                return (leftHeight + 1);
            else
                return (rightHeight + 1);
        }
    }

    /**
     * Liefert Knoten des Baums ausgehend von Start in Reihenfolge der Breitensuche zurück
     *
     * @param start Startknoten für Teilbaum
     * @return Liste der Knoten in Breitenfolge
     */
    public List<Integer> getBreadthFirstOrder(Node<Integer> start) {

        ArrayList<Integer> nodes = new ArrayList<>();

        // Höhe berechnen, um die Anzahl der zu traversirenden Levels herauszufinden
        int height = calculateHeight(start), i;

        // jedes Level besuchen und für jedes Level die Nodes sammeln
        for (i = 1; i <= height; i++) {
            nodes.addAll(getBreadthFirstOrderForLevel(start, i));
        }
        //System.out.println(nodes.toString());

        // gesammelte Nodes zurückliefern
        return nodes;
    }

    /**
     * Liefert Knoten des Baums ausgehend von Start in Reihenfolge der Breitensuche zurück,
     * allerdings nur jene Knoten, die in der angegebenen Ebene liegen (Start hat Ebene=1)
     *
     * @param start Startknoten für Teilbaum
     * @param level Nur Knoten dieser Ebene ausgeben
     * @return Liste aller Knoten
     */
    public List<Integer> getBreadthFirstOrderForLevel(Node<Integer> start, int level) {

        ArrayList<Integer> nodesOnLevel = new ArrayList<>();

        if (start == null)
            return null;

        // passiert "nach" dem letzten if, da man ja per Rekursion nach unten traversiert
        // bis man am richtigen Level angekommen ist
        // hier wird die Node zur Liste hinzugefügt
        if (level == 1) {
            nodesOnLevel.add(start.getValue());
            //System.out.print(start.getValue() + " - ");
        }

        if (level > 1) {
            // Via Rekursion so lange im Baum nach unten traversieren, wie es linke / rechte Kindselemente gibt
            // wird das richtige Level erreicht, kommt man ins vorherige if; wenn nicht wird
            // Level verringert und weiter gesucht
            if (start.getLeft() != null)
                nodesOnLevel.addAll(getBreadthFirstOrderForLevel(start.getLeft(), level-1));
            if (start.getRight() != null)
                nodesOnLevel.addAll(getBreadthFirstOrderForLevel(start.getRight(), level-1));
        }

        // gesammelte Nodes zurückliefern
        return nodesOnLevel;
    }
}
