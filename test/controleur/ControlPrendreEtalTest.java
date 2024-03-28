package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlPrendreEtalTest {
	
	private Village village;
	private Chef chef;
	ControlVerifierIdentite controlVerfierIdentite;
	ControlPrendreEtal controlPrendreEtal;
	
	@BeforeEach
	void setUp() {
		village = new Village("le village des irréductibles", 10, 5);
		chef = new Chef("Abraracourcix", 10, village);
		village.setChef(chef);
		controlVerfierIdentite = new ControlVerifierIdentite(village);
		controlPrendreEtal = new ControlPrendreEtal(controlVerfierIdentite, village);
	}

	@Test
	void testControlPrendreEtal() {
		assertNotNull(controlPrendreEtal);
	}

	@Test
	void testResteEtals() {
		assertTrue(controlPrendreEtal.resteEtals());
		Village villageSansEtals = new Village("le village sans etals", 10, 0);
		villageSansEtals.setChef(chef);
		ControlPrendreEtal controlPrendreEtalSansEtals = new ControlPrendreEtal(controlVerfierIdentite, villageSansEtals);
		assertFalse(controlPrendreEtalSansEtals.resteEtals());
	}

	@Test
	void testPrendreEtal() {
		/* Test vendeur est villagois et etals disponibles */
		int noEtal1 = controlPrendreEtal.prendreEtal("Abraracourcix", "fleurs", 15);
		assertTrue(noEtal1 > 0);
		
		/* Test cas village sans etals disponibles */
		Village villageSansEtals = new Village("le village sans etals", 10, 0);
		villageSansEtals.setChef(chef);
		ControlPrendreEtal controlPrendreEtalSansEtals = new ControlPrendreEtal(controlVerfierIdentite, villageSansEtals);
		int noEtal2 = controlPrendreEtalSansEtals.prendreEtal("Abraracourcix", "fleurs", 15);
		assertFalse(noEtal2 > 0);
		
//		/* Test vendeur n'est pas villageois */
//		int noEtal3 = controlPrendreEtal.prendreEtal("nonVillageois", "choses", 15);
//		assertFalse(noEtal3 > 0); 
	}

	@Test
	void testVerifierIdentite() {
		assertTrue(controlPrendreEtal.verifierIdentite("Abraracourcix"));
		assertFalse(controlPrendreEtal.verifierIdentite("nonVillageois"));
	}

}
