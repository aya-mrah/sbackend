package com.example.demo.repositories;
import com.example.demo.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Repository
public interface  ProduitRepository extends JpaRepository<Produit,Long> {


    @Query("select i from Produit i where i.categorie.id=:x")
    public Collection<Produit> trouveInscrit(@Param("x")Long categorie);

    @Transactional
    @Modifying
    @Query("delete from  Produit i where i.categorie.id=:x")
    public void suprimerproduit(@Param("x")Long categorie);

}
