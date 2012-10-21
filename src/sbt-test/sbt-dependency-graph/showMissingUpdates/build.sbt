import net.virtualvoid.sbt.graph.Plugin._

graphSettings

scalaVersion := "2.9.2"

libraryDependencies +=
  "at.blub" % "blib" % "1.2.3" % "test"

TaskKey[Unit]("check") <<= (ivyReport in Test, asciiGraph in Test) map { (report, graph) =>
  def sanitize(str: String): String = str.split('\n').drop(1).mkString("\n")
  val expectedGraph =
    """default:default-91180e_2.9.2:0.1-SNAPSHOT
      |  +-at.blub:blib:1.2.3 (error: not found)
      |  +-org.scala-lang:scala-library:2.9.2
      |  """.stripMargin
  require(sanitize(graph) == sanitize(expectedGraph), "Graph for report %s was '\n%s' but should have been '\n%s'" format (report, sanitize(graph), sanitize(expectedGraph)))
  ()
}