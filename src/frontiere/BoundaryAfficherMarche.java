package frontiere;

import controleur.ControlAfficherMarche;

public class BoundaryAfficherMarche {
	private ControlAfficherMarche controlAfficherMarche;

	public BoundaryAfficherMarche(ControlAfficherMarche controlAfficherMarche) {
		this.controlAfficherMarche = controlAfficherMarche;
	}

	public void afficherMarche(String nomAcheteur) {
		String[] infosMarche = controlAfficherMarche.donnerEtatMarche();
		if (infosMarche.length == 0) {
			System.out.println("Le marché est vide, revenez plus tard.");
		} else {
			System.out.println(nomAcheteur + ", vous trouverez au marché:");
			int i = 0;
			while (i < infosMarche.length-1) {
				StringBuilder chaine = new StringBuilder();
				chaine.append("- ");
				chaine.append(infosMarche[i]);
				i++;
				chaine.append(" qui vend ");
				chaine.append(infosMarche[i]);
				i++;
				chaine.append(" ");
				chaine.append(infosMarche[i]);
				System.out.println(chaine.toString());
			}
		}
	}
}
