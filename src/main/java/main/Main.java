package main;

import menu.UserInterface;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in))
        {
            UserInterface userInterface = new UserInterface(sc);
            userInterface.showMenu();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
