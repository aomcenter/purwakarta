package purwakarta.kota.kuliner.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import purwakarta.kota.kuliner.R;
import purwakarta.kota.kuliner.model.Event;

/**
 * Created by aomcenter on 8/25/2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private List<Event> eventList;
    private Context mContext;

    public EventAdapter(Context context, List<Event> events) {
        this.eventList = events;
        this.mContext = context;

    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_event, null);
        EventViewHolder viewHolder = new EventViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {

        Event event = eventList.get(position);
        holder.textJudul.setText(event.getJudulevent());
        Picasso.with(mContext).load("http://kulinerpwk.id/maranggi/" + event.getImage()).error(android.R.drawable.stat_notify_error).fit().into(holder.imageEvent);

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}
