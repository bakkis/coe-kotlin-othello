package com.erikb.maven.kotlin.model

import com.erikb.maven.kotlin.enum.Player
import com.erikb.maven.kotlin.enum.Space
import com.erikb.maven.kotlin.enum.Status

class Game {
    val width = 10
    val height = 10

    val board = array2dOfInt(width, height)
    var status = Status.ACTIVE
    var player = Player.ONE

    init {

        for (i in 0 until width) {
            this.board[i][0] = Space.OFFBOARD.nr
            this.board[i][width - 1] = Space.OFFBOARD.nr
            this.board[0][i] = Space.OFFBOARD.nr
            this.board[height - 1][i] = Space.OFFBOARD.nr
        }

        for (i in 1..height - 2)
            for (j in 1..width - 2)
                this.board[i][j] = Space.EMPTY.nr

        this.board[height/2-1][width/2-1] = Player.ONE.nr
        this.board[height/2][width/2-1] = Player.TWO.nr
        this.board[height/2-1][width/2] = Player.TWO.nr
        this.board[height/2][width/2] = Player.ONE.nr
    }

    private fun array2dOfInt(sizeOuter: Int, sizeInner: Int): Array<IntArray> = Array(sizeOuter) { IntArray(sizeInner) }
}

