package com.example.teachomatic3000.models

class StudentModel(var studentID: Int, var firstName: String, var lastName: String) {

    override fun toString(): String {
        return "StudentModel(studentID='$studentID',firstName='$firstName', lastName='$lastName')"
    }
}