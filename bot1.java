import java.util.*;
import java.io.*;
import java.nio.*;
public class bot1 extends HandEval
{
	public static boolean owed = false;
	public static boolean button = false;
	public static double win;

 public static void main(String args[])
	{		//taking from Handeval
			double handwin;
			bot1 x = new bot1(); // new instance of bot

			String info ="nothing";
		/*	try {info = x.scan();}
			catch(FileNotFoundException Ex){info = "not found";} // reads from file saves string as info

			// String handnum = info.substring(0,info.indexOf("D"));
			// System.out.println(handnum);

			String button = info.substring(info.lastIndexOf("D") + 1,info.indexOf("A"));
			int butpos = Integer.parseInt(button); //prints button position
			// System.out.println(button);

			// starting hand
			String number = info.substring(info.lastIndexOf("A") + 1,info.indexOf("B"));
			String number2 = info.substring(info.lastIndexOf("B") + 1);
			int holecard1 = Integer.parseInt(number);
			int holecard2 = Integer.parseInt(number2); // gets starting hand
			// System.out.println(holecard1);
			// System.out.println(holecard2);



			int rank1 = holecard1/4; // assigns rank and suit to starting hands for handevaluator
			int suit1 = holecard1%4;
			int rank2 = holecard2/4;
			int suit2 = holecard2%4;



			HandEval hand = new HandEval();
			win = hand.HandEvaluation(rank1,suit1,rank2,suit2); // runs starting handevaluator, saves double win
			// System.out.println(win);
			String result =x.start(); //runs start evalutor
			// have a way to make another choice
			x.write(result); // wrties action to file    */    // TESTING comment back in *********************************

// **************************************************FLOP****************************************************
			try {info = x.scan();}
			catch(FileNotFoundException Ex){info = "not found";} // reads from file saves string as info

		/*  <hand number> D <dealer button position> P <action by all players in order from first to act, e.g. fccrf...>
			F <flop card 1> F <flop 2> F <flop 3>
			F <flop action starting with first player to act>
			T <turn card> T <turn action> R <river card> R <river action>*/


			// format change in casino to ensure cards are written as 07 - instead of 7 would make this section simple indexing of a string
			int first = info.indexOf("F");
			int second = info.indexOf("F", first + 1);
			int third = info.indexOf("F", second+1);
			int act1 = info.indexOf("c",third+1);
			int act2 = info.indexOf("r",third+1);
			int act3 = info.indexOf("f",third+1); // first move made after last instance of F

			// if an act doesnt occur it defaults to -1, i need to find the first index of an action after the third "F" in order to find the associated card
			if(act1<0)
				act1=100000;
			if(act2<0)
				act2=10000;
			if(act3<0)
				act3=10000;
			// String flop3 = info.substring(third+1,third+3);        if cards like 7 are written as 07
			String flop_3;

			if(act1<act2&&act1<act3)
				flop_3 = info.substring(third+1,act1);
				else if(act2<act3)
					flop_3 = info.substring(third+1,act2);
					else
						flop_3 = info.substring(third+1,act3);

			String flop_1 = info.substring(first+1,second);
			String flop_2 = info.substring(second+1,third);

			int flop1 = Integer.parseInt(flop_1);   // could parse directly i.e Integer.parseInt(info.substring(first+1,second));
			int flop2 = Integer.parseInt(flop_2);
			int flop3 = Integer.parseInt(flop_3);

			System.out.println(flop_1 + " " + flop_2 + " " + flop_3);
			int rank1 = 21/4; // assigns rank and suit to starting hands for handevaluator
			int suit1 = 21%4;
			int rank2 = 43/4;
			int suit2 = 43%4;
			int rank3 = flop1/4;
			int suit3 = flop1%4;
			int rank4 = flop2/4;
			int suit4 = flop2%4;
			int rank5 = flop3/4;
			int suit5 = flop3%4;

			HandEval hand = new HandEval();
			win = hand.FlopEvaluation(rank1,suit1,rank2,suit2,rank3,suit3,rank4,suit4,rank5,suit5);
			System.out.print(win);
			 String result=x.flop();
			 x.write(result);
// ***********************************************Turn************************************
			 try {info = x.scan();}
			catch(FileNotFoundException Ex){info = "not found";}

			 String turn1 = info.substring(info.indexOf("T")+1,info.lastIndexOf("T"));
			 int turn = Integer.parseInt(turn1);

			 int rank6 = turn/4;
			 int suit6 = turn%4;

			 win = hand.TurnEvaluation(rank1,suit1,rank2,suit2,rank3,suit3,rank4,suit4,rank5,suit5,rank6,suit6);
			 System.out.println(win);
			 result=x.turn();
			 x.write(result);

// ************************************************RIVER*********************************
			 try {info = x.scan();}
			catch(FileNotFoundException Ex){info = "not found";}

			 String river1 = info.substring(info.indexOf("R")+1,info.lastIndexOf("R"));
			 int river = Integer.parseInt(river1);

			 int rank7 = river/4;
			 int suit7 = river%4;
			 win = hand.RiverEvaulation(rank1,suit1,rank2,suit2,rank3,suit3,rank4,suit4,rank5,suit5,rank6,suit6,rank7,suit7);
			 System.out.println(win);
			 result= x.river();
			 x.write(result);

			// System.out.println(result);

			//hand evaluation
	}
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
			String flop()
			{
				String action;
				//takes handwin and otherinfo to make decision
				//if handwin and otherinfo dictates a high win chance
				if(owed &&  win > 60){ // win value alterations *********************
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
			String turn()
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
			String river()
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

String scan() throws FileNotFoundException
	{
	try
		{
			Scanner in = new Scanner(new FileReader("C:/Users/Patrick/Desktop/Filename.txt"));
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
        String fileName = "testing.txt";

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
