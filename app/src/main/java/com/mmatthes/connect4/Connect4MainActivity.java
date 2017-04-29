package com.mmatthes.connect4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Connect4MainActivity extends AppCompatActivity {

    private final int RED = 1;
    private final int BLACK = 2;
    Board board;
    Button[][] cells;
    private int row_landed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect4_main);

        board = new Board();
        cells = new Button[7][6];
        initializeButtons();
        row_landed = -1;
    }

//    public void changeBackground(View view)
//    {
//        Button button = (Button) findViewById(R.id.btn);
//        button.setBackgroundResource(R.drawable.c4_redcell);
//    }

    /**
     * Figure out which row in column # is available, and
     * change the background of the cell button. (If no cell is empty, then return)
     * Call a check for a win scenario.
     * Change the turn if no win was found.
     *
     * @param view
     */
    public void dropCOL0(View view)
    {
        row_landed = board.dropPiece(0);
        if (row_landed == -1)
            return;

        if (board.whoseTurn() == RED)
            cells[0][row_landed].setBackgroundResource(R.drawable.c4_redcell);
        else if(board.whoseTurn() == BLACK)
            cells[0][row_landed].setBackgroundResource(R.drawable.c4_blackcell);

        if (board.checkForWin())
            recreate();
        board.changePlayerTurn();
    }
    public void dropCOL1(View view)
    {
        row_landed = board.dropPiece(1);
        if (row_landed == -1)
            return;

        if (board.whoseTurn() == RED)
            cells[1][row_landed].setBackgroundResource(R.drawable.c4_redcell);
        else if(board.whoseTurn() == BLACK)
            cells[1][row_landed].setBackgroundResource(R.drawable.c4_blackcell);

        if (board.checkForWin())
            recreate();
        board.changePlayerTurn();
    }
    public void dropCOL2(View view)
    {
        row_landed = board.dropPiece(2);
        if (row_landed == -1)
            return;

        if (board.whoseTurn() == RED)
            cells[2][row_landed].setBackgroundResource(R.drawable.c4_redcell);
        else if(board.whoseTurn() == BLACK)
            cells[2][row_landed].setBackgroundResource(R.drawable.c4_blackcell);

        if (board.checkForWin())
            recreate();
        board.changePlayerTurn();
    }
    public void dropCOL3(View view)
    {
        row_landed = board.dropPiece(3);
        if (row_landed == -1)
            return;

        if (board.whoseTurn() == RED)
            cells[3][row_landed].setBackgroundResource(R.drawable.c4_redcell);
        else if(board.whoseTurn() == BLACK)
            cells[3][row_landed].setBackgroundResource(R.drawable.c4_blackcell);

        if (board.checkForWin())
            recreate();
        board.changePlayerTurn();
    }
    public void dropCOL4(View view)
    {
        row_landed = board.dropPiece(4);
        if (row_landed == -1)
            return;

        if (board.whoseTurn() == RED)
            cells[4][row_landed].setBackgroundResource(R.drawable.c4_redcell);
        else if(board.whoseTurn() == BLACK)
            cells[4][row_landed].setBackgroundResource(R.drawable.c4_blackcell);

        if (board.checkForWin())
            recreate();
        board.changePlayerTurn();
    }
    public void dropCOL5(View view)
    {
        row_landed = board.dropPiece(5);
        if (row_landed == -1)
            return;

        if (board.whoseTurn() == RED)
            cells[5][row_landed].setBackgroundResource(R.drawable.c4_redcell);
        else if(board.whoseTurn() == BLACK)
            cells[5][row_landed].setBackgroundResource(R.drawable.c4_blackcell);

        if (board.checkForWin())
            recreate();
        board.changePlayerTurn();
    }
    public void dropCOL6(View view)
    {
        row_landed = board.dropPiece(6);
        if (row_landed == -1)
            return;

        if (board.whoseTurn() == RED)
            cells[6][row_landed].setBackgroundResource(R.drawable.c4_redcell);
        else if(board.whoseTurn() == BLACK)
            cells[6][row_landed].setBackgroundResource(R.drawable.c4_blackcell);

        if (board.checkForWin())
            recreate();
        board.changePlayerTurn();
    }

    /**
     * Incredibly ugly way to initialize button array
     */
    public void initializeButtons()
    {
        cells[0][0] = (Button) findViewById(R.id.cell_00);
        cells[1][0] = (Button) findViewById(R.id.cell_10);
        cells[2][0] = (Button) findViewById(R.id.cell_20);
        cells[3][0] = (Button) findViewById(R.id.cell_30);
        cells[4][0] = (Button) findViewById(R.id.cell_40);
        cells[5][0] = (Button) findViewById(R.id.cell_50);
        cells[6][0] = (Button) findViewById(R.id.cell_60);

        cells[0][1] = (Button) findViewById(R.id.cell_01);
        cells[1][1] = (Button) findViewById(R.id.cell_11);
        cells[2][1] = (Button) findViewById(R.id.cell_21);
        cells[3][1] = (Button) findViewById(R.id.cell_31);
        cells[4][1] = (Button) findViewById(R.id.cell_41);
        cells[5][1] = (Button) findViewById(R.id.cell_51);
        cells[6][1] = (Button) findViewById(R.id.cell_61);

        cells[0][2] = (Button) findViewById(R.id.cell_02);
        cells[1][2] = (Button) findViewById(R.id.cell_12);
        cells[2][2] = (Button) findViewById(R.id.cell_22);
        cells[3][2] = (Button) findViewById(R.id.cell_32);
        cells[4][2] = (Button) findViewById(R.id.cell_42);
        cells[5][2] = (Button) findViewById(R.id.cell_52);
        cells[6][2] = (Button) findViewById(R.id.cell_62);

        cells[0][3] = (Button) findViewById(R.id.cell_03);
        cells[1][3] = (Button) findViewById(R.id.cell_13);
        cells[2][3] = (Button) findViewById(R.id.cell_23);
        cells[3][3] = (Button) findViewById(R.id.cell_33);
        cells[4][3] = (Button) findViewById(R.id.cell_43);
        cells[5][3] = (Button) findViewById(R.id.cell_53);
        cells[6][3] = (Button) findViewById(R.id.cell_63);

        cells[0][4] = (Button) findViewById(R.id.cell_04);
        cells[1][4] = (Button) findViewById(R.id.cell_14);
        cells[2][4] = (Button) findViewById(R.id.cell_24);
        cells[3][4] = (Button) findViewById(R.id.cell_34);
        cells[4][4] = (Button) findViewById(R.id.cell_44);
        cells[5][4] = (Button) findViewById(R.id.cell_54);
        cells[6][4] = (Button) findViewById(R.id.cell_64);

        cells[0][5] = (Button) findViewById(R.id.cell_05);
        cells[1][5] = (Button) findViewById(R.id.cell_15);
        cells[2][5] = (Button) findViewById(R.id.cell_25);
        cells[3][5] = (Button) findViewById(R.id.cell_35);
        cells[4][5] = (Button) findViewById(R.id.cell_45);
        cells[5][5] = (Button) findViewById(R.id.cell_55);
        cells[6][5] = (Button) findViewById(R.id.cell_65);
    }
}
