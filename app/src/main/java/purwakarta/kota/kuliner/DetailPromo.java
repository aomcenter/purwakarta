package purwakarta.kota.kuliner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import purwakarta.kota.kuliner.api.ApiBase;
import purwakarta.kota.kuliner.api.ApiInterface;
import purwakarta.kota.kuliner.helper.SessionManager;
import purwakarta.kota.kuliner.model.Promo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPromo extends AppCompatActivity {
    private SessionManager session;
    private ImageView imageView;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_promo);
        session = new SessionManager(this);
        HashMap<String, String> keyid = session.getKeyId();
        String idwr = keyid.get(SessionManager.KEY_ID);
        String nama = keyid.get(SessionManager.KEY_NAMA);
        setTitle(nama);
        imageView = (ImageView) findViewById(R.id.detail_gambar_promo);
        textView = (TextView) findViewById(R.id.deskripsi_promo);
        getDetailPromo(idwr);
    }

    private void getDetailPromo(String id) {


        ApiInterface apiService = ApiBase.getClient().create(ApiInterface.class);

        Call<List<Promo>> mService = apiService.getDetailPromo(id);
        mService.enqueue(new Callback<List<Promo>>() {
            //Asyncronous Request
            @Override
            public void onResponse(Call<List<Promo>> call, Response<List<Promo>> response) {
                try {

                    //dapatkan hasil parsing dari method response.body()
                    List<Promo> severWarung = response.body();
                    if (severWarung != null) {

                        Picasso.with(DetailPromo.this).load("http://kulinerpwk.id/maranggi/" + severWarung.iterator().next().getImage()).error(android.R.drawable.stat_notify_error).fit().into(imageView);
                        textView.setText(severWarung.iterator().next().getDeskripsi());

                    } else {
                        Toast.makeText(DetailPromo.this, "Data Kosong", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(DetailPromo.this, "Gagal Total", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Promo>> call, Throwable t) {
                call.cancel();
                Toast.makeText(DetailPromo.this, "Tidak Dapat Terhubung Ke Server", Toast.LENGTH_LONG).show();
            }

        });
    }
}
