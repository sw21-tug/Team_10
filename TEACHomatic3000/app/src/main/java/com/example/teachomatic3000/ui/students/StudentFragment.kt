package com.example.teachomatic3000.ui.students

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.teachomatic3000.R
import com.example.teachomatic3000.ui.home.StudentViewModel

class StudentFragment : Fragment() {

    private lateinit var studentViewModel: StudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        studentViewModel =
            ViewModelProvider(this).get(StudentViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_students, container, false)



        return root
    }
}