
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import kotlinx.coroutines.MainScope
import java.net.CookieStore

@Immutable
data class MatuleColor(
    val block: Color,
    val text: Color,
    val subTextDark: Color,
    val background: Color,
    val hint: Color,
    val accent: Color
)

@Immutable
data class MatuleText(
    val headingBold32: TextStyle,
    val subTitleRegular16: TextStyle,
    val bodyRegular16: TextStyle,
    val bodyRegular14: TextStyle,
    val bodyRegular12: TextStyle,
)

val LocalMatuleTexts = staticCompositionLocalOf {
    MatuleText(
        headingBold32 = TextStyle.Default,
        subTitleRegular16 = TextStyle.Default,
        bodyRegular16 = TextStyle.Default,
        bodyRegular14 = TextStyle.Default,
        bodyRegular12 = TextStyle.Default
    )
}

val matuleFontFamily = FontFamily(
    Font(R.font.roboto_serif, FontWeight.Normal) ,
    Font(R.font.roboto_serif_bold, FontWeight.Bold),
    Font(R.font.roboto_serif_black, FontWeight.Black),
    Font(R.font.roboto_serif_medium, FontWeight.Medium),
    Font(R.font.roboto_serif_medium, FontWeight.Medium),
    Font(R.font.roboto_serif_extrabold, FontWeight.ExtraBold),
    Font(R.font.roboto_serif_semibold, FontWeight.SemiBold),
)

val LocalMatuleColor = staticCompositionLocalOf{
    MatuleColor(
        block = Color.Unspecified,
        text = Color.Unspecified,
        subTextDark = Color.Unspecified,
        background = Color.Unspecified,
        hint = Color.Unspecified,
        accent = Color.Unspecified,
    )
}

@Composable
fun MatuleTheme( content: @Composable () -> Unit){
    val colors = MatuleColor(
        block = Color(0xFFFFFFFF),
        text = Color(0xFF2B2B2B),
        subTextDark = Color(0xFF707B81),
        background = Color(0xFFF7F7F9),
        hint = Color(0xFF6A6A6A),
        accent = Color(0xFF48B2E7)
    )
    val matuleTexts = MatuleText(
        headingBold32 = TextStyle(fontFamily = matuleFontFamily, fontWeight = FontWeight.Bold, fontSize = 32.sp),
        subTitleRegular16 = TextStyle(fontFamily = matuleFontFamily, fontWeight = FontWeight.Normal, fontSize = 16.sp),
        bodyRegular16 = TextStyle(fontFamily = matuleFontFamily, fontWeight = FontWeight.Normal, fontSize = 16.sp),
        bodyRegular14 = TextStyle(fontFamily = matuleFontFamily, fontWeight = FontWeight.Normal, fontSize = 14.sp),
        bodyRegular12 = TextStyle(fontFamily = matuleFontFamily, fontWeight = FontWeight.Normal, fontSize = 12.sp),

        )
    CompositionLocalProvider (
        LocalMatuleColor provides colors,
        LocalMatuleTexts provides matuleTexts,
        content = content
    )
}

object MatuleTheme{
    val colors: MatuleColor
        @Composable
        get() = LocalMatuleColor.current
    val texts
        @Composable
        get() = LocalMatuleTexts.current
}