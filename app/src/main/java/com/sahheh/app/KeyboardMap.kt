package com.sahheh.app

object KeyboardMap {

    private val en2ar: Map<Char, String> = mapOf(
        'q' to "َ", 'w' to "ً", 'e' to "ُ", 'r' to "ٌ", 't' to "لإ",
        'y' to "إ", 'u' to "`", 'i' to "÷", 'o' to "×", 'p' to "؛", 
        '[' to "<", ']' to ">", 'a' to "ِ", 's' to "ٍ", 'd' to "لأ", 
        'f' to "أ", 'g' to "ـ", 'h' to "أ", 'j' to "ـ", 'k' to "،", 
        'l' to "/", ';' to ":", '\'' to "\"", 'z' to "~", 'x' to "ْ", 
        'c' to "}", 'v' to "{", 'b' to "لآ", 'n' to "آ", 'm' to "'", 
        ',' to ",", '.' to ".", '/' to "؟"
    )

    private val ar2en: Map<String, Char> = en2ar
        .filter { it.value.length == 1 }
        .entries.associate { it.value to it.key }

    fun convert(text: String, direction: String): String {
        return if (direction == "en2ar") {
            buildString {
                for (ch in text) {
                    val lower = ch.lowercaseChar()
                    append(en2ar[lower] ?: ch.toString())
                }
            }
        } else {
            val cleaned = text.replace("لا", "ل")
            buildString {
                for (ch in cleaned) {
                    val mapped = ar2en[ch.toString()]
                    if (mapped != null) append(mapped) else append(ch)
                }
            }
        }
    }

    fun detectDirection(text: String): String {
        var arCount = 0
        var enCount = 0
        for (ch in text) {
            if (ch.code in 0x0600..0x06FF) arCount++
            else if (ch.isLetter() && ch.code < 128) enCount++
        }
        return if (arCount > enCount) "ar2en" else "en2ar"
    }

    fun fix(text: String): String {
        return convert(text, detectDirection(text))
    }
}
