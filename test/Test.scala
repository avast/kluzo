import com.avast.logger.slf4j.TrackingLoggerFactory
import com.avast.logger.RequestId


implicit val id = RequestId("jenda")

val logger = TrackingLoggerFactory.getLogger(getClass)

for (i <- 1 to 3) {
  logger.info(s"Hello $i")
  logger.info("Hi", new Exception(i.toString))
}

