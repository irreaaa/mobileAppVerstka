package com.example.myapplication.ui.screen.Popular

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.Favourite
import com.example.myapplication.R
import com.example.myapplication.ui.data.remote.NetworkResponseSneakers
import com.example.myapplication.ui.data.remote.dto.response.SneakersResponse
import com.example.myapplication.ui.screen.Home.ProductItem
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularScrn(navController: NavController, viewModel: PopularViewModel = koinViewModel()) {

    val sneakersState by viewModel.sneakersState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchSneakersByCategory("Популярное")
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Популярное",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.back_arrow),
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { navController.navigate(route = Favourite) },
                        modifier = Modifier
                            .size(36.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.heart),
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.padding(6.dp)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        when (sneakersState) {
            is NetworkResponseSneakers.Success -> {
                PopularContent(
                    sneakers = (sneakersState as NetworkResponseSneakers.Success).data,
                    onFavoriteClick = viewModel::toggleFavorite,
                    onAddToCart = {},
                    navController = navController,
                    modifier = Modifier.padding(paddingValues)
                )
            }
            is NetworkResponseSneakers.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is NetworkResponseSneakers.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Ошибка загрузки")
                }
            }
        }
    }
}

@Composable
fun PopularContent(
    sneakers: List<SneakersResponse>,
    onFavoriteClick: (Int, Boolean) -> Unit,
    onAddToCart: (SneakersResponse) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(sneakers) { sneaker ->
            ProductItem(
                sneaker = sneaker,
                //onItemClick = {  },
                onFavoriteClick = onFavoriteClick,
                onAddToCart = { onAddToCart(sneaker) },
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.85f)
            )
        }
    }
}