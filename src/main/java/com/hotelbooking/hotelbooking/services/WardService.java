package com.hotelbooking.hotelbooking.services;

import com.hotelbooking.hotelbooking.entities.Ward;
import com.hotelbooking.hotelbooking.ouputs.WardInformationResponse;
import com.hotelbooking.hotelbooking.repositories.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WardService {
    @Autowired
    private WardRepository wardRepository;

    public List<WardInformationResponse> getAllWardByDistrictID(Long districtID) {
        List<Ward> wardEntities = wardRepository.getAllByDistrictId(districtID);
        List<WardInformationResponse> wardInfoResponseList = new ArrayList<>();
        for (Ward entity : wardEntities) {
            WardInformationResponse wardInfoResponse =
                    new WardInformationResponse(entity.getId(), entity.getName().trim());
            wardInfoResponseList.add(wardInfoResponse);
        }
        return wardInfoResponseList;
    }
}
