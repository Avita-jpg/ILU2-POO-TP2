package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlAcheterProduitTest {

	private ControlAcheterProduit controleur;
	private Village village;
	private Chef abraracourcix;
	private ControlVerifierIdentite controlVerifierIdentite;
	
	@BeforeEach
	void setUp() throws Exception {
		village = new Village("le village des irréductibles", 10, 5);
		
		abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		controlVerifierIdentite = new ControlVerifierIdentite(village);
		ControlTrouverEtalVendeur controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		controleur = new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
	}

	@Test
	void testControlAcheterProduit() {
		assertNotNull(controleur);
	}

	@Test
	void testVerifierIdentite() {
		assertTrue(controleur.verifierIdentite("Abraracourcix"));
		assertFalse(controleur.verifierIdentite("nonVillageois"));
	}

	@Test
	void testRechercherVendeursProduit() {
		String produit = "fleurs";
		
		/* Test cas sans vendeurs */
		String[] listeVendeurs = controleur.rechercherVendeursProduit(produit);
		assertTrue(listeVendeurs.length == 0);
		
		/* Test cas avec vendeurs */
		village.installerVendeur(abraracourcix, produit, 5);
		listeVendeurs = controleur.rechercherVendeursProduit(produit);
		assertTrue(listeVendeurs.length == 1);
		assertTrue(listeVendeurs[0] == "Abraracourcix");
	}

	@Test
	void testAcheterProduit() {
		String produit = "fleurs";
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		
		int quantiteInitiale;
		int quantitePourAcheter;
		int quantiteAchetee;
		
		quantiteInitiale = 20;
		
		/* Test acheter 0 items */
		quantitePourAcheter = 0;
		controlEmmenager.ajouterGaulois("gaulois1", 5);
		controlPrendreEtal.prendreEtal("gaulois1", produit, quantiteInitiale);
		quantiteAchetee = controleur.acheterProduit("gaulois1", quantitePourAcheter);
		
		assertTrue(quantiteAchetee == 0);
		
		/* Test acheter plus d'items qu'il y en a */
		quantitePourAcheter = quantiteInitiale + 5;
		controlEmmenager.ajouterGaulois("gaulois2", 5);
		controlPrendreEtal.prendreEtal("gaulois2", produit, quantiteInitiale);
		quantiteAchetee = controleur.acheterProduit("gaulois2", quantitePourAcheter);
		
		assertTrue(quantiteAchetee == quantiteInitiale);
		
		/* Test acheter items quand il y en a plus */
		quantitePourAcheter = 5;
		quantiteAchetee = controleur.acheterProduit("gaulois2", quantitePourAcheter);
		
		assertTrue(quantiteAchetee == 0);
		
	}

}
