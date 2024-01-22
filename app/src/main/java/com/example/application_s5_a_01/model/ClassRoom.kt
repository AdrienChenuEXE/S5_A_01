package com.example.application_s5_a_01.model;

import com.example.application_s5_a_01.R
import kotlin.enums.EnumEntries

enum class ClassRooms(name: String, val description: String?, val image: Int?) {
    D251("d251", "Description for D251", R.drawable.classroom1),
    D351("d351", "Description for D351", R.drawable.classroom1),
    D360("d360", "Description for D360", R.drawable.classroom1),
    default("default", null, null)
}

class ClassRoom {
    companion object {

        fun getClassRooms(): EnumEntries<ClassRooms> {
            return ClassRooms.entries
        }
        fun getClassRoomsValues(): List<String> {
            return getClassRooms().map{it.name}
        }
    }
}
