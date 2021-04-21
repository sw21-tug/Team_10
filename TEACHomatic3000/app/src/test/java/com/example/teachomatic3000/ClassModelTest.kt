package com.example.teachomatic3000

import com.example.teachomatic3000.models.ClassModel
import org.junit.Test
import org.junit.Assert.*


class ClassModelTest {

    @Test
    fun classModelexists() {
        val testClass = ClassModel()

        assertEquals(testClass::class, ClassModel::class)
    }

    @Test
    fun checkClassParameters() {
        val class_name = "thistestclass"
        val class_id = 0
        val testClass = ClassModel(class_id, class_name)
        assertEquals(testClass.class_id, 0)
        assertEquals(testClass.class_name, "thistestclass")
    }


}