package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlAfficherMarcheTest {

	private Village village;
	private Chef abraracourcix;
	ControlAfficherMarche controlAfficherMarche;

	@BeforeEach
	public void initialiserSituation() {
		village = new Village("le village des irréductibles", 10, 5);
		abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		controlAfficherMarche = new ControlAfficherMarche(village);
	}

	@Test
	void testControlAfficherMarche() {
		assertNotNull(controlAfficherMarche);
	}

	@Test
	void testDonnerEtatMarche() {
		String[] etatMarche = controlAfficherMarche.donnerEtatMarche();
		assertNotNull(etatMarche);
		assertTrue(etatMarche.length < (5*3));
		// possible ??
		village.installerVendeur(abraracourcix, "fleurs", 15);
		etatMarche = controlAfficherMarche.donnerEtatMarche();
		assertTrue(etatMarche.length == 3);
		assertTrue(etatMarche[0].equals("Abraracourcix"));
		assertTrue(etatMarche[1].equals("15"));
		assertTrue(etatMarche[2].equals("fleurs"));
	}

}
