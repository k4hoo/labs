import AssemblyKeys._

assemblySettings

name := "Spark-Hbase-Project"

version := "1.0"

scalaVersion := "2.10.4"


libraryDependencies ++= Seq(
  "org.apache.hadoop"       % "hadoop-common"   % "2.5.0",
  "org.apache.hbase"        % "hbase-common"    % "0.98.5-hadoop2",
  "org.apache.hbase"        % "hbase-client"    % "0.98.5-hadoop2",
  ("org.apache.hbase" % "hbase-server" % "0.98.5-hadoop2").
    exclude("org.mortbay.jetty", "servlet-api-2.5").
    exclude("org.mortbay.jetty", "jsp-api-2.1").
    exclude("org.mortbay.jetty", "jsp-2.1"),
  "org.apache.spark" %% "spark-core" % "1.1.0" % "provided"
)

excludedJars in assembly <<= (fullClasspath in assembly) map { cp =>
  cp filter { Seq(
    "asm-3.1.jar",
    "commons-beanutils-1.7.0.jar",
    "commons-collections-3.2.1.jar") contains _.data.getName
  }
}

//exclude("org.mortbay.jetty", "servlet-api").
//exclude("org.mortbay.jetty", "jsp-api-2.1").
//exclude("commons-beanutils", "commons-beanutils-core").
//exclude("commons-collections", "commons-collections").
//exclude("commons-collections", "commons-collections").
//exclude("com.esotericsoftware.minlog", "minlog")

//exclude("org.eclipse.jetty", "jetty-server"),
//exclude("org.eclipse.jetty.orbit", "javax.servlet")
//exclude("org.eclipse.jetty.orbit", "javax.transaction")
//exclude("org.eclipse.jetty.orbit", "javax.mail")
//exclude("org.eclipse.jetty.orbit", "javax.activation")
//exclude("org.eclipse.jetty", "jetty-server"),

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case PathList("scala", "reflect", "api", xs @ _*) => MergeStrategy.last
    case PathList("org", "xmlpull", xs @ _*) => MergeStrategy.last
    case x => old(x)
  }
}


net.virtualvoid.sbt.graph.Plugin.graphSettings
