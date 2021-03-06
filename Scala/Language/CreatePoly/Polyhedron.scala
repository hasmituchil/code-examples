// Copyright 2013 Sean McKenna
// 
//    Licensed under the Apache License, Version 2.0 (the "License");
//    you may not use this file except in compliance with the License.
//    You may obtain a copy of the License at
// 
//        http://www.apache.org/licenses/LICENSE-2.0
// 
//    Unless required by applicable law or agreed to in writing, software
//    distributed under the License is distributed on an "AS IS" BASIS,
//    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//    See the License for the specific language governing permissions and
//    limitations under the License.
//

// creates a polyhedron from many triangles
// works with CreatePoly.scala
// not intended to be used alone

class Polyhedron(){
  
  // define points to start polyhedron
  val north = new Point(0.0, 1.0, 0.0)
  val east = new Point(1.0, 0.0, 0.0)
  val south = new Point(0.0, -1.0, 0.0)
  val west = new Point(-1.0, 0.0, 0.0)
  val near = new Point(0.0, 0.0, 1.0)
  
  // create faces of octahedron
  val northeast = new Triangle(north, east, near)
  val southeast = new Triangle(east, south, near)
  val southwest = new Triangle(near, south, west)
  val northwest = new Triangle(north, near, west)
  
  // store all faces in list
  var faces = List(northeast, southeast, southwest, northwest)
  
  // divide a triangular face into four smaller ones
  def subdivide(){
    var smallerFaces = List[Triangle]()
    for(i<-faces){
      smallerFaces = smallerFaces ++ i.subdivide()
    }
    faces = smallerFaces
  }
  
  // describe polyhedron in HTML with SVG
  def html(): String = {
    val rad = 256
    
    // grab all triangle code into one string
    var edges = ""
    for(i<-faces){
      edges += i.svg(rad/2)
      edges += "\n"
    }
    
    // generate output page (HTML)
    val output = "<html> \n <body> \n <h1> A Polyhedron </h1> \n <svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" height=\"" + (2*rad).toString() + "\" width=\"" + (2*rad).toString() + "\"> \n" + edges + "\n </svg> \n </body> \n </html>"
    output
  }
}
