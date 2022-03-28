package com.sp.algorithm.normal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: luchao
 * @date: Created in 3/21/22 5:04 PM
 */
public class QueueChess {
    public List<String[]> solveNQueue(int n){
        List<String[]> res = new ArrayList<>();
        playChess(0, new Boolean[n], new boolean[2 * n], new boolean[2 * n], new String[n], res);
        return res;
    }

    private void playChess(int r, Boolean[] cols, boolean[] d1, boolean[] d2, String[] board, List<String[]> res) {
        if(r == board.length){
            res.add(board.clone());
            return;
        }

        for (int c = 0; c < board.length; c++) {
            int id1 = r - c + board.length;
            int id2 = 2 * board.length - r - c - 1;

            if(!cols[c] && !d1[id1] && !d2[id2]){
                char[] row = new char[board.length];
                Arrays.fill(row, ',');
                row[c] = '0';

                board[r] = new String(row);
                cols[c] = true;
                d1[id1] = true;
                d2[id2] = true;
                playChess(r + 1, cols, d1, d2, board, res);
                cols[c] = false;
                d1[id1] = false;
                d2[id2] = false;
            }
        }
    }
}
