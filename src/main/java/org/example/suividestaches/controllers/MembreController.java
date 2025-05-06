    package org.example.suividestaches.controllers;

    import org.example.suividestaches.models.Membre;
    import org.example.suividestaches.services.MembreService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("membre")
    public class MembreController {
        @Autowired
        private MembreService membreService;

        @GetMapping("/all")
        public ResponseEntity<List<Membre>> getAllMembres() {
            return ResponseEntity.ok(membreService.getAllMembres());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Membre> getMembreById(@PathVariable Long id) {
            return ResponseEntity.ok(membreService.getMembreById(id));
        }


        @PostMapping("/create")
        public ResponseEntity<Membre> createMembre(@RequestBody Membre membre) {
            Membre newMembre = membreService.createMembre(membre);

            // Si l'équipe n'est pas trouvée ou une erreur s'est produite, retourner une erreur
            if (newMembre == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(null); // L'équipe n'a pas été trouvée ou une erreur s'est produite
            }

            // Si tout est OK, retourner le membre créé
            return ResponseEntity.status(HttpStatus.CREATED).body(newMembre);
        }
        @PutMapping("/membre/update/{id}")
        public ResponseEntity<Membre> updateMembre(@PathVariable Long id, @RequestBody Membre membre) {
            Membre updatedMembre = membreService.updateMembre(id, membre);
            return ResponseEntity.ok(updatedMembre);
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<Void> deleteMembre(@PathVariable Long id) {
            membreService.deleteMembre(id);
            return ResponseEntity.noContent().build();
        }
    }
