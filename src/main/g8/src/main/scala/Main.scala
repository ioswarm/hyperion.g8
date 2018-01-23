import akka.util.Timeout
import de.ioswarm.hyperion.Hyperion

import scala.concurrent.Await
import scala.concurrent.duration._

object Main extends App {

  import de.ioswarm.hyperion.Implicits._

  System.setProperty("java.library.path", "./target/native")

  val hy = Hyperion("$name$")

  hy.start("example" receive { ctx => {
    case s: String => ctx.sender() ! s.toUpperCase()
  }} route { ref =>
    import akka.http.scaladsl.server.Directives._
    import akka.http.scaladsl.model.StatusCodes._
    import akka.pattern.ask

    implicit val timeout: Timeout = Timeout(1.second)

    pathPrefix("$name$" / Segment) { s =>
      get {
        onSuccess(ref ? s) {
          case s: String => complete(s)
          case _ => complete(NotFound)
        }
      }
    }
  }
  )

  Await.result(hy.whenTerminated, Duration.Inf)

}
