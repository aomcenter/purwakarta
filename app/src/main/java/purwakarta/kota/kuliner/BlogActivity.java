package purwakarta.kota.kuliner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import purwakarta.kota.kuliner.adapter.BlogAdapter;
import purwakarta.kota.kuliner.api.ApiBase;
import purwakarta.kota.kuliner.api.ApiInterface;
import purwakarta.kota.kuliner.helper.RecyclerViewClickListener;
import purwakarta.kota.kuliner.helper.RecyclerViewTouchListener;
import purwakarta.kota.kuliner.helper.SessionManager;
import purwakarta.kota.kuliner.model.Blog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlogActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private BlogAdapter mAdapter;
    private List<Blog> blogLis;
    private SessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        setTitle("Blog");
        mRecyclerView = (RecyclerView) findViewById(R.id.rec_blog);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
        session = new SessionManager(this);
        getBlogna();
    }

    private void getBlogna() {


        ApiInterface apiService = ApiBase.getClient().create(ApiInterface.class);

        Call<List<Blog>> mService = apiService.getAllBlog();
        mService.enqueue(new Callback<List<Blog>>() {
            //Asyncronous Request
            @Override
            public void onResponse(Call<List<Blog>> call, Response<List<Blog>> response) {
                try {

                    //dapatkan hasil parsing dari method response.body()
                    List<Blog> blogList = response.body();
                    if (blogList != null) {
                        blogLis = blogList;
                        mAdapter = new BlogAdapter(BlogActivity.this, blogList);
                        mRecyclerView.setAdapter(mAdapter);

                        initClick();

                    } else {
                        Toast.makeText(BlogActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(BlogActivity.this, "Gagal Total", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Blog>> call, Throwable t) {
                call.cancel();
                Toast.makeText(BlogActivity.this, "Tidak Dapat Terhubung Ke Server", Toast.LENGTH_LONG).show();
            }

        });
    }

    private void initClick() {
        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(BlogActivity.this, mRecyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                String warung = blogLis.get(position).getJudulblog();
                String id = blogLis.get(position).getIdblog();
                //Toast.makeText(getContext(),kategori,Toast.LENGTH_SHORT).show();
                session.setKeyId(id, warung);
                Intent intent = new Intent(BlogActivity.this, DetailBlog.class);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        //Toast.makeText(getContext(),item,Toast.LENGTH_SHORT).show();

    }

}
