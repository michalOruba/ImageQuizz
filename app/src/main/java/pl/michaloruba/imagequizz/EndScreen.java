package pl.michaloruba.imagequizz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class EndScreen extends Activity {
    Button playAgain;
    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_screen);
        score = findViewById(R.id.questionTextView);
        playAgain = findViewById(R.id.playAgain);

        int scoreFromIntent = getIntent().getIntExtra("score", 0);
        score.setText(String.format(Locale.getDefault(), "%d/10", scoreFromIntent));

        playAgain.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}