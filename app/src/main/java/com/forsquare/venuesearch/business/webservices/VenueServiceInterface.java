package com.forsquare.venuesearch.business.webservices;

import com.forsquare.venuesearch.business.webservices.responses.VenueResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Interface used to describe the endpoints using special Retrofit annotations to encode details
 * about the calls and parameters passed to the request methods.
 * <p>
 * <p>
 * Created by Wraith
 */


public interface VenueServiceInterface {

    /**
     * Base URL to the server where the calls are made
     */
    String BASE_URL = "https://api.foursquare.com/";

    /**
     * Default client id for Foursquare application Authentication process
     */
    String DEFAULT_CLIENT_ID = "ZVAKBIRJ1E152AO2JRR3YYSVTKA0SM4RDNER0GYJEXL4HYBA";

    /**
     * Default client secret for Foursquare application Authentication process
     */
    String DEFAULT_CLIENT_SECRET = "FNXTZOGZCPN1SQWGL40KEGIXQ1VP40K1T13Z5ACTL5MC5E5O";

    /**
     * Default version added to calls
     */
    String DEFAULT_VERSION ="20160804";

    /**
     * Coordinates parameter for the get request
     */
    String COORDINATES_PARAMETER = "ll";

    /**
     * Category parameter for the request
     */
    String CATEGORY_ID_PARAMETER = "categoryId";

    /**
     * Client ID parameter for the request
     */
    String CLIENT_ID_PARAMETER = "client_id";

    /**
     * Client Secret parameter for the request
     */
    String CLIENT_SECRET_PARAMETER = "client_secret";

    /**
     * Version parameter for the request
     */
    String VERSION_PARAMETER = "v";


    @GET("/v2/venues/search")
    Call<VenueResponse> fetchVenues(@QueryMap Map<String, String> parameters);
}
