package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView  player1, player2;
    private Button[][] buttons = new Button[3][3];
    private boolean player1Turn=true;
    private int roundcount=0;
    private  int player1points=0;
    private  int player2points=0;
    private Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1 = (TextView)findViewById(R.id.player1);
        player2 = (TextView)findViewById(R.id.player2);

        reset = (Button)findViewById(R.id.btnReset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });

        buttons[0][0] = (Button)findViewById(R.id.button_00);
        buttons[0][1] = (Button)findViewById(R.id.button_01);
        buttons[0][2] = (Button)findViewById(R.id.button_02);
        buttons[1][0] = (Button)findViewById(R.id.button_10);
        buttons[1][1] = (Button)findViewById(R.id.button_11);
        buttons[1][2] = (Button)findViewById(R.id.button_12);
        buttons[2][0] = (Button)findViewById(R.id.button_20);
        buttons[2][1] = (Button)findViewById(R.id.button_21);
        buttons[2][2] = (Button)findViewById(R.id.button_22);

        for(int i=0;i<3;i++)
           for(int j=0;j<3;j++)
               buttons[i][j].setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Button b = (Button)view;

        if(!b.getText().toString().equals(""))
        {
            return;
        }

        if(player1Turn)
        {
            b.setText("X");
        }
        else
        {
            b.setText("O");
        }

        roundcount++;

        if(checkforWin())
        {
            if(player1Turn)
            {
                player1Wins();
            }
            else
            {
                player2Wins();
            }
        }
        else
            if(roundcount==9)
            {
                Draw();
            }
            else
            {
                player1Turn = !player1Turn;
            }
    }

    private  void player1Wins(){
        player1points++;
        Toast.makeText(this, "Player 1 Wins!",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private  void player2Wins(){
        player2points++;
        Toast.makeText(this, "Player 2 Wins!",Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private  void Draw(){
        player1points++;
        Toast.makeText(this, "Draw!",Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText()
    {
        player1.setText("Player 1 :"+player1points);
        player2.setText("Player 2 :"+player2points);
    }

     private void resetBoard()
    {
       for(int i=0;i<3;i++)
           for(int j=0;j<3;j++)
               buttons[i][j].setText("");

           roundcount=0;
           player1Turn=true;
    }

    private void resetGame()
    {
        player1points=0;
        player2points=0;
        updatePointsText();
        resetBoard();
    }

    private boolean checkforWin()
    {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                field[i][j] = buttons[i][j].getText().toString();

        for (int i = 0; i < 3; i++)
        {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals(""))
            {
                return true;
            }
        }

        for (int i = 0; i < 3; i++)
        {
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals(""))
            {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals(""))
        {
            return  true;
        }
        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals(""))
        {
            return  true;
        }
        return false;
    }
}

