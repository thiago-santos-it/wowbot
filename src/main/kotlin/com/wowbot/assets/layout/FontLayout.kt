package com.wowbot.assets.layout

class FontLayout {

    private val headers = arrayListOf(60, 50, 40, 30, 20, 10, 5)

    fun size(h: Int): Int {
        return headers[h - 1]
    }
}