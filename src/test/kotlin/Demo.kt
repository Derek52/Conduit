
import com.conduit.engine.Node
import com.conduit.engine.Window
import com.conduit.engine.g2d.Rect2D
import com.conduit.engine.gl.*
import org.joml.Matrix4f
import org.lwjgl.opengl.GL43.glDebugMessageCallback
import org.lwjgl.opengl.GL46.GL_NEAREST
import java.io.File

class Demo : Node() {


    private var texture : Texture = Texture("unnamed.png", GL_NEAREST, GL_NEAREST)


    lateinit var shader : Shader
    var camera : Camera = Camera()
    private lateinit var batch : Batch
    private lateinit var rect2D : Rect2D


    override fun init(){
        super.init()
        rect2D = Rect2D(0f, 0f, 150f, 150f)

        glDebugMessageCallback(GLDebug(), 0)
        batch = Batch()
        this.add(batch)

        shader = Shader(File("shaders/VertexShader.glsl"), File("shaders/FragmentShader.glsl"))

        shader.vertexAttribLayout(0, 2, false, 4, 0)
        shader.vertexAttribLayout(2, 2, false, 4, 2)


        rect2D.add(shader)

        rect2D.add(texture)
        batch.add(rect2D)

        batch.add(camera)
    }


    override fun update() {

        val model = Matrix4f()

        shader.setUniformMatrix4f("model",false, model, 16)
        shader.setUniformMatrix4f("view",false, camera.view,16)
        shader.setUniformMatrix4f("projection", false, camera.projection, 16)


        //Update Children
        super.update()

    }

    override fun dispose() {
        super.dispose()
        GLError.list()
    }
}

fun main() {

    val stage = Window(300, 300, "Demo")

    stage.root = Demo()
    stage.show()
}

