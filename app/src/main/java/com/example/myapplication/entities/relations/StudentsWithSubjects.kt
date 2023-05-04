package com.example.myapplication.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.myapplication.entities.Student
import com.example.myapplication.entities.Subject

data class StudentsWithSubjects(

    @Embedded
    val student: Student,

    @Relation(
        parentColumn = "studentName",
        entityColumn = "subjectName",
        associateBy = Junction(StudentSubjectCrossReference::class)
    )
    val subjects : List<Subject>

)