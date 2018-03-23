import java.util.Random;
import java.util.*;

public class HandEval
{
	Random rand = new Random();
	// heads up only, just need to change values in order for hand evaulator to work for more people
	// also add positioning on table, i.e dealer etc
	double HandEvaluation(int rank1, int suit1, int rank2, int suit2)
	{
		if(rank1 == rank2)
		{
			double ret =0.0;
			// hand winrate taken from - https://caniwin.com/texasholdem/preflop/heads-up.php
			switch(rank1){
			case 1:		ret= 	49.38;	break; // twos..
			case 2:		ret= 	52.83;	break;
			case 3:		ret= 	56.25;	break; // fours..
			case 4:		ret= 	59.64;	break;
			case 5:		ret=	62.70;	break;
			case 6:		ret= 	65.72;	break; // sevens..
			case 7:		ret= 	68.71;	break;
			case 8:		ret= 	71.66;	break;
			case 9:		ret= 	74.66;	break; // tens..
			case 10:		ret= 	77.15;	break;
			case 11:		ret= 	79.63;	break; //Queens..
			case 12:		ret= 	82.11;	break;
			case 13:		ret= 	84.93;	break; // aces..
			default: 		break;
		}
		return ret;
		}
		// idea behind summarizing these is to include randomness in played hands, i.e. Ace King suited, equates to Jack Ten suited.
		// ideally would like to do all hands with a specific value to optimize performance
		// high cards, same suit
		if(rank1 >= 10 && rank2 >= 10 && suit1 == suit2)
		{
			return 60.00;
		}
		// high cards, different suit
		else if(rank1 >= 10 && rank2 >= 10 && suit1 != suit2)
		{
			return 58.00;
		}
		// ace and 5 ... 10
		else if(rank1 == 13 && rank2 >=5)
		{
			if(rank2 > 8)
			{
				return 60.00;
			}
			else
			{
				return 57.00;
			}
		}
		// king and 8,9
		else if(rank1 == 12 && rank2 >=8)
		{
			return 57.00;
		}
		// one card above 10 and one card below, play a 55 winchance every 1/100 times
		else if(rank1 > 10 && rank2 < 10)
		{
		int  n = rand.nextInt(100) + 1;
			if(n == 50)
			{
				return 55.00;
			}
			else
			{
				return 50.00;
			}
		}
		else
		{
			return 0.00;
		}
	}

	/**
	 * 	need to implement outs
	 *	need to rework and evaluate return values
	 **/

	// evaluates your current hand with board, checks from best hand to worst
	// works by the fact sorting an array will put similiar cards beside one another
	// optimizations found yet to be implemented
	//
	double FlopEvaluation(int rank1, int suit1, int rank2, int suit2, int rank3, int suit3, int rank4, int suit4, int rank5, int suit5)
	{
		// store flop and cards in an array to sort cards, requires less comparisens throughtout
		int hand [] = new int[5];
		hand[0]=rank1;
		hand[1]=rank2;
		hand[2]=rank3;
		hand[3]=rank4;
		hand[4]=rank5;
		Arrays.sort(hand);
		// store x as start of straight
		int x =hand[0];
		// could implement check to see if x+4 == hand[4] only.
		if(x+1==hand[1]&&x+2==hand[2]&&x+3==hand[3]&&x+4==hand[4]&&suit1==suit5)	// royal flush / straight flush
		{
			return 100.00;
		}
		else if(hand[0]==hand[3] || hand[4]==hand[1])	// four of a kind
		{
			return 100.00;
		}
		else if((hand[0]==hand[2] && hand[3]==hand[4]) || (hand[4]==hand[2] && hand[1]==hand[0])) // full house
		{
			return 90.00;
		}
		else if(suit1==suit5)		//flush
		{
			return 80.00;
		}
		else if(x+1==hand[1]&&x+2==hand[2]&&x+3==hand[3]&&x+4==hand[4])	//straight
		{
			return 80.00;
		}
		else if(hand[0]==hand[2] || hand[1]==hand[3] || hand[2]==hand[4])	// three of a kind
		{
			return 70.00;
		}
		else if((hand[0]==hand[1] && hand[2]==hand[3]) || (hand[0]==hand[1] && hand[3]==hand[4]) || (hand[1]==hand[2] && hand[3]==hand[4]))	// two pair
		{
			return 60.00;
		}
		else if(hand[0]==hand[1] || hand[1]==hand[2] || hand[2]==hand[3] || hand[3]==hand[4]) // pair
		{
			return 55.00;
		}
		else // high card
		{
			return 45.00;
		}
	}
	double TurnEvaluation(int rank1, int suit1, int rank2, int suit2, int rank3, int suit3, int rank4, int suit4, int rank5, int suit5, int rank6, int suit6)
	{
		int hand [] = new int[6];
		hand[0]=rank1;
		hand[1]=rank2;
		hand[2]=rank3;
		hand[3]=rank4;
		hand[4]=rank5;
		hand[5]=rank6;
		Arrays.sort(hand);

		int suit [] = new int[6];
		suit[0]=suit1;
		suit[1]=suit2;
		suit[2]=suit3;
		suit[3]=suit4;
		suit[4]=suit5;
		suit[5]=suit6;
		Arrays.sort(suit);

		if(hand[0]+4==hand[4]&&suit1==suit5 || hand[1]+4==hand[5]&& suit6==suit2)	// royal flush / straight flush
		{
			return 100.00;
		}
		else if(hand[0]==hand[3] || hand[1]==hand[4] || hand[2]==hand[5])	// four of a kind
		{
			return 100.00;
		}	// check if 1=2=3 the if 4=5 or 5=6, if 2=3=4 if 5=6, 3=4=5, if 1=2, 4=5=6, if 1=2 or 2=3
		else if(hand[0]==hand[2] && hand[3]==hand[4]|| hand[4]==hand[5]	||
				hand[1]==hand[3] && hand[4]==hand[5]						||
				hand[2]==hand[4] && hand[0]==hand[1]						||
				(hand[3]==hand[5] && hand[0]==hand[1] || hand[1]==hand[2])) // full house
		{
			return 90.00;
		}
		else if(suit[0]==suit[4] || suit[1]==suit[5] )		//flush
		{
			return 80.00;
		}
		else if(hand[0]+4==hand[4] || hand[1]+4==hand[5])	//straight
		{
			return 80.00;
		}
		else if(hand[0]==hand[2] || hand[1]==hand[3] || hand[2]==hand[4] || hand[3]==hand[5])	// three of a kind
		{
			return 70.00;
		}	// 12, 34 or 45 or 56. 	`   or			23, 45. 		or		34, 56.
		else if((hand[0]==hand[1] && hand[2]==hand[3] || hand[3]==hand[4] || hand[4]==hand[5]) ||
			(hand[1]==hand[2] && hand[3]==hand[4]) ||
			(hand[2]==hand[3] && hand[4]==hand[5]))	// two pair
		{
			return 60.00;
		}
		else if(hand[0]==hand[1] || hand[1]==hand[2] || hand[2]==hand[3] || hand[3]==hand[4] || hand[4]==hand[5]) // pair
		{
			return 50.00;
		}
		else // high card
		{
			return 45.00;
		}
	}
	double RiverEvaulation(int rank1, int suit1, int rank2, int suit2, int rank3, int suit3, int rank4, int suit4, int rank5, int suit5, int rank6, int suit6, int rank7, int suit7)
	{
		int hand [] = new int[7];
		hand[0]=rank1;
		hand[1]=rank2;
		hand[2]=rank3;
		hand[3]=rank4;
		hand[4]=rank5;
		hand[5]=rank6;
		hand[6]=rank7;
		Arrays.sort(hand);

		int suit [] = new int[7];
		suit[0]=suit1;
		suit[1]=suit2;
		suit[2]=suit3;
		suit[3]=suit4;
		suit[4]=suit5;
		suit[5]=suit6;
		suit[6]=suit7;
		Arrays.sort(hand);


		// if suit[x]==suit[x+4],optimization
		if(hand[0]+4==hand[4]&&suit1==suit5 || hand[1]+4==hand[5]&& suit2==suit6 ||hand[2]+4==hand[6] && suit7==suit3)	// royal flush / straight flush
		{
			return 100.00;
		}	// if hand[0]==hand[3],optimization
		else if(hand[0]==hand[3] || hand[1]==hand[4] || hand[2]==hand[5] || hand[3]==hand[6])	// four of a kind
		{
			return 100.00;
		}	// optimize hand[0]==hand[1]
		else if((hand[0]==hand[2] && hand[3]==hand[4] || hand[4]==hand[5] || hand[5]==hand[6])||  // 1=2=3.. 4=5, 5=6, 6=7
				(hand[1]==hand[3]&& hand[4]==hand[5] || hand[5]==hand[6]) ||
				(hand[2]==hand[4]&& hand[5]==hand[6]||hand[0]==hand[1]) ||
				(hand[3]==hand[5]&& hand[0]==hand[1]||hand[1]==hand[2]) ||
				(hand[4]==hand[6]&& hand[0]==hand[1]||hand[1]==hand[2]||hand[2]==hand[3])) // full house
		{
			return 90.00;
		}
		else if(suit[0]==suit[4] || suit[1]==suit[5] || suit[6]==suit[2])		//flush
		{
			return 80.00;
		}
		else if(hand[0]+4==hand[4] || hand[1]+4==hand[5] || hand[2]+4==hand[6])	//straight
		{
			return 80.00;
		}	//optimize hand[0]==hand[2] etc
		else if(hand[0]==hand[2] || hand[1]==hand[3] || hand[2]==hand[4] || hand[3]==hand[5] || hand[4]==hand[6])	// three of a kind
		{
			return 70.00;
		}	// 1=2, 34,45,56,67. 2=3,45,56,67. 3=4,56,67. 4=5,67.
		else if((hand[0]==hand[1] && hand[2]==hand[3] || hand[3]==hand[4] || hand[4]==hand[5] || hand[5]==hand[6]) ||
			(hand[1]==hand[2] && hand[3]==hand[4] || hand[4]==hand[5] || hand[5]==hand[6]) ||
			(hand[2]==hand[3] && hand[4]==hand[5] ||hand[5]==hand[6]) ||
			(hand[3]==hand[4] && hand[5]==hand[6]))	// two pair
		{
			return 60.00;
		}
		else if(hand[0]==hand[1] || hand[1]==hand[2] || hand[2]==hand[3] || hand[3]==hand[4] || hand[4]==hand[5] || hand[5]==hand[6]) // pair
		{
			return 50.00;
		}
		else // high card
		{
			return 45.00;
		}
	}
}