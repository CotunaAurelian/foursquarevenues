package com.forsquare.venuesearch.business.webservices;

import android.support.annotation.NonNull;
import android.util.Log;

import com.forsquare.venuesearch.business.webservices.converter.VenueDeserializer;
import com.forsquare.venuesearch.business.webservices.responses.DataFetchListener;
import com.forsquare.venuesearch.business.webservices.responses.VenueDTO;
import com.forsquare.venuesearch.business.webservices.responses.VenueResponse;
import com.google.gson.GsonBuilder;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Manages the calls to the web server using Retrofit and OKHttp related classes.
 * All the Foursquare Venues search operations are handled by this api service.
 * Because there is no need for this class to have multiple instances, the calls can be made
 * accessing the single instance of this class using {@link #getInstance()} method
 * <p>
 * Created by Wraith
 */


public class VenueService {

    /**
     * Tag used to identify any logging made in this class
     */
    private static final String TAG = VenueService.class.getName();


    /**
     * Single instance of this class.
     */
    private static VenueService sInstance;

    /**
     * Private constructor to prevent any outside of class instance creation
     */
    private VenueService() {
    }

    /**
     * Returns a single instance of this class. If there are no available instances, a new one is created
     * and returned
     */
    public static VenueService getInstance() {
        if (sInstance == null) {
            sInstance = new VenueService();
        }
        return sInstance;
    }


    /**
     * Fetches venue for a certain categories, nearby
     *
     * @param categoryId        The category for which the venue are fetched
     * @param dataFetchListener The listener used to notify the status of the fetch mechanism
     */
    public void fetchVenueForCategory(String categoryId, @NonNull final DataFetchListener dataFetchListener) {

        //Because the response received from the foursquare server is quite complex and we don't need all that data
        //we create a gson builder that will handle only the part we are going to use
        //formatter:off
        GsonBuilder gsonBuilder = new GsonBuilder().
                excludeFieldsWithoutExposeAnnotation().
                registerTypeAdapter(VenueResponse.class, new VenueDeserializer());

        //Prepare the retrofit object and add all the necessary parameters to it through the builder
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(VenueServiceInterface.BASE_URL).
                addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .build();

        //Create the api search access object based on the already defined AsciiSearchServiceInterface
        VenueServiceInterface apiSearchAccess = retrofit.create(VenueServiceInterface.class);

        //Make the async calls
        Map<String, String> parameters = new HashMap<>();
        //We are going to return only products that are in stock
        parameters.put(VenueServiceInterface.COORDINATES_PARAMETER, "40.7,-74");
        parameters.put(VenueServiceInterface.CLIENT_ID_PARAMETER, VenueServiceInterface.DEFAULT_CLIENT_ID);
        parameters.put(VenueServiceInterface.CLIENT_SECRET_PARAMETER, VenueServiceInterface.DEFAULT_CLIENT_SECRET);
        parameters.put(VenueServiceInterface.VERSION_PARAMETER, VenueServiceInterface.DEFAULT_VERSION);


        Call<VenueResponse> call = apiSearchAccess.fetchVenues(parameters);
        call.enqueue(new Callback<VenueResponse>() {
            @Override
            public void onResponse(Call<VenueResponse> call, Response<VenueResponse> response) {
                int code = response.code();
                if (code == HttpURLConnection.HTTP_OK) {
                    //If the response code is ok
                    dataFetchListener.onSuccess(response.body());
                } else {
                    dataFetchListener.onError(new Exception("Response not ok " + code));
                }
            }

            @Override
            public void onFailure(Call<VenueResponse> call, Throwable throwable) {
                dataFetchListener.onError(throwable);
            }
        });


        //formatter:on
    }
}
