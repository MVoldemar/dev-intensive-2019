package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?):Pair<String?,String?>{
        val parts:List<String>? = fullName?.split(" ")
        val firstName = if( (parts?.getOrNull(0) == null) || (parts?.getOrNull(0) =="" ||
                parts?.getOrNull(0) ==" ")) {"null"}   else parts?.getOrNull(0)
        val lastName = if( (parts?.getOrNull(1) == null) || (parts?.getOrNull(0) =="" ||
                parts?.getOrNull(1) =="")) {"null"} else  parts?.getOrNull(1)
        return firstName to lastName
    }

    fun transliteration(payload: String, divider:String = " "): String {
        return "Null"
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        return "Null"
    }
}