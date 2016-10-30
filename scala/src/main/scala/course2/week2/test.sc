package course2.week2

object test {
 val problem = new Pouring(Vector(4, 9))          //> problem  : course2.week2.Pouring = course2.week2.Pouring@b97c004
 problem.moves                                    //> res0: scala.collection.immutable.IndexedSeq[Product with Serializable with co
                                                  //| urse2.week2.test.problem.Move] = Vector(Empty(0), Empty(1), Fill(0), Fill(1),
                                                  //|  Pour(0,1), Pour(1,0))
 problem.pathSets.take(3).toList                  //> res1: List[Set[course2.week2.test.problem.Path]] = List(Set(-->Vector(0, 0))
                                                  //| , Set(Fill(0)-->Vector(4, 0), Fill(1)-->Vector(0, 9)), Set(Fill(0)Fill(1)-->
                                                  //| Vector(4, 9), Fill(0)Pour(0,1)-->Vector(0, 4), Fill(1)Fill(0)-->Vector(4, 9)
                                                  //| , Fill(1)Pour(1,0)-->Vector(4, 5)))
 problem.solutions(6)                             //> res2: Stream[course2.week2.test.problem.Path] = Stream(Fill(1)Pour(1,0)Empty
                                                  //| (0)Pour(1,0)Empty(0)Pour(1,0)Fill(1)Pour(1,0)-->Vector(4, 6), ?)
       
}