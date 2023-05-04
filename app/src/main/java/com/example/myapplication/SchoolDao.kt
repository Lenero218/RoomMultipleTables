package com.example.myapplication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.myapplication.entities.Director
import com.example.myapplication.entities.School
import com.example.myapplication.entities.Student
import com.example.myapplication.entities.Subject
import com.example.myapplication.entities.relations.SchoolAndDirector
import com.example.myapplication.entities.relations.SchoolWithStudents
import com.example.myapplication.entities.relations.StudentSubjectCrossReference
import com.example.myapplication.entities.relations.StudentsWithSubjects
import com.example.myapplication.entities.relations.SubjectsWithStudents


@Dao
interface SchoolDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchool(school: School)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDirector(director:Director)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student:Student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: Subject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudentSubjectCrossReference(crossRef : StudentSubjectCrossReference)



    @Transaction  //This will not let other query to change the database when a current query is running - prevents multi-threading
    @Query("SELECT * from SCHOOL WHERE schoolName = :schoolName")
    suspend fun getSchoolAndDirectorWithSchoolName(schoolName:String) : List<SchoolAndDirector>

    @Transaction
    @Query("Select * from school Where schoolName = :schoolName")
    suspend fun getSchoolWithStudents(schoolName : String) : List<SchoolWithStudents>


    @Transaction  //This will not let other query to change the database when a current query is running - prevents multi-threading
    @Query("SELECT * from Subject WHERE subjectName = :subjectName")
    suspend fun getStudentOfSubject(subjectName:String) : List<SubjectsWithStudents>

    @Transaction  //This will not let other query to change the database when a current query is running - prevents multi-threading
    @Query("SELECT * from Student WHERE studentName = :studentName")
    suspend fun getSubjectOfStudent(studentName:String) : List<StudentsWithSubjects>



}