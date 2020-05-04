public class Map {
    char[][] ocean;

    // Zero-parameter constructor
    // Initializes a map consisting of a 10x10 2D array representing the ocean/board
    public Map() {
        ocean = new char[10][10];
    }

    // Return true if a ship is currently occupying these coordinates on the board, false otherwise
    public boolean isOccupied(Ship s) {
        return ocean[s.getX()][s.getY()] == 0;
    }

    // Add a player ship to the board
    public void addPlayerShip(Ship s) {
        ocean[s.getX()][s.getY()] = '@';
    }

    // Add a computer ship to the board
    public void addComputerShip(Ship s) {
        ocean[s.getX()][s.getY()] = '2';
    }

    // Remove a player ship from the board
    public void removePlayerShip(Ship s) {
        ocean[s.getX()][s.getY()] = 'x';
    }

    // Remove a computer ship from the board
    public void removeComputerShip(Ship s) {
        ocean[s.getX()][s.getY()] = '!';
    }

    // Add a player missed guess to the board
    public void addPlayerMiss(Ship s) {
        ocean[s.getX()][s.getY()] = '-';
    }

    // Print the ocean/board to the screen
    // Location of computer ships (represented with '2') will remain invisible to the user
    public void printOcean() {

        System.out.print("   ");
        for (int i = 0; i < 10; i++) {
            System.out.print(i);
        }
        System.out.println();

        for (int i = 0; i < this.ocean.length; i++) {
            System.out.print(i + " |");

            for (int j = 0; j < this.ocean[i].length; j++) {

                if (this.ocean[i][j] == 0 || this.ocean[i][j] == '2') {
                    System.out.print(" ");
                } else {
                    System.out.print(this.ocean[i][j]);
                }
            }

            System.out.print("| " + i);
            System.out.println();
        }

        System.out.print("   ");
        for (int i = 0; i < 10; i++) {
            System.out.print(i);
        }
    }
}
