import { Equipe } from "./Equipe";
import { User } from "./User";

export interface ChefDequipe extends User {
    equipe?: Equipe;
  }