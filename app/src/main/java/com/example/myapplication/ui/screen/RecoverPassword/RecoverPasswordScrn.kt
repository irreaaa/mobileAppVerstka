package com.example.myapplication.ui.screen.RecoverPassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.ui.screen.component.AuthButton
import com.example.myapplication.ui.screen.component.AuthTextField
import com.example.myapplication.ui.screen.component.TitleWithSubtitleText

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