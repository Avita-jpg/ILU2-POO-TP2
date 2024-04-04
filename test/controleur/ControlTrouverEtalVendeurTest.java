package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Etal;
import villagegaulois.Village;

class ControlTrouverEtalVendeurTest {

	private ControlTrouverEtalVendeur controleur;
	private Village village;
	private Chef abraracourcix;
	
	@BeforeEach
	void setUp() throws Exception {
		village = new Village("le village des irréductibles", 10, 5);
		abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		controleur = new ControlTrouverEtalVendeur(village);
	}

	@Test
	void testControlTrouverEtalVendeur() {
		assertNotNull(controleur);
	}

	@Test
	void testTrouverEtalVendeur() {
		/* Test avec un non vendeur */
		Etal etal = controleur.trouverEtalVendeur("nonVendeur");
		assertNull(etal);
		
		/* Test avec un vendeur */
		village.installerVendeur(abraracourcix, "fleurs", 15);
		etal = controleur.trouverEtalVendeur("Abraracourcix");
		assertNotNull(etal);
		String [] donneesEtal = etal.etatEtal();
		assertTrue(donneesEtal[1].equals("Abraracourcix"));
	}

}
