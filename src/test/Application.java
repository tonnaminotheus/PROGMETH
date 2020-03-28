package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;
import logic.GameController;
import logic.addBarricadeFail;
import logic.removeBarricadeFail;
import entity.base.Entity;

class Application {

	@Test
	void test() {
		GameController.IntializeMap();
		Scanner scanner = new Scanner(System.in);	
		while(true)
		{
			System.out.println("-----------Turn "+GameController.get_turn()+" -------------");
			for(int j=16;j>=0;j--)
			{
				for(int i=0;i<17;i++)
				{
					Entity check=GameController.getCurrentMap().getEntity(i, j);
					if(check.is_BarricadeTile())
					{
						System.out.print("B");
					}
					else if(check.is_BlackTile())
					{
						System.out.print("_");
					}
					else if(check.is_WhiteTile())
					{
						System.out.print(" ");
					}
					else if(check.is_SpecialTile())
					{
						System.out.print("S");
					}
					else
					{
						System.out.print("P");
					}
				}
				System.out.println("");
			}
			
			
			System.out.println("Please choose action");
			System.out.println("(1) move");
			System.out.println("(2) place barricade");
			System.out.println("(3) remove barricade");
			System.out.println("(4) pass the turn");
			System.out.println("(999) Exit");
			String choice = scanner.nextLine();
			String result="";
			System.out.println(choice);
			if(choice.equals("1"))
			{
				int x,y,posx,posy;
				if(GameController.get_turn()%2==1)
				{
					x=GameController.get_Player1().getX();
					y=GameController.get_Player1().getY();
					System.out.println("Player1 at possition: "+x+" "+y);
					while(true)
					{
						posx=scanner.nextInt();
						posy=scanner.nextInt();
						boolean t1=GameController.getCurrentMap().getEntity((posx+x)/2, (posy+y)/2).is_WhiteTile();
						boolean t2=GameController.getCurrentMap().getEntity(posx, posy).is_BlackTile();
						boolean t3=GameController.getCurrentMap().getEntity(posx, posy).is_SpecialTile();
						//System.out.println(t1+" "+t2);
						if(t1 && (t2||t3))
						{
							break;
						}
						else
						{
							System.out.println("Can not move there please try again");
						}
					}
					GameController.move(GameController.get_Player1(),posx,posy);
					if(posx==16)
					{
						result="player1 win";
						GameController.setGameWin(true);
					}
				}
				else
				{
					x=GameController.get_Player2().getX();
					y=GameController.get_Player2().getY();
					System.out.println("Player2 at possition: "+x+" "+y);
					while(true)
					{
						posx=scanner.nextInt();
						posy=scanner.nextInt();
						boolean t1=GameController.getCurrentMap().getEntity((posx+x)/2, (posy+y)/2).is_WhiteTile();
						boolean t2=GameController.getCurrentMap().getEntity(posx, posy).is_BlackTile();
						boolean t3=GameController.getCurrentMap().getEntity(posx, posy).is_SpecialTile();
						//System.out.println(t1+" "+t2);
						if(t1 && (t2||t3))
						{
							break;
						}
						else
						{
							System.out.println("Can not move there please try again");
						}
					}
					GameController.move(GameController.get_Player2(),posx,posy);
					if(posx==0)
					{
						result="player2 win";
						GameController.setGameWin(true);
					}
				}
				scanner.nextLine();
				
			}
			else if(choice.equals("2"))
			{
				if(GameController.get_turn()%2==1)
					if(GameController.get_Player1().getHaveBaricade()==0)
					{
						System.out.println("you dont have any barricade left");
						continue;
					}
					else
						GameController.get_Player1().setBarricade(GameController.get_Player1().getHaveBaricade()-1);
				else
					if(GameController.get_Player2().getHaveBaricade()==0)
					{
						System.out.println("you dont have any barricade left");
						continue;
					}
					else
						GameController.get_Player2().setBarricade(GameController.get_Player2().getHaveBaricade()-1);
				
				//System.out.println(GameController.get_Player1().getHaveBaricade()+" "+GameController.get_Player2().getHaveBaricade());
			
				System.out.println("(1) place horrisontal");
				System.out.println("(2) place vertical");
				while(true)
				{
					String ch = scanner.nextLine();
					int x=scanner.nextInt();
					int y=scanner.nextInt();
					scanner.nextLine();
					if(ch.equals("1"))
					{
						if(GameController.addHorisontalBarricade(x,y))
						{
							break;
						}
					}
					else
					{
						if(GameController.addVerticalBarricade(x,y))
						{
							break;
						}
					}
					System.out.println("you can not place barricade there please try again");
				}
			}
			else if(choice.equals("3"))
			{
				while(true)
				{
					int x=scanner.nextInt();
					int y=scanner.nextInt();
					scanner.nextLine();
					if(GameController.removeBarricade(x, y))
					{
						break;
					}
					System.out.println("can not do that operation please try again");
				}
			}
			else if(choice.equals("999"))
			{
				System.out.println("done");
				break;
			}
			
			
			if(GameController.is_win())
			{
				System.out.println(result);
				break;
			}
			GameController.set_turn(GameController.get_turn()+1);
		}
		
		
		
		
		
		
	}

}
