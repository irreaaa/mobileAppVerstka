package com.example.myapplication.ui.screen.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.Listing
import com.example.myapplication.Popular
import com.example.myapplication.R
import com.example.myapplication.ui.data.remote.NetworkResponseSneakers
import com.example.myapplication.ui.data.remote.dto.response.SneakersResponse
import com.example.myapplication.ui.screen.Popular.PopularViewModel
import com.example.myapplication.ui.screen.component.BottomProfile
import com.example.myapplication.ui.screen.component.TopPanel
import com.example.myapplication.ui.theme.MatuleTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val popularViewModel: PopularViewModel = koinViewModel<PopularViewModel>()

    Scaffold(
        topBar = {
            TopPanel(title = "Главная",
                menuImage = painterResource(R.drawable.menu),
                basketImage = painterResource(R.drawable.top_baskett),
                textSize = 40
            )
        },
        bottomBar = { BottomProfile(navController) }
    ){
            paddingValues -> HomeScreenContent(paddingValues, popularViewModel, navController)
    }
}

@Composable
fun HomeScreenContent(paddingValues: PaddingValues, viewModel: PopularViewModel, navController: NavController) {
    val message = remember { mutableStateOf("") }
    val sneakersState by viewModel.sneakersState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.fetchSneakersByCategory("Популярное")
    }


    Column(
        modifier = Modifier
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 22.dp, vertical = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 16.dp, bottom = 24.dp),
                value = message.value,
                onValueChange = { message.value = it },
                placeholder = { Text("Поиск") },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.finder),
                        contentDescription = null,
                        Modifier.size(20.dp)
                    )
                },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MatuleTheme.colors.block,
                    unfocusedContainerColor = MatuleTheme.colors.background,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(onClick = { }) {
                Image(
                    painter = painterResource(R.drawable.adjuster),
                    contentDescription = "",
                    modifier = Modifier.size(100.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(2.dp))

        Column(modifier = Modifier.padding(start = 4.dp)) {
            Text(
                text = "Категории",
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 15.dp)
            )

            LazyRow {
                items(listOf("Все", "Outdoor", "Tennis")) { category ->
                    Button(
                        onClick = {
                            navController.currentBackStackEntry?.savedStateHandle?.set("selectedCategory", category)
                            navController.navigate(route = Listing)
                        },
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .height(40.dp)
                            .width(115.dp),
                        shape = RoundedCornerShape(7.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(text = category, fontSize = 15.sp, fontWeight = FontWeight.Light)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        when (sneakersState) {
            is NetworkResponseSneakers.Success -> {
                val sneakers = (sneakersState as NetworkResponseSneakers.Success<List<SneakersResponse>>).data
                Column(
                    modifier = Modifier.padding(start = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Популярное",
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(bottom = 15.dp)
                        )
                        Text(
                            text = "Все",
                            modifier = Modifier.clickable {
                                navController.navigate(route = Popular)
                            },
                            fontSize = 12.sp,
                            color = MatuleTheme.colors.accent
                        )
                    }

                    LazyRow(
                        modifier = Modifier.padding(top = 2.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(sneakers) { sneaker ->
                            ProductItem(
                                sneaker = sneaker,
//                                onItemClick = {
//                                    //navController.navigate("details/${sneaker.id}")
//                                },
                                onFavoriteClick = { id, isFavorite ->
                                    viewModel.toggleFavorite(id, isFavorite)
                                },
                                onAddToCart = {

                                },
                                modifier = Modifier.width(160.dp)
                            )
                        }
                    }
                }
            }
            is NetworkResponseSneakers.Error -> {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Ошибка загрузки",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            is NetworkResponseSneakers.Loading -> {
                Box(modifier = Modifier.fillMaxWidth()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Column(modifier = Modifier.padding(bottom = 30.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Акции",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "Все",
                    fontSize = 12.sp,
                    color = MatuleTheme.colors.accent,
                )
            }

            Box(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .height(120.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.sale),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                )
            }
        }
    }
}