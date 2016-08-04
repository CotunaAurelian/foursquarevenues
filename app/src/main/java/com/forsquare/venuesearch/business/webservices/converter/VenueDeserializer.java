package com.forsquare.venuesearch.business.webservices.converter;

import android.content.Context;

import com.forsquare.venuesearch.business.webservices.responses.VenueDTO;
import com.forsquare.venuesearch.business.webservices.responses.VenueResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Venue custom deserializer used to parse only a part of the response
 * Created by Wraith
 */

public class VenueDeserializer implements JsonDeserializer<VenueResponse> {

    /**
     * The Venues identifier in the complex json this class dematerializes
     */
    private String VENUES_TAG = "venues";

    /**
     * The Response identifier in the complex json this class dematerializes
     */
    private String RESPONSE_TAG = "response";


    @Override
    public VenueResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        //Get the response part of the json
        JsonObject response = ((JsonObject) json).getAsJsonObject(RESPONSE_TAG);

        JsonArray elements = response.getAsJsonArray(VENUES_TAG);

        Type listType = new TypeToken<List<VenueDTO>>() {
        }.getType();
        // Deserialize it the new part. We are using a new instance of GSON to avoid infinite recursion
        // on this deserializer
        ArrayList<VenueDTO> venuesList = new Gson().fromJson(elements, listType);

        //Create the propper response and return it
        VenueResponse venueResponse = new VenueResponse();
        venueResponse.setVenueDTOList(venuesList);
        return venueResponse;

    }
}
