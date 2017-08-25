package purwakarta.kota.kuliner.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import purwakarta.kota.kuliner.R;
import purwakarta.kota.kuliner.model.Promo;

/**
 * Created by aomcenter on 8/25/2017.
 */

public class PromoAdapter extends RecyclerView.Adapter<PromoViewHolder> {

    private List<Promo> promoList;
    private Context mContext;

    public PromoAdapter(Context context, List<Promo> promos) {
        this.promoList = promos;
        this.mContext = context;

    }

    @Override
    public PromoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_promo, null);
        PromoViewHolder viewHolder = new PromoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PromoViewHolder holder, int position) {

        Promo promo = promoList.get(position);
        holder.textJudul.setText(promo.getJudulPromo());
        Picasso.with(mContext).load("http://kulinerpwk.id/maranggi/" + promo.getImage()).error(android.R.drawable.stat_notify_error).fit().into(holder.imagePromo);

    }

    @Override
    public int getItemCount() {
        return promoList.size();
    }
}
