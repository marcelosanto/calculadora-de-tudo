import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.developermarcelo.calculadora.common.App


fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CALCULADORA DE TUDO",
        resizable = false
        //icon = Icon(painter = painterResource(useResource()), contentDescription = null)
    ) {
        App()
    }
}
