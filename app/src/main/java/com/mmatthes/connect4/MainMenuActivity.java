package com.mmatthes.connect4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    /**
     * Start a default settings game.
     *
     * @param view
     */
    public void startNewGame(View view)
    {
        Intent intent = new Intent(this, Connect4MainActivity.class);
        startActivity(intent);
    }
}
