import com.google.inject.AbstractModule
import services._

class Module extends AbstractModule {

  override def configure() = {
    bind(classOf[TodoService]).to(classOf[TodoServiceImpl])
  }
}
