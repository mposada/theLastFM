package mposadar.com.thelastfm.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

import mposadar.com.thelastfm.R;
import mposadar.com.thelastfm.io.ApiConstants;
import mposadar.com.thelastfm.io.ApiService;
import mposadar.com.thelastfm.io.ServiceGenerator;
import mposadar.com.thelastfm.io.model.TopAlbumsResponse;
import mposadar.com.thelastfm.ui.ItemOffsetDecoration;
import mposadar.com.thelastfm.ui.adapter.TopAlbumsAdapter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopAlbumsFragment extends Fragment {

    // Logic References
    private static final String TAG = TopAlbumsFragment.class.getSimpleName();
    private TopAlbumsAdapter adapter;

    // UI References
    private RecyclerView topAlbumsList;

    public TopAlbumsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new TopAlbumsAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_albums, container, false);
        topAlbumsList = (RecyclerView) view.findViewById(R.id.top_albums_list);
        setupList();
        return view;
    }

    private void setupList() {
        topAlbumsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        topAlbumsList.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d(TAG, "onResume");

        HashMap<String, String> params = new HashMap<>();
        params.put(ApiConstants.QUERY_KEY_FORMAT, ApiConstants.QUERY_VALUE_FORMAT);
        params.put(ApiConstants.QUERY_API_KEY, "066f583a8a96a308ebc340f14e4c33a2");
        params.put(ApiConstants.QUERY_KEY_ARTISTS, "ColdPlay");
        params.put(ApiConstants.QUERY_KEY_METHOD, ApiConstants.QUERY_VALUE_METHOD_2);

        /*
         * observeOn: define where the response is going to be observer
         * in this case it will be observer in the Main Thread.
         *
         * subscribeOn: subscribe the request on a separated thread Schedulers.io()
         *
         * subscribe: define what to do with objects when finish to observeOn
         */
        ApiService client = ServiceGenerator.createService(ApiService.class);
        Observable<TopAlbumsResponse> observable = client.getTopAlbums(params);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<TopAlbumsResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(TopAlbumsResponse topAlbumsResponse) {
                        adapter.addAll(topAlbumsResponse.getAlbums());
                    }
                });

    }

}
