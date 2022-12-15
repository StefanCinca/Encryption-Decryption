package encryptdecrypt

import java.io.File

fun main(args: Array<String>) {

    val cmdArguments = CommandLineArguments.parseArguments(args)
    var input = cmdArguments.input

    if (input.isBlank() && cmdArguments.fileName.isNotBlank()) {
        val file = File(cmdArguments.fileName)
        if (file.exists()) {
            input = file.readText(Charsets.US_ASCII)
        } else print("ERROR")
    }

    val output = when (cmdArguments.operation) {
        "enc" -> encrypt(input, cmdArguments.key, cmdArguments.algorithm)
        "dec" -> decrypt(input, cmdArguments.key, cmdArguments.algorithm)
        else -> throw IllegalStateException("Not supported")
    }

    writeOutput(output, cmdArguments.outputFile)
}

private fun writeOutput(output: String, outputFile: String = "") {
    if (outputFile.isBlank()) {
        print(output)
        return
    }
    File(outputFile).writeText(output, Charsets.US_ASCII)
}

private fun encrypt(
    input: String,
    key: Int = 0,
    algorithm: String,
): String {
    when (algorithm) {
        "shift" -> return EncryptUtil.encryptByShifting(input, key)
        "unicode" -> return EncryptUtil.encryptUnicode(input, key)
        else -> throw Exception("Algorithm not supported $algorithm")
    }
}

private fun decrypt(
    input: String,
    key: Int = 0,
    algorithm: String,
): String {
    when (algorithm) {
        "shift" -> return EncryptUtil.decryptByShifting(input, key)
        "unicode" -> return EncryptUtil.decryptUnicode(input, key)
        else -> throw Exception("Algorithm not supported $algorithm")
    }
}

