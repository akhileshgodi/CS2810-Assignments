/**
  *@Author : Akhilesh Godi (CS10B037)
  *Assignment 7 - Tic Tac Toe
  *CS 2810 - Advanced Programming Lab
  */
package cse.iitm.cs10b037.tictactoe;

public interface BotInterface 
{
    public void getNextMove();
    public void makeNextMove();
    public int getRow();
    public int getColumn();
}
