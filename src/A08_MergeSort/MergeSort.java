package A08_MergeSort;

import java.util.Arrays;


public class MergeSort implements PersonenSort {

    /**
     * Sortier-Funktion
     */
    public void sort(Person[] personen) {
        // Start des rekursiven Aufrufs
        sort(personen, 0, personen.length - 1);
    }

    /**
     * Rekursive Funktion zum Sortieren eines Teils des Arrays
     *
     * @param personen Zu sortierendes Array
     * @param start    Startpunkt im Array
     * @param end      Endpunkt im Array
     */
    public void sort(Person[] personen, int start, int end) {

        if (start < 0 || end <= 0)
            return;

        if (start < end) {

            int middle = (end + start) / 2;

            // Rekursion zum Zerteilen bis nur noch 1er Blöcke übring sind
            sort(personen, start, middle);
            sort(personen, middle + 1, end);

            // Für Merge: Hälften in eigene Arrays kopieren
            // Hinweis: bei copyOfRange ist Obergrenze exklusiv, deshalb "+ 1"
            Person[] teil1 = Arrays.copyOfRange(personen, start, middle + 1);
            Person[] teil2 = Arrays.copyOfRange(personen, middle + 1, end + 1);

            // Beide Hälften zusammenfügen und in data-Array schreiben
            merge(teil1, teil2, personen, start);
        }
    }


    /**
     * Merge zweier Arrays in ein Ergebnis-Array
     *
     * @param pers1  Erstes Array
     * @param pers2  Zweites Array
     * @param result Ergebnisarray
     * @param start  Position für Speicherung in Ergebnisarray
     */
    public void merge(Person[] pers1, Person[] pers2, Person[] result, int start) {

        int i = 0, j = 0, k = start, l = pers1.length, r = pers2.length;

        while (i < l && j < r) {
            if (pers1[i].compareTo(pers2[j]) < 0) {

                result[k] = pers1[i];
                i++;
            } else {

                result[k] = pers2[j];
                j++;
            }

            k++;
        }

        while (i < l) {

            result[k] = pers1[i];
            i++;
            k++;
        }

        while (j < r) {

            result[k] = pers2[j];
            j++;
            k++;
        }
    }
}
