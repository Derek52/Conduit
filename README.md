# Conduit
Conduit is a simple 2D game engine written in Kotlin. It uses OpenGL as it's backend and currently only works on Desktop.
It's named after a mysterious artifact in Xenoblade Chronicles 2 that opens portals to new dimensions.

# Features:

- Custom Shaders

- Built-in easy to use ECS.

- Hardware-Accelerated

# Dependencies: 

- JOML for Matrix Math.

- LWJGL for OpenGL bindings.

# Project Info

The Project uses Gradle as it's build system, so basic knowledge of that is probably a must. 
It uses OpenGL 3.x so you'll need knowledge of Shaders, Matrices, Buffers, Textures, Perspectives
So on. There's plenty of bad code all around which I'm working to fix. 

Everything in the code is a Node. Nodes have other nodes. Stored horribly in a child node list. 
Each node updates their children with the update() methods. Any new children automatically get 
updated.



# Roadmap

I want this project to be what libGDX really wasn't. I'm not hating on libGDX by the way, don't get me wrong,
but over the years it's just gotten bigger and bigger and bigger. Extensions for everything. Physics? Box2D.
 ECS? Ashley. 2D Lighting? Box2D-Lights. Tilemap Editor? Tiled. Generic Level Editor? Overlap2D. 
 
 Get it? I think a game engine should be more than just a renderer. ECS, Physics? Editor? All those
 should be encapsulated. 
 
# 

I don't have enough time to work on this. Especially with School, I'm only working on this on Weekends.

If you'd like to help, or have any questions, shoot me a message at stacksite8@gmail.com

In the mean time check out the Demo.kt example to see the working code.

I'll try to get back to you soon. 


