package macros

import scala.reflect.macros.blackbox.Context
import scala.language.experimental.macros

/**
  * Created by vishnu on 11/19/16.
  */
object MaximumMacro {

  def maximum(a: Int, b: Int): Int =
    macro maximumMacro

  def maximumMacro(c: Context)(a: c.Tree, b: c.Tree): c.Tree = {
    import c.universe._
    q"if ($a > $b) $a else $b"
  }

}
