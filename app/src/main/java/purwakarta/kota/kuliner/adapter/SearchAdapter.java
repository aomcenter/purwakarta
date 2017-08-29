package purwakarta.kota.kuliner.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import purwakarta.kota.kuliner.DetailActivity;
import purwakarta.kota.kuliner.R;
import purwakarta.kota.kuliner.helper.SessionManager;
import purwakarta.kota.kuliner.model.DetailWarung;

/**
 * Created by aomcenter on 8/28/2017.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<DetailWarung> mDataWarung, filterList;
    private Context context;
    private SessionManager session;

    public SearchAdapter(Context context, List<DetailWarung> dataWarungList) {
        this.mDataWarung = dataWarungList;
        this.context = context;
        this.filterList = new ArrayList<>();
        // we copy the original list to the filter list and use it for setting row values
        this.filterList.addAll(this.mDataWarung);
        session = new SessionManager(context);

    }


    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_warter, null);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchAdapter.SearchViewHolder holder, final int position) {
        DetailWarung dataWarung = filterList.get(position);
        holder.namaTXT.setText(dataWarung.getNamawarung());
        holder.alamatTxt.setText(dataWarung.getDeskripsi());
        holder.jarakTxt.setVisibility(View.GONE);
        holder.imageWarter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String warung = filterList.get(position).getNamawarung();
                String id = filterList.get(position).getIdWarung();
                //Toast.makeText(getContext(),kategori,Toast.LENGTH_SHORT).show();
                session.setKeyId(id, warung);
                Intent intent = new Intent(context, DetailActivity.class);
                context.startActivity(intent);
            }
        });
        Picasso.with(context).load("http://kulinerpwk.id/maranggi/" + dataWarung.getGambar()).error(android.R.drawable.stat_notify_error).fit().into(holder.imageWarter);

    }

    @Override
    public int getItemCount() {
        return (null != filterList ? filterList.size() : 0);
    }

    // Do Search...
    public void filter(final String text) {

        // Searching could be complex..so we will dispatch it to a different thread...
        new Thread(new Runnable() {
            @Override
            public void run() {

                // Clear the filter list
                filterList.clear();

                // If there is no search value, then add all original list items to filter list
                if (TextUtils.isEmpty(text)) {

                    filterList.addAll(mDataWarung);

                } else {
                    // Iterate in the original List and add it to filter list...
                    for (DetailWarung item : mDataWarung) {
                        if (item.getNamawarung().toLowerCase().contains(text.toLowerCase()) ||
                                item.getAlamat().toLowerCase().contains(text.toLowerCase())) {
                            // Adding Matched items
                            filterList.add(item);
                        }
                    }
                }

                // Set on UI Thread
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Notify the List that the DataSet has changed...
                        notifyDataSetChanged();
                    }
                });

            }
        }).start();
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {
        ImageView imageWarter;
        TextView namaTXT, alamatTxt, jarakTxt;

        public SearchViewHolder(View itemView) {
            super(itemView);

            imageWarter = (ImageView) itemView.findViewById(R.id.gambar_warter);
            namaTXT = (TextView) itemView.findViewById(R.id.warung_warter);
            alamatTxt = (TextView) itemView.findViewById(R.id.alamat_warter);
            jarakTxt = (TextView) itemView.findViewById(R.id.jarak_warter);

        }
    }
}
