package com.example.demo.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;
import javax.swing.*;


@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Categories implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String Nom;
    private Integer QT;
    @CreationTimestamp
    private Timestamp Date_creation;
    private Timestamp Date_mmodif =null;


    @OneToMany(mappedBy="categorie", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Produit> product;


}
