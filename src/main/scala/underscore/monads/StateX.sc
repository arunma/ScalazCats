import cats.data.State

val a = State[Int, String] { state =>
  (state, s"The start state is $state")
}

val step1 = State[Int, String]{state =>
  val ans = state + 1
  (ans, s"Result of step1 is $ans")
}

val step2 = State[Int, String]{state =>
  val ans = state * 3
  (ans, s"Result of step2 is $ans")
}

val both = for {
  s1 <- step1
  s2 <- step2
} yield (s1, s2)


a.run(10).value

both.run(20).value

both.run(20)