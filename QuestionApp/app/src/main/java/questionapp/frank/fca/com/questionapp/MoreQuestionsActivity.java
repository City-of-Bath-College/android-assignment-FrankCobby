package questionapp.frank.fca.com.questionapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;

public class MoreQuestionsActivity extends AppCompatActivity {

    //variables go here
    private Button btnFalse;
    private Button btnTrue;
    private TextView lblQuestion;
    private ImageView imgPicture;
    private TextView lblScore;

    private List<QuestionObject> questions;

    private QuestionObject currentQuestion;
    private int index;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //connect variables to interface items
        btnFalse = (Button) findViewById(R.id.btnFalse);
        btnTrue = (Button) findViewById(R.id.btnTrue);
        lblQuestion = (TextView) findViewById(R.id.lblQuestion);
        imgPicture = (ImageView) findViewById(R.id.imgPicture);
        lblScore = (TextView) findViewById(R.id.lblScore);


// set questionnaire text
        lblQuestion.setText("He is not Harry Potter");

// set image picture
        imgPicture.setImageResource(R.drawable.potter);

//on click listeners
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                determineButtonPress(true);
            }
        });

//onclick listeners
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                determineButtonPress(false);
            }
        });

        index = 0;
        score = 0;
        generateQuestions();

        setupQuestion();

        Paper.init(this);

    }

    private void generateQuestions() {

        questions = new ArrayList<>();


        questions.add(new QuestionObject("He is not Harry Potter", false, R.drawable.potter));
        questions.add(new QuestionObject("Inception won the Oscar for Best Picture", false, R.drawable.inception));
        questions.add(new QuestionObject("He is the main character from Matrix", true, R.drawable.matrix));
        questions.add(new QuestionObject("They are characters from Despicable Me", false, R.drawable.toystory));
        questions.add(new QuestionObject("Cristoph Waltz was born in Germany", false, R.drawable.waltz));
        questions.add(new QuestionObject("This character is from Batman: The Dark Knight", true, R.drawable.joker));
        questions.add(new QuestionObject("Leonardo DiCaprio has never won an Oscar", true, R.drawable.dicaprio));
        questions.add(new QuestionObject("His name is Matt Damon", false, R.drawable.mark));
        questions.add(new QuestionObject("He is from Narnia", false, R.drawable.ewok));
        questions.add(new QuestionObject("His name is Martin Scorsese", false, R.drawable.woody));

    }

    private void setupQuestion() {

        if (index == questions.size()) {
            // end game
            Log.d("Questionnaire App", "You answered all the questions");

            endGame();

        } else {

            currentQuestion = questions.get(index);

            lblQuestion.setText(currentQuestion.getQuestion());
            imgPicture.setImageResource(currentQuestion.getPicture());

            index++;

        }

    }

    private void determineButtonPress(boolean answer) {

        boolean expectedAnswer = currentQuestion.isAnswer();

        if (answer == expectedAnswer) {
            // You were right!
            score++;
            Toast.makeText(MoreQuestionsActivity.this, "Well done!", Toast.LENGTH_SHORT).show();
        } else {
            // You were wrong!

            Toast.makeText(MoreQuestionsActivity.this, "Wrong answer", Toast.LENGTH_SHORT).show();

        }


        lblScore.setText("Score = " + score);
        setupQuestion();

    }


    private void endGame() {

        final AlertDialog alertDialog = new AlertDialog.Builder(MoreQuestionsActivity.this)
                .setTitle("Congratulations")
                .setMessage("You scored " + score + " points!")
                .setNeutralButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //return back to the intro screen
                        finish();
                    }
                })
                .create();

        alertDialog.show();

    }

}
