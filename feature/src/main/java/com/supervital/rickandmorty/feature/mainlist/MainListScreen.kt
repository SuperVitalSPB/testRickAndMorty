package com.supervital.rickandmorty.feature.mainlist

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.supervital.rickandmorti.models.CharacterInfo
import com.supervital.rickandmorti.models.CharactersListInfo


@Composable
fun MainListScreen(viewModel: MainListViewModel = hiltViewModel()) {

    val data by viewModel.data.observeAsState(initial = CharactersListInfo())
    var page by remember { mutableIntStateOf(1) }
    LaunchedEffect(page) {
        viewModel.loadData(page)
    }
    LazyVerticalGrid (modifier = Modifier.height(300.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(
            items = data.characters,
            key = { it.id }) { item ->
            CharacterInfoScreen(item)
            Spacer(modifier = Modifier.height(10.dp))

        }
    }
}

@Composable
fun CharacterInfoScreen(characterInfo: CharacterInfo) {
    Box(modifier = Modifier
        .width(160.dp)
        .height(280.dp)
        .background(color = Color.DarkGray, shape = RoundedCornerShape(16.dp))
    ) {
        Column (modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Box() {
                AsyncImage(
                    model = characterInfo.image,
                    contentDescription = "im2",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(
                                topStart = 16.dp,
                                topEnd = 16.dp
                            )
                        )
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .height(32.dp)
                        .width(characterInfo.status.widthBox)
                        .clip(RoundedCornerShape(32.dp, 0.dp, 0.dp, 0.dp))
                        .background(color = Color.Black)
                ) {
                    Canvas(Modifier.size(50.dp)) {
                        drawCircle(
                            color = characterInfo.status.colorPunkt,
                            center = center,
                            radius = 4.dp.toPx()
                        )
                    }
                    Text(
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .align (Alignment.CenterEnd),
                        textAlign = TextAlign.End,
                        color = Color.Gray,
                        text = characterInfo.status.statusString)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
            Text(textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                text = characterInfo.name)
            Text(textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                text = "${characterInfo.gender} | ${characterInfo.species}")
        }

    }
}