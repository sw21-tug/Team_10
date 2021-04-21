package com.example.teachomatic3000.models

class StudentModel {
    var studentID: Int
    var firstName: String
    var lastName: String

    constructor(studentID: Int, firstName: String, lastName: String) {
        this.studentID = studentID
        this.firstName = firstName
        this.lastName = lastName
    }

    override fun toString(): String {
        return "StudentModel(studentID='$studentID',firstName='$firstName', lastName='$lastName')"
    }
}