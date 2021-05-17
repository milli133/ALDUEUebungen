package A07_BubbleSort;


public class BubbleSort implements PersonenSort {

	/**
	 * Sortier-Funktion
	 */
	public void sort(Person[] personen) {
		Person temp;
		for(int i=1; i<personen.length; i++) {
			for(int j=0; j<personen.length-i; j++) {
				if(personen[j].compareTo(personen[j+1]) > 0) {
					temp=personen[j];
					personen[j]=personen[j+1];
					personen[j+1]=temp;
				}
			}
		}
	}
	
}
