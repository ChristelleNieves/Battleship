import java.util.ArrayList;

public class Player {
    ArrayList<Ship> ships; // Holds the ships that the player currently has on the board
    ArrayList<Ship> guesses; // Holds the ship coordinates that the player has guessed

    // Zero-parameter constructor
    public Player() {
        ships = new ArrayList<>();
        guesses = new ArrayList<>();
    }

    // Add a ship to the ships ArrayList
    public void addShip(Ship s) {
        this.ships.add(s);
    }

    // Remove a ship from the ships ArrayList
    public void removeShip(Ship s) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).getX() == s.getX() && ships.get(i).getY() == s.getY()) {
                ships.remove(i);
            }
        }
    }

    // Add a guess to the guesses ArrayList
    public void addGuess(Ship s) {
        this.guesses.add(s);
    }

    // Return true if the player has already guessed these ship coordinates, false otherwise
    public boolean hasGuessed(Ship s) {
        for (Ship guess : guesses) {
            if (guess.getX() == s.getX() && guess.getY() == s.getY()) {
                return true;
            }
        }
        return false;
    }

    // Return true if the player already has a ship at these coordinates, false otherwise
    public boolean hasShipAtLocation(Ship s) {
        for (Ship ship : ships) {
            if (ship.getX() == s.getX() && ship.getY() == s.getY()) {
                return true;
            }
        }
        return false;
    }

    // Return true if the player has no ships left on the board, false otherwise
    public boolean noShipsLeft() {
        return this.ships.size() == 0;
    }
}
