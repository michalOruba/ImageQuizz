package pl.michaloruba.imagequizz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    ImageView image;
    TextView scoreTextView;
    TextView questionTextView;
    List<Button> buttons = new ArrayList<>();
    boolean preventFromActionsWhileDelayLock = false;
    Game game = new Game();
    Random random = new Random();
    Locale locale = Locale.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreTextView = findViewById(R.id.scoreTextView);
        questionTextView = findViewById(R.id.questionTextView);
        image = findViewById(R.id.image);

        buttons.add(findViewById(R.id.answer1));
        buttons.add(findViewById(R.id.answer2));
        buttons.add(findViewById(R.id.answer3));
        buttons.add(findViewById(R.id.answer4));
        for (Button b : buttons) {
            b.setOnClickListener(this);
        }
        setUpQuestion();
    }

    private void setUpQuestion() {
        if (game.questionsAsked() < 10) {
            playGame();
        }
        else {
            endGame();
        }
        preventFromActionsWhileDelayLock = false;
    }

    private void playGame() {
        setNumberOfQuestionsAskedInTextView();
        setScoreInTextView();
        game.rollNextQuestion();
        image.setImageResource(game.getCurrentQuestionImage());
        setDefaultColorsOnButtons();
        shuffleAnswersInButtons();
    }

    private void setNumberOfQuestionsAskedInTextView() {
        questionTextView.setText(String.format(locale, "Pytanie: %d/10", game.questionsAsked() + 1));
    }

    private void setScoreInTextView() {
        scoreTextView.setText(String.format(locale, "Wynik: %d", game.getScore()));
    }

    private void setDefaultColorsOnButtons() {
        for (Button b : buttons) {
            b.setBackgroundColor(ContextCompat.getColor(this, R.color.buttonColor));
        }
    }

    private void shuffleAnswersInButtons() {
        Set<Integer> buttonIndexes = new LinkedHashSet<>();
        fillSetWithUniqueIndexes(buttonIndexes);
        setAnswersInButtons(buttonIndexes);
    }

    private void fillSetWithUniqueIndexes(Set<Integer> buttonIndexes) {
        do {
            buttonIndexes.add(random.nextInt(4));
        }
        while (buttonIndexes.size() < 4);
    }

    private void setAnswersInButtons(Set<Integer> buttonIndexes) {
        Iterator<Integer> iterator = buttonIndexes.iterator();
        for (int i = 0; i < 4; i++) {
            buttons.get(iterator.next()).setText(game.getCurrentQuestionAnswer(i));
        }
    }

    private void endGame() {
        Intent i = new Intent(getApplicationContext(), EndScreen.class);
        i.putExtra("score", game.getScore());
        startActivity(i);
        finish();
    }

    @Override
    public void onClick(View v) {
        Button clickedButton;
        if (v instanceof Button && !preventFromActionsWhileDelayLock) {
            preventFromActionsWhileDelayLock = true;
            clickedButton = (Button) v;
            checkUsersGuess(clickedButton);
            holdScreenFor2Seconds();
        }
    }

    private void checkUsersGuess(Button clickedButton) {
        if (buttonTextIsAnAnswer(clickedButton)) {
            game.increaseScore();
            highlightCorrectAnswer(clickedButton);
        }
        else {
            highlightWrongAnswer(clickedButton);
            highlightCorrectAnswer(findButtonWithCorrectAnswer());
        }
    }

    private boolean buttonTextIsAnAnswer(Button clickedButton) {
        return clickedButton.getText() == game.getCorrectAnswer();
    }

    private void holdScreenFor2Seconds() {
        new Handler().postDelayed(this::setUpQuestion, 2000);
    }

    private void highlightWrongAnswer(Button clickedButton) {
        clickedButton.setBackgroundColor(ContextCompat.getColor(this, R.color.wrongAnswer));
    }

    private void highlightCorrectAnswer(Button clickedButton) {
        clickedButton.setBackgroundColor(ContextCompat.getColor(this, R.color.correctAnswer));
    }

    private Button findButtonWithCorrectAnswer() {
        Button correctAnswer = null;
        for (Button b : buttons){
            if (buttonTextIsAnAnswer(b)) correctAnswer = b;
        }
        return correctAnswer;
    }
}