package com.kevin.kotlin.sealed

import java.io.File
import java.io.IOError
import javax.sql.DataSource

/**
 * Created by: kevin
 * Date: 2022-07-05
 */
sealed interface Error{
    sealed class IOError : Error{
        class FileReadError(val file: File): IOError()
        class DatabaseError(val source: DataSource): IOError()
    }
    object RuntimeError : Error
}


fun log(e: Error) = when(e) {
    is Error.IOError.FileReadError -> { println("Error while reading file ${e.file}") }
    is Error.IOError.DatabaseError -> { println("Error while reading from database ${e.source}")}
    Error.RuntimeError -> { println("Runtime error") }
}

fun log(e: Error.IOError) = when(e) {
    is Error.IOError.FileReadError -> { println("Error while reading file ${e.file}") }
    is Error.IOError.DatabaseError -> { println("Error while reading from database ${e.source}")}
}

fun main() {
    log(Error.RuntimeError)
    log(Error.IOError.FileReadError(File("/")))
}