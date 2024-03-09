package com.example.eldiploma.presentation

import android.app.AppComponentFactory
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.arkivanov.decompose.defaultComponentContext
import com.example.eldiploma.MainApp
import com.example.eldiploma.data.local.LocalDatabase
import com.example.eldiploma.data.local.repository.GroupRepositoryImpl
import com.example.eldiploma.data.local.repository.StudentGroupRepositoryImpl
import com.example.eldiploma.data.local.repository.StudentRepositoryImpl
import com.example.eldiploma.data.network.AccountRequestParams
import com.example.eldiploma.data.network.ApiFactory
import com.example.eldiploma.data.network.ApiService
import com.example.eldiploma.data.network.WhereCondition
import com.example.eldiploma.data.network.repository.GroupNetworkRepositoryImpl
import com.example.eldiploma.data.network.repository.StudentGroupNetworkRepositoryImpl
import com.example.eldiploma.data.network.repository.StudentNetworkRepositoryImpl
import com.example.eldiploma.domain.local.usecase.AddGroupsUseCase
import com.example.eldiploma.domain.local.usecase.AddStudentGroupsUseCase
import com.example.eldiploma.domain.local.usecase.AddStudentsUseCase
import com.example.eldiploma.domain.local.usecase.GetStudentGroupsUseCase
import com.example.eldiploma.domain.local.usecase.GetStudentsUseCase
import com.example.eldiploma.domain.network.usecase.GetGroupNetworkUseCase
import com.example.eldiploma.domain.network.usecase.GetStudentGroupNetworkUseCase
import com.example.eldiploma.domain.network.usecase.GetStudentsNetworkUseCase
import com.example.eldiploma.presentation.root.DefaultRootComponent
import com.example.eldiploma.presentation.root.RootContent
import com.example.eldiploma.presentation.ui.theme.ElDiplomaTheme
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var rootComponentFactory: DefaultRootComponent.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as MainApp).applicationComponent.inject(this)
            lifecycleScope.launch {

                val requestParamsForGroup = AccountRequestParams(
                    where = listOf(
                        WhereCondition(
                            type = "equals",
                            attribute = "leadId",
                            value = listOf("65e1817fa60499374")
                        )
                    )
                )

                val repository2 = GroupNetworkRepositoryImpl(ApiFactory.apiService)
                val groups2 = GetGroupNetworkUseCase(repository2).invoke(Gson().toJson(requestParamsForGroup))
                val repositoryLocal2 = GroupRepositoryImpl(LocalDatabase.getInstance(applicationContext).groupDao())
                AddGroupsUseCase(repositoryLocal2).invoke(groups2)

                val groupsIds = groups2.map{it.id}
                val requestParamsForStudentGroup = AccountRequestParams(
                    where = listOf(
                        WhereCondition(
                            type = "equals",
                            attribute = "groupId",
                            value = groupsIds
                        )
                    )
                )
                val repository = StudentGroupNetworkRepositoryImpl(ApiFactory.apiService)
                val studentGroups = GetStudentGroupNetworkUseCase(repository).invoke(Gson().toJson(requestParamsForStudentGroup))
                val studentsIds = studentGroups.map{it.studentId}


                val requestParamsForStudents = AccountRequestParams(
                    where = listOf(
                        WhereCondition(
                            type = "equals",
                            attribute = "id",
                            value = studentsIds
                        )
                    )
                )
                val repository1 = StudentNetworkRepositoryImpl(ApiFactory.apiService)
                val students1 = GetStudentsNetworkUseCase(repository1).invoke(Gson().toJson(requestParamsForStudents))
                val repositoryLocal1 = StudentRepositoryImpl(LocalDatabase.getInstance(applicationContext).studentDao())
                AddStudentsUseCase(repositoryLocal1).invoke(students1)

                val repositoryLocal = StudentGroupRepositoryImpl(LocalDatabase.getInstance(applicationContext).studentGroupDao())
                AddStudentGroupsUseCase(repositoryLocal).invoke(studentGroups)

            }

            setContent {
            RootContent(component = rootComponentFactory.create(defaultComponentContext()))
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ElDiplomaTheme {
        Greeting("Android")
    }
}