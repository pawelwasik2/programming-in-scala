object Training extends App {
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
      * Ogolnie to kwestia setow i hashsetow sie troche wyjasnila na tym forum, .getClass zwraca Set$Set4 na <5 a >=5 HashSet
      * Kiedy indziej ogarne
      **
      *Mozna powiedziec ze Set to taki glowny typ, a HashSet czy Set$Set3 to wynik metody .toString wywalonej na obiekcie typu Set
      * Nie wiem do konca o co dokladnie chodzi, pewnie ogarne za jakis czas
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
    /*
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
*/
    /**
      * CHAPTER 5
      **/

    //symbol value
    /*val s = 'symbol
  s.name

  //mozna pisac w unicode
  //rzutowanie na zmienna z pierwsza jej literą na koncu np bedzie typem double:
  val i = 213d
  println("""|Welcome to Ultamix 3000.
             |Type "HELP" for help.""".stripMargin)
  val escapes = "\\\"\'"
  //scala> val B\u0041\u0044 = 1
  // BAD: Int = 1

  //string
  val name = "Pawel"
  println(s"Hello, $name!")

  val strS = s"The answer is ${6 * 7}."
  val strSS = s"No\\\\escape!"//prints: res1: String = No\\escape!
  val strRAW = raw"No\\\\escape!" //prints: No\\\\escape!
  val pi = "Pi" //pi: String = Pi
  f"$pi is approximately ${math.Pi}%.8f." // String = Pi is approximately 3.14159265.

  //operator notation: 1 + 2 means (1).+(2)
  //u can use ANY method in operator notation like:
  val str = "hello world"
  println(str indexOf 3)
  println(str indexOf 'o')
  //as here, it works. Any method can be an operator, precise: INFIX OPERATOR - something before and sth after operator
  //Prefix operator: -2.0 (mehtod "-" invoked on "2.0"):
  (2.0).unary_-
  (0xFF).unary_~
  //Postfix operator: str.toLoweCase:
  str toLowerCase

  //example of &&. PL: lewa strona wykonuje sie zawsze, poniewaz od niej zalezy jaki będzie wynik. Jeśli false, to wynikiem bedzie
  //false no i oczywiscie druga strona sie nie wykona np:
  def salt() = { println("salt"); false }
  def pepper() = { println("pepper"); true }
  pepper() && salt() //wypisze sie pepper i salt
  salt() && pepper() //wypisze sie tylko salt
  //If you want to evaluate the right hand side no matter what, use & and | instead. The & method performs
  //a logical-and operation
  salt() & pepper() //wypisz sie salt i pepper

  //Objects equality
  //you can compare objects as lists, basic types, even different types
  ("he" + "llo") == "hello" //true
  //cause scala's "==" compare what's inside objects not if the objects are the same
  //is scala's == the same as java's equals() method?

  //KOLEJNOSC DZIALAN
  // If the method name starts with a*, for example, it will have a higher precedence than
  //a method that starts with a +. Thus2 + 2 * 7 will be evaluated as 2 + (2 * 7). Similarly, a ++
  //+ b *** c (in which a, b, and c are variables, and +++ and *** are methods) will be evaluated a ++
  //+ (b *** c), because the *** method has a higher precedence than the +++ method.
  //PL: W skrocie, w scali nie ma operatorow (sa tylko metody zapisane w postaci operatorow),
  //Pierwsza litera nazwy metody decyduje o tym ktora zostanie wykonana wczesniej
  //QUEUE:
  //(all other special characters)
  //* / %
  //+ -
  //:
  //= !
  //< >
  //&
  //^
  //|
  //(all letters)
  //(all assignment operators)

  //If an operator ends in an equals character (=), and the operator is not one of the
  //comparison operators <=, >=, ==, or !=, then the precedence of the operator is the same as that of
  //simple assignment (=)

  // any method that ends in a `:' character is invoked on its right operand
  //So a * b yields a.*(b), but a ::: byields b.:::(a).

  //Table 5.4 - Some rich operations
  //Code Result
  //0 max 5 5
  //0 min 5 0
  //-2.7 abs 2.7
  //-2.7 round -3L
  //1.5 isInfinity false
  //(1.0 / 0) isInfinity true
  //4 to 6 Range(4, 5, 6)
  //"bob" capitalize "Bob"
  //"robert" drop 2 "bert"
  //Table 5.5 - Rich wrapper classes
  //Basic type Rich wrapper
  //Byte scala.runtime.RichByte
  //Short scala.runtime.RichShort
  //Int scala.runtime.RichInt
  //Long scala.runtime.RichLong
  //Char scala.runtime.RichChar
  //Float scala.runtime.RichFloat
  //Double scala.runtime.RichDouble
  //Boolean scala.runtime.RichBoolean
  //String scala.collection.immutable.StringOps*/

   */

    /**
      * CHAPTER 7
      * BUILD-IN CONTROL STRUCTURES
      **/

    /*val value =
    if(1==1) "test"
    else "passed"
  //if returns a value

  //scala> val hi = ()
  //hi: Unit = ()
  //Unit is a type what loops (while, do-while) returns. It perfectly shows how differ is Java's void than Unit

  //functional languages has expressions, no loops
  //Scala compiler will not infer semicolons while inside parentheses.

  val values =
    for{
      i <- 1 to 10
      if i==5
      j <- 1 to 20
      if i + j == 10
    }yield "done"
  println(values)
  //example of nester for expression, idk why return Vector(done),

  //closing files in finally block
  def urlFor(path: String) =
    try {
      new URL(path)
    } catch {
      case e: MalformedURLException =>
        new URL("http://www.scala-lang.org")
    }
  //scala's try catch can return value
  //finally should be treated as a cleaner (closing resources or sth like that)

  def f(): Int = try return 1 finally return 2
  //calling f() results in 2. By contrast, given:
  def g(): Int = try 1 finally 2
  //calling g() iresult is 1
  //that is when finally clause contains explicit return statement, so its overrule other returns

  val firstArg = "JD"
  firstArg match {
    case "salt" => println("pepper")
    case "chips" => println("salsa")
    case "eggs" => println("bacon")
    case _ => println("huh?")
  }

  //IMPERATIVE
  def printMultiTable() = {
    var i = 1
    // only i in scope here
    while (i <= 10) {
      var j = 1
      // both i and j in scope here
      while (j <= 10) {
        val prod = (i * j).toString
        // i, j, and prod in scope here
        var k = prod.length
        // i, j, prod, and k in scope here
        while (k < 4) {
          print(" ")
          k += 1
        }
        print(prod)
        j += 1
      }
      // i and j still in scope; prod and k out of scope
      println()
      i += 1
    }
    // i still in scope; j, prod, and k out of scope
  }

  //FUNCTIONAL
  // Returns a row as a sequence
  def makeRowSeq(row: Int) =
    for (col <- 1 to 10) yield {
      val prod = (row * col).toString
      val padding = " " * (4 - prod.length)
      padding + prod
    }
  // Returns a row as a string
  def makeRow(row: Int) = makeRowSeq(row).mkString
  // Returns table as a string with one row per line
  def multiTable() = {
    val tableSeq = // a sequence of row strings
      for (row <- 1 to 10)
        yield makeRow(row)

    tableSeq.mkString("\n")
  }

  //Podsumowując ten rozdzial, troche funkcyjnego programowania, w którym chodzi o to zeby nie miec efektow ubocznych jak println,
  //zeby funkje zwracaly cos, zeby byly podzielone na helper functions, ktore tworza kod bardziej czytelnym, */

    /**
      * CHAPTER 8
      * FUNCTIONS AND CLOSUER
      **/

    /*//Functional programming style: programs should be decomposed into many small functions that each do a well-defined task
    //So the best way to write a large ammount of small functions is to write them inside other functions (local functions)
    //Local functions cac access wariable of it's enclosing functions:
  import scala.io.Source
    object LongLines {

        def processFile(filename: String, width: Int) = {

            def processLine(line: String) = {
                if (line.length > width)
                    println(filename + ": " + line.trim)
            }

            val source = Source.fromFile(filename)
            for (line <- source.getLines())
                processLine(line)
        }
    }

    //funkcje ktore przyjuja funkcje jako parametry lub ktorych wynik jest funkcja, mogą tak robić ponieważ:
  //W scali funkcje są wartosciami pierwszej kategorii (first-class values)

  var increase = (x: Int) => x + 1
  increase(10)

    //As forech is element of all collections we can pass to it a function as an argument and invokes the function on all elements:
    val someNumbers = List(-11, -10, -5, 0, 5, 10)
    someNumbers.foreach((x: Int) => println(x)) // or shorter: someNumbers.foreach(x => println(x))
    //-11
    // -10
    // -5
    // 0
    // 5

    //another way is underscore which means (every wariable in set)
    someNumbers.foreach(_ => println)
    someNumbers.filter(_ > 0)
    //You can think of the underscore as a "blank" in the expression that needs to be "filled in." This blank
    //will be filled in with an argument to the function each time the function is invoked
    //First underscore represent first parameter, second - second, and so on..

    someNumbers.foreach(println _)
    //Thus, the underscore in this case is not a placeholder for a single parameter. It is a placeholder for an
    //entire parameter list

    //partially applied functions
    def sum(a: Int, b: Int, c: Int) = a + b + c
    val a = sum _
    a(1, 2, 3)
    //res11: Int = 6
    val b = sum(1, _: Int, 3)
    b(2)
    // res13: Int = 6
    // Since only one argument is missing, the Scala compiler generates a new FUNCTION CLASS whose apply method takes one argument.
    someNumbers.foreach(println)
    //This upper example is last form is allowed only in places where a function is required, such as the invocation
    //of foreach in this example. The compiler knows a function is required in this case,
    //because foreach requires that a function be passed as an argument. In situations where a function is not
    //required, attempting to use this form will cause a compilation error. Here's an example:
    //scala> val c = sum
    // <console>:8: error: missing arguments for method sum;
    // follow this method with `_' if you want to treat it as a
    // partially applied function
    // val c = sum
    // ^
    // scala> val d = sum _
    // d: (Int, Int, Int) => Int = <function3>
    //
    // scala> d(10, 20, 30)
    // res14: Int = 60

    //CLOSURES

    var more = 1
    val addMore = (x: Int) => x + more
    addMore(10)
    // res16: Int = 11
    more = 9999
    addMore(10)
    //res17: Int = 10009

    def makeIncreaser(more: Int) = (x: Int) => x + more
    val inc1 = makeIncreaser(1)
    val inc9999 = makeIncreaser(9999)
    inc1(10) //res20: Int = 11
    inc9999(10) //res21: Int = 10009

    //reapeted parameter
    def echo(args: String*) =
        for (arg <- args) println(arg)
    echo("one") //one
    echo("hello", "world!")//hello world!
    //echo(arr: _*) //to pass an array to fun, it tells the compiler to pass every argument from array as an other

    def speed(distance: Float, time: Float): Float =
        distance / time
    speed(time = 10, distance = 100)

    //default value
    def printTime(out: java.io.PrintStream = Console.out) =
        out.println("time = " + System.currentTimeMillis())
    printTime()
    printTime(Console.err) //overridew default argument?

    //TAIL RECURSION
    //The Scala compiler detects tail recursion and replaces it with
    //a jump back to the beginning of the function, after updating the function parameters with the new values*/

    /**
      * CHAPTER 9
      * CONTROL ABSTRACTION
      **/

    /*object FileMatcher {
        private def filesHere = (new java.io.File(".")).listFiles

        private def filesMatching(matcher: String => Boolean) =
            for (file <- filesHere; if matcher(file.getName))
                yield file

        def filesEnding(query: String) =
            filesMatching(_.endsWith(query))//mozna to zapisac jako (file.getName: String) => (file.getName).endWith(query)
                                            //taki skrocony zapis jest mozliwy bo kompilator wie, ze wymagana jest tu fuknkcja?? chyba
                                            //

        def filesContaining(query: String) =
            filesMatching(_.contains(query))

        def filesRegex(query: String) =
            filesMatching(_.matches(query))
    }
    //PL: jako argument do funkcji filesMatching przekazujemy funkcje - matcher. Z jednego brakującego elementu oblicza
    //czy spelnia warunki zwracając Boolean


  //imperative way to write a function to check in collection for negative number
  def containsNeg(nums: List[Int]): Boolean = {
    var exists = false
    for (num <- nums)
      if (num < 0)
        exists = true
    exists
  }

  //and the same function in functional style using high-order functions
  def containsNegFP(nums: List[Int]) = nums.exists(_ < 0)

  //CURRYING
  //for creagting control abstraction that look like originqal scala library function is needed to use currying
  //Any time you find a control pattern repeated in multiple parts of your code, you should think about
  //implementing it as a new control structure
  //currying is invoking function which needs 2 or more parameters with separate parentheses,
  //to make your new control structure to looks like more like built-in you need to use curly braces for
  //arguments which are functions:
  def withPrintWriter(file: File)(op: PrintWriter => Unit) = {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }

  val file = new File("date.txt")

  withPrintWriter(file) { writer =>
    writer.println(new java.util.Date)
  }

  //in scala you can invoke function with argument in curly braces only when this function needs only one argument:
  println("siema")
  println{"hey"}

  //BY-NAME PARAMETERS
  //By-name parameters exist precisely so that you can do this. To make a by-name parameter, you give the
  //parameter a type starting with => instead of () =>. For example, you could
  //changemyAssert's predicate parameter into a by-name parameter by changing its type, "() => Boolean", into "=> Boolean".
  var assertionsEnabled = true
  def myAssert(predicate: () => Boolean) =
    if (assertionsEnabled && !predicate())
      throw new AssertionError
  myAssert(() => 5 > 3)

  def byNameAssert(predicate: => Boolean) =
    if (assertionsEnabled && !predicate)
      throw new AssertionError
  byNameAssert(5 > 3)

  //Funkcje byNameAssert mozemy zapisac rownież jako (predicate: Boolean), ale
  //roznica wtedy jest taka, ze 5 > 3 zyielduje wynik i z takim wynikiem wywoła funkcje byNameAssert, natomiast
  //gdy uzyjemy formy => Boolean, 5 > 3 wywola sie dopiero w linijcie !predicate()*/

    /**
      * CHAPTER 10
      * Composition and Inheritance
      **/

    //Composition means one class holds a reference to another, using the referenced class to help it fulfill its mission.
    // Inheritance is the superclass/subclass relationship.
    //
    /**
      * CHAPTER 11
      * Scala's Hierarchy
      **/

    //just as Any is a superclass of every other class, Nothing is a subclass of every other class
    //Any contains:
    final def ==(that: Any): Boolean

    final def !=(that: Any): Boolean

    def equals(that: Any): Boolean

    def ##:

    Int

    def hashCode: Int

    def toString: String

    // Unit = ()
    //ll value classes are subtypes of scala.AnyVal, but they do not
    //subclass each other. Instead there are implicit conversions between different value class types. For
    //example, an instance of class scala.Int is automatically widened (by an implicit conversion) to an
    //instance of class scala.Long when required.
    //Implicit conversion - niejawna konwersja

    // So classes written in Java, as well as classes written in Scala, all inherit from AnyRef

    //The equality operation == in Scala is designed to be transparent with respect to the type's representation

    NULL
    //Class Null is the type of the null reference; it is a subclass of every reference class (i.e., every class that
    //itself inherits from AnyRef so you cannot assign null value to AnyVal objects (i.e. Int)

    NOTHING
    //Nothing is a subclass of every other class - including NULL
    //There exists no value to this class
    //For example there is a function:
    // def error(message: String): Nothing =
    // throw new RuntimeException(message)
    //Which not return normally, it throws an exception instead. Its not even return Unit, just NOTHING.

    UNIT
    //there is only one value for class Unit: () and its not represented by any object
    //its something like Java's void
    //Function that doesn't return anything must have the return type Unit
    //If it were Nothing then the function could not return a result

    NIL

    //Represents an empty List of anything of zero length. Its not that it refers to nothing but it refers to List which has no contents.

    //OWN VALUE CLASSES:
    //-it must have exactlyone parameter
    //-it must have nothing inside it except defs
    //-no other class can extend a value class
    //-value class cannot redefine equals or hashCode

    //example
    class Dollars(val amount: Int) extends AnyVal {
        override def toString() = "$" + amount
    }

    /**
      * CHAPTER 12
      * TRAITS
      **/

    //Classes can mix in any number of traits
    //example trait:
    trait Philosophical {
        def philosophize() = {
            println("I consume memory, therefore I am!")
        }
    }

    //this trair does not declare a superclass so like a class it has a default superclass of AnyRef

    //mixing in and overridiing:
    class Frog extends Philosophical {
        override def toString = "green"

        override def philosophize() = {
            println("It ain't easy being " + toString + "!")
        }
    }

    val frog = new Frog
    frog.philosophize()
    //trait is also a type
    val phil: Philosophical = frog

    class Animal

    trait HasLegs

    class Frog extends Animal with Philosophical with HasLegs {
        override def toString = "green"
    }

    //DIFFERENCES WITH CLASS
    //-u cannot pass parameteres to trait:
    //trait NoPoint(x: Int, y: Int) //does not compile
    //-second is whereas in classe invoking method super.toString we know which method would be invkoen
    //in traits its more complicated

    //ORDERED TRAIT
    class Rational(n: Int, d: Int) extends Ordered[Rational] { // specify the type
        // ...
        def compare(that: Rational) =
            (this.numer * that.denom) - (that.numer * this.denom)
    }

    //This trait is used to compare objects. All we need to do is mixin Ordered[Class] and:
    //The second thing you need to do is define a compare method for comparing two objects. This method
    //should compare the receiver, this, with the object passed as an argument to the method. It should return
    //an integer that is zero if the objects are the same, negative if receiver is less than the argument, and
    //positive if the receiver is greater than the argument.


    //STACKABLE MODIFICATIONS TRAIT
    abstract class IntQueue {
        def get(): Int

        def put(x: Int)
    }

    class BasicIntQueue extends IntQueue {
        private val buf = new ArrayBuffer[Int]

        def get() = buf.remove(0)

        def put(x: Int) = {
            buf += x
        }
    }

    trait Doubling extends IntQueue {
        abstract override def put(x: Int) = {
            super.put(2 * x)
        }
    }

    // The first is that it declares a superclass, IntQueue. This declaration means that the trait can only be mixed
    //into a class that also extends IntQueue. Thus, you can mix Doubling intoBasicIntQueue, but not
    //into Rational.
    //The second funny thing is that the trait has a super call on a method declared abstract. Such calls are
    //illegal for normal classes because they will certainly fail at run time. For a trait, however, such a call
    //can actually succeed. Since super calls in a trait are dynamically bound, the super call in
    //trait Doubling will work so long as the trait is mixed in after another trait or class that gives a concrete
    //definition to the method.

    trait Incrementing extends IntQueue {
        abstract override def put(x: Int) = {
            super.put(x + 1)
        }
    }

    trait Filtering extends IntQueue {
        abstract override def put(x: Int) = {
            if (x >= 0) super.put(x)
        }
    }

    val queue = (new BasicIntQueue
      with Incrementing with Filtering)

    //The order of mixins is significant
    //Its define an order which def put is first and which is last

    // Thus, when you write a method that
    //calls super, that method is definitely modifying the behavior of the superclasses and mixed in traits, not
    //the other way around.
    // Multiple inheritance thought experiment
    val q = new BasicIntQueue with Incrementing with Doubling
    q.put(42) // which put would be called?
    //ans: Doubling's put and then << to the left, cause /\ text above

    //WHEN TO USE A TRAIT?
    //-If the behavior will not be reused, then make it a concrete class. It is not reusable behavior after all.
    //-If it might be reused in multiple, unrelated classes, make it a trait. Only traits can be mixed into
    //different parts of the class hierarchy.
    //-If you want to inherit from it in Java code, use an abstract class. Since traits with code do not have a
    //close Java analog, it tends to be awkward to inherit from a trait in a Java class.
    //-If you still do not know, after considering the above, then start by making it as a trait.

    /**
      * CHAPTER 13
      * IMPORTS AND PACKAGES
      **/


    // In file launch.scala
    package launch {
        class Booster3
    }

    // In file bobsrockets.scala
    package bobsrockets {
        package navigation {
            package launch {
                class Booster1
            }
            class MissionControl {
                val booster1 = new launch.Booster1
                val booster2 = new bobsrockets.launch.Booster2
                val booster3 = new _root_.launch.Booster3
            }
        }
        package launch {
            class Booster2
        }
    }
///////////////////////////////////////////////////////
    package bobsdelights

    abstract class Fruit(
                          val name: String,
                          val color: String
                        )

    object Fruits {
        object Apple extends Fruit("apple", "red"){
            //??????
        }
        object Orange extends Fruit("orange", "orange")
        object Pear extends Fruit("pear", "yellowish")
        val menu = List(Apple, Orange, Pear)
    }

    // easy access to Fruit
    import bobsdelights.Fruit

    // easy access to all members of bobsdelights
    import bobsdelights._

    // easy access to all members of Fruits
    import bobsdelights.Fruits._

    //Its also possible
    def showFruit(fruit: Fruit) = {
        import fruit._
        println(name + "s are " + color)
    }
    //There is no rule that import must be at the begginign of a file

    //SCALA'S FLEXIBLE IMPORTS
    //-may appear anywhere
    //• may refer to objects (singleton or regular) in addition to packages
    //• let you rename and hide some of the imported members

//does not import Apple from Fruits,
    import Fruits.{Apple => _, _}

    //three implicit import in Scala
    import java.lang._ // everything in the java.lang package
    import scala._ // everything in the scala package
    import Predef._ // everything in the Predef object

    //you can writeList instead of scala.List, for instance.

    package bobsrockets

    package navigation {
        private[bobsrockets] class Navigator {
            protected[navigation] def useStarChart() = {}
            class LegOfJourney {
                private[Navigator] val distance = 100
            }
            private[this] var speed = 200
        }
    }
    package launch {
        import navigation._
        object Vehicle {
            private[launch] val guide = new Navigator
        }
    }
    //Access modifiers in Scala can be augmented with qualifiers. A modifier of the
    //form private[X]or protected[X] means that access is private or protected "up to" X, where X designates
    //some enclosing package, class or singleton object.

  // A class shares all its access rights with its companion object and vice versa

  package object bobsdelights {
    def showFruit(fruit: Fruit) = {
      import fruit._
      println(name + "s are " + color)
    }
  }

  // In file PrintMenu.scala
  package printmenu
  import bobsdelights.Fruits
  import bobsdelights.showFruit

  object PrintMenu {
    def main(args: Array[String]) = {
      for (fruit <- Fruits.menu) {
        showFruit(fruit)
      }
    }
  }

  //package object
  // In file bobsdelights/package.scala
  package object bobsdelights {
    def showFruit(fruit: Fruit) = {
      import fruit._
      println(name + "s are " + color)
    }
  }

  // In file PrintMenu.scala
  package printmenu
  import bobsdelights.Fruits
  import bobsdelights.showFruit

  object PrintMenu {
    def main(args: Array[String]) = {
      for (fruit <- Fruits.menu) {
        showFruit(fruit)
      }
    }
  }












}

