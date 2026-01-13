package id.hafiz.aplikasiberita;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private Context context;
    private List<News> newsList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(News news);
    }

    public NewsAdapter(Context context, List<News> newsList, OnItemClickListener listener) {
        this.context = context;
        this.newsList = newsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.tvTitle.setText(news.getTitle());
        holder.tvDate.setText(news.getDate());
        holder.chipCategory.setText(news.getCategory());

        Glide.with(context)
                .load(news.getImageUrl())
                .into(holder.imgNews);

        holder.itemView.setOnClickListener(v -> listener.onItemClick(news));
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDate;
        ImageView imgNews;
        Chip chipCategory;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            imgNews = itemView.findViewById(R.id.imgNews);
            chipCategory = itemView.findViewById(R.id.chipCategory);
        }
    }
}