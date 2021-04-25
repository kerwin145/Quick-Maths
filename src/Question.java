import java.util.ArrayList;
import java.util.Random;


public class Question{

	int num1;
	int num2;
	String questionText;
	int answer;
	int type; //goes from 0 to 3. 0 for addition
	int difficulty;
	Random gen= new Random();

	//rows goes: add, subtract, mult, div. 
	//Columnns go (diff1 low, diff1 high, diff2 low, diff2 high, diff low, diff3 high, diff4 low, diff4 high)
	int[][][] numBounds = {
						//num1
						{{1, 10, 10, 100, 10, 1000, 10, 10000}, //addition bounds
						{1, 20, 10, 200, 100, 5000, 1000, 10000}, //subtraction bounds
						{1, 10, 1, 10, 10, 50, 10, 100}, //multiplication bounds
						{1, 30, 1, 1000, 1, 5000, 1, 10000}}, //division bounds
						//num2
						{{1, 10, 10, 1000, 10, 10000, 10, 10000}, //addition bounds
						{1, 20, 10, 200, 100, 5000, 1000, 10000}, //subtraction bounds
						{1, 10, 10, 50, 10, 50, 10, 100}, //multiplication bounds
						{1, 10, 2, 10, 1, 20, 1, 100}} //division bounds
						}; //division Bounds
	
	
	public Question(ArrayList<Integer> types, int difficulty) {
		int randOperation = gen.nextInt(types.size());//get random index from types list
		type = types.get(randOperation); //chhoose that index. 
		
		
		//generate numbers. Type correlates to problem type. Column correlates to the diff and low high
		setNums(type, difficulty);
		
		if (type == 0){
			questionText = num1 + " + " + num2 + " = ";
			answer = num1+num2;
		}
		if (type == 1){ 
			questionText = num1 + " - " + num2 + " = ";
			answer = num1-num2;
		}
		if (type == 2){ 			
			questionText = num1 + " * " + num2 + " = ";
			answer = num1*num2;
		}
		if (type == 3){ 
			//num1 will be greater than num2-- Check the get boundedRand method.
			questionText = num1 + " / " + num2 + " = ";
			answer = (int)(num1/num2);
		}

	}
	
	public int getNum1(){return num1;}
	public int getNum2(){return num2;}
	public String getQuestionText(){return questionText;}
	public int getAnswer(){return answer;}

	public int getBoundedRand(int low, int high){
		//lower bound is inc, upper is ex
		return gen.nextInt(high - low) + low;
	}
	
	public void setNums(int type, int difficulty){
		num1 = getBoundedRand(numBounds[1][type][difficulty * 2], numBounds[1][type][difficulty * 2+1]);
		num2 = getBoundedRand(numBounds[0][type][difficulty * 2], numBounds[0][type][difficulty * 2+1]);
		
		//for division, you want num1 to be the bigger number
		if (type == 3){
			int temp;
			if (num2 > num1){
				temp = num2; //set temp to high number
				num2 = num1; //change higher number to low number
				num1 = temp; //set low number to temp, which has been saved as the high number
			}
		}

	}
	
	public int getType(){return type;}
	
}
