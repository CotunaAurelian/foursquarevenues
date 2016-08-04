package com.forsquare.venuesearch.business.webservices.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Data Transport Object for Venues
 * Created by Wraith on 8/4/2016.
 */

public class VenueDTO {

    @SerializedName("id")
    @Expose
    String id;

    @SerializedName("name")
    @Expose
    String name;

    public VenueDTO(){

    }
}
