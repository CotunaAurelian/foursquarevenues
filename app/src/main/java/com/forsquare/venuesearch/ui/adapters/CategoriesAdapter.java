package com.forsquare.venuesearch.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.forsquare.venuesearch.R;
import com.forsquare.venuesearch.model.CategoryDTO;
import com.forsquare.venuesearch.ui.OnRecyclerItemClickListener;

import java.util.List;

/**
 * Provides views and customization for the categories {@link android.support.v7.widget.RecyclerView}
 * Created by Wraith
 */

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {


    /**
     * Dataset handled by the adapter
     */
    private List<CategoryDTO> mDataSet;

    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tile_layout, parent, false);
        return new ViewHolder(contentView);
    }

    @Override
    public void onBindViewHolder(CategoriesAdapter.ViewHolder holder, int position) {
        holder.mCategoryName.setText(mDataSet.get(position).getCategoryName());
        holder.mCategoryImage.setBackgroundResource(mDataSet.get(position).getImageResId());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    /**
     * Listener to be notified when a click event on one of the element occurs.
     */
    private OnRecyclerItemClickListener mOnItemClickListener;


    /**
     * Creates a new instance of this adapter and sets it's elements
     *
     * @param itemsList List of {@link CategoryDTO} for this adapter
     */
    public CategoriesAdapter(List<CategoryDTO> itemsList) {
        this.mDataSet = itemsList;
    }


    /**
     * View holder for item reusability
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mCategoryName;
        private ImageView mCategoryImage;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mCategoryImage = (ImageView) itemView.findViewById(R.id.category_image);
            this.mCategoryName = (TextView) itemView.findViewById(R.id.category_name);
        }
    }

}
