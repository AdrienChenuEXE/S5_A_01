package com.example.application_s5_a_01.model;

import kotlin.enums.EnumEntries

enum class ClassRooms(name: String) {
    D251("d251"),
    D351("d351"),
    D360("d360"),
    default("default")
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
