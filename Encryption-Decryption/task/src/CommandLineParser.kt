package encryptdecrypt

import java.io.File

/**
Stefan created on 12/14/2022
 */

data class CommandLineArguments private constructor(
    val operation: String,
    val input: String,
    val key: Int,
    val fileName: String,
    val outputFile: String,
    val algorithm: String,
) {

    companion object {
        const val MODE: String = "-mode"
        const val KEY: String = "-key"
        const val DATA: String = "-data"
        const val INPUT_FILE: String = "-in"
        const val OUTPUT_FILE: String = "-out"
        const val ALGORTIHM: String = "-alg"

        fun parseArguments(args: Array<String>): CommandLineArguments {
            var operation = "enc"
            var input = ""
            var key = 0
            var fileName = ""
            var outputFile = ""
            var algorithm = "shift"

            for ((index, element) in args.withIndex()) {
                if (element == MODE) {
                    operation = args[index + 1]
                } else if (element == KEY) {
                    key = args[index + 1].toInt()
                } else if (element == DATA) {
                    input = args[index + 1]
                } else if (element == INPUT_FILE) {
                    fileName = args[index + 1]
                } else if (element == OUTPUT_FILE) {
                    outputFile = args[index + 1]
                } else if (element == ALGORTIHM) {
                    algorithm = args[index + 1]
                }
            }

            return CommandLineArguments(
                operation,
                input,
                key,
                fileName,
                outputFile,
                algorithm
            )
        }
    }
}