
# 📰 Nusantara News

**Nusantara News** adalah aplikasi pembaca berita modern untuk Android yang menyajikan berita terkini dari berbagai kategori secara *real-time*. Aplikasi ini menggunakan **NewsAPI.org** sebagai sumber data utama dan dirancang dengan antarmuka **Material Design 3** yang bersih dan responsif.

## ✨ Fitur Utama

* **Berita Utama (Featured News)**: Menampilkan satu berita besar sebagai sorotan utama di halaman beranda dengan visual yang menarik.
* **Kategori Dinamis**: Pengguna dapat memfilter berita berdasarkan kategori seperti Teknologi, Bisnis, Olahraga, Kesehatan, dan Hiburan menggunakan sistem *Chips*.
* **Update Real-time**: Mengambil data berita terbaru langsung dari server menggunakan library Retrofit.
* **Detail Berita Mendalam**: Halaman detail yang menggunakan `CollapsingToolbarLayout` untuk memberikan pengalaman membaca yang imersif, lengkap dengan informasi penulis, tanggal, dan konten berita.
* **Format Tanggal Lokal**: Tanggal berita secara otomatis dikonversi dari format standar ISO ke format Indonesia agar lebih mudah dipahami pengguna.

## 🛠️ Stack Teknologi & Library

* **Bahasa Pemrograman**: Java.
* **Networking**: [Retrofit 2.9.0](https://square.github.io/retrofit/) & OkHttp untuk komunikasi API.
* **Image Loading**: [Glide 4.15.1](https://github.com/bumptech/glide) untuk pemuatan gambar yang cepat dan efisien.
* **UI Components**: [Material Components for Android](https://material.io/develop/android) (CoordinatorLayout, CardView, ChipGroup, BottomNavigationView).
* **Data Parsing**: GSON Converter untuk mengubah respon JSON menjadi objek Java.

## 📂 Struktur Proyek Penting

* `MainActivity.java`: Mengelola pengambilan data API, pengaturan kategori, dan daftar berita utama.
* `DetailActivity.java`: Menampilkan konten lengkap dari berita yang dipilih oleh pengguna.
* `NewsApiService.java`: Mendefinisikan endpoint API dan konfigurasi Retrofit Client.
* `activity_main.xml` & `activity_detail.xml`: Tata letak antarmuka pengguna yang modern.

---

## 📸 Panduan Screenshot Aplikasi

Berikut adalah panduan pengambilan screenshot untuk dokumentasi visual yang profesional:

| **Halaman Utama (Home)** | **Filter Kategori** | **Detail Berita** |
| --- | --- | --- |
|  |  |  |
| Menampilkan *Featured News* di bagian atas dan daftar berita di bawahnya. | Menunjukkan interaksi saat menekan salah satu *Chip* kategori. | Menampilkan gambar besar dengan teks berita yang rapi. |

---

## 🚀 Cara Menjalankan

1. **Clone Repositori**: Unduh atau clone kode sumber ini.
2. **Buka di Android Studio**: Pastikan Anda menggunakan versi terbaru (rekomendasi: Flamingo atau lebih baru).
3. **API Key**: Aplikasi ini sudah menyertakan API Key dalam `MainActivity.java`. Jika limit tercapai, Anda bisa mendapatkan key baru di [NewsAPI.org](https://newsapi.org/).
4. **Sync Gradle**: Biarkan Android Studio mengunduh semua library yang diperlukan.
5. **Jalankan**: Klik tombol *Run* pada emulator atau perangkat fisik (Minimal Android API 33).

## 📝 Lisensi

Proyek ini dikembangkan oleh **Hafiz Fauzi Nugraha** sebagai bagian dari portofolio pengembangan aplikasi Android menggunakan Java dan Retrofit.
