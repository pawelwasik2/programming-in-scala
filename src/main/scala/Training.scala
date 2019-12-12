object Training extends App{
  //instead of for in for loops
  //(1 to 3).foreach(i => (1 to 3).foreach(j => println( i, j)))

  //1 + 2 invoke method named +, originally its (1).+(2)
  //the same as in for loop syntax like: for(i <- 1 to 2) equal to for(i <- (1).to(2))
  //summary: if a method takes only one parameter you can call it without dot or parentheses
  //println(1 + 2)
  //println((1).+(2))

  //So greetStrings(i) gets transformed into greetStrings.apply(i)
  //val arr = new Array[String](3)
  //arr(0) = "Zero" //equals to arr.update(0, "Zero")
  //print(arr(0)) //equals to print(arr.apply(0))

  /*
  val oneTwoThreeFour = oneTwo ::: threeFour
  println(oneTwo + " and " + threeFour + " were not mutated.")
  println("Thus, " + oneTwoThreeFour + " is a new list.")

  val twoThree = List(2, 3)
  val oneTwoThree = 1 :: twoThree
  println(oneTwoThree)
  println(twoThree)

  ::: - add list to each other
  :: - "cons" - add new element to the beggining of List (above example)

  is actually a simple rule to
  remember: If a method is used in operator notation, such as a * b, the method is invoked on the left
  operand, as in a.*(b)—unless the method name ends in a colon. If the method name ends in a colon, the
  method is invoked on the right operand.


  Use the new keyword when you want to refer to a class's own constructor:
  class Foo { }
  val f = new Foo

  Omit new if you are referring to the companion object's apply method:
  class Foo { }
  object Foo {
    def apply() = new Foo
  }

  // Both of these are legal
  val f = Foo()
  val f2 = new Foo

  ***PL w skrocie, new uzywamy gdy chcemy odwolac sie do konstruktora danej klasy, natomiast bez new
        musimy stworzyc object w danej klasie (chyba taka sama nazwa) a w nim metode apply, ktora zachowauje sie
        jak konstruktor, wtedy przy tworzeniu objektu wlasnie ta funkcja apply bedzie wywolana

  PAGE 48 - usefull LIST's methods

  Tuples:
  val pair = (99, "Luftballons")
  println(pair._1)
  println(pair._2)

  //todo sets?????? maps ????? hashset?
  var jetSet = Set("Boeing", "Airbus")
  jetSet += "Lear"
  println(jetSet.contains("Cessna"))

  //todo trait? set and hashset difference?

   */

  /*
  // oki tutaj jest cos takiego, ze przy val immutable nie mozna dac += a przy val mutable mozna

  import scala.collection.immutable

  val movieSet = immutable.Set("Hitch", "Poltergeist")
  movieSet += "Shrek"
  println(movieSet)*/

  //https://4programmers.net/Forum/Inne/333704-scala_niezrozumienie_pewnej_kwestii_w_kolekcjach_set

  /**
    Ogolnie to kwestia setow i hashsetow sie troche wyjasnila na tym forum, .getClass zwraca Set$Set4 na <5 a >=5 HashSet
    Kiedy indziej ogarne

    Mozna powiedziec ze Set to taki glowny typ, a HashSet czy Set$Set3 to wynik metody .toString wywalonej na obiekcie typu Set
    Nie wiem do konca o co dokladnie chodzi, pewnie ogarne za jakis czas
  */

  /*
  import scala.io.Source

   def widthOfLength(s: String) = s.length.toString.length

  if (args.length > 0) {

  val lines = Source.fromFile(args(0)).getLines().toList

  val longestLine = lines.reduceLeft(
  (a, b) => if (a.length > b.length) a else b
   )
  val maxWidth = widthOfLength(longestLine)

  for (line <- lines) {
   val numSpaces = maxWidth - widthOfLength(line)
  val padding = " " * numSpaces
   println(padding + line.length + " | " + line)
   }
   }
   else
  Console.err.println("Please enter filename")
    */

  //PROGRAMOWANIE FUNKCYJNE:
  //Prefer vals, immutable objects, and methods without side effects.

  /**
    * CHAPTER 4
    **/

  //public is default scala access level in fields in classes
  //scala function parameters are vals !!
  //A method that is executed only for its side effects is known as a procedure
  class ChecksumAccumulator {
      private var sum = 0
      def add(b: Byte): Unit = sum += b
      def checksum(): Int = ~(sum & 0xFF) + 1

      object ChecksumAccumulator {
          import scala.collection.mutable
          private val cache = mutable.Map.empty[String, Int]
          def calculate(s: String): Int =
              if (cache.contains(s))
                  cache(s)
              else {
                  val acc = new ChecksumAccumulator
                  for (c <- s)
                      acc.add(c.toByte)
                  val cs = acc.checksum()
                  cache += (s -> cs)
                  cs
              }
      }

  }

    //class is called companion class of the singleton object
    //companion object - same name as the class

    val acc = new ChecksumAccumulator
    acc.add(2)
    println(acc.checksum())

    //Line ending is treated as a semicolon ";" unless:
    //The line in question ends in a word that would not be legal as the end of a statement, such as a period or an infix operator.
    //The next line begins with a word that cannot start a statement.
    //The line ends while inside parentheses (...) or brackets [...], because these cannot contain multiple statements anyway.

    //singleton object is a home for static methods
    //One difference between classes and singleton objects is that singleton objects cannot take parameters, whereas classes can

    //entry point for application - astandalone object with a main method

    //Scala diff Java - u can name file as u want, dont need to name file as a public class

    //adding "extend App" trait u dont need to write a main method in astandalone object to run your app




}

