import com.avast.kluzo.TraceId
import com.avast.kluzo.logger._

implicit val id = TraceId.create("jenda")

val logger = TracingLoggerFactory.getLogger(getClass)

for (i <- 1 to 3) {
  logger.info(s"Hello $i")
  logger.info("Hi", new Exception(i.toString))
}

