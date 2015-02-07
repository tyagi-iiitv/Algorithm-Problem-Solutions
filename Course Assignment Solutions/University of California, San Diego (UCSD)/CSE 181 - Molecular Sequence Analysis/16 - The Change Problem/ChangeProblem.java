/**
 *  CHANGE PROBLEM
 *
 *  Find the minimum number of coins needed to make change.
 *
 *  Given:  An integer money and an array Coins of positive integers.
 *  Return: The minimum number of coins with denominations Coins that changes money.
 */

import java.util.*;
public class ChangeProblem {
    public static void main( String[] args ) {
        // DEFINE "money" AND "Coins"!!!
        int money = 0;
        int[] coins = {};
        // DEFINE "money" AND "Coins"!!!
        System.out.println(minCoins(money,coins));
    }
    
    public static int minCoins( int money, int[] coins ) {
        int[] min = new int[money+1];
        min[0] = 0;
        for(int i = 1; i <= money; ++i) {
            int best = Integer.MAX_VALUE;
            for(int j = 0; j < coins.length; ++j) {
                int coin = coins[j];
                if(i >= coin) {
                    int curr = min[i-coin] + 1;
                    if(curr < best) {
                        best = curr;
                    }
                }
            }
            min[i] = best;
        }
        return min[money];
    }
}