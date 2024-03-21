package controleur;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ControlAcheterProduit {
	private Village village;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlVerifierIdentite controlVerifierIdentite;

	public ControlAcheterProduit(ControlVerifierIdentite controlVerifierIdentite,
			ControlTrouverEtalVendeur controlTrouverEtalVendeur,
			Village village) {
		this.village = village;
		this.controlVerifierIdentite = controlVerifierIdentite;
		this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
	}

	public boolean verifierIdentite(String nomVendeur) {
		return controlVerifierIdentite.verifierIdentite(nomVendeur);
	}
	
	public String[] rechercherVendeursProduit(String produit) {
		Gaulois[] vendeursGaulois = village.rechercherVendeursProduit(produit);
		if (vendeursGaulois.length != 0) {
			String[] listeVendeurs = new String[vendeursGaulois.length];
			for (int i = 0; i < vendeursGaulois.length; i++) {
				listeVendeurs[i] = vendeursGaulois[i].getNom();
			}
			return listeVendeurs;
		}
		return null;
	}
	
	public int acheterProduit(String nomVendeur, int quantite) {
		Etal etal = controlTrouverEtalVendeur.trouverEtalVendeur(nomVendeur);
		return etal.acheterProduit(quantite);
	}

}
