import { ChefDequipe } from "./ChefDequipe";
import { Membre } from "./Membre";


export interface Equipe {
  id?: number;
  nom: string;
  description: string;
 chefDequipe?: ChefDequipe;
  membres?: Membre[];
 // projets?: Projet[];
}
