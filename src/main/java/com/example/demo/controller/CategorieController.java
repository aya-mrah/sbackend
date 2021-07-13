package com.example.demo.controller;

import com.example.demo.models.Categories;
import com.example.demo.models.Produit;
import com.example.demo.repositories.CategorieRepository;
import com.example.demo.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin(origins ="http://localhost:4200")

@RequestMapping("/Category")
public class CategorieController {



    private final CategorieRepository categorierepo;

    private final ProduitRepository produitr;

    @Autowired
    public  CategorieController(CategorieRepository categorierepo,ProduitRepository produitr){
        this.categorierepo=categorierepo;
        this.produitr=produitr;

    }


    @GetMapping("/get")
    public List<Categories> getAllCategories() {
        List <Categories> categories = categorierepo.findAll();
        return categories;
    }

    @DeleteMapping("/categoriedelete/{id}")
    public ResponseEntity<?> deleteCategories(@PathVariable(value = "id") Long categorieId) {
        Categories categorie = categorierepo.findById(categorieId).orElseThrow(null);

        categorierepo.delete(categorie);

        return ResponseEntity.ok().build();
    }



    @GetMapping("/categorie/{id}")
    public Categories getCategorieById(@PathVariable(value = "id") long Id) {
        return categorierepo.findById(Id).orElseThrow(null);
    }

    @PostMapping("/categorieadd")
    public Categories createCategorie(@Valid @RequestBody Categories categorie) {
        return categorierepo.save(categorie);
    }



    @GetMapping(value = "/produit/{categorie}")

    public List<Produit> afficherlesProduit(@PathVariable Long categorie){

        List<Produit> cities = (List<Produit>) produitr.trouveInscrit(categorie);
        return cities;

    }

    @DeleteMapping("/deletecategorie/{categorie}")
    public ResponseEntity<?> delete(@PathVariable Long categorie) {
        List<Produit> ca = (List<Produit>) produitr.trouveInscrit(categorie);
         produitr.deleteAll(ca);
       Categories categori = categorierepo.findById(categorie).orElseThrow(null);

        categorierepo.delete(categori);

        return ResponseEntity.ok().build();
    }


    @PutMapping("/categorieupdate/{id}")
    public Categories updatedCategorie(@PathVariable(value = "id") Long Id,
                               @Valid @RequestBody Categories categorieDetails) {

        Categories categorie = categorierepo.findById(Id).orElseThrow(null);
        categorie.setId(categorieDetails.getId());
        categorie.setNom(categorieDetails.getNom());
        categorie.setQT(categorieDetails.getQT());
        categorie.setDate_creation(categorieDetails.getDate_creation());
        categorie.setDate_mmodif(categorieDetails.getDate_mmodif());
        Categories updatedCategorie = categorierepo.save(categorie);
        return updatedCategorie;
    }



}
