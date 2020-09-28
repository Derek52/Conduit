import com.conduit.engine.gl.*
import com.engine.jade.Node
import com.engine.jade.Stage
import org.joml.Matrix4f
import org.joml.Vector3f
import org.lwjgl.opengl.GL46.*
import java.io.File


class Player : Node() {
    private var angle: Float = 0f
    private var texture : Texture = Texture("samurai.jpg", GL_LINEAR, GL_LINEAR)
    private lateinit var batch : RenderBatch
    private lateinit var mesh : Mesh

    override fun init(){
        super.init()
        glDebugMessageCallback(DebugImpl(), 0)



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

        var shader = Shader(File("shaders/vtx.glsl"), File("shaders/frg.glsl"))
        shader.vertexAttribLayout(0, 2, false, 4, 0)
        shader.vertexAttribLayout(2, 2, false, 4, 2)

        texture.init()
        batch.shader = shader

        children.add(texture)
        children.add(mesh)

    }



    override fun update() {
        super.update()

        batch.update()

        angle+=1f

        var model = Matrix4f()

        model.rotate(Math.toRadians((-angle).toDouble()).toFloat(), Vector3f(1.0f, 1.0f, 0.0f))

        var projection = Matrix4f()
        projection.ortho( -1f, 1f, -1.0f, 1.0f, -1.0f, 1.0f)
        batch.shader.setUniformMatrix4f("model",false, model, 16)
        batch.shader.setUniformMatrix4f("view",true, batch.camera.view,16)
        batch.shader.setUniformMatrix4f("projection", true, projection, 16)

        glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0)

    }

    override fun dispose() {
        super.dispose()
        batch.shader.dispose()
        GLError.list()
    }
}

fun main() {
    var stage = Stage(300, 300, "Client")

    stage.root = Player()

    stage.show()
}

