package com.forsquare.venuesearch.business.webservices.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Response for a venue received from the server
 * Created by Wraith
 */

public class VenueResponse {
    @SerializedName("venues")
    @Expose
    List<VenueDTO> venueDTOList;


    @SerializedName("confident")
    @Expose
    boolean isConfident;

    public VenueResponse(){

    }
}
