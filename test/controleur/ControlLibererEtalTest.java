package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlLibererEtalTest {

	private ControlLibererEtal controleur;
	private Village village;
	private Chef abraracourcix;
	private ControlPrendreEtal controlPrendreEtal;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	
	@BeforeEach
	void setUp() throws Exception {
		village = new Village("le village des irréductibles", 10, 5);
		abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		
		ControlVerifierIdentite ControlVerifierIdentite = new ControlVerifierIdentite(village);
		controlPrendreEtal = new ControlPrendreEtal(ControlVerifierIdentite, village);
		controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		controleur = new ControlLibererEtal(controlTrouverEtalVendeur);
	}

	@Test
	void testControlLibererEtal() {
		assertNotNull(controleur);
	}

	@Test
	void testIsVendeur() {
		/* test cas ou le personnage n'est pas un vendeur */
		assertFalse(controleur.isVendeur("Abraracourcix"));
		
		/* test cas ou le personnage est un vendeur */
		controlPrendreEtal.prendreEtal("Abraracourcix", "fleurs", 10);
		assertTrue(controleur.isVendeur("Abraracourcix"));

	}

	@Test
	void testLibererEtal() {
		controlPrendreEtal.prendreEtal("Abraracourcix", "fleurs", 10);
		String[] donneesEtal = controleur.libererEtal("Abraracourcix");
		for (int i = 0; i< donneesEtal.length; i++) {
			System.out.println(donneesEtal[i]);
		}
		assertTrue(donneesEtal[0].equals("true"));
		assertTrue(donneesEtal[1].equals("Abraracourcix"));
		assertTrue(donneesEtal[2].equals("fleurs"));
		assertTrue(donneesEtal[3].equals("10"));
		assertTrue(donneesEtal[4].equals("0"));
	}

}
