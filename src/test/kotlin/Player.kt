import com.engine.jade.Node2D
import com.engine.jade.gl.*
import org.lwjgl.BufferUtils
import org.lwjgl.glfw.GLFW.glfwGetTime
import org.lwjgl.opengl.GL40.*
import org.lwjgl.stb.STBImage
import java.io.File
import java.lang.Math.sin
import java.nio.ByteBuffer
import java.nio.IntBuffer
import kotlin.math.sin


class Player : Node2D() {
    private lateinit var shader2d : Shader2D
    var vao : VertexArrayObject = VertexArrayObject()
    var ebo : BufferGL = ElementBufferObject()
    private lateinit var quadGL: QuadGL
    var texture : Int = 0
    override fun init(){
        super.init()
        glEnable(GL_TEXTURE_2D)
        glEnable(GL_DEPTH_TEST)
        /*quadGL = QuadGL(floatArrayOf( // positions          // colors           // texture coords
            0.5f, 0.5f, 0.0f,
            //1.0f, 1.0f,  // top right
            0.5f, -0.5f, 0.0f,
            //1.0f, 0.0f,  // bottom right
            -0.5f, -0.5f, 0.0f,
            //0.0f, 0.0f,  // bottom left
            -0.5f, 0.5f, 0.0f
            //0.0f, 1.0f // top left
        ))*/

        quadGL = QuadGL(floatArrayOf( //Subtract 0.5f from all values
            -0.5f, -0.5f, 0f,

            0.5f, -0.5f, 0f,

            0.5f, 0.5f, 0f,

            -0.5f, 0.5f, 0f
        ))

        shader2d = Shader2D(File("player.vs.shader"), File("player.fs.shader"))

        println(ErrorGL.error(glGetError()))

        vao.children.add(quadGL)

        vao.createAndBind()
        ebo.new()

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(2, 2, GL_FLOAT, false, 3 * 4, 0);

        glEnableVertexAttribArray(2);



        println(ErrorGL.error(glGetError()))

        var width : IntBuffer = BufferUtils.createIntBuffer(1);
        var height : IntBuffer = BufferUtils.createIntBuffer(1);
        var nrChannels : IntBuffer = BufferUtils.createIntBuffer(1);

        STBImage.stbi_set_flip_vertically_on_load(true)

        var image : ByteBuffer? = STBImage.stbi_load("wall.jpg", width, height, nrChannels, 4)



        texture = glGenTextures()
        glActiveTexture(GL_TEXTURE0) // activate the texture unit first before binding texture
        glBindTexture(GL_TEXTURE_2D, texture)

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT)	// set texture wrapping to GL_REPEAT (default wrapping method)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT)
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width.get(), height.get(), 0, GL_RGBA, GL_UNSIGNED_BYTE, image)
        println(ErrorGL.error(glGetError()))

        println(ErrorGL.error(glGetError()))


    }



    override fun update() {
        super.update()
        //Use our new Shader

        glBindTexture(GL_TEXTURE_2D, texture)
        shader2d.bind()
        val timeValue = glfwGetTime().toFloat()
        val greenValue: Float = (sin(timeValue) / 2.0f + 0.5f)
        val vertexColorLocation = glGetUniformLocation(shader2d.shaderProgram, "ourColor")
        glUniform4f(vertexColorLocation, 0.0f, greenValue, 0.0f, 1.0f)
        vao.bind()
        ebo.bind()

        quadGL.update()
    }

    override fun dispose() {
        super.dispose()
    }
}
