// Name: Waleed Albishri
// Section: XA (40202)
// Email: wwalbishri@stu.kau.edu.sa
package BST;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainProgram {

    private static Scanner read;
    private static PrintWriter write;
    private static GameTree Game;

    public static void main(String[] args) throws FileNotFoundException {
        // input file
        File input = new File("Commands.txt");
        // check if file exists or not
        if (!(input.exists())) {
            System.out.println("File is not exists!");
            System.exit(1);
        }
        // scanner to read from the file
        read = new Scanner(input);
        write = new PrintWriter("output.txt");
        Game = new GameTree();
        write.printf("\t%s\n", "Welcome to Game Tree Program");
        write.printf("\t%s", "---------------------------------------------------------\n");
        // command
        String command;
        // loop to go thru all the file (commands.txt)
        while (read.hasNext()) {
            command = read.next();
            switch (command) {
                case "STARTUP":
                    startup();
                    break;
                case "DISPLAY_ALL_PLAYERS":
                    displayall();
                    break;
                case "NUM_OF_PLAYERS":
                    numplayers();
                    break;
                case "DISPLAY_PLAYER_INFO":
                    displayplayerinfo();
                    break;
                case "ADD_PLAYER":
                    addplayer();
                    break;
                case "DELETE_PLAYER":
                    deleteplayer();
                    break;
                case "UPDATE_PLAYER_INFO":
                    update();
                    break;
                case "QUIT":
                    quit();
                    break;
            }
        }
        read.close();
        write.close();
    }

    //////////////////////////////////////
    private static void startup() throws FileNotFoundException {
        // input file
        File input2 = new File("intialInformation.txt");
        // check if file exists or not
        if (!(input2.exists())) {
            System.out.println("File is not exists!");
            System.exit(1);
        }
        // scanner to read from the file
        Scanner read2 = new Scanner(input2);
        // take the size from the file
        int size = read2.nextInt();
        for (int i = 0; i < size; i++) {
            Game.add_player(read2.nextInt(), read2.next());
        }
    }

    //////////////////////////////////////
    private static void displayall() {
        // display all players
        write.println("The players existed in the game are:\n\n");
        write.println("\tPlayerID\tPlayer Name \t Stamina level");
        write.println("---------------------------------------------------");
        Game.displayall(write);
        write.println("\n\n===================================================================================================\n");
    }

    //////////////////////////////////////
    private static void numplayers() {
        // num of players in the game
        write.println("Number of players : " + Game.numplayers());
        write.println("===================================================================================================\n");
    }

    /////////////////////////////////////
    private static void displayplayerinfo() {
        // display player info
        int id = read.nextInt();
        PlayerNode player = Game.displayplayerinfo(id);
        if (player != null) {
            write.println("Player with ID <" + player.getPlayerID() + "> is <" + player.getPlayername() + "> and the stamina level is <" + player.getStamina() + ">");
            write.println("===================================================================================================\n");

        } else {
            write.println("Not found any player with ID number <" + id + ">");
            write.println("===================================================================================================\n");
        }
    }

    /////////////////////////////////////
    private static void addplayer() {
        // add player
        Game.add_player(read.nextInt(), read.next());
    }

    ///////////////////////////////////
    private static void deleteplayer() {
        // delete player
        int id = read.nextInt();
        PlayerNode player = Game.displayplayerinfo(id);
        if (player != null) {
            Game.delete_player(id);
            write.println("The player <" + player.getPlayername() + "> left the game");
            write.println("===================================================================================================\n");
        } else {
            write.println("Not found any player with ID number <" + id + ">");
            write.println("===================================================================================================\n");
        }
    }

    ///////////////////////////////////
    private static void update() {
        // update player stamina
        int id = read.nextInt();
        PlayerNode player = Game.displayplayerinfo(id);
        if (player != null) {
             Game.update(id);
            if (player.getStamina() == 0) {
                write.println("The stamina level for the player <" + player.getPlayername() + "> reaches zero and <cute_kitty> left the game!â€");
                write.println("===================================================================================================\n");
                Game.delete_player(id);
            } else {
                write.println("The player <" + player.getPlayername() + "> received a hit and the stamina level reduced by 5");
                write.println("===================================================================================================\n");
            }
        } else {
            write.println("Not found any player with ID number <" + id + ">");
            write.println("===================================================================================================\n");
        }
    }

    ///////////////////////////////////
    private static void quit() {
        // quit
        write.println("\t\t -------------------------------------");
        write.println("\t\t|\t\tGood Bye\t\t|");
        write.println("\t\t -------------------------------------");
        write.close();
    }
    ///////////////////////////////////
}
