package com.hotelbooking.hotelbooking.services;

import com.hotelbooking.hotelbooking.entities.District;
import com.hotelbooking.hotelbooking.ouputs.DistrictInformationResponse;
import com.hotelbooking.hotelbooking.ouputs.WardInformationResponse;
import com.hotelbooking.hotelbooking.repositories.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DistrictService {
    @Autowired
    private DistrictRepository districtRepository;

    public List<DistrictInformationResponse> getAllDistrictByCityID(Long cityID) {
        List<District> districtEntities = districtRepository.getAllByCityId(cityID);
        List<DistrictInformationResponse> districtInfoResponseList = new ArrayList<>();
       for (District entity : districtEntities) {
           DistrictInformationResponse districtInformationResponse =
                   new DistrictInformationResponse(entity.getId(), entity.getName().trim());
           districtInfoResponseList.add(districtInformationResponse);
       }
       return districtInfoResponseList;
    }
}
