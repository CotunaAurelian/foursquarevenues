package com.forsquare.venuesearch.ui.activities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.forsquare.venuesearch.R;
import com.forsquare.venuesearch.business.webservices.VenueService;
import com.forsquare.venuesearch.business.webservices.responses.DataFetchListener;
import com.forsquare.venuesearch.business.webservices.responses.VenueResponse;
import com.forsquare.venuesearch.model.CategoryDTO;
import com.forsquare.venuesearch.ui.adapters.CategoriesAdapter;

import java.util.ArrayList;

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

    /**
     * Categories adapter for the recycler view
     */
    private CategoriesAdapter mAdapter;


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
        populateCategories();
    }

    /**
     * Populate the categories tiles
     */
    private void populateCategories() {
        ArrayList<CategoryDTO> categoriesList = new ArrayList<>();
        categoriesList.add(new CategoryDTO(R.drawable.ic_add_shopping_cart_white_48dp, "Shopping Cart"));
        categoriesList.add(new CategoryDTO(R.drawable.ic_cake_white_48dp, "Cake"));
        categoriesList.add(new CategoryDTO(R.drawable.ic_local_airport_white_48dp, "Airport"));
        categoriesList.add(new CategoryDTO(R.drawable.ic_local_bar_white_48dp, "Bar"));
        categoriesList.add(new CategoryDTO(R.drawable.ic_local_cafe_white_48dp, "Coffee"));
        categoriesList.add(new CategoryDTO(R.drawable.ic_local_hotel_white_48dp, "Hotel"));
        categoriesList.add(new CategoryDTO(R.drawable.ic_local_mall_white_48dp, "Mall"));
        categoriesList.add(new CategoryDTO(R.drawable.ic_local_movies_white_48dp, "Cinema"));
        categoriesList.add(new CategoryDTO(R.drawable.ic_star_border_white_48dp, "Favorites"));

        mAdapter = new CategoriesAdapter(categoriesList);
        mCategoriesRecyclerView.setAdapter(mAdapter);
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
