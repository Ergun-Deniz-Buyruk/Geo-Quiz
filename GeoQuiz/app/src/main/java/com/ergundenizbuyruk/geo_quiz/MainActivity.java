package com.ergundenizbuyruk.geo_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.ergundenizbuyruk.geo_quiz.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;//View Binding
    private Question[] questionBank;// Sorulari tutan liste
    private int currentIndex = 0;// listedeki hangi soruda oldugumuzu anladigimiz imlec
    private int dogruSayisi = 0, yanlisSayisi = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Tum soruları bir listede tut.
        questionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)};

        // ilk soruyu soru textinde göster.
        updateQuestion();
        binding.previousButton.setVisibility(View.INVISIBLE);

        // True butonuna basildiginda calissin.
        binding.trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);// Cevabi kontrol et.
                // Her soruya cevap verdiginde butonlari gorunmez yap ki tekrar cevap vermesin.
                binding.trueButton.setVisibility(View.INVISIBLE);
                binding.falseButton.setVisibility(View.INVISIBLE);
            }
        });

        // False butonuna basildiginda calissin.
        binding.falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false); // Cevabi kontrol et.
                // Her soruya cevap verdiginde butonlari gorunmez yap ki tekrar cevap vermesin.
                binding.trueButton.setVisibility(View.INVISIBLE);
                binding.falseButton.setVisibility(View.INVISIBLE);
            }
        });

        // Next butonuna basildiginda calissin.
        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex++;
                if(currentIndex == questionBank.length) {// soru bitmis demektir.
                    binding.nextButton.setVisibility(View.INVISIBLE);
                    String message = "Tebrikler!\nToplam Puanınız 100 üzerinden: ";
                    int toplamPuan = (int)(dogruSayisi/(float)(dogruSayisi + yanlisSayisi) * 100);
                    message += toplamPuan;

                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                } else {
                    binding.trueButton.setVisibility(View.VISIBLE);
                    binding.falseButton.setVisibility(View.VISIBLE);

                    // Diger soruyu soru textinde göster.
                    updateQuestion();
                }
                if(currentIndex != 0) {
                    binding.previousButton.setVisibility(View.VISIBLE);
                }
            }
        });

        // Previous butonuna basildiginda calissin.
        binding.previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex != 0) {
                    // OutIndexOfException yememek icin hep moduna bak.
                    // Mod alindigi icin +6 bir sey degistirmez sadece negatif olmamasini korudum.
                    currentIndex = (--currentIndex + 6) % questionBank.length;

                    // Diger soruyu soru textinde göster.
                    updateQuestion();
                } else {
                    binding.previousButton.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    // indexin belittigi soruyu textViewde goster.
    private void updateQuestion() {
        int questionResourcesId = questionBank[currentIndex].getTextResourcesId();
        binding.quizText.setText(questionResourcesId);
    }

    // Kullanicinin cevabini kontrol eder ve dogru yapip yapmadigini Toast mesajıyla ekrana yazdirir.
    private void checkAnswer(boolean userAnswer) {
        if(questionBank[currentIndex].getAnswer() == userAnswer) {
            Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT).show();
            dogruSayisi++;
        } else {
            Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
            yanlisSayisi++;
        }
    }
}