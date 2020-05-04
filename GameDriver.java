import java.util.Random;
import java.util.Scanner;

public class GameDriver {
    public static Map board = new Map();
    public static Player user = new Player();
    public static Player computer = new Player();

    public static void main(String[] args) {

        // Display the welcome message
        System.out.print("**** Welcome to the Battle Ship game ****\n\n");
        board.printOcean();
        System.out.println();

        // Get coordinates for player's ships
        Scanner in = new Scanner(System.in);

        // We will get 5 coordinate pairs from the player and validate each coordinate as we go
        for (int i = 0; i < 5; i++) {
            System.out.print("Enter X coordinate for ship " + (i + 1) + ": ");
            int x = validateCoordinate(in.nextInt());
            System.out.print("Enter Y coordinate for ship " + (i + 1) + ": ");
            int y = validateCoordinate(in.nextInt());

            // Create a new ship with the given coordinates
            Ship newShip = new Ship(x, y);

            // Check if the player already has a ship at that location. If not, add the ship to the board.
            if (!user.hasShipAtLocation(newShip)) {
                user.addShip(newShip);
                board.addPlayerShip(newShip);
            } else {
                System.out.println("You already have a ship at coordinates: (" + x + ", " + y + ") ");
            }
        }

        // Generate computer ships
        System.out.println("Deploying computer ships: ");

        // Keep track of the amount of computer ships we've deployed so far. We need 5.
        boolean allDeployed = false;
        int count = 0;

        // Continue to generate random coordinates and add ships until we've added 5 ships to the board.
        while (!allDeployed) {
            int x = new Random().nextInt(10);
            int y = new Random().nextInt(10);

            // Check if the computer already has a ship at this location and that the location is empty.
            Ship newShip = new Ship(x, y);
            if (!computer.hasShipAtLocation(newShip) && board.isOccupied(newShip)) {
                computer.addShip(newShip);
                board.addComputerShip(newShip);
                System.out.println((count + 1) + ". ship DEPLOYED");
                count++;
            }

            // If we've reached 5 ships, we've deployed all of them.
            if (count == 5) {
                allDeployed = true;
            }
        }

        // Start the game
        playGame();
    }

    // Loop until either the player or the computer have no ships left.
    // We will print the map at the end of each round.
    public static void playGame() {
        while (!user.noShipsLeft() && !computer.noShipsLeft()) {
            playerTurn();
            computerTurn();

            board.printOcean();
            System.out.println();
            System.out.println("Your ships: " + user.ships.size() + " | " +
                    "Computer ships: " + computer.ships.size());
        }

        // When the game is over, we will print the board one more time and announce the winner.
        System.out.println();
        board.printOcean();
        System.out.println();

        System.out.println("Your ships: " + user.ships.size() + " | " +
                "Computer ships: " + computer.ships.size());

        // If the user has no ships left, they lost the game. Otherwise, they won.
        if (user.noShipsLeft()) {
            System.out.println("YOU LOST :(");
        } else {
            System.out.println("YOU WON!");
        }
    }

    // Get X and Y coordinates from the player representing their guess.
    // If they've already guess these coordinates, do nothing.
    // If they hit their own ship, remove their ship from the board.
    // If they hit the computer's ship, remove it from the board.
    // If they miss, do nothing.
    public static void playerTurn() {
        Scanner in = new Scanner(System.in);

        System.out.println("YOUR TURN");
        System.out.print("Enter X coordinate: ");
        int x = in.nextInt();
        System.out.print("Enter Y coordinate: ");
        int y = in.nextInt();

        Ship newShip = new Ship(x, y);

        // Check the coordinates on the board and print the corresponding message
        if (user.hasGuessed(newShip)) {
            System.out.println("You've already guessed those coordinates");
        } else if (computer.hasShipAtLocation(newShip)) {
            System.out.println("Boom! You sunk the ship!");
            board.removeComputerShip(newShip);
            computer.removeShip(newShip);
        } else if (user.hasShipAtLocation(newShip)) {
            System.out.println("Oh no, you sunk your own ship :(");
            board.removePlayerShip(newShip);
            user.removeShip(newShip);
        } else if (board.isOccupied(newShip)) {
            System.out.println("You missed.");
            board.addPlayerMiss(newShip);
        }

        // Add the coordinates to the player's list of guesses
        user.addGuess(newShip);
    }

    // Generate X and Y coordinates from the computer representing their guess.
    // If they've already guess these coordinates, do nothing.
    // If they hit their own ship, remove their ship from the board.
    // If they hit the computer's ship, remove it from the board.
    // If they miss, do nothing.
    public static void computerTurn() {
        int x = new Random().nextInt(10);
        int y = new Random().nextInt(10);

        Ship newShip = new Ship(x, y);

        // Check the board for the coordinates and print the corresponding message
        if (computer.hasGuessed(newShip)) {
            System.out.println("Computer already guessed those coordinates");
        }
        if (user.hasShipAtLocation(newShip)) {
            System.out.println("The computer sunk one of your ships!");
            board.removePlayerShip(newShip);
            user.removeShip(newShip);
        } else if (computer.hasShipAtLocation(newShip)) {
            System.out.println("The computer sunk one of its own ships");
            board.removeComputerShip(newShip);
            computer.removeShip(newShip);
        } else if (board.isOccupied(newShip)) {
            System.out.println("The computer missed.");
        }

        // Add the coordinates to the computer's list of guesses
        computer.addGuess(newShip);
    }

    // Ensure the player enters a number within the specified range (0 - 9)
    public static int validateCoordinate(int x) {
        Scanner in = new Scanner(System.in);

        while (x < 0 || x > 10) {
            System.out.print("Please enter a valid number between 0 - 9 inclusive: ");
            x = in.nextInt();
        }

        return x;
    }
}
