package com.example.gameapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun CustomOutlinedTextField(
    editValue: String,
    editValueChange: (String) -> Unit,
    titleLabel: String
) {
    OutlinedTextField(
        value = editValue,
        onValueChange = editValueChange,
        label = { Text(titleLabel) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    )
}