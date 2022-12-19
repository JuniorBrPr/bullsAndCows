package bullscows;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BullsCows {
    private int turn;
    private char[] code;
    private Set<Character> set;

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
            char[] input = new char[this.code.length];

            System.out.println("Turn " + this.turn + ":");

            do {
                Scanner in = new Scanner(System.in);
                String userInput = in.nextLine();

                if (userInput.length() != this.code.length) {
                    System.out.println("\nInvalid input!\nTry again!\n");
                } else {
                    input = userInput.toCharArray();
                    userInputCorrect = true;
                }

            } while (!userInputCorrect);

            int bulls = 0;
            int cows = 0;

            for (int i = 0; i < code.length; i++) {
                System.out.println(Arrays.toString(this.code));
                if (input[i] == this.code[i]) {
                    bulls += 1;
                } else if (set.contains(input[i])) {
                    cows += 1;
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
                guessed = true;
            } else {
                System.out.println();
            }
        } while (!guessed);
    }

    private void setCode() {
        int symbols;

        do {
            System.out.println("Please, enter the secret code's length:");
            Scanner in = new Scanner(System.in);
            String userLength = in.nextLine();

            if (userLength.matches("\\d+")) {
                int length = Integer.parseInt(userLength);
                if (length > 36) {
                    System.out.println("Error: can't generate a secret number with a length of " + length +
                            " because there aren't enough unique digits adn letters.\n");

                } else if (length < 1) {
                    System.out.println("Error: can't generate a secret number with a length of " + length + "\n");
                } else {
                    System.out.println("Input the number of possible symbols in the code:");
                    String userSymbols = in.nextLine();
                    if (userSymbols.matches("\\d+")) {
                        symbols = Integer.parseInt(userSymbols);
                        if (symbols > 36) {
                            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).\n");
                        } else if (symbols < length) {
                            System.out.println("Error: it's not possible to generate a code with a length of " + length +
                                    " with " + symbols + " unique symbols.\n");
                        } else {
                            generateCode(length, symbols);
                        }
                    } else {
                        System.out.println("Error: \"" + userSymbols + "\" isn't a valid number.\n");
                    }
                }
            } else {
                System.out.println("Error: \"" + userLength + "\" isn't a valid number.\n");
            }
        } while (this.code == null || this.code.length < 1);
    }

    private void generateCode(int length, int symbols) {
        this.code = new char[length];
        set = new HashSet<>();
        int i = 0;
        do {
            int random = (int) (Math.random() * symbols);
            char c = (char) (random < 10 ? random + '0' : random + 'a' - 10);
            if (set.add(c)) {
                this.code[i] = c;
                i++;
            }
        } while (set.size() < this.code.length);

        if (symbols == 1) {
            System.out.println("The secret is prepared: " + "*".repeat(this.code.length) + " (0).");
        } else if (symbols == 11) {
            System.out.println("The secret is prepared: " + "*".repeat(this.code.length) + " (0-9, a).");
        } else if (symbols >= 12 && symbols <= 36) {
            System.out.println("The secret is prepared: " + "*".repeat(this.code.length) + " (0-9, a-" + (char) (symbols + 'a' - 11) + ").");
        } else if (symbols <= 10) {
            System.out.println("The secret is prepared: " + "*".repeat(this.code.length) + " (0-" + (symbols - 1) + ").");
        }
    }
}
