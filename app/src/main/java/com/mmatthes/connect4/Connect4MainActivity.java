package com.mmatthes.connect4;

import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.Random;

public class Connect4MainActivity extends AppCompatActivity {

    private final int RED = 1;
    private final int BLACK = 2;
    private Board board;
    private Button[][] cells;
    private int row_landed;
    private ImageView turnIndicator;
    private boolean vscomputer;
    private Random random;
    private int randInt;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect4_main);

        cells = new Button[7][6];
        initializeButtons();
        turnIndicator = (ImageView) findViewById(R.id.turnImageView);
        row_landed = -1;
        random = new Random();
        vscomputer = true;

        if (savedInstanceState != null)
        {
            int[][] restore_board_data = (int[][])savedInstanceState.getSerializable("SAVED_BOARD");
            int restore_total_moves = savedInstanceState.getInt("TOTAL_MOVES");
            int restore_player_turn = savedInstanceState.getInt("PLAYER_TURN");

            board = new Board(restore_board_data,restore_player_turn,restore_total_moves);

            if (board.whoseTurn() == RED)
                turnIndicator.setImageResource(R.drawable.c4_redturn);
            else if (board.whoseTurn() == BLACK)
                turnIndicator.setImageResource(R.drawable.c4_blackturn);

            for (int r = 0; r <= 5; r++)
            {
                for (int c = 0; c <= 6; c++)
                {
                    if (restore_board_data[c][r] == RED)
                        cells[c][r].setBackgroundResource(R.drawable.c4_redcell);
                    else if (restore_board_data[c][r] == BLACK)
                        cells[c][r].setBackgroundResource(R.drawable.c4_blackcell);
                }
            }

            if (board.checkForWin())
                endGame(board.whoseTurn());
            else if (board.drawGame())
                endGame(0);
        }
        else
            board = new Board();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState)
    {
        int[][] save_board_data = board.getCurrent_board();
        int save_total_moves = board.getTotal_moves();
        int save_player_turn = board.whoseTurn();

        savedInstanceState.putSerializable("SAVED_BOARD",save_board_data);
        savedInstanceState.putInt("TOTAL_MOVES",save_total_moves);
        savedInstanceState.putInt("PLAYER_TURN",save_player_turn);
        super.onSaveInstanceState(savedInstanceState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null)
        {
            int[][] restore_board_data = (int[][])savedInstanceState.getSerializable("SAVED_BOARD");
            int restore_total_moves = savedInstanceState.getInt("TOTAL_MOVES");
            int restore_player_turn = savedInstanceState.getInt("PLAYER_TURN");

            board = new Board(restore_board_data,restore_player_turn,restore_total_moves);

            if (board.whoseTurn() == RED)
                turnIndicator.setImageResource(R.drawable.c4_redturn);
            else if (board.whoseTurn() == BLACK)
                turnIndicator.setImageResource(R.drawable.c4_blackturn);

            for (int r = 0; r <= 5; r++)
            {
                for (int c = 0; c <= 6; c++)
                {
                    if (restore_board_data[c][r] == RED)
                        cells[c][r].setBackgroundResource(R.drawable.c4_redcell);
                    else if (restore_board_data[c][r] == BLACK)
                        cells[c][r].setBackgroundResource(R.drawable.c4_blackcell);
                }
            }

            if (board.checkForWin())
                endGame(board.whoseTurn());
            else if (board.drawGame())
                endGame(0);
        }
    }

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
        fillCell(0);
        if (endTurn())
            return;

        if (vscomputer)
            computerTurn(0);
    }
    public void dropCOL1(View view)
    {
        row_landed = board.dropPiece(1);
        if (row_landed == -1)
            return;
        fillCell(1);
        if (endTurn())
            return;

        if (vscomputer)
            computerTurn(1);
    }
    public void dropCOL2(View view)
    {
        row_landed = board.dropPiece(2);
        if (row_landed == -1)
            return;
        fillCell(2);
        if (endTurn())
            return;

        if (vscomputer)
            computerTurn(2);
    }
    public void dropCOL3(View view)
    {
        row_landed = board.dropPiece(3);
        if (row_landed == -1)
            return;
        fillCell(3);
        if (endTurn())
            return;

        if (vscomputer)
            computerTurn(3);
    }
    public void dropCOL4(View view)
    {
        row_landed = board.dropPiece(4);
        if (row_landed == -1)
            return;
        fillCell(4);
        if (endTurn())
            return;

        if (vscomputer)
            computerTurn(4);
    }
    public void dropCOL5(View view)
    {
        row_landed = board.dropPiece(5);
        if (row_landed == -1)
            return;
        fillCell(5);
        if (endTurn())
            return;

        if (vscomputer)
            computerTurn(5);
    }
    public void dropCOL6(View view)
    {
        row_landed = board.dropPiece(6);
        if (row_landed == -1)
            return;
        fillCell(6);
        if (endTurn())
            return;

        if (vscomputer)
            computerTurn(6);
    }

    /**
     * Playing against computer turn.
     */
    public void computerTurn(final int last_col)
    {
        disableButtons();

        handler.postDelayed(new Runnable()
        {
            @Override
            public void run() {

                do
                {
                    if (!board.isColumnFull(last_col))
                        randInt = random.nextInt((2) + 1) + last_col - 1;
                    else
                        randInt = random.nextInt((6)+1);
//                    System.out.println(randInt);
                    row_landed = board.dropPiece(randInt);
                } while(row_landed == -1);
                fillCell(randInt);
                endTurn();

                enableButtons();
            }
        }, 1000);
    }

    /**
     * Clear the game data and recreate.
     *
     * @param view
     */
    public void restartGame(View view)
    {
        board.clearBoardData();
        recreate();
    }
    /**
     * Change the background resource of the corresponding piece
     * that was just dropped.
     *
     * @param col   column dropped
     */
    public void fillCell(int col)
    {
        if (board.whoseTurn() == RED)
            cells[col][row_landed].setBackgroundResource(R.drawable.c4_redcell);
        else if(board.whoseTurn() == BLACK)
            cells[col][row_landed].setBackgroundResource(R.drawable.c4_blackcell);
    }

    /**
     * Check for a winner or draw game.
     * Else change the player's turn.
     */
    public boolean endTurn()
    {
        if (board.checkForWin())
        {
            endGame(board.whoseTurn());
            return true;
        }
        else if (board.drawGame())
        {
            endGame(0);
            return true;
        }
        else
        {
            if (board.whoseTurn() == RED)
                turnIndicator.setImageResource(R.drawable.c4_blackturn);
            else if (board.whoseTurn() == BLACK)
                turnIndicator.setImageResource(R.drawable.c4_redturn);
            board.changePlayerTurn();
            return false;
        }
    }

    /**
     * End the game and display a message who won or if
     * it ended in a draw.
     *
     * @param whowon    (0 == draw; neither player)
     *                  RED or BLACK int value
     */
    public void endGame(int whowon)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(Connect4MainActivity.this).create();
        alertDialog.setTitle("Game Over!");
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        switch (whowon)
        {
            case 0: alertDialog.setMessage("Draw!"); break;
            case RED: alertDialog.setMessage("RED wins!"); break;
            case BLACK: alertDialog.setMessage("BLACK wins!"); break;
        }
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Restart",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Restarting game...", Toast.LENGTH_SHORT).show();
                        board.clearBoardData();
                        dialog.dismiss();
                        recreate();
                    }
                });
        alertDialog.show();
    }

    /**
     * Disable and don't allow buttons to be clicked
     */
    public void disableButtons()
    {
        for (int r = 0; r <= 5; r++)
            for (int c = 0; c <= 6; c++)
            {
                cells[c][r].setEnabled(false);
                cells[c][r].setClickable(false);
            }
    }

    /**
     * Enable and allow buttons to be clicked
     */
    public void enableButtons()
    {
        for (int r = 0; r <= 5; r++)
            for (int c = 0; c <= 6; c++)
            {
                cells[c][r].setEnabled(true);
                cells[c][r].setClickable(true);
            }
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
