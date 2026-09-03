package com.sahheh.app

object KeyboardMap {

    private val en2ar: Map<Char, String> = mapOf(
        '`' to "ذ", 'q' to "ض", 'w' to "ص", 'e' to "ث", 'r' to "ق", 't' to "ف",
        'y' to "غ", 'u' to "ع", 'i' to "ه", 'o' to "خ", 'p' to "ح", '[' to "ج", ']' to "د",
        'a' to "ش", 's' to "س", 'd' to "ي", 'f' to "ب", 'g' to "ل", 'h' to "ا",
        'j' to "ت", 'k' to "ن", 'l' to "م", ';' to "ك", '\'' to "ط",
        'z' to "ئ", 'x' to "ء", 'c' to "ؤ", 'v' to "ر", 'b' to "لا", 'n' to "ى", 'm' to "ة",
        ',' to "و", '.' to "ز", '/' to "ظ"
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
            val cleaned = text.replace("لا", "b")
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

    fun fix(text: String): String = convert(text, detectDirection(text))
}
