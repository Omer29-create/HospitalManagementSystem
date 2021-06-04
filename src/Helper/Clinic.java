package Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public enum Clinic {
	INTENSIV, AUGE, KREBS, INNER, ALLGEMEIN;

	public static void main(String[] args) {

//		Clinic clnk = Clinic.INTENSIV;
//		if (clnk == INTENSIV) {
//			System.out.println("Intensiv Station wurde ausgewählt");
//		}

		System.out.println("Bitte wählen Sie Ihre wollende Fach aus");

		Clinic alleFächer[] = Clinic.values();
		for (Clinic abc : alleFächer) {
			System.out.println(abc.ordinal() + 1 + "-)" + abc);
		}

		Scanner scan = new Scanner(System.in);
		int choose = scan.nextInt();

		switch (choose) {
		case 1:
			System.out.println("Sie haben Intensivstation gewählt");
			break;
		case 2:
			System.out.println("Sie haben die Abteilung für Auge Krankheiten gewählt");
			break;
		case 3:
			System.out.println("Sie haben die Abteilung für Krebs Krankheiten gewählt");
			break;
		case 4:
			System.out.println("Sie haben die Abteilung für Innere Krankheiten ausgewählt");
			break;
		case 5:
			System.out.println("Sie haben die Abteilung für Allgemein gewählt");
			break;
		default:
			System.out.println("Leider, hier gibt es keine diese Abteilung");
			break;
		}
		
		scan.close();
	}

}
