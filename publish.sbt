publishTo <<= version { v: String =>
  if (v.trim.endsWith("SNAPSHOT"))
    Some("Indix Snapshot Artifactory" at "http://artifacts.indix.tv:8081/artifactory/libs-snapshot-local")
  else
    Some("Indix Release Artifactory" at "http://artifacts.indix.tv:8081/artifactory/libs-release-local")
}

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => true }

pomExtra :=
  Helpers.generatePomExtra("git@github.com:jrudolph/sbt-dependency-graph.git",
                           "scm:git:git@github.com:jrudolph/sbt-dependency-graph.git",
                           "jrudolph", "Johannes Rudolph")

//useGpg := true
