package com.example.ondc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by GAGAN.HV on 08/01/22
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class DroolEngineDto {

    BigDecimal mrp;

    String category;

    String season;

    Date date;

    String pincode;

    int currentNoOfOrderInPincode;

    int availableInventory;

    TimeZone zone;

    BigDecimal sellingPrice;

    double percentage;

    double categoryWeitage;

    double seasonWeitage;

    double inventoryWeitage;

    double orderWeightage;

    double categoryPercentage;

    double seasonPercentage;

    double inventoryPercentage;

    double orderPercentage;

    public DroolEngineDto(EanDataResponse eanData, String pincode){
        this.availableInventory=eanData.getEanData().getQuantity();
        this.mrp = BigDecimal.valueOf(Double.valueOf(eanData.getEanData().getBasePrice()));
        this.category = eanData.getEanData().getCategory();
        this.pincode = pincode;
        this.zone = TimeZone.getDefault();
        this.date = new Date();
    }
}
