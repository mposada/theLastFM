package mposadar.com.thelastfm.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import mposadar.com.thelastfm.R;
import mposadar.com.thelastfm.domain.Artists;
import mposadar.com.thelastfm.io.ApiConstants;
import mposadar.com.thelastfm.io.ApiService;
import mposadar.com.thelastfm.io.ServiceGenerator;
import mposadar.com.thelastfm.io.model.TopArtistsResponse;
import mposadar.com.thelastfm.ui.adapter.TopArtistsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mposadar on 29/06/16.
 */
public class TopArtistsFragment extends Fragment {

    // Log variable
    public String TAG = TopArtistsFragment.class.getSimpleName();
    // num of columns to use in RecyclerView
    public static final int NUM_COLUMNS = 2;
    // instance of adapter
    private TopArtistsAdapter adapter;

    // UI References
    private RecyclerView mHypedArtistsList;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Log.i(TAG, "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "onCreate");

        // init adapter
        adapter = new TopArtistsAdapter(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*
         * inflate the view
         * last parameter (attachToRoot): if true, click events will notify parent class!
         */
        View view = inflater.inflate(R.layout.fragment_top_artists, container, false);
        // get reference of RecyclerView from .xml
        mHypedArtistsList = (RecyclerView) view.findViewById(R.id.hyped_artists_list);

        setupArtistsList();
        getTopArtists();
        // setDummyContent();
        // return the view
        return view;
    }

    /*
     * execute the request to get the top artists
     */
    private void getTopArtists() {

        ApiService client = ServiceGenerator.createService(ApiService.class);

        HashMap<String, String> params = new HashMap<>();
        params.put(ApiConstants.QUERY_KEY_FORMAT, ApiConstants.QUERY_VALUE_FORMAT);
        params.put(ApiConstants.QUERY_API_KEY, "066f583a8a96a308ebc340f14e4c33a2");
        params.put(ApiConstants.QUERY_KEY_COUNTRY, ApiConstants.QUERY_VALUE_COUNTRY);
        params.put(ApiConstants.QUERY_KEY_METHOD, ApiConstants.QUERY_VALUE_METHOD);
        Call<TopArtistsResponse> call = client.getTopArtists(params);
        call.enqueue(new Callback<TopArtistsResponse>() {
            @Override
            public void onResponse(Call<TopArtistsResponse> call, Response<TopArtistsResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body().getArtists().size() > 0) {
                        adapter.addAll(response.body().getArtists());
                    }
                }
                else {
                    try {
                        String error = response.errorBody().string();
                        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<TopArtistsResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.i(TAG, "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.i(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.i(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.i(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.i(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.i(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.i(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();

        Log.i(TAG, "onDetach");
    }

    /**
     * configuration of recycler view
     */
    private void setupArtistsList() {
        // set the Layout appearance
        mHypedArtistsList.setLayoutManager(new GridLayoutManager(getActivity(), NUM_COLUMNS));
        // set the adapter to the RecyclerView
        mHypedArtistsList.setAdapter(adapter);
        // set item decoration: space between items
        mHypedArtistsList.addItemDecoration(new ItemOffsetDecoration(getActivity(), R.integer.offset));
    }

}
