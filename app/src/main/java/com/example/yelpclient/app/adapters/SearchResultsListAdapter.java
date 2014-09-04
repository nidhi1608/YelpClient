package com.example.yelpclient.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yelpclient.app.R;
import com.example.yelpclient.app.models.Business;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SearchResultsListAdapter extends ArrayAdapter<Business> {

    public SearchResultsListAdapter(Context context, ArrayList<Business> businesses) {
        super(context, R.layout.item_search_result, businesses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Business business = (Business)getItem(position);
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_search_result, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.tvBusinessName.setText(position+1 + ". " + business.getName());
        Picasso.with(getContext()).load(business.getImageUrl()).into(holder.ivBusiness);
        Picasso.with(getContext()).load(business.getRating_img_url_large()).into(holder.ivRating);
        holder.tvReviewCount.setText(business.getReview_count() + " Reviews");
        holder.tvLocation.setText(business.getLocation().getAddress() + ", " + business.getLocation().getNeighborhood());
        holder.tvCategories.setText(business.getCategories());
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.tvBusinessName) TextView tvBusinessName;
        @InjectView(R.id.ivBusiness) ImageView ivBusiness;
        @InjectView(R.id.ivRating) ImageView ivRating;
        @InjectView(R.id.tvReviewCount) TextView tvReviewCount;
        @InjectView(R.id.tvLocation) TextView tvLocation;
        @InjectView(R.id.tvCategories) TextView tvCategories;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

}
