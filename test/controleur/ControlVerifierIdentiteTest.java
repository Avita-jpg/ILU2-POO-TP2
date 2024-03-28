package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlVerifierIdentiteTest {

	private Village village;
	private Chef chef;
	ControlVerifierIdentite controlVerfierIdentite;
	
	@BeforeEach
	void setUp() {
		village = new Village("le village des irréductibles", 10, 5);
		chef = new Chef("Abraracourcix", 10, village);
		village.setChef(chef);
		controlVerfierIdentite = new ControlVerifierIdentite(village);
	}

	@Test
	void testControlVerifierIdentite() {
		assertNotNull(controlVerfierIdentite);
	}

	@Test
	void testVerifierIdentite() {
		assertTrue(controlVerfierIdentite.verifierIdentite("Abraracourcix"));
		assertFalse(controlVerfierIdentite.verifierIdentite("nonVillageois"));
	}

}
