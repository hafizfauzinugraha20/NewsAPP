package id.hafiz.aplikasiberita;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {

    @GET("v2/top-headlines")
    Call<ArticleResponse> getTopHeadlines(
            @Query("country") String country,
            @Query("category") String category,
            @Query("apiKey") String apiKey
    );

    class RetrofitClient {
        private static final String BASE_URL = "https://newsapi.org/";
        private static Retrofit retrofit = null;

        public static NewsApiService getService() {
            if (retrofit == null) {

                // 1. Buat Logging Interceptor (Agar error muncul di Logcat)
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);

                // 2. Buat Client dengan Header User-Agent
                OkHttpClient client = new OkHttpClient.Builder()
                        .addInterceptor(logging)
                        .addInterceptor(chain -> {
                            Request original = chain.request();
                            Request request = original.newBuilder()
                                    .header("User-Agent", "NewsApp-Student-Project") // Header wajib untuk NewsAPI
                                    .method(original.method(), original.body())
                                    .build();
                            return chain.proceed(request);
                        })
                        .build();

                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(client) // Pasang client yang sudah diedit
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit.create(NewsApiService.class);
        }
    }
}