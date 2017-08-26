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
import purwakarta.kota.kuliner.model.Event;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailEvent extends AppCompatActivity {
    private SessionManager session;
    private ImageView imageView;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);
        session = new SessionManager(this);
        HashMap<String, String> keyid = session.getKeyId();
        String idwr = keyid.get(SessionManager.KEY_ID);
        String nama = keyid.get(SessionManager.KEY_NAMA);
        setTitle(nama);
        imageView = (ImageView) findViewById(R.id.detail_gambar_event);
        textView = (TextView) findViewById(R.id.deskripsi_event);
        getDetailEvent(idwr);
    }

    private void getDetailEvent(String id) {


        ApiInterface apiService = ApiBase.getClient().create(ApiInterface.class);

        Call<List<Event>> mService = apiService.getDetailEvent(id);
        mService.enqueue(new Callback<List<Event>>() {
            //Asyncronous Request
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                try {

                    //dapatkan hasil parsing dari method response.body()
                    List<Event> severWarung = response.body();
                    if (severWarung != null) {

                        Picasso.with(DetailEvent.this).load("http://kulinerpwk.id/maranggi/" + severWarung.iterator().next().getImage()).error(android.R.drawable.stat_notify_error).fit().into(imageView);
                        textView.setText(severWarung.iterator().next().getDeskripsi());

                    } else {
                        Toast.makeText(DetailEvent.this, "Data Kosong", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(DetailEvent.this, "Gagal Total", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                call.cancel();
                Toast.makeText(DetailEvent.this, "Tidak Dapat Terhubung Ke Server", Toast.LENGTH_LONG).show();
            }

        });
    }
}
