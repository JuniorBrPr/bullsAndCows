package bullscows;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BullsCows {
    private int turn;
    private int[] code;

    public BullsCows() {
        this.turn = 0;
        setCode();

        game();
    }

    private void game() {
        boolean guessed = false;
        System.out.println("Okay, let's start a game!");

        do {
            ++this.turn;
            String bullGrade;
            String cowGrade;
            boolean userInputCorrect = false;
            int[] input = new int[this.code.length];

            System.out.println("Turn " + this.turn + ":");

            do {
                Scanner in = new Scanner(System.in);
                String userInput = in.nextLine();

                if (userInput == null) {
                    System.out.println("\nInvalid input!\nTry again!\n");
                } else {
                    if (!userInput.matches("\\d*") || userInput.length() > code.length) {
                        System.out.println("\nWrong code pattern!");
                    } else {
                        String[] inputStringArray = userInput.split("");
                        for (int i = 0; i < inputStringArray.length; i++) {
                            input[i] = Integer.parseInt(inputStringArray[i]);
                        }
                        userInputCorrect = true;
                    }
                }
            } while (!userInputCorrect);

            int bulls = 0;
            int cows = 0;

            for (int i = 0; i < code.length; i++) {
                if (input[i] == this.code[i]) {
                    bulls += 1;
                } else {
                    for (int j : code) {
                        if (input[i] == j) {
                            cows += 1;
                        }
                    }
                }
            }

            if (bulls > 1) {
                bullGrade = bulls + " bulls";
            } else if (bulls > 0) {
                bullGrade = bulls + " bull";
            } else {
                bullGrade = "";
            }

            if (cows > 1) {
                cowGrade = cows + " cows";
            } else if (cows > 0) {
                cowGrade = cows + " cow";
            } else {
                cowGrade = "";
            }

            if (!cowGrade.equals("") && !bullGrade.equals("")) {
                System.out.print("Grade: " + bullGrade + " and " + cowGrade + ".");
            } else if (!cowGrade.equals("")) {
                System.out.print("Grade: " + cowGrade + ".");
            } else if (!bullGrade.equals("")) {
                System.out.print("Grade: " + bullGrade + ".");
            } else {
                System.out.print("Grade: None.");
            }

            if (bulls == code.length) {
                System.out.println("Congratulations! You guessed the secret code.");
                for (int i : code) {
                    System.out.print(i);
                }
                System.out.println(".");

                guessed = true;
            }

            System.out.println();
        } while (!guessed);
    }

    private void setCode() {
        System.out.println("Please, enter the secret code's length:");

        do {
            Scanner in = new Scanner(System.in);
            int length = in.nextInt();

            if (length > 10) {
                System.out.println("Error: can't generate a secret number with a length of " + length +
                        " because there aren't enough unique digits.");
            } else {
                code = new int[length];
                Set<Character> set = new HashSet<>();
                StringBuilder builder = new StringBuilder();

                do {
                    char[] characters = Long.toString(System.nanoTime()).toCharArray();
                    for (int i = characters.length - 1; i >= 0; i--) {
                        if (set.size() == 0 && characters[i] == '0') {
                            continue;
                        }

                        if (set.add(characters[i])) {
                            builder.append(characters[i]);
                        }

                        if (builder.toString().length() == length) {
                            break;
                        }
                    }
                } while (builder.toString().length() != length);

                for (int i = 0; i < length; i++) {
                    code[i] = (int) builder.charAt(i) - '0';
                }
            }
        } while (this.code == null || this.code.length < 1);
    }
}
