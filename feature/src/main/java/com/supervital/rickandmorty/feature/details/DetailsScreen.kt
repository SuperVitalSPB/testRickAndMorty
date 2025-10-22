package com.supervital.rickandmorty.feature.details

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.supervital.rickandmorty.R
import com.supervital.rickandmorty.models.CharacterInfo
import com.supervital.rickandmorty.models.LocationInfo

@Composable
fun DetailsScreen(
    idCharacter: Long,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    viewModel.loadData(idCharacter)
    val characterInfo by viewModel.data.observeAsState()
    val characterLocation by viewModel.dataLocation.observeAsState()

    characterInfo?.let { characterInfo ->
        CharacterInfoScreen(characterInfo, characterLocation)
    }
}

@Composable
fun TitleDetailScreen() {
    Text(text = LocalContext.current.getString(R.string.title_detail))
}

@Composable
fun CharacterInfoScreen(
    characterInfo: CharacterInfo,
    characterLocation: LocationInfo?
) {
    var isTypeVisible by remember { mutableStateOf(true) }
    isTypeVisible = characterInfo.type.isNotEmpty()

    var isLocationVisible by remember { mutableStateOf(false) }
    isLocationVisible = (characterLocation?.id ?: 0) > 0

    Box(modifier = Modifier
        .width(160.dp)
        .height(280.dp)
    ) {
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Box {
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
                        .width(characterInfo.statusInfo.widthBox)
                        .clip(RoundedCornerShape(32.dp, 0.dp, 0.dp, 0.dp))
                        .background(color = Color.Black)
                ) {
                    Canvas(Modifier.size(50.dp)) {
                        drawCircle(
                            color = characterInfo.statusInfo.colorPunkt,
                            center = center,
                            radius = 4.dp.toPx()
                        )
                    }
                    Text(
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .align(Alignment.CenterEnd),
                        textAlign = TextAlign.End,
                        color = Color.Gray,
                        text = characterInfo.statusInfo.statusString)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
            Row(modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.wrapContentHeight(),
                    text = LocalContext.current.getString(R.string.name))
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    style = MaterialTheme.typography.titleMedium,
                    text = characterInfo.name
                )
            }

            Row(modifier = Modifier
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.wrapContentHeight(),
                    text = LocalContext.current.getString(R.string.gender))
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    style = MaterialTheme.typography.titleMedium,
                    text = characterInfo.gender
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.wrapContentHeight(),
                    text = LocalContext.current.getString(R.string.species))
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    style = MaterialTheme.typography.titleMedium,
                    text = characterInfo.species
                )
            }
            if (isTypeVisible) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.wrapContentHeight(),
                        text = LocalContext.current.getString(R.string.type)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        style = MaterialTheme.typography.titleMedium,
                        text = characterInfo.type
                    )
                }
            }
            Row(modifier = Modifier
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.wrapContentHeight(),
                    text = LocalContext.current.getString(R.string.location))
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    style = MaterialTheme.typography.titleMedium,
                    text = characterInfo.location.name
                )
            }
            if (isLocationVisible) {
                characterLocation?.let { characterLocation ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 32.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.wrapContentHeight(),
                            text = LocalContext.current.getString(R.string.type)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            style = MaterialTheme.typography.titleMedium,
                            text = characterLocation.type
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 32.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.wrapContentHeight(),
                            text = LocalContext.current.getString(R.string.dimension)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            style = MaterialTheme.typography.titleMedium,
                            text = characterLocation.dimension
                        )
                    }
                }
            }
        }
    }
}