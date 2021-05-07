package A04_TraverseTree;

public class Demo {

    public static void main(String[] args) {
        W�rterbuch wb = new W�rterbuch();
        wb.add("Basa");
        wb.add("Dora");
        wb.add("Sira");
        wb.add("Gauss");
        wb.add("Kalle");
        wb.add("Valle");
        wb.add("Cern");
        wb.add("Galle");

        System.out.println("# of found nodes: " + wb.countWordsInSubTree(wb.find("Gauss")));
        String prefix = "Ga";
        System.out.println("Gefundene W�rter mit Pr�fix <"+ prefix + "> " + wb.getWordsWithPrefix(prefix));
    }
}
