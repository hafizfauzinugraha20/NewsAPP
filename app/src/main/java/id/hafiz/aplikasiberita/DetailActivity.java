package id.hafiz.aplikasiberita;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Setup Toolbar Back Button
        Toolbar toolbar = findViewById(R.id.detailToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());
        getSupportActionBar().setTitle("");

        // Retrieve Data
        News news = (News) getIntent().getSerializableExtra("news_data");

        if (news != null) {
            ImageView imgDetail = findViewById(R.id.imgDetail);
            TextView tvTitle = findViewById(R.id.tvDetailTitle);
            TextView tvAuthor = findViewById(R.id.tvDetailAuthor);
            TextView tvDate = findViewById(R.id.tvDetailDate);
            TextView tvContent = findViewById(R.id.tvDetailContent);
            Chip chipCategory = findViewById(R.id.chipDetailCategory);

            tvTitle.setText(news.getTitle());
            tvAuthor.setText(news.getAuthor());
            tvDate.setText(news.getDate() + " • " + news.readTime);
            tvContent.setText(news.getContent());
            chipCategory.setText(news.getCategory());

            Glide.with(this).load(news.getImageUrl()).into(imgDetail);
        }
    }
}