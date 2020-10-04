
import com.conduit.engine.Node
import com.conduit.engine.Window
import com.conduit.engine.gl.*
import org.joml.Matrix4f
import org.lwjgl.opengl.GL46.*
import java.io.File


class ConduitDemo : Node() {

    private var texture : Texture = Texture("zim.jpg", GL_LINEAR, GL_LINEAR)

    private lateinit var mesh : Mesh
    lateinit var shader : Shader
    var camera : Camera = Camera()
    private lateinit var batch : Batch

    override fun init(){
        super.init()
        texture.init()
        glDebugMessageCallback(GLDebug(), 0)
        batch = Batch()
        this.add(batch)

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


        shader = Shader(File("shaders/VertexShader.glsl"), File("shaders/FragmentShader.glsl"))
        shader.vertexAttribLayout(0, 2, false, 4, 0)
        shader.vertexAttribLayout(2, 2, false, 4, 2)
        mesh.add(texture)


        batch.add(mesh)
        batch.add(camera)
        batch.add(shader)

    }

    override fun update() {
        super.update()


        batch.update()


        var model = Matrix4f()

        //model.translate(x--, y--, 0f)

        shader.setUniformMatrix4f("model",false, model, 16)
        shader.setUniformMatrix4f("view",false, camera.view,16)
        shader.setUniformMatrix4f("projection", false, camera.projection, 16)


        glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0)

    }

    override fun dispose() {
        super.dispose()
        GLError.list()
    }
}

fun main() {
    val stage = Window(300, 300, "ConduitDemo")

    stage.root = ConduitDemo()

    stage.show()
}

