import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class Yatzy {

	private int[] dice;
    
    public Yatzy(int d1, int d2, int d3, int d4, int d5)
    {
        this.dice = new int[]{d1,d2,d3,d4,d5};
    }

    public int chance()
    {
    	return Arrays.stream(this.dice).sum();
    }

    public int yatzy()
    {
    	return Arrays.stream(this.dice).boxed().collect(Collectors.toSet()).size()==1 ? 50 : 0;

    }
    
    public int ones() {
    	return this.sumNumber(1);
    }

    public int twos() {
    	return this.sumNumber(2);
    }

    public int threes() {
    	return this.sumNumber(3);
    }

    public int fours()
    {
    	return this.sumNumber(4);
    }

    public int fives()
    {
    	return this.sumNumber(5);
    }

    public int sixes()
    {
    	return this.sumNumber(6);
    }

    public int pair()
    {
    	return this.getPairs().size()>=1 ? this.getPairs().get(0)*2 : 0;
    }

    public int twoPairs()
    {
    	return this.getPairs().size()>=2 ?  (this.getPairs().get(0)+ this.getPairs().get(1))*2 :  0;	
    }

    public int threeOfAKind()
    {
    	return this.nDiceOfKind(3);
    }
    
    public int fourOfAKind()
    {
    	return this.nDiceOfKind(4);
    }

    public int smallStraight()
    {
    	int[] smallStraightArray = new int [] {1, 2, 3, 4,5};
    	if(this.checkStraight(smallStraightArray))
    		return 15;
    	return 0;

    }

    public int largeStraight()
    {
    	int[] smallStraightArray = new int [] {2, 3, 4,5,6};
    	if(this.checkStraight(smallStraightArray))
    		return 20;
    	return 0;
    }

    public int fullHouse()
    {
    	int pairValue=this.diceEqualsN(2);  	
    	int tripleValue=this.diceEqualsN(3);

    	if(pairValue != tripleValue && pairValue!=0 && tripleValue!=0)
    		return pairValue * 2 + tripleValue * 3;
    	
    	return 0;
    }
    
    // function to calculate the sum of the dice that reads the given number
    private int sumNumber(int number)
    {
    	int sum = 0;
        for(int i=0;i<this.dice.length;i++)
        {
        	if(this.dice[i]==number)
        		sum+=number;
        }

        return sum;
    }
    
    // function return an array of occurences of dice
    private int[] diceOccurrences()
    {
    	int[] occurrences = new int[6];
    	for(int i=0;i<occurrences.length-1;i++)
    	{
    		occurrences[this.dice[i]-1]++;
    	}
    	return occurrences;
    }
    
    // function to score N dice of kinds    
    private int nDiceOfKind(int kinds)
    {
    	int[] occurrences = this.diceOccurrences();
    	for (int i = occurrences.length-1; i >=0; i--)
    	{
    		if (occurrences[i] >= kinds)  
                return (i+1)*kinds;    
    	}   
        return 0;
    }
    // function to get dice value repeated N time
    private int diceEqualsN(int N)
    {
    	int[] occurrences = this.diceOccurrences();
    	for (int i = occurrences.length-1; i >=0; i--)
    	{
    		if (occurrences[i] == N)  
                return (i+1);    
    	}   
        return 0;
    }
    
    //function to check if the dice is small or large    
    private boolean checkStraight(int[] straightArray)
    {
    	int[] sortedDice=this.dice;
    	Arrays.sort(sortedDice);
    	if(Arrays.equals(sortedDice, straightArray))
    		return true;
    	return false;
    }
    
    // function to get pairs value on revresed array list
    // 5,3,6,6,5 ===> 6,5
    // if we want the highest pair ==> getPairs().get(0)
    // if we want the 2 highest pair ==> getPairs().get(0) and getPairs().get(1) 
    private ArrayList<Integer> getPairs()
    {
    	int[] occurrences = this.diceOccurrences();
    	ArrayList<Integer> pairs = new ArrayList<Integer>();
    	for (int i = 0; i <occurrences.length; i++)
        {
        	if (occurrences[i] >= 2) 
        	{
        		pairs.add(i+1);
            }  
        }   
    	Collections.reverse(pairs);
    	return pairs;
    }
}



