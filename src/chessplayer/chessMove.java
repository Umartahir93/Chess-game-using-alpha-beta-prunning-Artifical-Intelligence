/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessplayer;

/**
 *
 * @author mubasher.baig
 */
public class chessMove {
    int fromRow, fromColumn;
    int toRow, toColumn;
    
    chessMove() {
        fromRow = -1; 
        fromColumn = -1;
        
        toRow = -1; 
        toColumn = -1;
    }
    
    chessMove(int fromRow, int fromColumn , int toRow , int toColumn ) {
        this.fromRow = fromRow; 
        this.fromColumn = fromColumn;
        this.toRow = toRow; 
        this.toColumn = toColumn;
    }
    
 }
