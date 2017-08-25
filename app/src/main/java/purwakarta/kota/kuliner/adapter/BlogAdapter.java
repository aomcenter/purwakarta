package purwakarta.kota.kuliner.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import purwakarta.kota.kuliner.R;
import purwakarta.kota.kuliner.model.Blog;

/**
 * Created by aomcenter on 8/25/2017.
 */

public class BlogAdapter extends RecyclerView.Adapter<BlogViewHolder> {

    private List<Blog> blogList;
    private Context mContext;

    public BlogAdapter(Context context, List<Blog> blogs) {
        this.blogList = blogs;
        this.mContext = context;

    }

    @Override
    public BlogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_blog, null);
        BlogViewHolder viewHolder = new BlogViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BlogViewHolder holder, int position) {

        Blog blog = blogList.get(position);
        holder.textJudul.setText(blog.getJudulblog());
        Picasso.with(mContext).load("http://kulinerpwk.id/maranggi/" + blog.getImage()).error(android.R.drawable.stat_notify_error).fit().into(holder.imageBlog);

    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }
}
