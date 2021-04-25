import java.util.Random;
import java.util.Scanner;

//HAVE A FIFTH OPTION OF RANDOM OPERATION

public class quickMathConsoleVersion {
	
	static Scanner scanner = new Scanner(System.in);
	static int numQuestions;
	static int questionNumber = 0;
	static boolean newQuestionSet = true; //we actually don't need this variable, but for clarity, we shall.
	static int QUESTIONTYPE;
	static int DIFFICULTY;
	
	//main
	public static void main(String[] args) {
		
		
		//initialize the problem set
		System.out.println("How many questions?");
		numQuestions = scanner.nextInt();
		System.out.println("What operation? type 1 for addition, 2 for subtraction, 3 for multiplicaiton, and 4 for division.");
		QUESTIONTYPE = scanner.nextInt();
		System.out.println("What difficulty? From 1(easy) to 3(hard)");
		DIFFICULTY = scanner.nextInt();
		
		if (numQuestions > 0){ System.out.println("Answer a question with \"exit\" to exit the problem set");}
		else {System.out.println();}
		
		
		while(questionNumber < numQuestions && newQuestionSet){
			genQuestion(QUESTIONTYPE, DIFFICULTY);
		}
		
		System.out.println("Thanks for playin, Cya!");
		
	}
	
	public static void genQuestion(int questionType, int difficulty){
		String userAnswer;
		int userAnswerInt;

		Question newQuestion = new Question(questionType, difficulty);
		System.out.println(newQuestion.getQuestionText());
		userAnswer = scanner.next();
		
		//check if they wanna stop
		if (userAnswer.toUpperCase().equals("EXIT")){questionNumber = numQuestions;}
		
		else{
			userAnswerInt = Integer.parseInt(userAnswer);
			if (userAnswerInt == newQuestion.getAnswer()){
				System.out.println("Nice!");
			}
			else 
				if (userAnswerInt > 0 && userAnswerInt == newQuestion.getAnswer() * -1 && questionType == 2 ){
				System.out.println("Zouch! the answer was " + newQuestion.getAnswer() + ". You forgot the negative!" );
				}
			
				else System.out.println("Whoopsies...the answer was actually " + newQuestion.getAnswer());
		}
		
			questionNumber++;
			
			if (questionNumber == numQuestions){
				newQuestionSet = false;
				System.out.println("Would you like to go for another round? Y/N");
				if (scanner.next().toLowerCase().equals("y")){
					newQuestionSet = true;
					
					System.out.println("How many more questions?");
					numQuestions += scanner.nextInt();
					System.out.println("What operation? type 1 for addition, 2 for subtraction, 3 for multiplicaiton, and 4 for division.");
					QUESTIONTYPE = scanner.nextInt();
					System.out.println("Difficulty?");
					DIFFICULTY = scanner.nextInt();
					
					System.out.println("Here's to more math! (Cheers)");
				}
			}
	}

}
