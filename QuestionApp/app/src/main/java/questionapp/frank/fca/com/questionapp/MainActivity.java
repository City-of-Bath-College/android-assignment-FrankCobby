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

public class MainActivity extends AppCompatActivity {

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
        lblQuestion.setText("This spacecraft belongs to Interstellar");

// set image picture
        imgPicture.setImageResource(R.drawable.interstellar);

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


        questions.add(new QuestionObject("This spacecraft belongs to Interstellar", true, R.drawable.interstellar));
        questions.add(new QuestionObject("Tyler Durden is a character from American History X", false, R.drawable.fightclub));
        questions.add(new QuestionObject("He is the main character from Taxi Driver", true, R.drawable.taxidriver));
        questions.add(new QuestionObject("Is he Vito Corleone?", true, R.drawable.thegodfather));
        questions.add(new QuestionObject("His name is Luke Skywalker", false, R.drawable.vader));
        questions.add(new QuestionObject("This character is from The Shining", false, R.drawable.nicholson));
        questions.add(new QuestionObject("Tom Hanks has never won an Oscar", false, R.drawable.hanks));
        questions.add(new QuestionObject("His name is Quentin Tarantino", true, R.drawable.tarantino));
        questions.add(new QuestionObject("Al Pacino was born in Italy", false, R.drawable.alpacino));
        questions.add(new QuestionObject("He is the Man with No Name", true, R.drawable.eastwood));

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
            Toast.makeText(MainActivity.this, "Well done!", Toast.LENGTH_SHORT).show();
        } else {
            // You were wrong!

            Toast.makeText(MainActivity.this, "Wrong answer", Toast.LENGTH_SHORT).show();

        }


        lblScore.setText("Score = " + score);
        setupQuestion();

    }


    private void endGame() {

        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
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





