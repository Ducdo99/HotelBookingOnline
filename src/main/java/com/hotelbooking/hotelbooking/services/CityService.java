package com.hotelbooking.hotelbooking.services;

import com.hotelbooking.hotelbooking.entities.City;
import com.hotelbooking.hotelbooking.ouputs.CityInformationResponse;
import com.hotelbooking.hotelbooking.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<CityInformationResponse> getAllCity() {
        List<City> cityEntities = cityRepository.findAll();
        if(cityEntities.isEmpty()) {

        }
        List<CityInformationResponse> cityInfoResponseList = new ArrayList<>();
        for (City entity : cityEntities) {
            CityInformationResponse cityInfoResponse
                    = new CityInformationResponse(entity.getId(), entity.getName().trim());
            cityInfoResponseList.add(cityInfoResponse);
        }
        return cityInfoResponseList;
    }
}
