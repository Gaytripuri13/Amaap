import java.util.*;


public class DiceGame{
   public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter name of 1st Player - ");
        String player_1st = sc.nextLine();

        System.out.print("Enter name of 2nd Player - ");
        String player_2nd = sc.nextLine();

        Dice dice = new Dice();
           Dice p1_Dice=new Dice();
           Dice p2_Dice =new Dice();

        int[] player1_dice = p1_Dice.throwDice();
        int[] player2_dice = p2_Dice.throwDice();

        System.out.println("Below are dice values for " + player_1st + "-");
        p1_Dice.printValues();
        String p1_combo = p1_Dice.getCombination();
        System.out.println("Combination for " + player_1st + " is " + p1_combo);

        System.out.println("Below are dice values for " + player_2nd+ "-");
        p2_Dice.printValues();
        String p2_combo = p2_Dice.getCombination();
        System.out.println("Combination for " + player_2nd + " is " + p2_combo);

        if (p1_combo.equals(p2_combo)) {
            System.out.println("Winner is " + (p1_Dice.getHighestValue() > p2_Dice.getHighestValue() ? player_1st : player_2nd) + "..!!");
        } else {
            System.out.println("Winner is " + (p1_Dice.getRank(p1_combo) > p2_Dice.getRank(p2_combo) ? player_1st : player_2nd) + "..!!");
        }
    }
}

class Dice {
    private int[] dice;
     Random random = new Random();

    public Dice() {
        this.dice = new int[5];
        throwDice();
    }

    public int[] throwDice() {
        for (int i = 0; i <dice.length; i++) {
            dice[i] = 1+random.nextInt(6);
        }
        Arrays.sort(dice);
        return dice;
    }

    public void printValues() {
        for (int i : dice) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public String getCombination() {
        int f[] = new int[7];
        for (int i : dice) {
            f[i]++;
        }

        if (f[6] == 5) 
       return "Five of a kind";
        for (int i = 1; i <= 6; i++) {
            if (f[i] == 4) 
               return "Four of a kind";
            else if (f[i] == 3) {
                for (int j = 1; j <= f.length-1; j++) {
                    if (f[j] == 2) return "Three of a kind and a pair";
                }
                return "Three of a kind";
            } else if (f[i] == 2)
                 return "A pair";
        }
        return "Highest number";
    }

    public static int getRank(String combi) {
        if ("Five of a kind".equals(combi)) {
            return 6;
        } else if ("Four of a kind".equals(combi)) {
            return 5;
        } else if ("Three of a kind and a pair".equals(combi)) {
            return 4;
        } else if ("Three of a kind".equals(combi)) {
            return 3;
        } else if ("A pair".equals(combi)) {
return 2;
        } else {
            return 1;
        }
    }

    public int getHighestValue() {
        return dice[4];
    }
}