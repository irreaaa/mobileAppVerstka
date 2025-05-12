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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.R


@Composable
fun BottomProfile(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(R.drawable.niz),
            contentDescription = "",
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
                IconButton(onClick = { }, modifier = Modifier.offset(y = 28.dp)) {
                    Image(
                        painter = painterResource(R.drawable.home),
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                    )
                }

                IconButton(onClick = { }, modifier = Modifier.offset(y = 28.dp)) {
                    Image(
                        painter = painterResource(R.drawable.heart),
                        contentDescription = "",
                        modifier = Modifier.size(33.dp)
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
                    contentDescription = "",
                    modifier = Modifier.size(152.dp)
                )
            }


            Row(horizontalArrangement = Arrangement.spacedBy(40.dp)) {
                IconButton(onClick = { }, modifier = Modifier.offset(y = 28.dp)) {
                    Image(
                        painter = painterResource(R.drawable.notif),
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                    )
                }

                IconButton(onClick = { }, modifier = Modifier.offset(y = 28.dp)) {
                    Image(
                        painter = painterResource(R.drawable.profile),
                        contentDescription = "",
                        modifier = Modifier.size(33.dp)
                    )
                }

            }
        }
    }
}