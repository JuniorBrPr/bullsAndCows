package bullscows;

import java.util.Arrays;
import java.util.Scanner;

public class BullsCows {
    private int turn;
    private int[] code;
    final private static Scanner in = new Scanner(System.in);

    public BullsCows() {
        this.turn = 0;
        setCode();
    }

    public void game() {
        boolean guessed = false;

        System.out.println("The secret code is prepared: ****.\n\n");

        do {
            ++this.turn;
            String userInput;
            String bullGrade;
            String cowGrade;
            boolean userInputCorrect = false;
            int[] input = new int[4];

            System.out.println("Turn " + this.turn + ". Answer:");

            do {
                userInput = in.nextLine();
                if (userInput == null) {
                    System.out.println("\nInvalid input!\nTry again!\n");
                } else {
                    if (!userInput.matches("\\d\\d\\d\\d")) {
                        System.out.println("\nWrong code pattern!\nShould be a 4 digit code !\n");
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

            for (int i = 0; i < userInput.length(); i++) {
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
                System.out.println("Grade: " + bullGrade + " and " + cowGrade + ".");
            } else if (!cowGrade.equals("")) {
                System.out.println("Grade: " + cowGrade + ".");
            } else if (!bullGrade.equals("")) {
                System.out.println("Grade: " + bullGrade + ".");
            } else {
                System.out.println("Grade: None.");
            }

            if (bulls == 4) {
                System.out.println("Congrats! The secret code is " + code[0] + code[1] + code[2] + code[3] + ".");
                guessed = true;
            }

            System.out.println();
        } while (!guessed);
    }

    private void setCode() {
        this.code = new int[4];
        int intCode = (int) Math.round(Math.random() * 10000);
        String temp = Integer.toString(intCode);

        if (intCode > 999) {
            for (int i = 0; i < this.code.length; i++) {
                this.code[i] = Integer.parseInt(String.valueOf(temp.charAt(i)));
            }
        } else if (intCode > 99) {
            this.code[0] = 0;
            for (int i = 1; i < this.code.length - 1; i++) {
                this.code[i] = Integer.parseInt(String.valueOf(temp.charAt(i - 1)));
            }
        } else if (intCode > 9) {
            for (int i = 0; i < this.code.length - 2; i++) {
                this.code[i] = 0;
            }
            for (int i = 2; i < this.code.length - 1; i++) {
                this.code[i] = Integer.parseInt(String.valueOf(temp.charAt(i - 2)));
            }
        } else if (intCode > 0) {
            for (int i = 0; i < this.code.length - 1; i++) {
                this.code[i] = 0;
            }
            this.code[3] = Integer.parseInt(String.valueOf(temp.charAt(0)));
        } else {
            Arrays.fill(this.code, 0);
        }
    }
}
