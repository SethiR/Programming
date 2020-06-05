import scala.io.StdIn.{readLine, readInt}
import scala.math._

object ScalaTutorial{
    def main(args: Array[String]){

        var numberGuess = 0

        do {
            print("Guess a number : ")
            numberGuess = readLine.toInt
            
        }while(numberGuess != 15)

        printf("You guessed the secret number %d\n", 15)

    }
}