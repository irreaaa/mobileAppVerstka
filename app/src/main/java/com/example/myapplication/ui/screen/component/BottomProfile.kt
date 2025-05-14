package com.example.myapplication.ui.screen.component

import androidx.compose.material3.IconButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.Favourite
import com.example.myapplication.R
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.Home


@Composable
fun BottomProfile(navController: NavController) {
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route

    val isHomeSelected = currentRoute == Home::class.simpleName
    val isFavouriteSelected = currentRoute == Favourite::class.simpleName

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(R.drawable.niz),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(horizontalArrangement = Arrangement.spacedBy(30.dp)) {
                IconButton(
                    onClick = { navController.navigate(Home) },
                    modifier = Modifier.offset(y = 28.dp)
                ) {
                    Image(
                        painter = if (isHomeSelected) {
                            painterResource(R.drawable.home_accent)
                        } else {
                            painterResource(R.drawable.home)
                        },
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                }

                IconButton(onClick = {
                    navController.navigate(Favourite)
                }, modifier = Modifier.offset(y = 28.dp)) {
                    Image(
                        painter =
                        if (isFavouriteSelected) {
                            painterResource(R.drawable.heart_accent)
                        } else {
                            painterResource(R.drawable.heart)
                        },
                        contentDescription = null,
                        modifier = Modifier.size(33.dp),
                    )
                }
            }

            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(112.dp)
                    .offset(y = 8.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.bottom_basket),
                    contentDescription = null,
                    modifier = Modifier.size(152.dp)
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(40.dp)) {
                IconButton(onClick = { }, modifier = Modifier.offset(y = 28.dp)) {
                    Image(
                        painter = painterResource(R.drawable.notif),
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                }

                IconButton(onClick = { }, modifier = Modifier.offset(y = 28.dp)) {
                    Image(
                        painter = painterResource(R.drawable.profile),
                        contentDescription = null,
                        modifier = Modifier.size(33.dp)
                    )
                }
            }
        }
    }
}
