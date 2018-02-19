package com.erikb.maven.kotlin.service

import com.erikb.maven.kotlin.enum.Player
import com.erikb.maven.kotlin.enum.Status
import com.erikb.maven.kotlin.model.Game
import com.erikb.maven.kotlin.model.Score


fun getColumnIndex(input: String): Int {
    return input[1].toString().toInt()
}

fun getRowIndex(input: String): Int {
    return rows().indexOf(input[0].toString().toUpperCase())
}

fun getScore(game: Game): Score {

    var playerOne = 0
    var playerTwo = 0

    for (i in 1..game.height - 2) {
        for (j in 1..game.width - 2) {
            if (game.board[i][j] == Player.ONE.nr) playerOne++
            if (game.board[i][j] == Player.TWO.nr) playerTwo++
        }
    }

    return Score(playerOne, playerTwo)
}

fun runMove(game: Game, row: Int, column: Int, player: Player, flip: Boolean): Boolean {

    var legal = false

    if (game.board[row][column] == 0) {

        var posRow: Int
        var posColumn: Int
        var found: Boolean
        var current: Int

        for (x in -1..1) {
            for (y in -1..1) {

                posColumn = column + x
                posRow = row + y
                found = false
                current = game.board[posRow][posColumn];

                if (current == -1 || current == 0 || current == player.nr) {
                    continue
                }

                while (!found) {
                    posColumn += x
                    posRow += y
                    current = game.board[posRow][posColumn]

                    if (current == player.nr) {
                        found = true
                        legal = true

                        if (flip) {
                            posColumn -= x
                            posRow -= y
                            current = game.board[posRow][posColumn]

                            while (current != 0) {
                                game.board[posRow][posColumn] = player.nr
                                posColumn -= x
                                posRow -= y
                                current = game.board[posRow][posColumn]
                            }
                        }
                    } else if (current == -1 || current == 0) {
                        found = true
                    }
                }
            }
        }
    }

    return legal
}

fun rows(): Array<String> {
    return arrayOf("", "A", "B", "C", "D", "E", "F", "G", "H", "")
}
fun columns(): Array<String> {
    return arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")
}

fun fillSpace(space: Int): String {
    return when (space) {
        1 -> " X "
        2 -> " O "
        else -> {
            " . "
        }
    }
}

fun isValid(input: String): Boolean {
    if(input.length != 2) return false
    if(!rows().contains(input[0].toString().toUpperCase())) return false
    if(!columns().contains(input[1].toString())) return false
    return true
}

fun availableMoves(game: Game, player: Player): Boolean {
    var availableMoves = 0

    for (i in 1..Game().height - 2) {
        (1..Game().width - 2)
                .filter { runMove(game, i, it, player, false) }
                .forEach { availableMoves++ }
    }
    return availableMoves > 0
}

fun switchStatus(status: Status): Status {
    return when(status) {
        Status.ACTIVE -> Status.GAME_OVER
        Status.GAME_OVER -> Status.ACTIVE
    }
}


fun put(oldSpace: Int, newSpace: Int): Int {
    return when (oldSpace) {
        0 -> newSpace
        else -> oldSpace
    }
}

fun switchPlayer(player: Player): Player {
    return when (player) {
        Player.ONE -> Player.TWO
        Player.TWO -> Player.ONE
    }
}



