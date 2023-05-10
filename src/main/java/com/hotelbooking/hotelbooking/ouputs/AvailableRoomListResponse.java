package com.hotelbooking.hotelbooking.ouputs;

import java.io.Serializable;
import java.util.List;

public class AvailableRoomListResponse extends MyPaging implements Serializable {
    private List<AvailableRoomInformationResponse> availableRoomListResponse;

    public AvailableRoomListResponse() {
    }

    public AvailableRoomListResponse(Integer totalPage, Integer previousPage,
                                     Integer currentPage, Integer nextPage, Integer pageSize,
                                     List<AvailableRoomInformationResponse> availableRoomListResponse) {
        super(totalPage, previousPage, currentPage, nextPage, pageSize);
        this.availableRoomListResponse = availableRoomListResponse;
    }

    public List<AvailableRoomInformationResponse> getAvailableRoomListResponse() {
        return availableRoomListResponse;
    }

    public void setAvailableRoomListResponse(List<AvailableRoomInformationResponse> availableRoomListResponse) {
        this.availableRoomListResponse = availableRoomListResponse;
    }
}
