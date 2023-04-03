package com.kevin.kotlin

/**
 * Created by: kevin
 * Date: 2022-10-31
 */
fun main() {
    val str = "ys7\rdgasdg\n\rv12234\nj382r\r\n1254"

    val split = str.split("\r\n","\n\r","\r","\n")

    System.out.println(split)
}