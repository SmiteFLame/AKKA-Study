package com.scala.code.monad

object MonadsForBeginners {
  case class SafeValue[+T](private val internalValue: T) {
    def get: T = synchronized {
      // does something interesting
      internalValue
    }

    def transform[S](transformer: T => SafeValue[S]): SafeValue[S] = synchronized {
      transformer(internalValue)
    }
  }

  // "external" API
  def gimmeSafeValue[T](value: T): SafeValue[T] = SafeValue(value)

  val safeString: SafeValue[String] = gimmeSafeValue("Scala")
  // extract
  val string = safeString.get
  // transform
  val upperString = string.toUpperCase()
  // wrap
  val upperSafeString = SafeValue(upperString)
  // ETW -> extract transform wrap
  val upperSafeString2 = safeString.transform(s => SafeValue(s.toUpperCase()))
}
