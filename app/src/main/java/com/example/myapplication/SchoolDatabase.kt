package com.example.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.entities.Director
import com.example.myapplication.entities.School
import com.example.myapplication.entities.Student
import com.example.myapplication.entities.Subject
import com.example.myapplication.entities.relations.StudentSubjectCrossReference


@Database(
    entities = [
        School::class,
        Student::class,
        Director::class,
        Subject::class,
        StudentSubjectCrossReference::class
    ],
    version = 1
)
abstract class SchoolDatabase : RoomDatabase() {

    abstract val schoolDao : SchoolDao

    companion object{

        @Volatile //Whenever we change the value of this instance variable then the change must be visible to other threads
        private var INSTANCE : SchoolDatabase?=null  //Volatile helps to prevents race conditions

        fun getInstance(context : Context) : SchoolDatabase{

            synchronized(this){ //This will make sure that whatever we want to execute, then it must be executed by single thread
                //Helps when 2 different threads wants to create the instance at once, in that case we wont be having single instance

                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    SchoolDatabase::class.java,
                    "school_db"
                ).build().also{
                    INSTANCE = it
                }

            }

        }

    }

}