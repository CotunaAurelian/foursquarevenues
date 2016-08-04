package com.forsquare.venuesearch;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.forsquare.venuesearch.business.webservices.VenueService;
import com.forsquare.venuesearch.business.webservices.responses.DataFetchListener;
import com.forsquare.venuesearch.business.webservices.responses.VenueResponse;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DataFetchListener {

    /**
     * Recyclerview containing the grid for the categories
     */
    private RecyclerView mCategoriesRecyclerView;

    /**
     * Number of squares on horizontal on the recycler view
     */
    private int SPAN_COUNT = 3;

    /**
     * The manager that handles views in the grid on the recycler
     */
    private GridLayoutManager mGridLayoutManager;


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

        initializeCategoriesRecyclerView();
    }

    /**
     * Prepares and initialize the recycler view and it's layout manager
     */
    private void initializeCategoriesRecyclerView() {
        mCategoriesRecyclerView = (RecyclerView) findViewById(R.id.categories_recycler_view);

        mCategoriesRecyclerView.setHasFixedSize(true);
        mGridLayoutManager = new GridLayoutManager(MainActivity.this, SPAN_COUNT);
        mCategoriesRecyclerView.setLayoutManager(mGridLayoutManager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.first_category:
                VenueService.getInstance().fetchVenueForCategory("4d4b7104d754a06370d81259", this);
        }
    }

    @Override
    public void onSuccess(@NonNull VenueResponse response) {
        if (response != null) {
            Toast.makeText(MainActivity.this, "Received " + response.getVenueDTOList().size(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(@Nullable Throwable exception) {

    }
}
