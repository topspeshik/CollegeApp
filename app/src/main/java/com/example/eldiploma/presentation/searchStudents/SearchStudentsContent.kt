package com.example.eldiploma.presentation.searchStudents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarColors
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eldiploma.R
import com.example.eldiploma.domain.entity.StudentGroup

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchStudentsContent(component: SearchStudentsComponent) {
    val state by component.model.collectAsState()
    val focusRequester = remember{
        FocusRequester()
    }
    
    LaunchedEffect(key1 = Unit){
        focusRequester.requestFocus()
    }

    SearchBar(
        modifier = Modifier.focusRequester(focusRequester),
        placeholder = { Text(text = stringResource(R.string.Search))},
        query = state.searchQuery,
        onQueryChange = {component.changeSearchQuery(it)},
        onSearch = {component.onClickSearch()},
        active = true,
        onActiveChange = {},
        leadingIcon = {
            IconButton(onClick = {component.onClickBack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Click back"
                )
            }
        },
        trailingIcon = {
            IconButton(onClick = {component.onClickSearch() }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Click back"
                )
            }
        },
        colors =SearchBarDefaults.colors(
           containerColor = Color(0xFFFEFEFE)
        )
    ) {
        when(val searchState = state.searchState){
            SearchStudentsStore.State.SearchState.EmptyResult -> {
                Text(
                    text = stringResource(R.string.NothingFound),
                    modifier = Modifier.padding(8.dp)
                )
            }
            SearchStudentsStore.State.SearchState.Initial -> {

            }
            SearchStudentsStore.State.SearchState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
            is SearchStudentsStore.State.SearchState.SuccessLoaded -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(16.dp)
                ){
                    items(
                        items = searchState.students,
                        key = {it.studentId}
                    ){
                       StudentCard(studentGroup = it, onStudentClick = {component.onStudentClick(it)})
                    }
                }

            }
        }
    }
}

@Composable
private fun StudentCard(
    studentGroup: StudentGroup,
    onStudentClick: () -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onStudentClick() }
            .shadow(
                elevation = 4.dp,
                shape = MaterialTheme.shapes.large
            ),
        colors = CardDefaults.cardColors(Color(0xFFFEFEFE)),
        shape = MaterialTheme.shapes.large
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .sizeIn(minHeight = 90.dp)
                .padding(PaddingValues(24.dp, 0.dp, 0.dp, 0.dp)),
            verticalAlignment = Alignment.CenterVertically

        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                studentGroup.name?.let {
                    Text(
                        fontSize = 20.sp,
                        text = it
                    )
                }
                studentGroup.groupName?.let {
                    Text(
                        fontSize = 14.sp,
                        text = it
                    )
                }
            }

        }
    }
}