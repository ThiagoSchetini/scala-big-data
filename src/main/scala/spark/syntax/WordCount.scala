package spark.syntax


import org.apache.spark._


class IOLayer(var inputPath: String, var outputPath:String, args:Array[String]) {
  if (args.length == 2) {
    inputPath = args(0)
    outputPath = args(1)
  }
}

object WordCount extends App {

  val io = new IOLayer(
    "src/test/scala/mock/read.txt",
    s"src/test/scala/mock/write${String.valueOf(math.random).replace(".", "")}",
    args)

  val conf = new SparkConf().setAppName("wordCount").setMaster("local[*]")
  val sc = new SparkContext(conf)

  val in = sc.textFile(io.inputPath)
  val words = in.flatMap(_.split(" "))
  val reduced = words.map((_, 1)).reduceByKey(_ + _)
  reduced.saveAsTextFile(io.outputPath)

}
