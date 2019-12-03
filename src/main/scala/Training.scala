object Training extends App{
  //instead of for in for loops
  //(1 to 3).foreach(i => (1 to 3).foreach(j => println(i, j)))

  //1 + 2 invoke method named +, originally its (1).+(2)
  //the same as in for loop syntax like: for(i <- 1 to 2) equal to for(i <- (1).to(2))
  //summary: if a method takes only one parameter you can call it without dot or parentheses
  //println(1 + 2)
  //println((1).+(2))
}

