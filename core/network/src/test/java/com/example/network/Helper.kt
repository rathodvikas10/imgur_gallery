package com.example.network

object Helper {
    fun readFile(fileName: String): String {
        val inputStream = Helper::class.java.classLoader?.getResourceAsStream(fileName)
        val builder = StringBuilder()
        val reader = inputStream?.bufferedReader()
        reader?.forEachLine {
            builder.append(it)
        }
        return builder.toString()
    }
}