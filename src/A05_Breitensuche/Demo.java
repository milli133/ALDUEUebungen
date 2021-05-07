package A05_Breitensuche;

public class Demo {
    public static void main(String[] args) {
        Breitensuche bs = new Breitensuche();

        bs.add(6);
        bs.add(3);
        bs.add(10);
        bs.add(5);
        bs.add(2);
        bs.add(1);
        bs.add(12);
        bs.add(11);
        bs.add(15);

        System.out.println(bs.getBreadthFirstOrderForLevel(bs.getRoot(), 2).toString());
    }
}
