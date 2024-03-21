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
		String produit = Clavier.entrerChaine("Quel produit voulez-vous acheter?");
		String[] vendeurs = controlAcheterProduit.rechercherVendeursProduit(produit);
		if (vendeurs == null) {
			System.out.println("D�sol�, personne ne vend ce produit au march�.");
		} else {
			StringBuilder question = new StringBuilder("Chez quel commer�ant voulez-vous acheter des fleurs?");
			for (int i = 0; i < vendeurs.length; i++) {
				question.append((i+1) + " - "+ vendeurs[i]);
			}
			int indiceVendeurChoisi = Clavier.entrerEntier(question.toString());
			String vendeurChoisi = vendeurs[indiceVendeurChoisi];
			
			System.out.println(nomAcheteur + " se d�place jusqu'� l'�tal du vendeur "+vendeurChoisi);
			System.out.println("Bonjour "+ nomAcheteur);
			//TODO
			int nbProduitAchete = Clavier.entrerEntier("");
			
		}
	}
}
