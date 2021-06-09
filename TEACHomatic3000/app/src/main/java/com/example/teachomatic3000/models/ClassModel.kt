package com.example.teachomatic3000.models

class ClassModel {
    var classId: Int
    var className: String

    constructor() {
        this.classId = 0
        this.className = "Klasse"
    }

    constructor(class_id: Int, class_name: String) {
        this.classId = class_id
        this.className = class_name
    }

    override fun toString(): String {
        return "$classId $className"
    }
}