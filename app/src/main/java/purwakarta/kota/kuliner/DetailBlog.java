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
import purwakarta.kota.kuliner.model.Blog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailBlog extends AppCompatActivity {
    private SessionManager session;
    private ImageView imageView;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_blog);
        session = new SessionManager(this);
        HashMap<String, String> keyid = session.getKeyId();
        String idwr = keyid.get(SessionManager.KEY_ID);
        String nama = keyid.get(SessionManager.KEY_NAMA);
        setTitle(nama);
        imageView = (ImageView) findViewById(R.id.detail_gambar_blog);
        textView = (TextView) findViewById(R.id.deskripsi_blog);
        getDetailBlog(idwr);
    }

    private void getDetailBlog(String id) {


        ApiInterface apiService = ApiBase.getClient().create(ApiInterface.class);

        Call<List<Blog>> mService = apiService.getDetailBlog(id);
        mService.enqueue(new Callback<List<Blog>>() {
            //Asyncronous Request
            @Override
            public void onResponse(Call<List<Blog>> call, Response<List<Blog>> response) {
                try {

                    //dapatkan hasil parsing dari method response.body()
                    List<Blog> severWarung = response.body();
                    if (severWarung != null) {

                        Picasso.with(DetailBlog.this).load("http://kulinerpwk.id/maranggi/" + severWarung.iterator().next().getImage()).error(android.R.drawable.stat_notify_error).fit().into(imageView);
                        textView.setText(severWarung.iterator().next().getDeskripsi());

                    } else {
                        Toast.makeText(DetailBlog.this, "Data Kosong", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(DetailBlog.this, "Gagal Total", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Blog>> call, Throwable t) {
                call.cancel();
                Toast.makeText(DetailBlog.this, "Tidak Dapat Terhubung Ke Server", Toast.LENGTH_LONG).show();
            }

        });
    }
}
