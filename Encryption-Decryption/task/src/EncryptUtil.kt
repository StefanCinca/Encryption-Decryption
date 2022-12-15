package encryptdecrypt

/**
Stefan created on 12/14/2022
 */
class EncryptUtil {

    companion object {

        fun decryptByShifting(input: String, poz: Int): String = encryptByShifting(input, -poz)

        fun encryptByShifting(input: String, key: Int): String {
            val sb = StringBuilder()
            for (ch in input) {
                if (ch != ' ') {
                    sb.append(shiftChar(ch, key))
                } else {
                    sb.append(ch)
                }
            }
            return sb.toString()
        }

        fun decryptUnicode(input: String, key: Int): String = encryptUnicode(input, -key)

        fun encryptUnicode(input: String, key: Int): String {
            val res = StringBuilder()
            for (ch in input) {
                res.append(encryptCharWithKey(ch, key))
            }
            return res.toString()
        }

        private fun shiftChar(char: Char, poz: Int): Char {
            val range: Int = 'z'.code - 'a'.code + 1
            var shiftedPozFromA = (char.code - 'a'.code)
            while (shiftedPozFromA + poz < 0) {
                shiftedPozFromA += range
            }
            return ((shiftedPozFromA + poz) % range + 'a'.code).toChar()
        }

        private fun encryptCharWithKey(char: Char, key: Int): Char? {
            val ascii = getAsciiMap()
            var value = char.code
            while (value < 0) {
                value  += char.code
            }
            return ascii[(char.code + key) % 127]
        }


        private fun getAsciiMap(): Map<Int, Char> {
            val intArray = IntArray(127) { it }
            return intArray.associateWith { it.toChar() }
        }
    }
}