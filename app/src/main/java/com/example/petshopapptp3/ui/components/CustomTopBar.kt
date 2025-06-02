package com.example.petshopapptp3.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petshopapptp3.ui.theme.Poppins
import androidx.compose.foundation.Image
import androidx.compose.ui.draw.shadow
import com.example.petshopapptp3.R
import androidx.compose.ui.res.painterResource



@Composable
fun CustomTopBar(
    title: String,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp)
            .height(46.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .size(56.dp)
                .width(46.dp)
                .height(46.dp)
                .align(Alignment.TopStart)
                .offset(x = 24.dp)

        ) {
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "Back",
                modifier = Modifier.size(56.dp)
                    .width(46.dp)
                    .height(46.dp)
            )
        }


    }
}

