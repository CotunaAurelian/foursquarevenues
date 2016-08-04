package com.forsquare.venuesearch.business.webservices.responses;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Interface definition for callbacks to be invoked when data is received. This interface will be used only in the business layer. It will handle
 * base responses that are abstraction dtos used to wrap different kind of data.
 * <p>
 * <p>
 * Created by Wraith
 */

public interface DataFetchListener {

    /**
     * Called when the data has been successfully fetched
     *
     * @param response The response data
     */
    void onSuccess(@NonNull VenueResponse response);

    /**
     * Called when there was something wrong with the request or response
     *
     * @param exception The throwable that causes the call to fail. This parameter might be null
     */
    void onError(@Nullable Throwable exception);

}
