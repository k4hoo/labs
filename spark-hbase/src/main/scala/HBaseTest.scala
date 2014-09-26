
package labs

import org.apache.hadoop.hbase.client.HBaseAdmin
import org.apache.hadoop.hbase.{HBaseConfiguration, HTableDescriptor}
import org.apache.hadoop.hbase.mapreduce.TableInputFormat

import org.apache.spark._

object HBaseTest {

  def main(args: Array[String]) {

    val sparkConf = new SparkConf().setAppName("HBaseTest")
    val sc = new SparkContext(sparkConf)
    val conf = HBaseConfiguration.create()

    conf.set(TableInputFormat.INPUT_TABLE, args(0))

    val hBaseRDD = sc.newAPIHadoopRDD(conf, classOf[TableInputFormat],
      classOf[org.apache.hadoop.hbase.io.ImmutableBytesWritable],
      classOf[org.apache.hadoop.hbase.client.Result])

    val c = hBaseRDD.count()
    println(s"Number of row: ${c}")

    sc.stop()
  }
}
