import com.engine.jade.Stage2D


fun main(){
    var stage = Stage2D(300, 300, "")

    var player = Player();

    stage.root = player;

    stage.show()
}