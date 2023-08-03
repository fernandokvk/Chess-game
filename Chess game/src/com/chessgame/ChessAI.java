package com.chessgame;

import com.chessgame.Board.Board;
import com.chessgame.Board.Move;
import com.chessgame.Game.Game;
import com.chessgame.Pieces.Piece;

import java.util.ArrayList;
import java.util.Random;

public class ChessAI {

    public static Move makeRandomMove(Board board){
        ArrayList<Piece> allPieces = Game.AllPieces;
        ArrayList<Move> aiPossibleMoves = new ArrayList<>();

        // Collect all possible moves for the AI's pieces
        for (Piece piece : allPieces) {
            if (!piece.isWhite()) { // AI's pieces are black
                piece.fillAllPseudoLegalMoves(board);
                aiPossibleMoves.addAll(piece.getMoves());
            }
        }

        // Make a random move from the list of possible moves
        if (!aiPossibleMoves.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(aiPossibleMoves.size());
            return aiPossibleMoves.get(randomIndex);
        }

        // If no moves are available, return null (this should not happen in a legal chess position)
        return null;
    }
    public static Move makeMove(Board board, Game game){
        if (Game.gameOver) {
            return null;
        }
        if (!Game.player) {
            Random r = new Random();
            game.active = Game.bPieces.get(r.nextInt(Game.bPieces.size()));
            while (game.active.getMoves().isEmpty()) {
                game.active = Game.bPieces.get(r.nextInt(Game.bPieces.size()));
            }
            Move m = game.active.getMoves().get(r.nextInt(game.active.getMoves().size()));
            game.move(m.getToX(), m.getToY());
        }
        return null;
    }
}
