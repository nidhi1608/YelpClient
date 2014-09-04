package com.example.yelpclient.app.fragments;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.yelpclient.app.R;
import com.example.yelpclient.app.adapters.SearchResultsListAdapter;
import com.example.yelpclient.app.models.Business;
import com.example.yelpclient.app.net.YelpAPI;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SearchResultsListFragment extends Fragment {
    private View mCachedView;
    private ViewGroup mCachedViewGroup;
    @InjectView(R.id.lvSearchResults) ListView lvSearchResults;

    public SearchResultsListFragment() {
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mCachedViewGroup == container) return mCachedView;
        mCachedViewGroup = container;
        mCachedView = inflater.inflate(R.layout.fragment_search_results_list, container, false);
        mCachedView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        ButterKnife.inject(this, mCachedView);
        populateList();
        return mCachedView;
    }

    private void populateList() {
        new AsyncTask<Void, Void, ArrayList<Business>>() {
            @Override
            protected ArrayList<Business> doInBackground(Void... params) {
                String businesses = YelpAPI.getYelp(getActivity()).searchForBusinessesByLocation("vegetarian", "San Francisco");
                try {
                    return processBusinessesFromJson(businesses);
                } catch (JSONException e) {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(ArrayList<Business> businesses) {
                SearchResultsListAdapter searchResultsListAdapter = new SearchResultsListAdapter(getActivity(), businesses);
                lvSearchResults.setAdapter(searchResultsListAdapter);
            }
        }.execute();
    }

    private ArrayList<Business> processBusinessesFromJson(String jsonStuff) throws JSONException {
        Gson gson = new Gson();
        JSONObject json = new JSONObject(jsonStuff);
        Type listType = new TypeToken<List<Business>>() {}.getType();
        ArrayList<Business> businessObjs = gson.fromJson(json.get("businesses").toString(), listType);
        return businessObjs;
    }
}
