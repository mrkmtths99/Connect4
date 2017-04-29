package com.mmatthes.connect4;

public class Board
{
    private int[][] current_board;
    private final int RED = 1;
    private final int BLACK = 2;
    private int player_turn;
    private int total_moves;

    /**
     * New board initializer.
     */
    public Board()
    {
        current_board = new int[7][6];
        player_turn = RED;
        total_moves = 0;
    }

    /**
     * Change to the next player's turn
     */
    public void changePlayerTurn()
    {
        if (player_turn == RED)
            player_turn = BLACK;
        else
            player_turn = RED;
    }

    /**
     * @return RED or BLACK value
     */
    public int whoseTurn()
    {
        return player_turn;
    }

    /**
     * Search the column the piece was selected to drop in for an empty
     * cell to place the piece.
     *
     * @param column_dropped        column indicated to search
     * @return                      row number on which the piece found room for
     *                              return -1 if there was no open cell
     */
    public int dropPiece(int column_dropped)
    {
        int row_landed = -1;
        for (int row = 0; row <= 5; row++)
        {
            if (current_board[column_dropped][row] == 0)
            {
                current_board[column_dropped][row] = player_turn;
                total_moves++;
                row_landed = row;
                break;
            }
        }
        return row_landed;
    }

    /**
     * Check the entire board for any 4 pieces in a row
     * for the current player's turn.
     *
     * @return      true for win; false for no win
     */
    public boolean checkForWin()
    {
        //Horizontal win
        for (int r = 0; r <= 5; r++)
        {
            for (int c = 0; c <= 3; c++)
            {
                if (current_board[c][r] == player_turn &&
                        current_board[c+1][r] == player_turn &&
                        current_board[c+2][r] == player_turn &&
                        current_board[c+3][r] == player_turn)
                    return true;
            }
        }
        //Vertical win
        for (int c = 0; c <= 6; c++)
        {
            for (int r = 0; r <= 2; r++)
            {
                if (current_board[c][r] == player_turn &&
                        current_board[c][r+1] == player_turn &&
                        current_board[c][r+2] == player_turn &&
                        current_board[c][r+3] == player_turn)
                    return true;
            }
        }
        //Shortest diagonals/anti diagonals (cell length == 4)
        //postive slope
        if (current_board[0][2] == player_turn && current_board[1][3] == player_turn && current_board[2][4] == player_turn && current_board[3][5] == player_turn)
            return true;
        if (current_board[3][0] == player_turn && current_board[4][1] == player_turn && current_board[5][2] == player_turn && current_board[6][3] == player_turn)
            return true;
        //negative slope
        if (current_board[0][3] == player_turn && current_board[1][2] == player_turn && current_board[2][1] == player_turn && current_board[3][0] == player_turn)
            return true;
        if (current_board[3][5] == player_turn && current_board[4][4] == player_turn && current_board[5][3] == player_turn && current_board[6][2] == player_turn)
            return true;

        //Medium-length diagonals/anti diagonals (cell length == 5)
        //positive slope
        if (current_board[0][1] == player_turn && current_board[1][2] == player_turn && current_board[2][3] == player_turn && current_board[3][4] == player_turn)
            return true;
        if (current_board[1][2] == player_turn && current_board[2][3] == player_turn && current_board[3][4] == player_turn && current_board[4][5] == player_turn)
            return true;
        if (current_board[2][0] == player_turn && current_board[3][1] == player_turn && current_board[4][2] == player_turn && current_board[5][3] == player_turn)
            return true;
        if (current_board[3][1] == player_turn && current_board[4][2] == player_turn && current_board[5][3] == player_turn && current_board[6][4] == player_turn)
            return true;
        //negative slope
        if (current_board[0][4] == player_turn && current_board[1][3] == player_turn && current_board[2][2] == player_turn && current_board[3][1] == player_turn)
            return true;
        if (current_board[1][3] == player_turn && current_board[2][2] == player_turn && current_board[3][1] == player_turn && current_board[4][0] == player_turn)
            return true;
        if (current_board[2][5] == player_turn && current_board[3][4] == player_turn && current_board[4][3] == player_turn && current_board[5][2] == player_turn)
            return true;
        if (current_board[3][4] == player_turn && current_board[4][3] == player_turn && current_board[5][2] == player_turn && current_board[6][1] == player_turn)
            return true;

        //Longest diagonals/anti diagonals (cell length == 6)
        //positive slope
        for (int c=0, r=0; c <= 2 && r <= 2; c++, r++)
        {
            if (current_board[c][r] == player_turn &&
                    current_board[c+1][r+1] == player_turn &&
                    current_board[c+2][r+2] == player_turn &&
                    current_board[c+3][r+3] == player_turn)
                return true;
        }
        for (int c=1, r=0; c <= 3 && r <= 2; c++, r++)
        {
            if (current_board[c][r] == player_turn &&
                    current_board[c+1][r+1] == player_turn &&
                    current_board[c+2][r+2] == player_turn &&
                    current_board[c+3][r+3] == player_turn)
                return true;
        }
        //negative slope
        for (int c=0, r=5; c <= 2 && r >= 3; c++, r--)
        {
            if (current_board[c][r] == player_turn &&
                    current_board[c+1][r-1] == player_turn &&
                    current_board[c+2][r-2] == player_turn &&
                    current_board[c+3][r-3] == player_turn)
                return true;
        }
        for (int c=1, r=5; c <= 3 && r >= 3; c++, r--)
        {
            if (current_board[c][r] == player_turn &&
                    current_board[c+1][r-1] == player_turn &&
                    current_board[c+2][r-2] == player_turn &&
                    current_board[c+3][r-3] == player_turn)
                return true;
        }

        return false;
    }

    /**
     * @return  true if there are no more empty cells
     */
    public boolean drawGame()
    {
        return (total_moves >= 42);
    }
}
