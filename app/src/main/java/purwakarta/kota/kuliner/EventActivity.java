package purwakarta.kota.kuliner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import purwakarta.kota.kuliner.adapter.EventAdapter;
import purwakarta.kota.kuliner.api.ApiBase;
import purwakarta.kota.kuliner.api.ApiInterface;
import purwakarta.kota.kuliner.helper.RecyclerViewClickListener;
import purwakarta.kota.kuliner.helper.RecyclerViewTouchListener;
import purwakarta.kota.kuliner.helper.SessionManager;
import purwakarta.kota.kuliner.model.Event;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private EventAdapter mAdapter;
    private List<Event> eventLis;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        setTitle("Event");
        mRecyclerView = (RecyclerView) findViewById(R.id.rec_event);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_LOW);
        session = new SessionManager(this);
        getEventa();
    }

    private void getEventa() {


        ApiInterface apiService = ApiBase.getClient().create(ApiInterface.class);

        Call<List<Event>> mService = apiService.getAllEvent();
        mService.enqueue(new Callback<List<Event>>() {
            //Asyncronous Request
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                try {

                    //dapatkan hasil parsing dari method response.body()
                    List<Event> eventList = response.body();
                    if (eventList != null) {
                        eventLis = eventList;
                        mAdapter = new EventAdapter(EventActivity.this, eventList);
                        mRecyclerView.setAdapter(mAdapter);

                        initClick();

                    } else {
                        Toast.makeText(EventActivity.this, "Data Kosong", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(EventActivity.this, "Gagal Total", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                call.cancel();
                Toast.makeText(EventActivity.this, "Tidak Dapat Terhubung Ke Server", Toast.LENGTH_LONG).show();
            }

        });
    }

    private void initClick() {
        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(EventActivity.this, mRecyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                String warung = eventLis.get(position).getJudulevent();
                String id = eventLis.get(position).getIdevent();
                //Toast.makeText(getContext(),kategori,Toast.LENGTH_SHORT).show();
                session.setKeyId(id, warung);
                Intent intent = new Intent(EventActivity.this, DetailEvent.class);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        //Toast.makeText(getContext(),item,Toast.LENGTH_SHORT).show();

    }
}
