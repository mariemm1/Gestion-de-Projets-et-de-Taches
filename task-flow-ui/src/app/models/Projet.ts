import { Statut } from "./enum/Statut";
import { Equipe } from "./Equipe";

export interface Projet {
    id?: number;
    nom: string;
    description: string;
    dateDebut: Date;
    dateFin: Date;
    statut: Statut;
    equipe?: Equipe;
    //taches?: Taches[];
}  