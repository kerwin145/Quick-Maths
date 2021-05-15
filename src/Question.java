import java.util.ArrayList;
import java.util.Random;


public class Question{

	int num1;
	int num2;
	String questionText;
	int answer;
	int type; //goes from 0 to 3. 0 for addition
	int difficulty;
	boolean onlyPositive, perfectDivisor;
	Random gen= new Random();

	//3d goes num1 then num2
	//rows goes: add, subtract, mult, div. 
	//Columnns go (diff1 low, diff1 high, diff2 low, diff2 high, diff low, diff3 high, diff4 low, diff4 high)
	int[][][] numBounds = {
						//num1
						{{1, 10, 10, 100, 10, 1000, 10, 10000}, //addition bounds
						{1, 20, 10, 200, 100, 5000, 1000, 10000}, //subtraction bounds
						{1, 10, 1, 10, 10, 50, 10, 100}, //multiplication bounds
						{1, 10, 1, 40, 1, 100, 1, 1000}}, //division bounds
						//num2
						{{1, 10, 10, 1000, 10, 10000, 10, 10000}, //addition bounds
						{1, 20, 10, 200, 100, 5000, 1000, 10000}, //subtraction bounds
						{1, 10, 10, 50, 10, 50, 10, 100}, //multiplication bounds
						{1, 10, 2, 10, 2, 20, 2, 100}} //division bounds
						}; //division Bounds
	//note to self: division bounds and number generation is not the same. 
	
	//3d goes num1 and the num2. num2 may be divisible by num1 * num2  (we will add random number to the end)
	//row goes difficulty
	//columns go num1 and then num2
	int[][][]  numBoundsIsItDivisible = {
									    {{1, 10}, {10, 100}, {100, 1000}, {100, 1000}},
									    {{2, 5}, {2,10}, {3,12}, {3,16}}
									    };
	
	//vanilla question. Who needs inheritence lol
	public Question(ArrayList<Integer> types, int difficulty, boolean onlyPositive, boolean perfectDivisor) {
		int randOperation = gen.nextInt(types.size());//get random index from types list
		type = types.get(randOperation); //chhoose that index. 
		
		this.onlyPositive = onlyPositive;
		this.perfectDivisor = perfectDivisor;
		
		//generate numbers. Type correlates to problem type. Column correlates to the diff and low high
		setVanillaNums(type, difficulty);
		genQuestionText(type, num1, num2);
	}
	
	public Question(int type, int difficulty, int num2){
		setSpefOperationNum1(type, difficulty, num2);
		genQuestionText(type, num1, num2);
	}
	
	//is it divisible
	public Question(int difficulty){
		setNumsIsItDivisible(difficulty);
	}
	
	public int getBoundedRand(int low, int high){
		//lower bound is inc, upper is ex
		return gen.nextInt(high - low) + low;
	}
	
	public void setVanillaNums(int type, int difficulty){
		num1 = getBoundedRand(numBounds[1][type][difficulty * 2], numBounds[1][type][difficulty * 2+1]);
		num2 = getBoundedRand(numBounds[0][type][difficulty * 2], numBounds[0][type][difficulty * 2+1]);
		
		if(type == 0) answer = num1+num2;
		
		//make num1 the bigger number
		if(type == 1){
			if(onlyPositive){
				int temp;
				if (num2 > num1){
					temp = num2; //set temp to high number
					num2 = num1; //change higher number to low number
					num1 = temp; //set low number to temp, which has been saved as the high number
				}
			}//only positive
			answer = num1-num2;
		}
		if(type == 2) answer = num1*num2;

		if(type == 3){
			//lets say num1 = 2000 and num2 = 10. 
			//I want to ask what is num1*num2 / num2, and the answer will be num1
			//num2 will be the divisor. 
			int temp = num1;
			num1 = num1 * num2;
			answer = temp;
			if(!perfectDivisor){
				num1 += gen.nextInt(num2);
				//add a random amount of number less than num1 so you won't know if its perfect or not. 
			}
		}

	}
	
	public void setSpefOperationNum1(int type, int difficulty, int num2){
		num1 = getBoundedRand(numBounds[1][type][difficulty * 2]* 10, numBounds[1][type][difficulty * 2+1] * 10);
	
		//num2 is already set
		
		if(type == 0)answer = num1+num2;
		if(type == 1)answer = num1-num2;
		if(type == 2)answer = num1*num2;
		if(type == 3){
			int temp = num1;
			num1 = num1 * num2;
			answer = temp;
			num1 += gen.nextInt(num2);
		}//type == 3
	}
	
	public void setNumsIsItDivisible(int difficulty){
		num1 = getBoundedRand(numBoundsIsItDivisible[0][difficulty][0], numBoundsIsItDivisible[0][difficulty][1]);
		num2 = getBoundedRand(numBoundsIsItDivisible[1][difficulty][0], numBoundsIsItDivisible[1][difficulty][1]);
		
		int result = num1 * num2;
		
		int addRemainder = gen.nextInt(2); //dual purpose
		if(addRemainder == 0){
			addRemainder = gen.nextInt(num2-1) + 1;
			result += addRemainder;
			answer = 0;
		}
		else {
			addRemainder = 0; //for good measure.
			answer = 1; //if no remainder added, then the answer will be divisible. 
		}
		
		questionText =  "Is " + result + " divisible by " + num2 + " ?";
		System.out.println("Answer :" + answer + "Remainder is " + addRemainder);
		
	}

	private void genQuestionText(int type, int num1, int num2){
		if (type == 0) questionText = num1 + " + " + num2 + " = ";
		
		if (type == 1) questionText = num1 + " - " + num2 + " = ";

		if (type == 2) questionText = num1 + " * " + num2 + " = ";
		//num1 will be greater than num2-- Check the get boundedRand method.
		if (type == 3) questionText = num1 + " / " + num2 + " = ";
	}
	
	public int getNum1(){return num1;}
	public int getNum2(){return num2;}
	public String getQuestionText(){return questionText;}
	public int getAnswer(){return answer;}
	public int getType(){return type;}
	
}
