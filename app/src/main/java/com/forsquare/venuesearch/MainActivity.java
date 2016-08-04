package com.forsquare.venuesearch;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.forsquare.venuesearch.business.webservices.VenueService;
import com.forsquare.venuesearch.business.webservices.responses.DataFetchListener;
import com.forsquare.venuesearch.business.webservices.responses.VenueResponse;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DataFetchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        Button firstCategoryButton = (Button) findViewById(R.id.first_category);
        firstCategoryButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.first_category:
                VenueService.getInstance().fetchVenueForCategory("4d4b7104d754a06370d81259",this);
        }
    }

    @Override
    public void onSuccess(@NonNull VenueResponse response) {
        if (response!= null){

        }
    }

    @Override
    public void onError(@Nullable Throwable exception) {

    }
}
