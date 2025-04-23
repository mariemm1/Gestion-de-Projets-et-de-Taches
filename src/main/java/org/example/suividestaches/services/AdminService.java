package org.example.suividestaches.services;

import org.example.suividestaches.models.Admin;
import org.example.suividestaches.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    // Get all Admins
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    // Get Admin by ID
    public Admin getAdminById(Long id) {
        Optional<Admin> admin = adminRepository.findById(id);
        return admin.orElse(null);
    }

    // Create new Admin
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    // Update Admin by ID
    public Admin updateAdmin(Long id, Admin updatedAdmin) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        admin.setNom(updatedAdmin.getNom());
        admin.setPrenom(updatedAdmin.getPrenom());
        admin.setEmail(updatedAdmin.getEmail());
        admin.setPwd(updatedAdmin.getPwd());
        admin.setRole(updatedAdmin.getRole());
        // Any other fields specific to Admin can be updated here

        return adminRepository.save(admin);
    }

    // Delete Admin by ID
    public void deleteAdmin(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new RuntimeException("Admin not found for deletion");
        }
        adminRepository.deleteById(id);
    }
}
