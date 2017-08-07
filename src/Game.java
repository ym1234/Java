import java.util.*;

public class Game {

    Board board;

    public Game() {

        board = new Board(10, 10, 20);

        help();

        System.out.println("Generating Board");
        board.generate();

        start();

    }

    public void start() {
        while (true) {
            userInput();
        }
    }

    public static void main(String[] args) {
        new Game();
    }


    public void help() {
        System.out.println();
        System.out.println("Commands:");
        System.out.println("           \"help\" opens the help menu");
        System.out.println("           \"choose\" specify which tile you want to check");
        System.out.println("           \"flag\" specify which tile you want to flag");
        System.out.println("		   \"restart\" start a new game");
        System.out.println("		   \"quit\" to quit the game");
        System.out.println();
    }

    void userInput() {
        Scanner scan = new Scanner(System.in);

        System.out.print("$ ");
        String userInput;

        userInput = scan.nextLine();
        userInput = userInput.trim().toLowerCase();

        switch (userInput) {
            case "help":

                help();
                break;

            case "choose":

                int row = scan.nextInt() - 1;
                int column = scan.nextInt() - 1;
                choose(row, column);
                break;

            case "restart":

                restart(scan.nextInt(), scan.nextInt(), scan.nextInt());
                break;

            case "flag":

                flag(9, 9);
                board.printBoard();
                break;

            case "quit":

                scan.close();
                System.exit(0);

            case "":
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }

    void choose(int row, int column) {
        Cell cell = board.getBoard()[row][column];
        if (cell.isMine()) {
            board.printBoard();
            System.out.println("Lose");
        } else if (cell.hasValue()) {
            board.getBoard()[row][column].show();
            board.printBoard();
        } else if (!cell.hasValue()) {
            show(cell, new ArrayList<>(), new ArrayList<>());
        }
    }

    public void show(Cell cell, ArrayList<Cell> queue, ArrayList<Cell> processed) {

        cell.show();

        if (queue.isEmpty()) {
            ArrayList<Cell> surroundingTiles = cell.getSurroundingTiles();
            for (Cell cell2 : surroundingTiles) {
                if (!cell2.hasValue() && !cell.isMine()) {
                    queue.add(cell2);
                } else if (cell2.hasValue()) {
                    cell2.show();
                }
            }

            board.printBoard();
            System.out.println();

            if (queue.isEmpty()) {

            } else {
                show(queue.get(0), queue, processed);
            }
        } else {
            for (Cell cell2 : cell.getSurroundingTiles()) {
                if (queue.contains(cell2)) {

                } else if (processed.contains(cell2)) {

                } else if (!cell2.hasValue() && !cell2.isMine()) {
                    queue.add(cell2);
                } else if (cell2.hasValue()) {
                    cell2.show();
                } else if (cell2.isShown()) {

                } else if (cell2.isMine()) {

                }
            }

            processed.add(cell);
            queue.remove(cell);

            board.printBoard();
            System.out.println();

            if (queue.isEmpty()) {

            } else {
                show(queue.get(0), queue, processed);
            }
        }

        board.printBoard();
        System.out.println();

    }


    void flag(int row, int column) {

        Cell cell = board.getBoard()[row][column];

        if (cell.isFlagged()) {
            cell.setFlagged(false);
            return;
        }

        cell.setFlagged(true);

    }

    void restart(int length, int width, int numOfMines) {
        board = new Board(length, width, numOfMines);
        System.out.println("Generating new Board");
        board.generate();
    }
}
