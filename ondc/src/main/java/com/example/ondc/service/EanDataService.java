package com.example.ondc.service;

import com.example.ondc.model.EanData;
import com.example.ondc.model.EanDataResponse;
import com.example.ondc.model.OrderDetails;
import com.example.ondc.repository.EanDataRepository;
import com.example.ondc.repository.OrderDetailsRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EanDataService {

    @Autowired
    EanDataRepository eanDataRepository;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    public void insertEanData(EanData eandata) {
        log.info("Request to insert eandata : {}", eandata);
        eanDataRepository.save(eandata);
    }

    public void updateEanData(Long id, EanData eandata) {
        log.info("Request to insert eandata : {}", eandata);
        EanData oldEanData = eanDataRepository.getById(id);
        oldEanData.setEan(eandata.getEan());
        oldEanData.setCategory(eandata.getCategory());
        oldEanData.setMrp(eandata.getMrp());
        oldEanData.setQuantity(eandata.getQuantity());
        oldEanData.setSeasons(eandata.getSeasons());
        eanDataRepository.save(oldEanData);
    }

    public EanDataResponse getEanData(String ean) {
        log.info("Request to get eandata for ean : {}", ean);
        EanData eanData = eanDataRepository.findOneByEan(ean);
        List<OrderDetails> orderDetails = orderDetailsRepository.findAllByEan(eanData.getEan());
        return EanDataResponse.builder().eanData(eanData).orderDetails(orderDetails).build();
    }

    public List<EanDataResponse> getAllEanData() {
        log.info("Request to get all ean data");
        List<EanDataResponse> eanDataResponseList = new ArrayList<>();
        List<EanData> eanDataList = eanDataRepository.findAll();
        for (EanData eanData : eanDataList) {
            List<OrderDetails> orderDetails = orderDetailsRepository.findAllByEan(eanData.getEan());
            eanDataResponseList.add(EanDataResponse.builder().eanData(eanData).orderDetails(orderDetails).build());
        }
        return eanDataResponseList;
    }
}
