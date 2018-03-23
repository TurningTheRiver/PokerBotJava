import java.util.*;
import java.io.*;
import java.nio.*;
import java.util.regex.*;

public class bot1 extends HandEval
{
	public static boolean owed = false;
	public static boolean button = false;
	public static double win;

 public static void main(String args[])
	{		//taking from Handeval
			double handwin;
			bot1 x = new bot1(); // new instance of bot
			while(true){

			boolean run = true;
			while(run)
			{
			String info ="nothing";

			// gets holecards
			int holecard1=0;
			int holecard2=0;
			boolean start = true;
			while(start){
			try {info = x.scan();}
			catch(FileNotFoundException Ex){info = "not found";} // reads from file saves string as info
			String re1=".*?";	// Non-greedy match on filler
		    String re2="\\d+";	// Uninteresting: int
		    String re3=".*?";	// Non-greedy match on filler
		    String re4="\\d+";	// Uninteresting: int
		    String re5=".*?";	// Non-greedy match on filler
		    String re6="(\\d+)";	// Integer Number 1
		    String re7="(B)";	// Any Single Word Character (Not Whitespace) 1
		    String re8="(\\d+)";	// Integer Number 2

		    Pattern p = Pattern.compile(re1+re2+re3+re4+re5+re6+re7+re8,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		    Matcher m = p.matcher(info);
		    if (m.find())
		    {
		        String int1=m.group(1);
		        String w1=m.group(2);
        		String int2=m.group(3);
		        holecard1=Integer.parseInt(int1);
		        holecard2=Integer.parseInt(int2);
		        System.out.print("("+holecard1+")"+"("+holecard2+")"+"\n");
		        start = false; // end loop
		    }
			}

			int rank1 = holecard1/4; // assigns rank and suit to starting hands for handevaluator
			int suit1 = holecard1%4;
			int rank2 = holecard2/4;
			int suit2 = holecard2%4;

			HandEval hand = new HandEval();
			win = hand.HandEvaluation(rank1,suit1,rank2,suit2); // runs starting handevaluator, saves double win
			System.out.println(win);
			String result =x.start(); //runs start evalutor
			// have a way to make another choice
			x.write(result); // wrties action to file
			if(result=="f")
			 {
			 	System.out.println("end");
			 	break; // exit hand wait for new hand to be dealt
			 }
// **************************************************FLOP****************************************************

			int flop1=0;
			int flop2=0;
			int flop3=0;
			// gets flop
			start = true;
			while(start){
			try {info = x.scan();}
			catch(FileNotFoundException Ex){info = "not found";} // reads from file saves string as info
				String re1=".*?";	// Non-greedy match on filler
			    String re2="\\d+";	// Uninteresting: int
			    String re3=".*?";	// Non-greedy match on filler
			    String re4="\\d+";	// Uninteresting: int
			    String re5=".*?";	// Non-greedy match on filler
			    String re6="(\\d+)";	// Integer Number 1
			    String re7=".*?";	// Non-greedy match on filler
			    String re8="(\\d+)";	// Integer Number 2
			    String re9=".*?";	// Non-greedy match on filler
			    String re10="(\\d+)";	// Integer Number 3
			    String re11="(F)";	// Any Single Word Character (Not Whitespace) 1

			    Pattern p = Pattern.compile(re1+re2+re3+re4+re5+re6+re7+re8+re9+re10+re11,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
			    Matcher m = p.matcher(info);
			    if (m.find())
			    {
			        String int1=m.group(1);
			        String int2=m.group(2);
			        String int3=m.group(3);
			        flop1=Integer.parseInt(int1);
		        	flop2=Integer.parseInt(int2);
		        	flop3=Integer.parseInt(int3);
			        System.out.print("("+int1.toString()+")"+"("+int2.toString()+")"+"("+int3.toString()+")"+"\n");
			        start=false;
			    }
			}
			int rank3 = flop1/4;
			int suit3 = flop1%4;
			int rank4 = flop2/4;
			int suit4 = flop2%4;
			int rank5 = flop3/4;
			int suit5 = flop3%4;

			win = hand.FlopEvaluation(rank1,suit1,rank2,suit2,rank3,suit3,rank4,suit4,rank5,suit5);
			System.out.println(win);
			 result=x.flop(win);
			 System.out.println(result);
			 x.write(result);
			 if(result=="f")
			 {
			 	System.out.println("end");
			 	break; // exit hand wait for new hand to be dealt
			 }
// ***********************************************Turn************************************

			int turn = 0;
			//get turn
			start = true;
			while(start){
			try {info = x.scan();}
			catch(FileNotFoundException Ex){info = "not found";} // reads from file saves string as info
				String re1=".*?";	// Non-greedy match on filler
			    String re2="\\d+";	// Uninteresting: int
			    String re3=".*?";	// Non-greedy match on filler
			    String re4="\\d+";	// Uninteresting: int
			    String re5=".*?";	// Non-greedy match on filler
			    String re6="\\d+";	// Uninteresting: int
			    String re7=".*?";	// Non-greedy match on filler
			    String re8="\\d+";	// Uninteresting: int
			    String re9=".*?";	// Non-greedy match on filler
			    String re10="\\d+";	// Uninteresting: int
			    String re11=".*?";	// Non-greedy match on filler
			    String re12="(\\d+)";	// Integer Number 1
			    String re13="(T)";	// Any Single Word Character (Not Whitespace) 1

			    Pattern p = Pattern.compile(re1+re2+re3+re4+re5+re6+re7+re8+re9+re10+re11+re12+re13,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
			    Matcher m = p.matcher(info);
			    if (m.find())
			    {
			        String int1=m.group(1);
			        turn = Integer.parseInt(int1);
			        System.out.print("("+int1.toString()+")"+"\n");
			        start = false;
			    }
					}

			 int rank6 = turn/4;
			 int suit6 = turn%4;

			 win = hand.TurnEvaluation(rank1,suit1,rank2,suit2,rank3,suit3,rank4,suit4,rank5,suit5,rank6,suit6);
			 System.out.println(win);
			 result=x.turn(win);
			 x.write(result);
			 if(result=="f")
			 {
			 	System.out.println("end");
			 	break; // exit hand wait for new hand to be dealt
			 }
// ************************************************RIVER*********************************
			 int river = 0;
			//get turn
			start = true;
			while(start){
			try {info = x.scan();}
			catch(FileNotFoundException Ex){info = "not found";} // reads from file saves string as info
				    String re1=".*?";	// Non-greedy match on filler
				    String re2="\\d+";	// Uninteresting: int
				    String re3=".*?";	// Non-greedy match on filler
				    String re4="\\d+";	// Uninteresting: int
				    String re5=".*?";	// Non-greedy match on filler
				    String re6="\\d+";	// Uninteresting: int
				    String re7=".*?";	// Non-greedy match on filler
				    String re8="\\d+";	// Uninteresting: int
				    String re9=".*?";	// Non-greedy match on filler
				    String re10="\\d+";	// Uninteresting: int
				    String re11=".*?";	// Non-greedy match on filler
				    String re12="\\d+";	// Uninteresting: int
				    String re13=".*?";	// Non-greedy match on filler
				    String re14="(\\d+)";	// Integer Number 1
				    String re15="(R)";	// Any Single Character 1

				Pattern p = Pattern.compile(re1+re2+re3+re4+re5+re6+re7+re8+re9+re10+re11+re12+re13+re14+re15,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
			    Matcher m = p.matcher(info);
			    if (m.find())
			    {
			        String int1=m.group(1);
			        river= Integer.parseInt(int1);
			        System.out.print("("+int1.toString()+")"+"\n");
			        start = false;
			    }
					}

			 int rank7 = river/4;
			 int suit7 = river%4;
			 win = hand.RiverEvaulation(rank1,suit1,rank2,suit2,rank3,suit3,rank4,suit4,rank5,suit5,rank6,suit6,rank7,suit7);
			 System.out.println(win);
			 result= x.river(win);
			 x.write(result);
			 System.out.println("end of this hand");
			}
	}
	}
//*********************************To determine action******************************


			String start()
			{
				String action;
				//takes handwin and otherinfo to make decision
				//if handwin and otherinfo dictates a high win chance
				if(owed){
					action = "r";
				}
					//if handwin and otherinfo dictates a medium win chance
					else if(owed){
							action = "c";
					}
						//if handwin and otherinfo dictates a low win chance
						else if(owed){
								action = "f";
						}
							//if unsure defaults to c for information gathering
							else{
								action = "c";
							}

				return action;
			}
			String flop(double win)
			{
				String action;
				//takes handwin and otherinfo to make decision
				//if handwin and otherinfo dictates a high win chance
				if(owed &&  win > 60){ // win value alterations *********************
					action = "r";
				}
					//if handwin and otherinfo dictates a medium win chance
					else if(owed && win > 50){
							action = "c";
					}
						//if handwin and otherinfo dictates a low win chance
						else if(win < 50){
								action = "f";
						}
							//if unsure defaults to c for information gathering
							else{
							action = "c";
							}

				return action;
			}
			String turn(double win)
			{
				String action;
				//takes handwin and otherinfo to make decision
				//if handwin and otherinfo dictates a high win chance
				if(owed &&  win > 60){ // win value alterations *********************
					action = "r";
				}
					//if handwin and otherinfo dictates a medium win chance
					else if(owed && win > 50){
							action = "c";
					}
						//if handwin and otherinfo dictates a low win chance
						else if(win < 50){
								action = "f";
						}
							//if unsure defaults to c for information gathering
							else{
							action = "c";
							}

				return action;
			}
			String river(double win)
			{
				String action;
				//takes handwin and otherinfo to make decision
				//if handwin and otherinfo dictates a high win chance
				if(owed &&  win > 60){ // win value alterations *********************
					action = "r";
				}
					//if handwin and otherinfo dictates a medium win chance
					else if(owed && win > 50){
							action = "c";
					}
						//if handwin and otherinfo dictates a low win chance
						else if(win < 50){
								action = "f";
						}
							//if unsure defaults to c for information gathering
							else{
							action = "c";
							}

				return action;
			}

String scan() throws FileNotFoundException
	{
	try
		{
			Scanner in = new Scanner(new FileReader("C:/MinGW/pokercasino-master/botfiles/casinoToBot3"));
			StringBuilder sb = new StringBuilder();

					while(in.hasNext())
						{
    				sb.append(in.next());

						}
						in.close();
						String out = sb.toString();
						return out;
		}
 	catch (FileNotFoundException ex)
    	{
        return "error not found";
    	}
	}

void write(String x) {

        // The name of the file to open.
        String fileName = "C:/MinGW/pokercasino-master/botfiles/botToCasino3";

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(x);
            // Always close files.
            bufferedWriter.close();
        	}
        catch(IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        }
    }
}
