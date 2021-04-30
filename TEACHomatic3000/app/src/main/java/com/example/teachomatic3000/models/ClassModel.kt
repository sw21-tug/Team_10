package com.example.teachomatic3000.models

class ClassModel {
    var class_id: Int
    var class_name: String

    constructor() {
        this.class_id = 0
        this.class_name = "Klasse"
    }

    constructor(class_id: Int, class_name: String) {
        this.class_id = class_id
        this.class_name = class_name
    }

    fun toDebugString(): String {
        return "ClassModel(class_id=$class_id, class_name='$class_name')"
    }

    override fun toString(): String {
        return "$class_id $class_name"
    }
}