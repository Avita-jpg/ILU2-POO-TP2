package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlAfficherVillageTest {

	private Village village;
	private Chef chef;
	ControlAfficherVillage controlAfficherVillage;
	
	
	@BeforeEach
	public void setUp() {
		village = new Village("le village des irréductibles", 10, 5);
		chef = new Chef("Abraracourcix", 10, village);
		village.setChef(chef);
		controlAfficherVillage = new ControlAfficherVillage(village);
	}

	@Test
	void testControlAfficherVillage() {
		assertNotNull(controlAfficherVillage);
	}

	@Test
	void testDonnerNomsVillageois() {
		String[] nomsVillageois = controlAfficherVillage.donnerNomsVillageois();
		assertNotNull(nomsVillageois);
		assertTrue(nomsVillageois[0].equals("Abraracourcix"));
		assertTrue(nomsVillageois.length <= 10);
	}

	@Test
	void testDonnerNomVillage() {
		String nomVillage = controlAfficherVillage.donnerNomVillage();
		assertTrue(nomVillage.equals("le village des irréductibles"));
	}

	@Test
	void testDonnerNbEtals() {
	 int nbEtals = controlAfficherVillage.donnerNbEtals();
	 assertTrue(nbEtals == 5);
	}

}