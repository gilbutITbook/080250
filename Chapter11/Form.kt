import java.awt.BorderLayout
import java.awt.Component
import java.awt.Container
import java.awt.Dimension
import javax.swing.*
import kotlin.properties.Delegates.observable

@DslMarker
annotation class LayoutDsl

fun label(text: String) = JLabel(text)
fun button(text: String) = JButton(text)
fun textArea(text: String) = JTextArea(text)

@LayoutDsl
class ContainerBuilder(private val container: Container) {
    operator fun Component.unaryPlus() = apply { container.add(this) }

    fun borderLayout(body: BorderLayoutBuilder.() -> Unit) {
        BorderLayoutBuilder(container).body()
    }

    fun horizontalBoxLayout(body: BoxLayoutBuilder.() -> Unit) {
        BoxLayoutBuilder(container, BoxLayout.LINE_AXIS).body()
    }

    fun verticalBoxLayout(body: BoxLayoutBuilder.() -> Unit) {
        BoxLayoutBuilder(container, BoxLayout.PAGE_AXIS).body()
    }
}

fun panel(body: ContainerBuilder.() -> Unit) = JPanel().apply {
    ContainerBuilder(this).body()
}

fun dialog(
    title: String,
    body: ContainerBuilder.() -> Unit
): JDialog = JDialog().apply {
    this.title = title
    pack()
    defaultCloseOperation = JDialog.DISPOSE_ON_CLOSE
    ContainerBuilder(contentPane).body()
}

@LayoutDsl
class BoxLayoutBuilder(private val container: Container, direction: Int) {
    init {
        container.layout = BoxLayout(container, direction)
    }

    operator fun Component.unaryPlus() = apply { container.add(this) }

    fun filler(width: Int, height: Int) =
        Box.createRigidArea(Dimension(width, height))
}

fun constrained(
    container: Container,
    constraint: Any?
) = observable<Component?>(null) { _, _, value ->
    container.add(value, constraint)
}

@LayoutDsl
class BorderLayoutBuilder(container: Container) {
    init {
        container.layout = BorderLayout()
    }

    var north by constrained(container, BorderLayout.NORTH)
    var south by constrained(container, BorderLayout.SOUTH)
    var west by constrained(container, BorderLayout.WEST)
    var east by constrained(container, BorderLayout.EAST)
    var center by constrained(container, BorderLayout.CENTER)
}

fun main() {
    val form = dialog("Send a message") {
        borderLayout {
            south = panel {
                +button("Send")
                +button("Cancel")
            }
            center = panel {
                verticalBoxLayout {
                    +filler(0, 10)
                    +panel {
                        horizontalBoxLayout {
                            +filler(5, 0)
                            +label("Message: ")
                            +filler(10, 0)
                            +textArea("")
                            +filler(5, 0)
                        }
                    }
                    +filler(0, 10)
                }
            }
        }
    }
    form.size = Dimension(300, 200)
    form.isVisible = true
}
