package com.example.ondc.controller;

import com.example.ondc.model.DroolsInputDto;
import com.example.ondc.service.DynamicPricingService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by GAGAN.HV on 08/01/22
 */

@RestController
@RequestMapping("/dp")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DroolsController {

    @Autowired
    DynamicPricingService dynamicPricingService;

    @PostMapping(value = "/calculation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> dynamicPricingCalculation(@RequestBody DroolsInputDto dpdto) {
        return ResponseEntity.ok(dynamicPricingService.calculateAndSetPrice(dpdto));
    }
}
