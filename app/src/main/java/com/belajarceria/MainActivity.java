package com.belajarceria;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private int dp(float value) {
        return (int) (value * getResources().getDisplayMetrics().density + 0.5f);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tampilan utama
        ScrollView scrollView = new ScrollView(this);
        scrollView.setFillViewport(true);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setPadding(dp(24), dp(30), dp(24), dp(30));
        root.setBackgroundColor(Color.rgb(255, 248, 240));

        scrollView.addView(root);

        // Judul aplikasi
        TextView title = new TextView(this);
        title.setText("🌈 Belajar Ceria");
        title.setTextSize(32);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setTextColor(Color.rgb(60, 60, 60));
        title.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams titleParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        titleParams.setMargins(0, 0, 0, dp(8));
        root.addView(title, titleParams);

        // Subjudul
        TextView subtitle = new TextView(this);
        subtitle.setText("Belajar sambil bermain! 🎉");
        subtitle.setTextSize(18);
        subtitle.setTextColor(Color.rgb(90, 90, 90));
        subtitle.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams subtitleParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        subtitleParams.setMargins(0, 0, 0, dp(30));
        root.addView(subtitle, subtitleParams);

        // Tombol Angka
        Button angkaButton = createMenuButton(
                "🔢  Mengenal Angka",
                Color.rgb(255, 205, 90)
        );

        angkaButton.setOnClickListener(v ->
                showMessage("🔢 Mengenal Angka",
                        "Belajar angka akan segera hadir!"));

        root.addView(angkaButton);

        // Tombol Huruf
        Button hurufButton = createMenuButton(
                "🔤  Mengenal Huruf",
                Color.rgb(130, 210, 170)
        );

        hurufButton.setOnClickListener(v ->
                showMessage("🔤 Mengenal Huruf",
                        "Belajar huruf akan segera hadir!"));

        root.addView(hurufButton);

        // Tombol Hewan
        Button hewanButton = createMenuButton(
                "🐶  Mengenal Hewan",
                Color.rgb(150, 195, 240)
        );

        hewanButton.setOnClickListener(v ->
                showMessage("🐶 Mengenal Hewan",
                        "Belajar hewan akan segera hadir!"));

        root.addView(hewanButton);

        // Tombol Bermain
        Button bermainButton = createMenuButton(
                "🎮  Bermain",
                Color.rgb(235, 170, 215)
        );

        bermainButton.setOnClickListener(v ->
                showMessage("🎮 Bermain",
                        "Permainan edukasi akan segera hadir!"));

        root.addView(bermainButton);

        // Informasi versi
        TextView version = new TextView(this);
        version.setText("⭐ Belajar Ceria • Versi 1.0");
        version.setTextSize(14);
        version.setTextColor(Color.GRAY);
        version.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams versionParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        versionParams.setMargins(0, dp(30), 0, 0);
        root.addView(version, versionParams);

        setContentView(scrollView);
    }

    private Button createMenuButton(String text, int backgroundColor) {

        Button button = new Button(this);

        button.setText(text);
        button.setTextSize(20);
        button.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        button.setTextColor(Color.rgb(50, 50, 50));
        button.setAllCaps(false);
        button.setGravity(Gravity.CENTER);

        button.setBackgroundColor(backgroundColor);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(70)
                );

        params.setMargins(0, 0, 0, dp(16));

        button.setLayoutParams(params);

        return button;
    }

    private void showMessage(String title, String message) {

        new android.app.AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}
