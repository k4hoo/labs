
assume you have HBase table name "test"

```
$ sbt compile
$ sbt assembly
$ $SPARK_HOME/bin/spark-submit \
  --class "labs.HBaseTest" \
  --master local[4] \
  target/scala-2.10/Spark-Hbase-Project-assembly-1.0.jar "test"
```
