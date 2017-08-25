package purwakarta.kota.kuliner.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import purwakarta.kota.kuliner.R;

/**
 * Created by aomcenter on 8/25/2017.
 */

public class PromoViewHolder extends RecyclerView.ViewHolder {

    ImageView imagePromo;
    TextView textJudul;

    public PromoViewHolder(View itemView) {
        super(itemView);
        imagePromo = (ImageView) itemView.findViewById(R.id.promo_gambar);
        textJudul = (TextView) itemView.findViewById(R.id.judul_promo);
    }
}
