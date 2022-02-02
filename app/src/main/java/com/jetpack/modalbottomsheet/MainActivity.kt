package com.jetpack.modalbottomsheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jetpack.modalbottomsheet.ui.theme.ModalBottomSheetTheme
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ModalBottomSheetTheme {
                Surface(color = MaterialTheme.colors.background) {
                    //Status and Navigation Bar color
                    val systemUiController = rememberSystemUiController()
                    systemUiController.setStatusBarColor(Color.Red)
                    systemUiController.setNavigationBarColor(Color.Red)

                    ModalBottomSheet()
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun ModalBottomSheet() {
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    ModalBottomSheetLayout(
        sheetContent = {
            Box(
                modifier = Modifier
                    .navigationBarsWithImePadding()
                    .height(200.dp)
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Bottom Sheet",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "Item 1",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Text(
                        text = "Item 2",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Text(
                        text = "Item 3",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
            }
        },
        sheetState = sheetState,
        sheetBackgroundColor = Color.White
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Modal Bottom Sheet",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                )
            }
        ) {
            MainContent(
                onClick = {
                    coroutineScope.launch {
                        sheetState.show()
                    }
                },
                modifier = Modifier.padding(it)
            )
        }
    }
}

@Composable
fun MainContent(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Click Me!",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6.copy(color = Color.White)
            )
        }
    }
}


























