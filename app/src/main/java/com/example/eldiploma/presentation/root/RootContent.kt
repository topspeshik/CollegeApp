package com.example.eldiploma.presentation.root

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.example.eldiploma.R
import com.example.eldiploma.presentation.pagesAttendance.PagesAttendanceContent
import com.example.eldiploma.presentation.pagesAttendance.attendanceList.AttendanceListContent
import com.example.eldiploma.presentation.pagesClassbook.PagesClassbookContent
import com.example.eldiploma.presentation.profile.ProfileContent
import com.example.eldiploma.presentation.ui.theme.ElDiplomaTheme

data class NavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RootContent(component: RootComponent) {
    ElDiplomaTheme {
      
        val childStack by component.stack.subscribeAsState()
        val activeComponent = childStack.active.instance

        Scaffold(bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = activeComponent is RootComponent.Child.Pages,
                    onClick = component::onClickPages,
                    icon = {
                        Icon(painter = painterResource(id = R.drawable.pages), contentDescription = null)
                    }
                )
                NavigationBarItem(
                    selected = activeComponent is RootComponent.Child.Profile,
                    onClick = component::onClickProfile ,
                    icon = {
                        Icon(painter = painterResource(id = R.drawable.profile), contentDescription = null)
                    }
                )

            }
        }) {
            Children(stack = component.stack) {
                when(val instance = it.instance){

                    is RootComponent.Child.Pages -> {
                        PagesClassbookContent(component = instance.component)
                    }
                    is RootComponent.Child.Profile -> {
                        ProfileContent(component = instance.component)
                    }

                    is RootComponent.Child.Attendance -> {
                        PagesAttendanceContent(component = instance.component)
                    }
                }
            }
        }



    }

}