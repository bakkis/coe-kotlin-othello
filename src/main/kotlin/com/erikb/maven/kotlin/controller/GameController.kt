package com.erikb.maven.kotlin.controller

import com.erikb.maven.kotlin.enum.Status
import com.erikb.maven.kotlin.model.Game
import com.erikb.maven.kotlin.service.*

fun runGame(game: Game) {

    printBoard(game.board)

    while (game.status == Status.ACTIVE) {

        printMakeYourMove(game.player)

        val input = readLine()!!

        if (isValid(input)) {
            val row = getRowIndex(input)
            val column = getColumnIndex(input)

            if (runMove(game, row, column, game.player, true)) {
                game.board[row][column] = put(game.board[row][column], game.player.nr)
                printBoard(game.board)

                if (availableMoves(game, switchPlayer(game.player))) {
                    game.player = switchPlayer(game.player)
                    printScore(getScore(game))
                } else {
                    if (availableMoves(game, game.player)) {
                        printScore(getScore(game))
                        printNoAvailableMoves(switchPlayer(game.player))
                    } else {
                        game.status = switchStatus(game.status)
                        printGameOver(game)
                    }
                }
            } else {
                printInvalidMove()
            }
        } else {
            printInvalidMove()
        }
    }
}






