package com.belajarceria;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.speech.tts.TextToSpeech;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class NumbersActivity extends Activity {

    private int currentNumber = 1;

    private TextView numberText;
    private TextView wordText;
    private TextView objectsText;
    private TextView progressText;

    private TextToSpeech textToSpeech;
    private boolean ttsReady = false;

    private final String[] numberWords = {
            "",
            "satu",
            "dua",
            "tiga",
            "empat",
            "lima",
            "enam",
            "tujuh",
            "delapan",
            "sembilan",
            "sepuluh"
    };

    private int dp(float value) {
        return (int) (value *
                getResources().getDisplayMetrics().density + 0.5f);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createInterface();

        initializeTextToSpeech();

        updateNumber(false);
    }

    private void initializeTextToSpeech() {

        textToSpeech = new TextToSpeech(
                this,
                status -> {

                    if (status == TextToSpeech.SUCCESS) {

                        int result = textToSpeech.setLanguage(
                                new Locale("id", "ID")
                        );

                        if (result == TextToSpeech.LANG_MISSING_DATA ||
                                result == TextToSpeech.LANG_NOT_SUPPORTED) {

                            ttsReady = false;

                            Toast.makeText(
                                    this,
                                    "Bahasa Indonesia belum tersedia di mesin suara HP.",
                                    Toast.LENGTH_LONG
                            ).show();

                        } else {

                            ttsReady = true;

                            textToSpeech.setSpeechRate(0.85f);
                            textToSpeech.setPitch(1.05f);
                        }

                    } else {

                        ttsReady = false;

                        Toast.makeText(
                                this,
                                "Fitur suara tidak tersedia.",
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        );
    }

    private void speakCurrentNumber() {

        if (!ttsReady || textToSpeech == null) {

            Toast.makeText(
                    this,
                    "Suara belum siap. Silakan coba lagi.",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        String text =
                numberWords[currentNumber];

        textToSpeech.speak(
                text,
                TextToSpeech.QUEUE_FLUSH,
                null,
                "NUMBER_" + currentNumber
        );
    }

    private void createInterface() {

        ScrollView scrollView = new ScrollView(this);

        scrollView.setFillViewport(true);

        LinearLayout root =
                new LinearLayout(this);

        root.setOrientation(
                LinearLayout.VERTICAL
        );

        root.setGravity(
                Gravity.CENTER_HORIZONTAL
        );

        root.setPadding(
                dp(20),
                dp(25),
                dp(20),
                dp(25)
        );

        root.setBackgroundColor(
                Color.rgb(255, 248, 240)
        );

        scrollView.addView(root);

        // ==========================================
        // TOMBOL KEMBALI
        // ==========================================

        Button backButton =
                new Button(this);

        backButton.setText(
                "← Kembali"
        );

        backButton.setTextSize(16);

        backButton.setAllCaps(false);

        backButton.setOnClickListener(
                v -> finish()
        );

        LinearLayout.LayoutParams backParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(55)
                );

        backParams.setMargins(
                0,
                0,
                0,
                dp(15)
        );

        root.addView(
                backButton,
                backParams
        );

        // ==========================================
        // JUDUL
        // ==========================================

        TextView title =
                new TextView(this);

        title.setText(
                "🔢 Mengenal Angka"
        );

        title.setTextSize(28);

        title.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        title.setTextColor(
                Color.rgb(60, 60, 60)
        );

        title.setGravity(
                Gravity.CENTER
        );

        root.addView(title);

        // ==========================================
        // PROGRESS
        // ==========================================

        progressText =
                new TextView(this);

        progressText.setTextSize(16);

        progressText.setTextColor(
                Color.GRAY
        );

        progressText.setGravity(
                Gravity.CENTER
        );

        LinearLayout.LayoutParams progressParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        progressParams.setMargins(
                0,
                dp(8),
                0,
                dp(15)
        );

        root.addView(
                progressText,
                progressParams
        );

        // ==========================================
        // ANGKA BESAR
        // ==========================================

        numberText =
                new TextView(this);

        numberText.setTextSize(100);

        numberText.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        numberText.setTextColor(
                Color.rgb(255, 140, 40)
        );

        numberText.setGravity(
                Gravity.CENTER
        );

        LinearLayout.LayoutParams numberParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(150)
                );

        root.addView(
                numberText,
                numberParams
        );

        // ==========================================
        // OBJEK APEL
        // ==========================================

        objectsText =
                new TextView(this);

        objectsText.setTextSize(35);

        objectsText.setGravity(
                Gravity.CENTER
        );

        objectsText.setTextColor(
                Color.rgb(80, 80, 80)
        );

        LinearLayout.LayoutParams objectsParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(100)
                );

        root.addView(
                objectsText,
                objectsParams
        );

        // ==========================================
        // NAMA ANGKA
        // ==========================================

        wordText =
                new TextView(this);

        wordText.setTextSize(30);

        wordText.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        wordText.setTextColor(
                Color.rgb(70, 70, 70)
        );

        wordText.setGravity(
                Gravity.CENTER
        );

        LinearLayout.LayoutParams wordParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(60)
                );

        wordParams.setMargins(
                0,
                dp(5),
                0,
                dp(15)
        );

        root.addView(
                wordText,
                wordParams
        );

        // ==========================================
        // TOMBOL SUARA
        // ==========================================

        Button soundButton =
                new Button(this);

        soundButton.setText(
                "🔊 Dengarkan"
        );

        soundButton.setTextSize(19);

        soundButton.setAllCaps(false);

        soundButton.setOnClickListener(
                v -> speakCurrentNumber()
        );

        LinearLayout.LayoutParams soundParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        dp(60)
                );

        soundParams.setMargins(
                0,
                0,
                0,
                dp(20)
        );

        root.addView(
                soundButton,
                soundParams
        );

        // ==========================================
        // NAVIGASI
        // ==========================================

        LinearLayout navigation =
                new LinearLayout(this);

        navigation.setOrientation(
                LinearLayout.HORIZONTAL
        );

        navigation.setGravity(
                Gravity.CENTER
        );

        Button previousButton =
                new Button(this);

        previousButton.setText(
                "◀ Sebelumnya"
        );

        previousButton.setTextSize(16);

        previousButton.setAllCaps(false);

        previousButton.setOnClickListener(
                v -> {

                    if (currentNumber > 1) {

                        currentNumber--;

                        updateNumber(true);

                    } else {

                        Toast.makeText(
                                this,
                                "Ini angka pertama 😊",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
        );

        Button nextButton =
                new Button(this);

        nextButton.setText(
                "Berikutnya ▶"
        );

        nextButton.setTextSize(16);

        nextButton.setAllCaps(false);

        nextButton.setOnClickListener(
                v -> {

                    if (currentNumber < 10) {

                        currentNumber++;

                        updateNumber(true);

                    } else {

                        Toast.makeText(
                                this,
                                "🎉 Kamu sudah sampai angka 10!",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
        );

        LinearLayout.LayoutParams navParams =
                new LinearLayout.LayoutParams(
                        0,
                        dp(60),
                        1
                );

        navParams.setMargins(
                dp(5),
                0,
                dp(5),
                0
        );

        navigation.addView(
                previousButton,
                navParams
        );

        navigation.addView(
                nextButton,
                navParams
        );

        root.addView(
                navigation
        );

        setContentView(
                scrollView
        );
    }

    private void updateNumber(
            boolean speak
    ) {

        numberText.setText(
                String.valueOf(currentNumber)
        );

        wordText.setText(
                numberWords[currentNumber].toUpperCase()
        );

        progressText.setText(
                "Angka " +
                        currentNumber +
                        " dari 10"
        );

        objectsText.setText(
                createObjects(currentNumber)
        );

        if (speak) {

            // Beri sedikit waktu agar UI berubah
            numberText.postDelayed(
                    this::speakCurrentNumber,
                    200
            );
        }
    }

    private String createObjects(
            int number
    ) {

        StringBuilder result =
                new StringBuilder();

        for (int i = 0; i < number; i++) {

            result.append("🍎 ");

            if ((i + 1) % 5 == 0 &&
                    i + 1 < number) {

                result.append("\n");
            }
        }

        return result.toString();
    }

    @Override
    protected void onDestroy() {

        if (textToSpeech != null) {

            textToSpeech.stop();

            textToSpeech.shutdown();

            textToSpeech = null;
        }

        super.onDestroy();
    }
}
