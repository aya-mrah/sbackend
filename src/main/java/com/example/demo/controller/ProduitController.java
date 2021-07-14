package com.example.demo.controller;
import com.example.demo.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.*;
import com.example.demo.repositories.ProduitRepository;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@RestController
@CrossOrigin(origins ="http://localhost:4200")

@RequestMapping("/produit")
public class ProduitController {


    private final ProduitRepository produitr;
    @Autowired
    public ProduitController(ProduitRepository productRepository) {
        this.produitr = productRepository;
    }


    @GetMapping("/get")
    public List<Produit> getAllProduts() {
        List <Produit> produits = produitr.findAll();
        return produits;
    }

    @GetMapping("/produit/{id}")
    public Produit getProduitById(@PathVariable(value = "id") Long Id) {
        return produitr.findById(Id).orElseThrow(null);
    }

    @PostMapping("/addproduit")
    public Produit createProduit(@RequestBody Produit produit) {

        return produitr.save(produit);
    }



    @DeleteMapping("/produitdelete/{id}")
    public ResponseEntity<?> deleteCategories(@PathVariable(value = "id") Long produitId) {
        Produit produit= produitr.findById(produitId).orElseThrow(null);

        produitr.delete(produit);

        return ResponseEntity.ok().build();
    }


    @PostMapping(path = "/uploadPhoto/{id}")
    public void uploadPhoto(MultipartFile file, @PathVariable Long id) throws Exception{
        Produit p=produitr.findById(id).get();
        p.setPhotoName(file.getOriginalFilename());
        Files.write(Paths.get(System.getProperty("user.home")+"/ecom/products/"+p.getPhotoName()),file.getBytes());
        produitr.save(p);
    }

    @GetMapping(path="/photoProduct/{id}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
        Produit p=produitr.findById(id).get();
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/ecom/products/"+p.getPhotoName()));
    }
    @PutMapping("/produitupdate/{id}")
    public Produit updatedProduit(@PathVariable(value = "id") Long Id,
                                       @Valid @RequestBody Produit produitDetails) {

        Produit produit = produitr.findById(Id).orElseThrow(null);
        produit.setId(produitDetails.getId());
        produit.setNom(produitDetails.getNom());
        produit.setQT(produitDetails.getQT());
        produit.setDisponible(produitDetails.isDisponible());
        produit.setDate_cre(produitDetails.getDate_cre());
        produit.setDate_mod(produitDetails.getDate_mod());
        produit.setCategorie(produitDetails.getCategorie());
        Produit updatedProduit = produitr.save(produit);
        return updatedProduit;
    }


}
