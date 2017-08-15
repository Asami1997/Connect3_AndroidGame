package com.example.asami.connect3;

import android.media.Image;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
            int activePlayer = 1; //if you put it inside function everytime active player will be 1
           //3 means unplayed
            int [] gameState = {3,3,3,3,3,3,3,3,3};
            int[][] wiiningPos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7}, {2,5,8},{0,4,8},{2,4,6}};
           boolean gameisActive = true;

         public void dropDown(View view)
            {
                // 1  = purple , 2 = green
                //purple starts the game
                //view here is the thing that was clicked on so we dont really need to find view by id
                ImageView counter = (ImageView) view;
                TextView win = (TextView) findViewById(R.id.winTextView);
                LinearLayout lin = (LinearLayout) findViewById(R.id.linId);
                 //to drop it i need to first move it to top of screen then drop either yellow or red
                 int tag = Integer.parseInt(counter.getTag().toString());
                if(gameState[tag] == 3 && gameisActive) {
                    counter.setTranslationY(-1000f);


                    if (activePlayer == 1) {
                        counter.setImageResource(R.drawable.purplec);

                        gameState[tag] = 1;
                        activePlayer = 2;
                        counter.animate().translationYBy(1000f).rotation(180).setDuration(500);

                    } else if (activePlayer == 2) {
                        counter.setImageResource(R.drawable.greenc);


                        gameState[tag] = 2;
                        activePlayer = 1;
                        counter.animate().translationYBy(1000f).rotation(180).setDuration(500);


                    }
                  //check if anyone has won the game
                    for (int [] hasWon : wiiningPos)
                    {
                       if(gameState[hasWon[0]] ==  gameState[hasWon[1]] && gameState[hasWon[0]]==gameState[hasWon[2]]
                               && gameState[hasWon[1]] != 3)
                          {
                              gameisActive = false;
                               if (gameState[hasWon[0]] ==  1 && gameState[hasWon[1]]== 1 && gameState[hasWon[2]]== 1)
                                  {
                                     lin.setVisibility(view.VISIBLE);

                                      win.setText("Purple Wins!");
                                  }else
                                      {
                                          lin.setVisibility(view.VISIBLE);
                                         win.setText("Green Wins!");
                                      }
                          }else
                               {
                                  boolean gameOver = true;
                                   for(int  draw : gameState )
                                      {
                                         if(draw == 3)
                                            {
                                              gameOver = false;
                                            }
                                      }
                                      if(gameOver == true)

                                         {
                                             win.setText("ITS A DRAW !");
                                             lin.setVisibility(view.VISIBLE);
                                         }
                               }
                    }


                }

            }

            public void playAgain(View view)
               {
                   gameisActive = true;
                   LinearLayout lin = (LinearLayout) findViewById(R.id.linId);
                   lin.setVisibility(view.GONE);
                   int activePlayer = 1; //if you put it inside function everytime active player will be 1
                   //3 means unplayed


                   for (int i =0 ; i<=8 ; i++)
                      {
                         gameState[i] = 3;
                      }

                   GridLayout gri = (GridLayout) findViewById(R.id.gridLayout);

                   // loop through the grid childs now
                   for (int x = 0 ; x<=8 ; x++)
                      {
                          // Need to typcast  the child object to imageview so i can use the setimageresourse function
                          ((ImageView)(gri.getChildAt(x))).setImageResource(0);
                      }

               }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
