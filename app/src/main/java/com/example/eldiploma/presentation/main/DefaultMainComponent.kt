package com.example.eldiploma.presentation.main

import com.arkivanov.decompose.ComponentContext

class DefaultMainComponent (
    componentContext: ComponentContext
) : MainComponent, ComponentContext by componentContext