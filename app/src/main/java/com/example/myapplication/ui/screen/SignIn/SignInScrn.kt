@file:OptIn(ExperimentalFoundationApi::class)

package com.example.myapplication.ui.screen.SignIn

import MatuleTheme
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lint.kotlin.metadata.Visibility
import com.example.myapplication.R


@Composable
fun SignInScrn(){
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth()
                    .height(40.dp)
            ){
                IconButton(onClick = {}) {
                    Icon(painter = painterResource(R.drawable.back_arrow),
                        contentDescription = null)
                }
            }
        },
        bottomBar = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 50.dp)
                    .fillMaxWidth()
                    .height(40.dp)
            ){
                Text(
                    text = stringResource(R.string.sign_up_first),
                    style = MatuleTheme.texts.bodyRegular16.copy(color = MatuleTheme.colors.text),
                    textAlign = TextAlign.Center
                )
            }
        },
    )
    { paddingValues ->
        SignInContent(paddingValues)
    }
}

@Composable
fun SignInContent(paddingValues: PaddingValues){
    Column(
        modifier = Modifier.padding(top = 100.dp)
    )
    {
        TitleWithSubtitleText(
            title = stringResource(R.string.hello),
            subTitle = stringResource(R.string.sign_in_subtitle)
        )
        val email = remember { mutableStateOf("") }
        Spacer(modifier = Modifier.height(35.dp))
        AuthTextField(
            labelText = stringResource(R.string.email),
            placeHolderText = stringResource(R.string.template_email),
            value = email.value,
            onChangeValue = {
                email.value = it
            }
        )
        val password = remember { mutableStateOf("") }
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            PasswordTextField(
                labelText = stringResource(R.string.password),
                placeHolderText = stringResource(R.string.star_password),
                value = password.value,
                onValueChange = {
                    password.value = it
                }
            )

        }
        Button(
            onClick = {},
            modifier = Modifier.padding(top = 8.dp, start = 275.dp),
            colors = ButtonColors(
                contentColor = Color.Unspecified,
                containerColor = Color.Unspecified,
                disabledContentColor = Color.Unspecified,
                disabledContainerColor = Color.Unspecified
            ),

            ) {
            Text(text = stringResource(R.string.recover),
                style = MatuleTheme.texts.bodyRegular12.copy(color = MatuleTheme.colors.subTextDark))
        }

        CommonButton(
            modifier = Modifier.padding(top = 10.dp),
            buttonLable = stringResource(R.string.enter)
        ) {

        }
    }
}
@Composable
fun TitleWithSubtitleText(title: String, subTitle: String){
    Column (
        modifier =  Modifier.padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text =  title,
            style = MatuleTheme.texts.headingBold32.copy(color = MatuleTheme.colors.text),
            textAlign = TextAlign.Center
        )
        Text(text =  subTitle,
            maxLines = 2,
            style = MatuleTheme.texts.subTitleRegular16.copy(color = MatuleTheme.colors.subTextDark),
            textAlign = TextAlign.Center
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthTextField(value: String, onChangeValue: (String) -> Unit,placeHolderText: String? = null, labelText: String? = null){
    Column (
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)

    ){
        if(labelText != null){
            Text(text = labelText, style = MatuleTheme.texts.bodyRegular16.copy(MatuleTheme.colors.text),
                textAlign = TextAlign.Right)
        }
        val interaction = remember { MutableInteractionSource() }
        BasicTextField(
            value = value,
            onValueChange = { onChangeValue(it) },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(MatuleTheme.colors.background)
        ){
                innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = value,
                singleLine = true,
                innerTextField = innerTextField,
                enabled = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = interaction,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MatuleTheme.colors.background,
                    disabledContainerColor = MatuleTheme.colors.background,
                    unfocusedContainerColor = MatuleTheme.colors.background,
                    errorContainerColor = MatuleTheme.colors.background,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,

                    ),
                placeholder = {
                    if (placeHolderText != null)
                        Text(text = placeHolderText,
                            style = MatuleTheme.texts.bodyRegular14.copy(color = MatuleTheme.colors.hint))
                }
            )
        }
    }
}


@Composable
fun CommonButton(modifier: Modifier, buttonLable: String, onClick: ()-> Unit){
    Button(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(MatuleTheme.colors.accent),
        colors = ButtonColors(
            contentColor = MatuleTheme.colors.accent,
            disabledContentColor = Color.Transparent,
            disabledContainerColor = MatuleTheme.colors.accent,
            containerColor = Color.Transparent
        ),
        onClick = onClick
    ) {
        Text(
            text = buttonLable,
            style = MatuleTheme.texts.bodyRegular14.copy(color = MatuleTheme.colors.background),
            textAlign = TextAlign.Center

        )
    }
}


@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeHolderText: String? = null,
    labelText: String? = null
) {
    var showPassword by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (labelText != null) {
            Text(
                text = labelText,
                style = MatuleTheme.texts.bodyRegular16.copy(MatuleTheme.colors.text),
                textAlign = TextAlign.Start
            )
        }

        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(end = 8.dp),
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            interactionSource = remember { MutableInteractionSource() },
            placeholder = {
                if (placeHolderText != null) {
                    Text(
                        text = placeHolderText,
                        style = MatuleTheme.texts.bodyRegular14.copy(color = MatuleTheme.colors.hint)
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        imageVector = if (showPassword) {
                            Icons.Filled.Visibility
                        } else {
                            Icons.Filled.VisibilityOff
                        },
                        contentDescription = "Переключить видимость пароля"
                    )
                }
            },
            shape = RoundedCornerShape(14.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MatuleTheme.colors.background,
                unfocusedContainerColor = MatuleTheme.colors.background,
                disabledContainerColor = MatuleTheme.colors.background,
                errorContainerColor = MatuleTheme.colors.background,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
            isError = false
        )
    }
}