package sodexo.pe.com.sodexo.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.domain.entity.BlogEntity;
import sodexo.pe.com.sodexo.presentation.interfaces.BlogListView;

/**
 * Created by ronaldvelasquez on 12/12/16.
 */

public class RVBlogListAdapter extends RecyclerView.Adapter<RVBlogListAdapter.VHBlogList> {
    private List<BlogEntity> list;
    private Context context;
    private BlogListView listView;

    public RVBlogListAdapter(Context context, BlogListView view) {
        this.list = new ArrayList<>();
        this.context = context;
        listView = view;
    }

    @Override
    public VHBlogList onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VHBlogList(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blog, parent, false));
    }

    @Override
    public void onBindViewHolder(VHBlogList holder, int position) {
        final BlogEntity blog = list.get(position);
        Glide.with(context).load(blog.getImage()).into(holder.ivBlog);
        holder.tvTitleBlog.setText(blog.getTitle());
        holder.tvSumillaBlog.setText(blog.getSumilla());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.showBlogDetail(blog);
            }
        });
        if (blog.isCompanyBlog()) {
            holder.star.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addBlogList(List<BlogEntity> blogEntities) {
        list.clear();
        list.addAll(blogEntities);
        notifyDataSetChanged();
    }

    public class VHBlogList extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_blog)
        ImageView ivBlog;
        @BindView(R.id.tv_title_blog)
        TextView tvTitleBlog;
        @BindView(R.id.tv_sumilla_blog)
        TextView tvSumillaBlog;
        @BindView(R.id.star)
        ImageView star;
        public VHBlogList(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
