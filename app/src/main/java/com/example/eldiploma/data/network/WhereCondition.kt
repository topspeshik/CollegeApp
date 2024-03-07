package com.example.eldiploma.data.network

data class WhereCondition(
    val type: String,
    val attribute: String,
    val value: List<String>
)