package purwakarta.kota.kuliner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import purwakarta.kota.kuliner.adapter.SearchAdapter;
import purwakarta.kota.kuliner.api.ApiBase;
import purwakarta.kota.kuliner.api.ApiInterface;
import purwakarta.kota.kuliner.model.DetailWarung;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView mSearchView;
    private RecyclerView mRecyclerView;
    private SearchAdapter mAdapter;
    private List<DetailWarung> detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setTitle("Cari Warung");
        mRecyclerView = (RecyclerView) findViewById(R.id.search_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
        getWarungna();
    }

    private void getWarungna() {


        ApiInterface apiService = ApiBase.getClient().create(ApiInterface.class);

        Call<List<DetailWarung>> mService = apiService.getAllWarung();
        mService.enqueue(new Callback<List<DetailWarung>>() {
            //Asyncronous Request
            @Override
            public void onResponse(Call<List<DetailWarung>> call, Response<List<DetailWarung>> response) {
                try {

                    //dapatkan hasil parsing dari method response.body()
                    List<DetailWarung> severWarung = response.body();
                    if (severWarung != null) {

                        detail = severWarung;
                        mAdapter = new SearchAdapter(SearchActivity.this, severWarung);
                        mRecyclerView.setAdapter(mAdapter);
                        setupSearchView();


                    } else {
                        Toast.makeText(SearchActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(SearchActivity.this, "Gagal Total", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<DetailWarung>> call, Throwable t) {
                call.cancel();
                Toast.makeText(SearchActivity.this, "Tidak Dapat Terhubung Ke Server", Toast.LENGTH_LONG).show();
            }

        });
    }

    private void setupSearchView() {
        mSearchView = (SearchView) findViewById(R.id.search_view);
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("Search Here");

    }

    public boolean onQueryTextChange(String newText) {
        mAdapter.filter(newText);
        return true;
    }

    public boolean onQueryTextSubmit(String query) {
        return false;
    }
}
