package demomacro

import scala.reflect.api.Symbols
import scala.language.experimental.macros
import scala.reflect.macros.blackbox.Context

object Macro {

  def printFound: String = macro doItImpl

  def doItImpl(c: Context) = {
    import c.universe._

    val bazIsPresent =
      try {
        java.lang.Class.forName("testlib.Baz")
        "is in Classpath"
      } catch {
        case err: Throwable =>
          //err.printStackTrace
          "it is NOT"
      }

    val fooIsPresent =
      try {
        java.lang.Class.forName("demo.Foo")
        "is in Classpath"
      } catch {
        case err: Throwable =>
          //err.printStackTrace
          "it is NOT"
      }

    val str = s"let see baz -> $bazIsPresent | foo -> $fooIsPresent"

    c.Expr[String](q"""
      $str
      """
    )
  }

}
