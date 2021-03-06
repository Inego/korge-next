import com.soywiz.klock.*
import com.soywiz.kmem.*
import com.soywiz.korge.*
import com.soywiz.korge.animate.*
import com.soywiz.korge.input.*
import com.soywiz.korge.tween.*
import com.soywiz.korge.view.*
import com.soywiz.korgw.awt.*
import com.soywiz.korim.color.*
import com.soywiz.korio.async.*
import com.soywiz.korio.util.*
import kotlinx.coroutines.*
import java.awt.*
import java.awt.Graphics
import javax.swing.*
import java.awt.event.*
import java.text.*
import javax.swing.border.*
import kotlin.reflect.*
import kotlin.reflect.jvm.*
import kotlin.system.*
import java.awt.Dimension

fun main() {
    val f = object : JFrame("hello") {
        override fun update(g: Graphics) {
            paint(g)
        }

        override fun paintAll(p0: Graphics?) {
        }

        override fun paint(p0: Graphics) {
        }
    }

    var color = 0f
    val canvas = object : GLCanvas() {
        //override fun render(gl: KmlGl, g: Graphics) {
        //    gl.clearColor(0f, color, 0f, 1f)
        //    color += 0.01f
        //    gl.clear(X11KmlGl.COLOR_BUFFER_BIT)
        //}
    }
    //if (true) {
    if (false) {
        f.contentPane.layout = GridLayout(1, 1)
        f.add(canvas)
    } else {
        f.contentPane.layout = GridLayout(3, 3)
        f.add(JButton("1"))
        f.add(JButton("2"))
        f.add(JButton("3"))
        f.add(JButton("4"))
        f.add(canvas)
        f.add(JButton("6"))
        f.add(JButton("7"))
        f.add(JButton("8"))
        f.add(JButton("9"))
    }
    //f.add(JButton("test"))

    f.size = Dimension(512, 512)
    f.isVisible = true
    f.setLocationRelativeTo(null)
    f.addWindowListener(object : WindowAdapter() {
        override fun windowClosing(event: WindowEvent) {
            exitProcess(0)
        }
    })

    //launchImmediately(Dispatchers.Unconfined) {
    runBlocking {
        println("[1]")
        val korge = GLCanvasKorge(canvas, 512, 512)
        println("[2]")
        launchImmediately {
            korge.executeInContext {
                val rect = solidRect(50, 50, Colors.BLUE)
                rect.mouse {
                    click {
                        println("CLICKED!")
                    }
                }
                animate {  }
                tween(rect::x[400])
                //views.gameWindow.exit()
            }
        }
        println("[3]")
        //delay(0.5.seconds)
        //korge.close()
    }

    //Timer(100) { canvas.repaint() }.start()
}
