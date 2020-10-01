
import com.conduit.engine.Window
import com.conduit.engine.gl.*
import com.engine.jade.Node
import org.joml.Matrix4f
import org.joml.Vector3f
import org.lwjgl.opengl.GL46.*
import java.io.File


class TexturedQuadDemo : Node() {

    private var angle: Double = 0.0
    private var texture : Texture = Texture("unnamed.png", GL_NEAREST, GL_NEAREST)
    private lateinit var batch : RenderBatch
    private lateinit var mesh : Mesh

    override fun init(){
        super.init()
        glDebugMessageCallback(GLDebug(), 0)



        batch = RenderBatch()



        mesh = Mesh(floatArrayOf(
                    0.5f, 0.5f,
                    1f , 1f,
                    0.5f, -0.5f,
                    1f, 0f,
                    -0.5f, -0.5f,
                    0f, 0f,
                    -0.5f, 0.5f,
                    0f, 1f))



        batch.elementBufferObject.insert(intArrayOf(0, 1, 3, 1, 2, 3))

        val shader = Shader(File("shaders/vtx.glsl"), File("shaders/frg.glsl"))
        shader.vertexAttribLayout(0, 2, false, 4, 0)
        shader.vertexAttribLayout(2, 2, false, 4, 2)

        texture.init()
        batch.shader = shader

        mesh.children.add(texture)
        children.add(mesh)

    }



    override fun update() {
        super.update()

        batch.update()

        val model = Matrix4f()
        angle+=5
        model.rotate(Math.toRadians(angle).toFloat(), Vector3f(0.0f, 1.0f, 1.0f))
        val projection = Matrix4f()
        projection.ortho( -1f, 1f, -1.0f, 1.0f, -1.0f, 1.0f)

        batch.shader.setUniformMatrix4f("model",true, model, 16)
        batch.shader.setUniformMatrix4f("view",true, batch.camera.view,16)
        batch.shader.setUniformMatrix4f("projection", true, projection, 16)

        glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0)

    }

    override fun dispose() {
        super.dispose()
        batch.dispose()

        GLError.list()
    }
}

fun main() {
    val stage = Window(300, 300, "Client")

    stage.root = TexturedQuadDemo()

    stage.show()
}

