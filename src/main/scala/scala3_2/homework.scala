package scala3_2


object homework1 {
  extension (x: String)
    def ++ (y: String): Int = (x + y).toInt

  @main def part1Ex(): Unit ={
    println("1" ++ "33")
  }
}

object homework2 {
  enum CompletionArg {
    case StringArg(s: String)
    case IntArg(i: Int)
    case FloatArg(f: Float)
  }

  object CompletionArg {
    given fromString: Conversion[String, CompletionArg] = StringArg(_)

    given fromInt: Conversion[Int, CompletionArg] = IntArg(_)

    given fromFloat: Conversion[Float, CompletionArg] = FloatArg(_)
  }
  import CompletionArg.*

  def complete(arg: CompletionArg) = arg match
    case StringArg(s) => s"String: $s"
    case IntArg(i) => s"Int: $i"
    case FloatArg(f) => s"Float: $f"

  @main def part2Ex(): Unit = {
    println(complete("String"))
    println(complete(1))
    println(complete(7f))
  }
}


object homework3 {
  opaque type Logarithm = Double

  object Logarithm{
    def apply(d: Double): Logarithm = math.log(d)
    def safe(d: Double): Option[Logarithm] = if d > 0.0 then Some(math.log(d)) else None
  }

  extension (x: Logarithm)
    def toDouble: Logarithm = math.exp(x)
    def + (y: Logarithm): Logarithm = Logarithm(math.exp(x) + math.exp(y))
    def * (y: Logarithm): Logarithm = x + y
}

@main def part3Ex(): Unit = {
  import homework3.Logarithm

  val l = Logarithm(1.0)
  val l2 = Logarithm(2.0)
  val l3 = l * l2
  val l4 = l + l2
  println(l3)
  println(l4)
}