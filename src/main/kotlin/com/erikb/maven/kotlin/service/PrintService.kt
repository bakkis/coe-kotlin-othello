package com.erikb.maven.kotlin.service

import com.erikb.maven.kotlin.enum.Player
import com.erikb.maven.kotlin.model.Game
import com.erikb.maven.kotlin.model.Score

fun printBoard(board: Array<IntArray>) {
    println("      1  2  3  4  5  6  7  8")
    println("------------------------------")

    for (i in 1..Game().height - 2) {
        print(" " + rows().get(i) + " | ")
        for (j in 1..Game().width - 2) {
            print(fillSpace(board[i][j]))
        }
        println()
    }
}

fun printScore(score: Score) {
    println()
    println("Score:")
    println("------------------------------")
    println("Player ONE: " + score.playerOne)
    println("Player TWO: " + score.playerTwo)
    println()
}

fun printMakeYourMove(player: Player) {
    print("Player " + player.name + ", make your move: ")
}

fun printInvalidMove(){
    println("Invalid move! Please try again: ")
}

fun printNoAvailableMoves(player: Player) {
    print("Player " + if (player.nr == 1) "ONE" else "TWO" + "has no available moves")
}

fun printGameOver(game: Game) {
    val score = getScore(game)
    println("\n")
    println("------------------------------")
    println("Game over!")

    if (score.playerOne == score.playerTwo) {
        println("It is a draw... :(")
    } else {
        println("Player " + if (score.playerOne > score.playerTwo) "ONE" else "TWO" + " wins!!!")
    }
}

