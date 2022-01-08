package com.example.ondc.service;

import com.example.ondc.model.DroolsInputDto;
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

    public DroolsInputDto calculateAndSetPrice(DroolsInputDto dpdto) {
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

    private void updateDefaultValue(DroolsInputDto dpdto) {
        if (ObjectUtils.isEmpty(dpdto.getZone())) {
            dpdto.setZone(TimeZone.getDefault());
        }

        if (ObjectUtils.isEmpty(dpdto.getDate())) {
            dpdto.setDate(new Date());
        }

    }

    private void updateSp(DroolsInputDto droolsInputDto) {
        int cnt = 4;

        if (droolsInputDto.getCategoryPercentage() == 0)
            cnt--;
        if (droolsInputDto.getCategoryPercentage() == 0)
            cnt--;
        if (droolsInputDto.getCategoryPercentage() == 0)
            cnt--;
        if (droolsInputDto.getCategoryPercentage() == 0)
            cnt--;

        if (cnt == 0) {
            droolsInputDto.setSellingPrice(droolsInputDto.getMrp());

        } else {
            BigDecimal value = BigDecimal.valueOf((droolsInputDto.getCategoryPercentage()
                    + droolsInputDto.getSeasonPercentage()
                    + droolsInputDto.getInventoryPercentage()
                    + droolsInputDto.getOrderPercentage()) / (cnt*100));
            droolsInputDto.setSellingPrice(value.multiply(droolsInputDto.getMrp()).add(droolsInputDto.getMrp()).setScale(2, BigDecimal.ROUND_HALF_EVEN));
        }

    }
}
