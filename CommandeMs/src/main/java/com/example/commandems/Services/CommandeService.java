package com.example.commandems.Services;
import com.example.commandems.Entities.Commande;
import com.example.commandems.Entities.Openfeign.Produit;
import com.example.commandems.Openfeign.ProduitOpenfeign;
import com.example.commandems.Repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService {
    @Autowired
    private CommandeRepository commandeRepository;
    private final ProduitOpenfeign produitOpenfeign;
    public CommandeService(ProduitOpenfeign produitopenfeign){
        produitOpenfeign=produitopenfeign;
    }

    public List<Commande> getAllCommande() {
        return commandeRepository.findAll();
    }
    public List<Produit> getAllProduitbyId(List<Long> listproduits) {
        return produitOpenfeign.getproduitAllById(listproduits);
    }
    public Commande addCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    public Commande updateCommande(Long commandeId, Commande newCommande) {
        if (commandeRepository.findById(commandeId).isPresent()) {
            Commande existingCommande = commandeRepository.findById(commandeId).get();
            existingCommande.setModePaiement(newCommande.getModePaiement());
            existingCommande.setSommeTotale(newCommande.getSommeTotale());

            return commandeRepository.save(existingCommande);
        } else
            return null;
    }
    public String deleteCommande(Long commandeId) {
        if (commandeRepository.findById(commandeId).isPresent()) {
            commandeRepository.deleteById(commandeId);
            return "Deleted";
        } else
            return "Not yet";
    }
}