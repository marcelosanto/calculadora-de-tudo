import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.developermarcelo.calculadora.common.App


fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = WindowState(size = DpSize(Dp(500F), Dp(500F))),
        title = "CALCULADORA DE TUDO",
        resizable = false
        //icon = Icon(painter = painterResource(useResource()), contentDescription = null)
    ) {
        App()
    }
}
