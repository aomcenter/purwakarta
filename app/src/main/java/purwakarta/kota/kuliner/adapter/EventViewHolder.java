package purwakarta.kota.kuliner.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import purwakarta.kota.kuliner.R;

/**
 * Created by aomcenter on 8/25/2017.
 */

public class EventViewHolder extends RecyclerView.ViewHolder {

    ImageView imageEvent;
    TextView textJudul;

    public EventViewHolder(View itemView) {
        super(itemView);
        imageEvent = (ImageView) itemView.findViewById(R.id.event_gambar);
        textJudul = (TextView) itemView.findViewById(R.id.judul_event);
    }
}
