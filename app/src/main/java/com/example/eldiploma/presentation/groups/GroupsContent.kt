package com.example.eldiploma.presentation.groups

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eldiploma.R
import com.example.eldiploma.domain.entity.StudentGroup

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GroupsContent(component: GroupsComponent) {

    val state = component.model.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(14.dp,0.dp,14.dp,14.dp)
    ){

        item{
            SearchCard { component.onClickSearch() }
        }
        items(
            items = state.value.groupsList,
            key = {it.groupId}
        ){
            GroupCard(it, onGroupClick = {component.onGroupClick(it)})
        }
    }
}


@Composable
private fun SearchCard(
    onClick: ()->Unit
) {

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
            .clickable { onClick() }
        ,
        colors = CardDefaults.cardColors(Color(0xFFFEFEFE)),
        shape = CircleShape,
        border = BorderStroke(1.dp, Color(0xFF5B5B5B)),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ){
            Icon(
                imageVector = Icons.Default.Search,
                tint = Color(0xFF8A8686),
                contentDescription = "Search",
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            )
            Text(
                text= stringResource(R.string.SearchGroups),
                color = Color(0xFF8A8686)
            )
        }
    }
}

@Composable
private fun GroupCard(
    studentGroup: StudentGroup,
    onGroupClick: () -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onGroupClick() }
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
                studentGroup.groupName?.let {
                    Text(
                        fontSize = 20.sp,
                        text = it
                    )
                }
                studentGroup.name?.let {
                    Text(
                        fontSize = 14.sp,
                        text = it
                    )
                }
            }

            IconButton(onClick = { onGroupClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.arrowleft),
                    contentDescription = "Перейти к группе",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Blue
                )
            }
        }
    }
}