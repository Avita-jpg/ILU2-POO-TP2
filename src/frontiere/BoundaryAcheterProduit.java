package frontiere;

//import java.util.Scanner;

import controleur.ControlAcheterProduit;

public class BoundaryAcheterProduit {
	//private Scanner scan = new Scanner(System.in);
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		boolean acheteurEstConnu = controlAcheterProduit.verifierIdentite(nomAcheteur);
		if (!acheteurEstConnu) {
			System.out.println("Je suis désolée "+ nomAcheteur + " mais il faut être une habitant de notre village pour commercer ici");
			return;
		}
		String produit = Clavier.entrerChaine("Quel produit voulez-vous acheter?");
		String[] vendeurs = controlAcheterProduit.rechercherVendeursProduit(produit);
		if (vendeurs.length == 0) {
			System.out.println("Désolé, personne ne vend ce produit au marché.");
			return;
		}
		StringBuilder question = new StringBuilder("Chez quel commerçant voulez-vous acheter des fleurs?\n");
		for (int i = 0; i < vendeurs.length; i++) {
			question.append((i+1) + " - "+ vendeurs[i]+"\n");
		}
		int indiceVendeurChoisi = Clavier.entrerEntier(question.toString());
		while (indiceVendeurChoisi < 1 || indiceVendeurChoisi > vendeurs.length) {
			indiceVendeurChoisi = Clavier.entrerEntier("Vous devez choisir un vendeur entre 1 et "+vendeurs.length);
		}
		String vendeurChoisi = vendeurs[indiceVendeurChoisi-1];
		
		boolean vendeurChoisiEstConnu = controlAcheterProduit.verifierIdentite(vendeurChoisi);
		// pourquoi vérfier si pour être vendeur il faut être un habitant du village ?
		if (!vendeurChoisiEstConnu) {
			System.out.println("Je suis désolée "+ vendeurChoisi +" mais il faut être un habitant de notre village pour commercer ici");
			return;
		}
		System.out.println(nomAcheteur + " se déplace jusqu'à l'étal du vendeur "+vendeurChoisi);
		System.out.println("Bonjour "+ nomAcheteur);
		
		int nbProduitAcheter = Clavier.entrerEntier("Combien de "+ produit +" voulez-vous acheter?");
		int nbProduitAcheteEffectif = controlAcheterProduit.acheterProduit(vendeurChoisi, nbProduitAcheter);
		
		if (nbProduitAcheteEffectif == 0) {
			System.out.println(nomAcheteur+ " veut acheter "+ nbProduitAcheter+" "+ produit+", malheureusement il n'y en a plus!");
		}
		else if (nbProduitAcheteEffectif < nbProduitAcheter) {
			System.out.println(nomAcheteur+ " veut acheter "+ nbProduitAcheter+" "+ produit+", malheureusement "+vendeurChoisi+" n'en a plus que "+nbProduitAcheteEffectif+". "+nomAcheteur+" achète tout le stock de "+vendeurChoisi+"." );
		}
		else {
			System.out.println(nomAcheteur+" achète "+nbProduitAcheteEffectif+" "+ produit + " à "+vendeurChoisi+".");
		}
	}
}
