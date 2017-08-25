package purwakarta.kota.kuliner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import purwakarta.kota.kuliner.adapter.PromoAdapter;
import purwakarta.kota.kuliner.api.ApiBase;
import purwakarta.kota.kuliner.api.ApiInterface;
import purwakarta.kota.kuliner.model.Promo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PromoActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private PromoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo);
        setTitle("Promo");
        mRecyclerView = (RecyclerView) findViewById(R.id.rec_promo);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
        getPromona();
    }

    private void getPromona() {


        ApiInterface apiService = ApiBase.getClient().create(ApiInterface.class);

        Call<List<Promo>> mService = apiService.getAllPromo();
        mService.enqueue(new Callback<List<Promo>>() {
            //Asyncronous Request
            @Override
            public void onResponse(Call<List<Promo>> call, Response<List<Promo>> response) {
                try {

                    //dapatkan hasil parsing dari method response.body()
                    List<Promo> promoList = response.body();
                    if (promoList != null) {
                        mAdapter = new PromoAdapter(PromoActivity.this, promoList);
                        mRecyclerView.setAdapter(mAdapter);

                    } else {
                        Toast.makeText(PromoActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(PromoActivity.this, "Gagal Total", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Promo>> call, Throwable t) {
                call.cancel();
                Toast.makeText(PromoActivity.this, "Tidak Dapat Terhubung Ke Server", Toast.LENGTH_LONG).show();
            }

        });
    }
}
