package main

import "flag"
import "fmt"

func main() {

	/*Registry server*/
	regserverPtr := flag.String("h", "localhost", "rmiregistryserver")

	/*Port number*/
	portPtr := flag.Int("port", 1099, "port number")

	/*Parse errythang*/
	flag.Parse()

	/*Output the flags*/
	fmt.Println("word:", *regserverPtr)
	fmt.Println("port:", *portPtr)

	fmt.Println("City:", flag.Arg(0))
	fmt.Println("State:", flag.Arg(1))

}
