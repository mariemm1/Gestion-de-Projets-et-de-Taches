import { PostMembre } from "./enum/PostMembre";
import { Equipe } from "./Equipe";
import { User } from "./User";

export interface Membre extends User {
    competences: string[];
    postMembre: PostMembre;
    equipe?: Equipe;
  }