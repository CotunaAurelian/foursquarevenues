package com.forsquare.venuesearch.business.webservices.converter;

import android.content.Context;

import com.forsquare.venuesearch.business.webservices.responses.VenueDTO;
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
import java.util.List;

/**
 * Venue custom deserializer used to parse only a part of the response
 * Created by Wraith
 */

public class VenueDeserializer implements JsonDeserializer<List<VenueDTO>> {


    @Override
    public List<VenueDTO> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        //Get the response part of the json
        JsonObject response = ((JsonObject) json).getAsJsonObject("response");

//        (((JsonObject) json).getAsJsonObject("response")).getAsJsonArray("venues")
        JsonArray elements = response.getAsJsonArray("venues");

        Type listType = new TypeToken<List<VenueDTO>>(){}.getType();
        // Deserialize it the new part. We are using a new instance of GSON to avoid infinite recursion
        // on this deserializer
        return new Gson().fromJson(elements, listType);

    }
}
