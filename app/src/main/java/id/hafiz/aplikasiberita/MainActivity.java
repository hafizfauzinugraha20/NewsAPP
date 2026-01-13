package id.hafiz.aplikasiberita;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvNews;
    private NewsAdapter adapter;
    private List<News> newsList = new ArrayList<>();

    // API KEY ANDA SUDAH DIPASANG DISINI
    private static final String API_KEY = "5adb5df029214ec89a22a6db2907dd6f";

    private MaterialCardView cardFeatured;
    private ImageView imgFeatured;
    private TextView tvFeaturedTitle, tvFeaturedCategory;
    private ChipGroup chipGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init Views
        rvNews = findViewById(R.id.rvNews);
        cardFeatured = findViewById(R.id.cardFeatured);
        imgFeatured = findViewById(R.id.imgFeatured);
        tvFeaturedTitle = findViewById(R.id.tvFeaturedTitle);
        tvFeaturedCategory = findViewById(R.id.tvFeaturedCategory);
        chipGroup = findViewById(R.id.chipGroupCategory);

        // Setup RecyclerView Kosong
        rvNews.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewsAdapter(this, new ArrayList<>(), this::openDetail);
        rvNews.setAdapter(adapter);

        // Setup Categories
        setupCategories();

        // Panggil API (Default: general)
        fetchNews("general");
    }

    private void fetchNews(String category) {
        // Memanggil API NewsAPI.org
        NewsApiService.RetrofitClient.getService()
                .getTopHeadlines("us", category, API_KEY) // Menggunakan region Indonesia ("id")
                .enqueue(new Callback<ArticleResponse>() {
                    @Override
                    public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<ArticleResponse.Article> articles = response.body().getArticles();
                            newsList.clear(); // Bersihkan data lama

                            // Konversi dari Model API ke Model Aplikasi Kita (News.java)
                            for (ArticleResponse.Article article : articles) {
                                // Filter artikel yang dihapus/broken
                                if (article.getTitle() != null && !article.getTitle().equals("[Removed]")) {
                                    newsList.add(new News(
                                            article.getTitle(),
                                            article.getSource().getName(), // Pakai nama sumber sebagai kategori
                                            article.getAuthor() != null ? article.getAuthor() : "Redaksi",
                                            formatDate(article.getPublishedAt()),
                                            "Baca Selengkapnya",
                                            article.getUrlToImage(),
                                            article.getContent() != null ? article.getContent() : article.getDescription(),
                                            article.getDescription()
                                    ));
                                }
                            }

                            updateUI();
                        } else {
                            Toast.makeText(MainActivity.this, "Gagal memuat berita: " + response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArticleResponse> call, Throwable t) {
                        Log.e("API_ERROR", t.getMessage());
                        Toast.makeText(MainActivity.this, "Koneksi Error. Cek internet anda.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateUI() {
        if (newsList.isEmpty()) return;

        // Update Featured News (Ambil item pertama sebagai Highlight)
        News featured = newsList.get(0);
        tvFeaturedTitle.setText(featured.getTitle());
        tvFeaturedCategory.setText(featured.getCategory());

        // Handle image untuk featured
        if (featured.getImageUrl() != null) {
            Glide.with(this).load(featured.getImageUrl()).into(imgFeatured);
        } else {
            imgFeatured.setImageResource(R.drawable.ic_launcher_background);
        }

        cardFeatured.setOnClickListener(v -> openDetail(featured));

        // Update List (Item ke-2 sampai terakhir)
        List<News> listOnly = new ArrayList<>(newsList);
        if (listOnly.size() > 1) {
            listOnly.remove(0); // Hapus headline agar tidak muncul dua kali
        }

        // Buat adapter baru atau update data adapter
        adapter = new NewsAdapter(this, listOnly, this::openDetail);
        rvNews.setAdapter(adapter);
    }

    private void setupCategories() {
        // Mapping kategori Tampilan UI -> Parameter API
        String[] apiCategories = {"general", "technology", "business", "sports", "health", "entertainment"};
        String[] displayCategories = {"Terkini", "Teknologi", "Bisnis", "Olahraga", "Kesehatan", "Hiburan"};

        for (int i = 0; i < apiCategories.length; i++) {
            Chip chip = new Chip(this);
            chip.setText(displayCategories[i]);
            chip.setCheckable(true);
            chip.setChipBackgroundColorResource(com.google.android.material.R.color.m3_chip_background_color);

            final String categoryKey = apiCategories[i];

            chip.setOnClickListener(v -> {
                // Logika agar visual hanya satu yang terpilih
                chipGroup.clearCheck();
                chip.setChecked(true);
                // Fetch data baru berdasarkan kategori
                fetchNews(categoryKey);
            });

            chipGroup.addView(chip);
        }
        // Set default check pada chip pertama
        if(chipGroup.getChildCount() > 0) ((Chip)chipGroup.getChildAt(0)).setChecked(true);
    }

    // Helper untuk format tanggal dari ISO 8601 ke format Indonesia
    private String formatDate(String dateString) {
        if (dateString == null) return "";
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
            Date date = inputFormat.parse(dateString);
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM, HH:mm", new Locale("id", "ID"));
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return dateString;
        }
    }

    private void openDetail(News news) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("news_data", news);
        startActivity(intent);
    }
}