package com.sample.brigthcovetvmazesample.util

fun String.removeHtmlTags(): String {
    return Regex(pattern = "<\\w+>|</\\w+>").replace(this, replacement = "")
}