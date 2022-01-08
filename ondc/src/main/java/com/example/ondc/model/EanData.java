package com.example.ondc.model;


import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ean_data")
public class EanData extends EanDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String ean;

    int quantity;

    String category;

    String seasons;

    String weather;

    String shelfLife;

    String mrp;

    String sellingPrice;
}