package com.example.application_s5_a_01.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.application_s5_a_01.R
import com.example.application_s5_a_01.data.enums.ClassRoom
import com.example.application_s5_a_01.ui.BottomMenu
import com.example.application_s5_a_01.utils.GraphicUtils


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassRoomListScreen(
    onClassRoomClicked: (classRoom: ClassRoom) -> Unit,
) {
    val searchText = remember { mutableStateOf(TextFieldValue("")) }
    val classRooms = ClassRoom.entries

    Scaffold(
        modifier = Modifier.padding(top = 20.dp),
        topBar = {
            TopAppBar(
                title = { Text("Bienvenue") },
                actions = {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = searchText.value,
                            onValueChange = { searchText.value = it },
                            placeholder = { Text("Rechercher une salle ...") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(),
                            leadingIcon = {
                                Icon(Icons.Default.Search, contentDescription = "Search")
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                cursorColor = MaterialTheme.colorScheme.onSurface,
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface
                            ),
                            textStyle = MaterialTheme.typography.bodyMedium,
                            shape = RoundedCornerShape(8.dp)
                        )
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Bienvenue",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(it)
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
        },
        bottomBar = {
            Box(modifier = Modifier
                .fillMaxWidth()
            ){
                BottomMenu(
                    onHomeClicked = { },
                    onFavoriteClicked = {  },
                    onSettingsClicked = {  }
                )
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassRoomCard(
    classRoom: ClassRoom,
    onClassRoomClicked: (classRoom: ClassRoom) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = { onClassRoomClicked(classRoom) }
    ) {
        Box(
            modifier = Modifier
                .padding(5.dp)
                .width(800.dp)
                .height(170.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    GraphicUtils.getMainBrush(
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.colorScheme.tertiary
                    )
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .align(Alignment.TopCenter)
                    .padding(vertical = 16.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.logoclass1),
                    contentDescription = "Logo de la salle de classe",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    text = classRoom.name,
                    color = Color.White,
                    fontSize = 28.sp,
                    style = TextStyle(fontWeight = FontWeight.Bold, fontStyle = FontStyle.Normal)
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}











