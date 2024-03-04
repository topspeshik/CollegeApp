package com.example.eldiploma

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
import com.example.eldiploma.data.network.ApiFactory
import com.example.eldiploma.ui.theme.ElDiplomaTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        CoroutineScope(Dispatchers.IO).launch {
//            try {
////                ApiFactory.apiService.addStudent("c8497eae2175fd4fb5b25e371ec9a6b0",
////                    StudentDbModel(null,"Maxim", "Marcinkevich", "+79500940323"))
//
//                val response = ApiFactory.apiService.getStudents()
//                withContext(Dispatchers.Main) {
//                    if (response.isSuccessful) {
//                        Log.d("Response", "$response")
//                        val contacts = response.body()?.list
//                        if (contacts != null) {
//                            for (contact in contacts) {
//                                val firstName = contact.name
//                                val lastName = contact.lastname
//                                Log.d("ResponseContacts", "$firstName")
//                            }
//                        } else {
//                            Log.d("ResponseContacts", "Contacts is null")
//                        }
//                    } else {
//                        Log.d("ResponseContacts", response.message())
//                    }
//                }
//            } catch (e: Exception) {
//                Log.e("ErrorResponse", e.message.toString())
//            }
//        }
            setContent {
            ElDiplomaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
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