package com.example.ondc.service;

import com.example.ondc.model.DroolEngineDto;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by GAGAN.HV on 08/01/22
 */
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DynamicPricingService {

    @Autowired
    KieContainer kieContainer;

    public DroolEngineDto calculateAndSetPrice(DroolEngineDto dpdto) {
        if (dpdto.getAvailableInventory() == 0) {
            dpdto.setSellingPrice(dpdto.getMrp());
        }
        KieSession kieSession = kieContainer.newKieSession();
        updateDefaultValue(dpdto);
        kieSession.insert(dpdto);
        kieSession.fireAllRules();
        kieSession.dispose();
        updateSp(dpdto);
        return dpdto;
    }

    private void updateDefaultValue(DroolEngineDto dpdto) {
        if (ObjectUtils.isEmpty(dpdto.getZone())) {
            dpdto.setZone(TimeZone.getDefault());
        }

        if (ObjectUtils.isEmpty(dpdto.getDate())) {
            dpdto.setDate(new Date());
        }

    }

    private void updateSp(DroolEngineDto droolEngineDto) {
        int cnt = 4;

        if (droolEngineDto.getCategoryPercentage() == 0)
            cnt--;
        if (droolEngineDto.getCategoryPercentage() == 0)
            cnt--;
        if (droolEngineDto.getCategoryPercentage() == 0)
            cnt--;
        if (droolEngineDto.getCategoryPercentage() == 0)
            cnt--;

        if (cnt == 0) {
            droolEngineDto.setSellingPrice(droolEngineDto.getMrp());

        } else {
            BigDecimal value = BigDecimal.valueOf((droolEngineDto.getCategoryPercentage()
                    + droolEngineDto.getSeasonPercentage()
                    + droolEngineDto.getInventoryPercentage()
                    + droolEngineDto.getOrderPercentage()) / (cnt*100));
            droolEngineDto.setSellingPrice(value.multiply(droolEngineDto.getMrp()).add(droolEngineDto.getMrp()).setScale(2, BigDecimal.ROUND_HALF_EVEN));
        }

    }
}
