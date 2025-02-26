package com.example.myapplication.ui.screen.SignIn

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.ui.screen.SignIn.component.AuthButton
import com.example.myapplication.ui.screen.SignIn.component.AuthTextField
import com.example.myapplication.ui.screen.SignIn.component.TitleWithSubtitleText
import com.example.myapplication.ui.theme.MatuleTheme

@Composable
fun RecoverPasswordScrn(){
    val recoverPasswordViewModel: RecoverPasswordViewModel = viewModel()
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth()
                    .height(40.dp)
            )
            {
                IconButton(onClick = {}) {
                    Icon(painter = painterResource(R.drawable.back_arrow),
                        contentDescription = null)
                }
            }
        },
    )
    {
        paddingValues ->
        RecoverPasswordContent(paddingValues, recoverPasswordViewModel)
    }
}
@Composable
fun RecoverPasswordContent(
    paddingValues: PaddingValues,
    recoverPasswordViewModel: RecoverPasswordViewModel
){
    val recoverPasswordState = recoverPasswordViewModel.recoverPasswordState
    Column(
        modifier = Modifier.padding(top = 100.dp)
    )
    {
        TitleWithSubtitleText(
            title = stringResource(R.string.miss_pass),
            subTitle = stringResource(R.string.enter_your_email)
        )

        Spacer(
            modifier = Modifier.height(35.dp)
        )
        AuthTextField(
            value = recoverPasswordState.value.email,
            onChangeValue = {
                recoverPasswordViewModel.setEmail(it)
            },
            isError = recoverPasswordViewModel.emailHasError.value,
            placeholder = {
                Text(text = stringResource(R.string.template_email))
            },
            supportingText = {
                Text(text = stringResource(R.string.incorrect_email))
            },
            label = {
                Text(text = stringResource(R.string.email))
            }
        )
        AuthButton(
            onClick = {}
        ) {
            Text(stringResource(R.string.recover))
        }
    }
}
