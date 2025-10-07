package com.kmx3.compose.ui.style

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.kmx3.compose.R

object Fonts {
    val ptSans = FontFamily(
        Font(R.font.pt_sans_regular, FontWeight.Normal),
        Font(R.font.pt_sans_bold, FontWeight.Bold),
    )
}