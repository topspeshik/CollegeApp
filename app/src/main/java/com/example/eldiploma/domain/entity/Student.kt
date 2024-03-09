package com.example.eldiploma.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student (
    val id: String,
    val name: String?,
    val lastname: String?,
    val phoneNumber: String?
) : Parcelable