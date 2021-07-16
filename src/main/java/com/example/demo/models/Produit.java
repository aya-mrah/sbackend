package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Produit implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Nom;
    private Integer QT;
    private boolean Disponible;
    @CreationTimestamp
    private Timestamp date_cre;
    private Timestamp date_mod =null;
    private String photoName="unknown.jpg";
    @ManyToOne()
    @JoinColumn(name="idCategorie")
    private Categories categorie;

}
