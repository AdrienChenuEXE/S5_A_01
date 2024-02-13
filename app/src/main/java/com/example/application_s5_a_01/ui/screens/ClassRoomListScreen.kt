package com.example.application_s5_a_01.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.application_s5_a_01.model.ClassRoom
import com.example.application_s5_a_01.model.ClassRooms

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassRoomListScreen(
    onClassRoomClicked: (classRoom: ClassRooms) -> Unit,
) {
    val searchText = remember { mutableStateOf(TextFieldValue("")) }
    val classRooms = ClassRoom.getClassRooms()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Class Rooms") },
                actions = {
                    TextField(
                        value = searchText.value,
                        onValueChange = { searchText.value = it },
                        placeholder = { Text("Search classrooms") },
                        singleLine = true
                    )
                }
            )
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier.padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(classRooms.filter {
                    it.name.contains(
                        searchText.value.text,
                        true
                    )
                }) { classRoom ->
                    ClassRoomCard(classRoom, onClassRoomClicked)
                }
            }
        }
    )
}

@Composable
fun ClassRoomCard(
    classRoom: ClassRooms,
    onClassRoomClicked: (classRoom: ClassRooms) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            classRoom.image?.let { painterResource(it) }?.let {
                Image(
                    painter = it,
                    contentDescription = "Classroom Image"
                )
            }
            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(text = classRoom.name)
                classRoom.description?.let { Text(text = it) }
                Button(onClick = {onClassRoomClicked(classRoom)}) {
                    Text("View Details")
                }
            }
        }
    }
}