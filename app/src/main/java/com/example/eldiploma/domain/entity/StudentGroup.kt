package com.example.eldiploma.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class StudentGroup(
    val id: String,
    val studentId: String,
    val name: String?,
    val groupId: String,
    val groupName: String?
) : Parcelable
