package com.forsquare.venuesearch.business.webservices.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Response for a venue received from the server
 * Created by Wraith
 */

public class VenueResponse {
    List<VenueDTO> venueDTOList;

    public VenueResponse(){

    }

    public List<VenueDTO> getVenueDTOList() {
        return venueDTOList;
    }

    public void setVenueDTOList(List<VenueDTO> venueDTOList) {
        this.venueDTOList = venueDTOList;
    }
}
