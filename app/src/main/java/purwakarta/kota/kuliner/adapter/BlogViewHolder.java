package purwakarta.kota.kuliner.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import purwakarta.kota.kuliner.R;

/**
 * Created by aomcenter on 8/25/2017.
 */

public class BlogViewHolder extends RecyclerView.ViewHolder {

    ImageView imageBlog;
    TextView textJudul;

    public BlogViewHolder(View itemView) {
        super(itemView);
        imageBlog = (ImageView) itemView.findViewById(R.id.blog_gambar);
        textJudul = (TextView) itemView.findViewById(R.id.judul_blog);
    }
}
