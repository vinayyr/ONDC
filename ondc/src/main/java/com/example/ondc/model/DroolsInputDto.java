package com.example.ondc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by GAGAN.HV on 08/01/22
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DroolsInputDto {

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

}
