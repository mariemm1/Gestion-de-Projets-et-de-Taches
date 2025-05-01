import { Role } from "./enum/Role";

export interface User {
    id?: number;
    nom: string;
    prenom: string;
    email: string;
    pwd: string;
    role: Role;

  }