package com.example.myapplication.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.myapplication.entities.Student
import com.example.myapplication.entities.Subject

data class SubjectsWithStudents(

    @Embedded
    val subject: Subject,

    @Relation(
        parentColumn = "subjectName",
        entityColumn = "studentName",
        associateBy = Junction(StudentSubjectCrossReference::class)
    )
    val subjects : List<Student>

)