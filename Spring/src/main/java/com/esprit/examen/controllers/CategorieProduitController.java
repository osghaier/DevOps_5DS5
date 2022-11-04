package com.esprit.examen.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.services.ICategorieProduitService;
import io.swagger.annotations.Api;

@RestController
@Api(tags = "Gestion des categories Produit")
@RequestMapping("/categorieProduit")
public class CategorieProduitController {
	public class CategorieProduitRequestModel {
		private Long idCategorieProduit;
		private String codeCategorie;
		private String libelleCategorie;
		private Set<Produit> produits;
		}
	@Autowired
	ICategorieProduitService categorieProduitService;
	
	// http://localhost:8089/SpringMVC/categorieProduit/retrieve-all-categorieProduit
	@GetMapping("/retrieve-all-categorieProduit")
	@ResponseBody
	public List<CategorieProduit> getCategorieProduit() {
		return categorieProduitService.retrieveAllCategorieProduits();
	}

	// http://localhost:8089/SpringMVC/categorieProduit/retrieve-categorieProduit/8
	@GetMapping("/retrieve-categorieProduit/{categorieProduit-id}")
	@ResponseBody
	public CategorieProduit retrieveCategorieProduit(@PathVariable("categorieProduit-id") Long categorieProduitId) {
		return categorieProduitService.retrieveCategorieProduit(categorieProduitId);
	}

	// http://localhost:8089/SpringMVC/categorieProduit/add-categorieProduit
	@PostMapping("/add-categorieProduit")
	@ResponseBody
	public CategorieProduit addCategorieProduit(@RequestBody CategorieProduitRequestModel cp) {
		CategorieProduit categorieProduit = new CategorieProduit();
		categorieProduit.setIdCategorieProduit(cp.idCategorieProduit);
		categorieProduit.setCodeCategorie(cp.codeCategorie);
		categorieProduit.setLibelleCategorie(cp.libelleCategorie);
		return categorieProduitService.addCategorieProduit(categorieProduit);
	}

	@DeleteMapping("/remove-categorieProduit/{categorieProduit-id}")
	@ResponseBody
	public void removeCategorieProduit(@PathVariable("categorieProduit-id") Long categorieProduitId) {
		categorieProduitService.deleteCategorieProduit(categorieProduitId);
	}

	// http://localhost:8089/SpringMVC/categorieProduit/modify-categorieProduit
	@PutMapping("/modify-categorieProduit")
	@ResponseBody
	public CategorieProduit modifyCategorieProduit(@RequestBody CategorieProduitRequestModel cp) {
		CategorieProduit categorieProduit = new CategorieProduit();
		categorieProduit.setIdCategorieProduit(cp.idCategorieProduit);
		categorieProduit.setCodeCategorie(cp.codeCategorie);
		categorieProduit.setLibelleCategorie(cp.libelleCategorie);
		categorieProduit.setProduits(cp.produits);
		return categorieProduitService.updateCategorieProduit(categorieProduit);
	}

	
}
