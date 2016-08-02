package mposadar.com.thelastfm.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopAlbumsFragment extends Fragment {

    // Logic References
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
        topAlbumsList.addItemDecoration(new ItemOffsetDecoration(getActivity(), R.integer.offset));
    }

    private void getTopAlbums() {
        ApiService client = ServiceGenerator.createService(ApiService.class);

        HashMap<String, String> params = new HashMap<>();
        params.put(ApiConstants.QUERY_KEY_FORMAT, ApiConstants.QUERY_VALUE_FORMAT);
        params.put(ApiConstants.QUERY_API_KEY, "...");
        params.put(ApiConstants.QUERY_KEY_ARTISTS, "ColdPlay");
        params.put(ApiConstants.QUERY_KEY_METHOD, ApiConstants.QUERY_VALUE_METHOD_2);
        Call<TopAlbumsResponse> call = client.getTopAlbums(params);
    }

}
