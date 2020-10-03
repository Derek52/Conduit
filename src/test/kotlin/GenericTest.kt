
import com.conduit.engine.Window
import com.conduit.engine.gl.*
import com.engine.jade.Node
import org.joml.Matrix4f
import org.lwjgl.opengl.GL46.*
import java.io.File


class TexturedQuadDemo : Node() {

    private var angle: Float = 0f
    private var texture : Texture = Texture("zim.jpg", GL_LINEAR, GL_LINEAR)

    private lateinit var renderer : Renderer
    private lateinit var mesh : Mesh

    override fun init(){
        super.init()
        texture.init()

        glDebugMessageCallback(GLDebug(), 0)

        renderer = Renderer()
        renderer.camera = Camera(300f, 300f)

        mesh = Mesh(floatArrayOf(
                100f, 100f,
                0f, 0f,
                200f, 100f,
                1f, 0f,
                200f, 200f,
                1f, 1f,
                100f, 200f,
                0f, 1f
        ))



        renderer.elementBufferObject.insert(intArrayOf(0, 1, 2, 2, 3, 0))

        val shader = Shader(File("shaders/VertexShader.glsl"), File("shaders/FragmentShader.glsl"))

        shader.vertexAttribLayout(0, 2, false, 4, 0)
        shader.vertexAttribLayout(2, 2, false, 4, 2)


        renderer.shader = shader

        mesh.children.add(texture)
        children.add(mesh)

    }

    override fun update() {
        super.update()

        renderer.update()


        var view = Matrix4f()
        //view.translate(0.0f, 0.0f, 0.0f)

        var projection = Matrix4f()

        projection.ortho(0f, 600f,0f, 400f, -1.0f, 1.0f)

        var model = Matrix4f()
        renderer.shader.setUniformMatrix4f("model",false, model, 16)
        renderer.shader.setUniformMatrix4f("view",false, view,16)
        renderer.shader.setUniformMatrix4f("projection", false, projection, 16)


        glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0)

    }

    override fun dispose() {
        super.dispose()
        renderer.dispose()
        GLError.list()
    }
}

fun main() {
    val stage = Window(600, 400, "Client")

    stage.root = TexturedQuadDemo()

    stage.show()
}

