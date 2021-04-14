package com.example.teachomatic3000.models

class StudentModel {
    var firstName: String
    var lastName: String

    constructor(firstName: String, lastName: String) {
        this.firstName = firstName
        this.lastName = lastName
    }

    override fun toString(): String {
        return "StudentModel(firstName='$firstName', lastName='$lastName')"
    }
}