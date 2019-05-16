// Jesse Ross
// November 13 2018

package main;
import heroes.*;
import monsters.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class MyGame 
{
	private static File saveGame = new File("Save.txt");
	private static int jokes = 1;
	private static int hardcore = 0;
	
	public static void main(String[] args)
	{
		Scanner kb = new Scanner(System.in);
		Hero player = null;
		
		// Choose whether to start a new game or load a save
		int start = newOrOld(kb);
		
		if(start == 2)
		{
			try
			{
				Scanner readFile = new Scanner(saveGame);
				String name = readFile.nextLine();
				String gender = readFile.nextLine();
				String type = readFile.nextLine();
				
				// Discovering the class of Hero
				if(type.equalsIgnoreCase("Warrior")) // Warrior
				{
					player = new Warrior(name, gender);
				}
				
				else if(type.equalsIgnoreCase("Sorceress") || type.equalsIgnoreCase("Sorcerer")) // Sorceress
				{
					player = new Sorceress(name, gender);
				}
				
				else if(type.equalsIgnoreCase("Thief")) // Thief
				{
					player = new Thief(name, gender);
				}
				
				else if(type.equalsIgnoreCase("Knight")) // Knight
				{
					player = new Knight(name, gender);
				}
				
				else if(type.equalsIgnoreCase("Battle Mage")) // Battle Mage
				{
					player = new BattleMage(name, gender);
				}
				
				else if(type.equalsIgnoreCase("Game Master")) // Game Master
				{
					player = new GameMaster(name, gender);
				}
				
				else if(type.equalsIgnoreCase("Arch Wizard")) // Megumin
				{
					player = new Megumin(name);
				}
				
				readFile.close();
				
			} 
			catch (Exception e) 
			{
				System.out.println("Save Game unable to be found. Please check if your \"Save.txt\" is in the current folder.");
				System.out.println("Redirecting to character creator.");
				start = 1;
			}
		}
		
		if(start == 1)
		{
			if(jokes == 1)
			{
				player = selectHeroJokes(kb, 0);
			}
			
			else
			{
				player = selectHero(kb, 0);
			}
			
			if(player == null) // They chose Quit
			{
				start = 3;
			}
		}
		
		if(start != 3)
		{
			// Main Menu
			int mode = 0;
			System.out.println("\n-------------------------------------------------------------");
			while(mode != 6)
			{
				String playerString = "Player: " + player.getHeroType() + " " + player.getName();
				mode = displayMenu(kb, start, playerString);

				switch (mode)
				{
				case 1: // Story Mode
					storyBegin(kb, player);
					break;
				case 2: // Single Battle
					singleBattle(kb, player);
					break;
				case 3: // Create New Hero				
					Hero tempPlayer = player;
					if(jokes == 1)
					{
						player = selectHeroJokes(kb, 0);
						System.out.println("-------------------------------------------------------------\n");
					}
					
					else
					{
						player = selectHero(kb, 0);
						System.out.println("-------------------------------------------------------------\n");
					}
					
					if(player == null) // They chose to go back
					{
						player = tempPlayer;
						System.out.println("Returning to Menu\n-------------------------------------------------------------\n");
					}
					break;
				case 4: // Save Hero
					saveGame(kb, player);
					break;
				case 5: // Toggles
					toggles(kb);;
					break;
				}
			}
			System.out.println("\nQuitting");
		}
	}
	
	// Display Menu
	private static int displayMenu(Scanner kb, int start, String playerStr)
	{
		// Intro
		int mode = 0;
		while (mode != 1 && mode != 2 && mode != 3 && mode != 4 && mode != 5 && mode != 6)
		{
			System.out.println(playerStr);
			System.out.println("Main Menu:\n"
					+ "\t1. Story Mode\n"
					+ "\t2. Single Battles\n"
					+ "\t3. Make New Hero\n"
					+ "\t4. Save Your Character\n"
					+ "\t5. Settings\n"
					+ "\t6. Quit");

			// Check if the input valid
			while(!(mode == 1 || mode == 2 || mode == 3 || mode == 4 || mode == 5 || mode == 6))
			{
				try
				{
					System.out.print("Option -----> ");
					mode = kb.nextInt(); // Choose Option
					kb.nextLine();  // Flush the buffer

					if(!(mode == 1 || mode == 2 || mode == 3 || mode == 4 || mode == 5 || mode == 6))
					{
						System.out.println("Invalid choice. Please choose 1, 2, 3, 4, 5, or 6.");
					}
				}

				catch(Exception e)
				{
					System.out.println("Invalid input. Pleaase try again.");
					kb.nextLine();  // Flush the buffer
				}
			}
		}
		
		return mode;
	}
	
	// New Save or Load Save
	private static int newOrOld(Scanner kb)
	{
		int choice = 0;
		
		System.out.println("Welcome to My Game: Console Edition");
		
		// New Game or Load a Save
		while (choice != 1 && choice != 2 && choice != 3)
		{
		System.out.println("Which would you like:\n"
						 + "\t1. New Game\n"
						 + "\t2. Continue a Save\n"
						 + "\t3. Quit");
		
			// Check if the input valid
			while(!(choice == 1 || choice == 2 || choice == 3))
			{
				try
				{
					System.out.print("Option -----> ");
					choice = kb.nextInt(); // Choose Option
					kb.nextLine();  // Flush the buffer

					if(!(choice == 1 || choice == 2 || choice == 3))
					{
						System.out.println("Invalid choice. Please choose 1, 2, or 3.");
					}
				}

				catch(Exception e)
				{
					System.out.println("Invalid input. Please try again.");
					kb.nextLine();  // Flush the buffer
				}
			}
		}
		
		if(choice == 2)
		{
			try
			{
				saveGame = new File("Save.txt");
			}
			
			catch(Exception e)
			{
				System.out.println("You currently do not have a save game. Please check to ensure that \"Save.txt\" is in the same folder as the game.");
				System.out.println("Switching to New Game mode.");
				choice = 1;
			}
		}
		
		return choice;
	}
	
	// Single Battle
	private static void singleBattle(Scanner kb, Hero player)
	{
		// Create Monster
		Monster monster = null;
		Random monsterGen = new Random();
		int monInt = monsterGen.nextInt(2);
		
		switch(monInt)
		{
		case 0: // Ogre
			monster = new Ogre();
			break;
		case 1: // Gremlin
			monster = new Gremlin();
			break;
		case 2: // Skeleton
			monster = new Skeleton();
			break;
		}
		
		// Begin
		System.out.println("-------------------------------------------------------------\n");
		System.out.println("\t _                _            \r\n" + 
						   "\t|_) _._|__|_| _  |_) _  _ o._  \r\n" + 
						   "\t|_)(_| |_ |_|(/_ |_)(/_(_||| | \r\n" + 
						   "\t                        _|     ");
		
		while(player.getHp() > 0 && monster.getHp() > 0)
		{
			// Calculate Number of Turns 
			int playerTurns = player.calcTurns(monster);
			int turns = playerTurns; // To get it as a constant for stats
			
			while(playerTurns > 0)
			{
				boolean showHit = true;
				
				if(playerTurns != 1)
				{
					System.out.println("\nYou are able to get " + playerTurns + " more hits in before they can attack!");
				}

				else
				{
					System.out.println("\nYou are able to get " + playerTurns + " hit in before they can attack!");
				}

				int option = 0;

				System.out.println("\nPlayer:");
				System.out.println("\t" + player.getHeroType() + " " + player.getName() + ": " + player.getHp() + "/" + player.getTotalHp() + " hp"); // Player Stats
				System.out.println("Enemy: ");
				System.out.println("\t" + monster.getName() + ": " + monster.getHp() + "/" + monster.getTotalHp() + " hp"); // Boss Stats

				System.out.print("\nWhat would you like to do:\r\n");
				System.out.println(player.getMenu());; // Display Menu

				while(!(option == 1 || option == 2 || option == 3 || option == 4 || option == 5))
				{
					try
					{
						System.out.print("Choice -----> ");
						option = kb.nextInt(); // Choose Option
						kb.nextLine();  // Flush the buffer

						if(!(option == 1 || option == 2 || option == 3 || option == 4 || option == 5))
						{
							System.out.println("Invalid choice. Please type 1, 2, 3, 4, or 5.");
						}

						if(option == 4)
						{
							if(player.getPotions() == 0 && player.getCharm() == 0 && player.getStrPot() == 0)
							{
								System.out.println("You don't have any items to use!");
								option = 0;
							}
						}
					}

					catch(Exception e)
					{
						System.out.println("You typed in: " + option + "\nThis is an invalid response. Please try again.");
						kb.nextLine();  // Flush the buffer
					}
				}

				System.out.println("-------------------------------------------------------------");

				// Attack Phase
				if(option == 1) // Normal Attack
				{
					System.out.println("\n\tPlayer's Turn");
					player.attack(monster);
				}
				
				else if(option == 2) // Special Attack
				{
					System.out.println("\n\tPlayer's Turn");
					player.superAttack(monster);
				}
				
				else if(option == 3)
				{
					System.out.println("\nStats:");
					System.out.println(player.getName() + ": " + player.getHp() + "/" + player.getTotalHp() + " hp \n\tGender: " + player.getGender() + "\n\tClass: " + player.getHeroType() + "\n\tTurns per round: " + turns);
					System.out.println("\n" + monster.getName() + ": " + monster.getHp() + "/" + monster.getTotalHp() + " hp" + "\n\tSpecies: " + monster.getMstrType());
					showHit = false;
					System.out.print("\nHit enter to return to the menu:");
					kb.nextLine();
					System.out.println("-------------------------------------------------------------");
					playerTurns++; // To counteract the turn that will be removed in a few lines
				}
				
				else if(option == 4)
				{
					int item = selectItem(kb, player);

					if(item == 1 && player.getPotions() > 0) // Potion
					{
						int heals = player.getHp() + 25;

						if(heals > player.getTotalHp()) // To ensure that the hp doesn't go over the maximum limit
						{
							player.setHp(player.getTotalHp());
							System.out.println("\nYou restored all of your hp!");
						}

						else
						{
							player.setHp(heals);
							System.out.println("\nYou restored 25 hp!\n");
						}

						player.setPotions(player.getPotions() - 1);
					}

					else if(item == 2 && player.getCharm() > 0) // Good Luck
					{
						player.setHitChnce(player.getHitChnce() + 15);
						System.out.println("\nChance to Hit +15\n");

						player.setCharm(player.getCharm() - 1);
					}

					else if(item == 3 && player.getStrPot() > 0) // Strength
					{
						player.setDmgMin(player.getDmgMin() + 25);
						player.setDmgMax(player.getDmgMax() + 25);

						System.out.println("\nStrength stats +25\n");

						player.setStrPot(player.getStrPot() - 1);
					}
					
					else if(item == 4) // Back to Menu
					{
						System.out.println("-------------------------------------------------------------");
						showHit = false;
						playerTurns++; // To counteract the turn that will be removed in a few lines
					}
				}

				else if(option == 5) // Quit
				{
					System.out.println("You ran away! Exiting to the main menu.");
					monster.setHp(0);
					playerTurns = 0;
					break;
				}
				
				playerTurns--;
				
				// Check if player continues to attack
				if(monster.getHp() < 0) // If monster dies, then battle is over
				{
					System.out.println("__      _______ _____ _______ ____  _______     __\r\n" + 
					           "\\ \\    / /_   _/ ____|__   __/ __ \\|  __ \\ \\   / /\r\n" + 
					           " \\ \\  / /  | || |       | | | |  | | |__) \\ \\_/ / \r\n" + 
					           "  \\ \\/ /   | || |       | | | |  | |  _  / \\   /  \r\n" + 
					           "   \\  /   _| || |____   | | | |__| | | \\ \\  | |   \r\n" + 
					           "    \\/   |_____\\_____|  |_|  \\____/|_|  \\_\\ |_|   \r\n" + 
						       "                                                  ");
					System.out.println("You have defeated the monster!\n");
					playerTurns = 0;
					break;
				}
				
				if(playerTurns > 0 && showHit == true)
				{
					System.out.print("Hit enter to return to the menu:");
					kb.nextLine();
					System.out.println("-------------------------------------------------------------");
				}
			}
			
			// Monster's Turn
			if(monster.getHp() > 0)
			{
				System.out.println("\n\tMonster's Turn:");
				monster.attack(player); // Monster attacks hero

				if(player.getHp() < 0)
				{
					System.out.println("\t _____  ______ ______ ______       _______ \r\n" + 
							   "\t|  __ \\|  ____|  ____|  ____|   /\\|__   __|\r\n" + 
							   "\t| |  | | |__  | |__  | |__     /  \\  | |   \r\n" + 
							   "\t| |  | |  __| |  __| |  __|   / /\\ \\ | |   \r\n" + 
							   "\t| |__| | |____| |    | |____ / ____ \\| |   \r\n" + 
							   "\t|_____/|______|_|    |______/_/    \\_\\_|   ");
					System.out.println("You have been defeated.\n");
					System.out.print("Hit enter to return to the menu:");
					kb.nextLine();
					break;
				}

				monster.regenHealth();
			}

			System.out.print("Hit enter to return to the menu:");
			kb.nextLine();
			System.out.println("-------------------------------------------------------------");
		}
	}
	
	// Story Begin
	private static void storyBegin(Scanner kb, Hero player)
	{
		Hero ally1 = null;
		Hero ally2 = null;
		Hero ally3 = null;
		
		// Konosuba Team
		if(player.getHeroType().equalsIgnoreCase("Arch Wizard")) // Player is Megumin
		{
			ally1 = new Aqua("");
			ally2 = new Darkness("");
			ally3 = new Kazuma("");
		}
		
		else if(player.getHeroType().equalsIgnoreCase("Goddess")) // Player is Aqua
		{
			ally1 = new Megumin("");
			ally2 = new Darkness("");
			ally3 = new Kazuma("");
		}
		
		else if(player.getHeroType().equalsIgnoreCase("Crusader")) // Player is Darkness
		{
			ally1 = new Megumin("");
			ally2 = new Aqua("");
			ally3 = new Kazuma("");
		}
		
		else if(player.getHeroType().equalsIgnoreCase("Adventurer")) // Player is Kazuma
		{
			ally1 = new Megumin("");
			ally2 = new Aqua("");
			ally3 = new Darkness("");
		}
		
		// Normal Team
		else
		{
			ArrayList<Hero> team = teamSetup(kb, player);
			
			ally1 = team.get(0);
			ally2 = team.get(1);
			ally3 = team.get(2);
		}
		
		Monster monster = null;
		
		int roundNum = 0;
		int result = 0;
		int doneEvent = -1;
		Integer[] eventResult = new Integer[2];
		
		while(roundNum < 6)
		{
			// Battle 1 
			System.out.println("-------------------------------------------------------------");
			System.out.println("\t  _____ _                     ____             _       \r\n" + 
							   "\t / ____| |                   |  _ \\           (_)      \r\n" + 
							   "\t| (___ | |_ ___  _ __ _   _  | |_) | ___  __ _ _ _ __  \r\n" + 
							   "\t \\___ \\| __/ _ \\| '__| | | | |  _ < / _ \\/ _` | | '_ \\ \r\n" + 
							   "\t ____) | || (_) | |  | |_| | | |_) |  __/ (_| | | | | |\r\n" + 
							   "\t|_____/ \\__\\___/|_|   \\__, | |____/ \\___|\\__, |_|_| |_|\r\n" + 
							   "\t                       __/ |              __/ |        \r\n" + 
							   "\t                      |___/              |___/         \r\n");
			System.out.println("\nYou and your team of adventurers start on your path to slay the legendary dragon!");
			System.out.println("While on your journey, you encounter a monster!");
			System.out.print("Hit enter to continue:");
			kb.nextLine();
			
			System.out.println("-------------------------------------------------------------");
			
			monster = new LowerBoss();
			
			System.out.print("Battle 1:");
			result = storyBattle(kb, player, ally1, ally2, ally3, monster);
			
			if(result == 1)
			{
				System.out.println("\nAfter a tough battle, you and your team finally beat the beast. After spending sometime recuperating and patching up all of your wounds, you continued on your journey!");
				roundNum++;
				
				// Reset Team Stats
				if(hardcore == 0)
				{
					player.resetStats();
					ally1.resetStats();
					ally2.resetStats();
					ally3.resetStats();
				}
			}
			
			else // Lost Battle
			{
				roundNum = 7;
				System.out.print("Hit enter to continue:");
				kb.nextLine();
				break;
			}
			
			System.out.print("Hit enter to continue:");
			kb.nextLine();

			// Event 1
			
			System.out.println("-------------------------------------------------------------");
			System.out.println("Event 1:");
			eventResult = event(kb, eventResult, doneEvent);
			result = eventResult[1];
			doneEvent = eventResult[0];

			if(result == 0) // Dead
			{
				roundNum = 7;
				System.out.print("Hit enter to continue:");
				kb.nextLine();
				break;
			}
			
			else if(result == 3) // Up HP
			{
				System.out.println("+25 Max Hp!");
				player.setHp(player.getHp() + 25);
				player.setTotalHp(player.getTotalHp() + 25);
			}
			
			else if(result == 4) // Up Strength
			{
				System.out.println("+25 Max Damage for your team!");
				player.setDmgMax(player.getDmgMax() + 25);
				ally1.setDmgMax(ally1.getDmgMax() + 25);
				ally2.setDmgMax(ally2.getDmgMax() + 25);
				ally3.setDmgMax(ally3.getDmgMax() + 25);
				
				player.setDmgMaxOG(player.getDmgMaxOG() + 25);
				ally1.setDmgMaxOG(ally1.getDmgMaxOG() + 25);
				ally2.setDmgMaxOG(ally2.getDmgMaxOG() + 25);
				ally3.setDmgMaxOG(ally3.getDmgMaxOG() + 25);
			}
			
			roundNum++;
			
			System.out.print("Hit enter to continue:");
			kb.nextLine();

			// Battle 2
			System.out.println("-------------------------------------------------------------");
			System.out.println("As you continue down the path, you encounter another monster!");
			monster = new LowerBoss();
			
			System.out.print("Battle 2:");
			result = storyBattle(kb, player, ally1, ally2, ally3, monster);
			
			if(result == 1)
			{
				System.out.println("\nIt wasn't easy, but you and your team fought back the beast and were able to keep moving forward!");
				roundNum++;
				
				// Reset Team Stats
				if(hardcore == 0)
				{
					player.resetStats();
					ally1.resetStats();
					ally2.resetStats();
					ally3.resetStats();
				}
			}
			
			else // Lost Battle
			{
				roundNum = 7;
				System.out.print("Hit enter to continue:");
				kb.nextLine();
				break;
			}
			
			System.out.print("Hit enter to continue:");
			kb.nextLine();

			// Event 2
			
			System.out.println("-------------------------------------------------------------");
			System.out.println("Event 2:");
			eventResult = event(kb, eventResult, doneEvent);
			result = eventResult[1];

			if(result == 0) // Dead
			{
				roundNum = 7;
				System.out.print("Hit enter to continue:");
				kb.nextLine();
				break;
			}
			
			else if(result == 3) // Up HP
			{
				System.out.println("+25 Max Hp!");
				player.setHp(player.getHp() + 25);
				player.setTotalHp(player.getTotalHp() + 25);
			}
			
			else if(result == 4) // Up Strength
			{
				System.out.println("+25 Max Damage for your team!");
				player.setDmgMax(player.getDmgMax() + 25);
				ally1.setDmgMax(ally1.getDmgMax() + 25);
				ally2.setDmgMax(ally2.getDmgMax() + 25);
				ally3.setDmgMax(ally3.getDmgMax() + 25);
				
				player.setDmgMaxOG(player.getDmgMaxOG() + 25);
				ally1.setDmgMaxOG(ally1.getDmgMaxOG() + 25);
				ally2.setDmgMaxOG(ally2.getDmgMaxOG() + 25);
				ally3.setDmgMaxOG(ally3.getDmgMaxOG() + 25);
			}
			
			roundNum++;
			
			System.out.print("Hit enter to continue:");
			kb.nextLine();

			// Final Boss Battle
			
			System.out.println("-------------------------------------------------------------");
			System.out.println("It was a long and painful journey, but you and your crew have finally found the terrifying dragon!");
			
			// Konosuba Team
			if(player.getHeroType().equalsIgnoreCase("Arch Wizard") || player.getHeroType().equalsIgnoreCase("Goddess") || player.getHeroType().equalsIgnoreCase("Crusader") || player.getHeroType().equalsIgnoreCase("Adventurer"))
			{
				monster = new FinalBoss(1);
			}
			
			// Super Mario
			else if(player.getName().equalsIgnoreCase("Mario") || player.getName().equalsIgnoreCase("Luigi") || player.getName().equalsIgnoreCase("Peach") || player.getName().equalsIgnoreCase("Wario") || player.getName().equalsIgnoreCase("Waluigi"))
			{
				monster = new FinalBoss(2);
			}
			
			// Legend of Zelda
			else if(player.getName().equalsIgnoreCase("Link") || player.getName().equalsIgnoreCase("Zelda"))
			{
				monster = new FinalBoss(3);
			}
			
			else
			{
				monster = new FinalBoss();
			}

			System.out.print("Final Boss Battle:");
			result = storyBattle(kb, player, ally1, ally2, ally3, monster);
			
			if(result == 1)
			{
				roundNum = 6;
				System.out.println("Congratulations! It was a long fought battle, and an even longer journey, but you guys did it!");
			}
			
			else // Lost Battle
			{
				roundNum = 7;
				System.out.print("Hit enter to continue:");
				kb.nextLine();
				break;
			}
			
			System.out.print("Hit enter to return to the menu:");
			kb.nextLine();
			System.out.println("-------------------------------------------------------------");
		}
	}
	
	// Story Team Setup
	private static ArrayList<Hero> teamSetup(Scanner kb, Hero player)
	{
		Hero ally1 = null;
		Hero ally2 = null;
		Hero ally3 = null;
		
		String selectTeam = "";
		System.out.println("\nWould you like to select your team, or just get the default.");
		while(!(selectTeam.equalsIgnoreCase("Y") || selectTeam.equalsIgnoreCase("N") || selectTeam.equalsIgnoreCase("Yes") || selectTeam.equalsIgnoreCase("No")))
		{
			try
			{
				System.out.print("Yes or No -----> ");
				selectTeam = kb.nextLine(); // Choose Option

				if(!(selectTeam.equalsIgnoreCase("Y") || selectTeam.equalsIgnoreCase("N") || selectTeam.equalsIgnoreCase("Yes") || selectTeam.equalsIgnoreCase("No")))
				{
					System.out.println("Invalid choice. Please choose Yes or No.");
				}
			}

			catch(Exception e)
			{
				System.out.println("Invalid Input. Try again.");
			}
		}
		
		if(selectTeam.equalsIgnoreCase("yes") || selectTeam.equalsIgnoreCase("y"))
		{
			int i = 0;
			while(i < 3)
			{
				// Ally 1
				System.out.println("-------------------------------------------------------------");
				System.out.println("Ally 1");
				System.out.println("\nRecommended Team Selection: Healer, Heavy Damage, Damage Absorber");
				if(jokes == 1)
				{
					ally1 = selectHeroJokes(kb, 2);
				}

				else
				{
					ally1 = selectHero(kb, 2);
				}

				if(ally1 == null) // They chose to go cancel
				{
					i = 4;
					selectTeam = "no";
					System.out.println("Team Selection Cancelled\nCreating Default Team\n-------------------------------------------------------------\n");
					break;
				}
				
				// Ally 2
				System.out.println("\nAlly 2");
				System.out.println("\nRecommended Team Selection: Healer, Heavy Damage, Damage Absorber");
				if(jokes == 1)
				{
					ally2 = selectHeroJokes(kb, 2);
				}

				else
				{
					ally2 = selectHero(kb, 2);
				}

				if(ally2 == null) // They chose to go cancel
				{
					i = 4;
					selectTeam = "no";
					System.out.println("Team Selection Cancelled\nCreating Default Team\n-------------------------------------------------------------\n");
					break;
				}
				
				// Ally 3
				System.out.println("\nAlly 3");
				System.out.println("\nRecommended Team Selection: Healer, Heavy Damage, Damage Absorber");
				if(jokes == 1)
				{
					ally3 = selectHeroJokes(kb, 2);
				}

				else
				{
					ally3 = selectHero(kb, 2);
				}

				if(ally3 == null) // They chose to go cancel
				{
					i = 4;
					selectTeam = "no";
					System.out.println("Team Selection Cancelled\nCreating Default Team\n-------------------------------------------------------------\n");
					break;
				}
				
				i = 4;
			}
		}
		
		else if(selectTeam.equalsIgnoreCase("no") || selectTeam.equalsIgnoreCase("n"))
		{
			if(player.getHeroType().equalsIgnoreCase("Warrior")) // Warrior
			{
				ally1 = new Sorceress("Bob");
				ally2 = new Thief("Steve");
				ally3 = new Knight("Joe");
			}
			
			else if(player.getHeroType().equalsIgnoreCase("Sorceress") || player.getHeroType().equalsIgnoreCase("Sorcerer")) // Sorceress
			{
				ally1 = new Warrior("Bob");
				ally2 = new Thief("Steve");
				ally3 = new Knight("Joe");
			}
			
			else if(player.getHeroType().equalsIgnoreCase("Thief")) // Thief
			{
				ally1 = new Sorceress("Bob");
				ally2 = new Warrior("Steve");
				ally3 = new Knight("Joe");
			}
			
			else if(player.getHeroType().equalsIgnoreCase("Knight")) // Knight
			{
				ally1 = new Sorceress("Bob");
				ally2 = new Thief("Steve");
				ally3 = new Warrior("Joe");
			}
			
			else
			{
				ally1 = new Sorceress("Bob");
				ally2 = new Warrior("Steve");
				ally3 = new Knight("Joe");
			}
		}
		
		ArrayList<Hero> team = new ArrayList<Hero>(3);
		team.add(ally1);
		team.add(ally2);
		team.add(ally3);
		
		return team;
	}
	
	// Story Battle
	private static int storyBattle(Scanner kb, Hero player, Hero ally1, Hero ally2, Hero ally3, Monster monster)
	{
		int ally1Dead = 0;
		int ally2Dead = 0;
		int ally3Dead = 0;
		int result = 0; // Win or Lose
		boolean quit = false;
		
		// Start
		while(player.getHp() > 0 && monster.getHp() > 0)
		{
			int playerTurns = player.calcTurns(monster);
			int turns = playerTurns;
			
			// Begin Player's Turns
			while(playerTurns > 0)
			{
				boolean showHit = true;

				// Number of Turns Remaining
				if(playerTurns != 1)
				{
					System.out.println("\nYou are able to get " + playerTurns + " more hits in before they can attack!");
				}

				else
				{
					System.out.println("\nYou are able to get " + playerTurns + " hit in before they can attack!");
				}

				int option = 0;

				String str1 = "Ally 1";
				String str2 = "Ally 2";
				String str3 = "Ally 3";
				
				String info1 = " " + ally1.getName() + ": " + ally1.getHp() + "/" + ally1.getTotalHp() + " hp ";
				String info2 = " " + ally2.getName() + ": " + ally2.getHp() + "/" + ally2.getTotalHp() + " hp ";
				String info3 = " " + ally3.getName() + ": " + ally3.getHp() + "/" + ally3.getTotalHp() + " hp ";
				
				// Found from here: https://stackoverflow.com/questions/8154366/how-to-center-a-string-using-string-format
				// Add Spaces to First
				int length = info1.length();
				str1 = String.format("%" + length + "s%s%" + length + "s", "", str1, "");
				int mid = (str1.length()/2);
			    int start = mid - (length / 2);
			    int end = start + length; 
			    str1 = str1.substring(start, end);
				
				// Add Spaces to Second
			    length = info2.length();
				str2 = String.format("%" + length + "s%s%" + length + "s", "", str2, "");
				mid = (str2.length()/2);
			    start = mid - (length / 2);
			    end = start + length; 
			    str2 = str2.substring(start, end);
				
				// Add Spaces to Third
			    length = info3.length();
				str3 = String.format("%" + length + "s%s%" + length + "s", "", str3, "");
				mid = (str3.length()/2);
			    start = mid - (length / 2);
			    end = start + length; 
			    str3 = str3.substring(start, end);
				
				System.out.println("\nPlayer:");
				System.out.println("\t" + player.getHeroType() + " " + player.getName() + ": " + player.getHp() + "/" + player.getTotalHp() + " hp"); // Player Stats
				
				// Printing Allies
				System.out.println("\nAllies: ");
				for(int i = 0; i < 2; i++)
				{
					if(ally1.getHp() > 0) // Ally 1 Stats
					{
						if(i == 0)
							System.out.print(str1);
						
						else
							System.out.print(info1);

						if(ally2.getHp() > 0 || ally3.getHp() > 0)
						{
							System.out.print("||");
						}
					}
					
					if(ally2.getHp() > 0) // Ally 2 Stats
					{
						if(i == 0)
							System.out.print(str2);
						
						else
							System.out.print(info2);

						if(ally3.getHp() > 0)
						{
							System.out.print("||");
						}
					}
					
					if(ally3.getHp() > 0) // Ally 3 Stats
					{
						if(i == 0)
							System.out.print(str3);
						
						else
							System.out.print(info3);
					}
					
					System.out.print("\n");
				}
				
				System.out.println("\nEnemy:");
				System.out.println("\t" + monster.getName() + ": " + monster.getHp() + "/" + monster.getTotalHp() + " hp"); // Boss Stats

				System.out.print("\nWhat would you like to do:\r\n");
				System.out.println(player.getMenu());; // Display Menu

				while(!(option == 1 || option == 2 || option == 3 || option == 4 || option == 5))
				{
					try
					{
						System.out.print("Choice -----> ");
						option = kb.nextInt(); // Choose Option
						kb.nextLine();  // Flush the buffer

						if(!(option == 1 || option == 2 || option == 3 || option == 4 || option == 5))
						{
							System.out.println("Invalid choice. Please type 1, 2, 3, 4, or 5.");
						}

						if(option == 4)
						{
							if(player.getPotions() == 0 && player.getCharm() == 0 && player.getStrPot() == 0)
							{
								System.out.println("You don't have any items to use!");
								option = 0;
							}
						}
					}

					catch(Exception e)
					{
						System.out.println("You typed in: " + option + "\nThis is an invalid response. Please try again.");
						kb.nextLine();  // Flush the buffer
					}
				}

				System.out.println("-------------------------------------------------------------");

				// Attack Phase
				if(option == 1) // Normal Attack
				{
					System.out.println("\tPlayer's Turn\n");
					player.attack(monster);
					System.out.println();
				}

				else if(option == 2) // Special Attack
				{
					System.out.println("\tPlayer's Turn\n");
					player.superAttack(monster);
					System.out.println();
				}

				else if(option == 3)
				{
					System.out.println("\nStats:");
					System.out.println(player.getName() + ": " + player.getHp() + "/" + player.getTotalHp() + " hp \n\tGender: " + player.getGender() + "\n\tClass: " + player.getHeroType() + "\n\tTurns per round: " + turns);
					System.out.println("\n" + monster.getName() + ": " + monster.getHp() + "/" + monster.getTotalHp() + " hp" + "\n\tSpecies: " + monster.getMstrType());
					showHit = false;
					System.out.print("\nHit enter to return to the menu:");
					kb.nextLine();
					System.out.println("-------------------------------------------------------------");
					playerTurns++; // To counteract the turn that will be removed in a few lines
				}

				else if(option == 4)
				{
					int item = selectItem(kb, player);

					if(item == 1 && player.getPotions() > 0) // Potion
					{
						int heals = player.getHp() + 25;

						if(heals > player.getTotalHp()) // To ensure that the hp doesn't go over the maximum limit
						{
							player.setHp(player.getTotalHp());
							System.out.println("\nYou restored all of your hp!");
						}

						else
						{
							player.setHp(heals);
							System.out.println("\nYou restored 25 hp!\n");
						}

						player.setPotions(player.getPotions() - 1);
					}

					else if(item == 2 && player.getCharm() > 0) // Good Luck
					{
						player.setHitChnce(player.getHitChnce() + 15);
						System.out.println("\nChance to Hit +15\n");

						player.setCharm(player.getCharm() - 1);
					}

					else if(item == 3 && player.getStrPot() > 0) // Strength
					{
						player.setDmgMin(player.getDmgMin() + 25);
						player.setDmgMax(player.getDmgMax() + 25);

						System.out.println("\nStrength stats +25\n");

						player.setStrPot(player.getStrPot() - 1);
					}

					else if(item == 4) // Back to Menu
					{
						System.out.println("-------------------------------------------------------------");
						showHit = false;
						playerTurns++; // To counteract the turn that will be removed in a few lines
					}
				}

				else if(option == 5) // Quit
				{
					System.out.println("You ran away! Exiting to the main menu.");
					monster.setHp(0);
					playerTurns = 0;
					quit = true;
					break;
				}

				playerTurns--;

				// Check if player continues to attack
				if(monster.getHp() < 0) // If monster dies, then battle is over
				{
					System.out.println("__      _______ _____ _______ ____  _______     __\r\n" + 
							           "\\ \\    / /_   _/ ____|__   __/ __ \\|  __ \\ \\   / /\r\n" + 
							           " \\ \\  / /  | || |       | | | |  | | |__) \\ \\_/ / \r\n" + 
							           "  \\ \\/ /   | || |       | | | |  | |  _  / \\   /  \r\n" + 
							           "   \\  /   _| || |____   | | | |__| | | \\ \\  | |   \r\n" + 
							           "    \\/   |_____\\_____|  |_|  \\____/|_|  \\_\\ |_|   \r\n" + 
								       "                                                  ");
					System.out.println("You have defeated the monster!\n");
					playerTurns = 0;
					result = 1;
					quit = true;
					break;
				}

				if(playerTurns > 0 && showHit == true)
				{
					System.out.print("Hit enter to return to the menu:");
					kb.nextLine();
					System.out.println("-------------------------------------------------------------");
				}
			}
			
			if(quit == true) // Quit
			{
				System.out.println("-------------------------------------------------------------\n");
				break;
			}
			
			// Monster's Turn
			if(monster.getHp() > 0)
			{
				Random r = new Random();
				int i = 0;

				while(i == 0)
				{
					int heroSelect = r.nextInt(4);
					if(heroSelect == 0)
					{
						if(player.getHp() > 0)
						{
							System.out.println("\tMonster's Turn");
							System.out.println("\nThe monster has targeted " + player.getName() + "!");
							monster.attack(player); // Monster attacks player
							i++;
						}

						else
						{
							heroSelect++;
							i++;
						}
					}

					if(heroSelect == 1)
					{
						if(ally1.getHp() > 0)
						{
							System.out.println("\tMonster's Turn");
							System.out.println("\nThe monster has targeted " + ally1.getName() + "!");
							monster.attack(ally1); // Monster attacks ally1
							i++;
						}

						else
						{
							heroSelect++;
							i++;
						}
					}

					if(heroSelect == 2)
					{
						if(ally2.getHp() > 0)
						{
							System.out.println("\tMonster's Turn");
							System.out.println("\nThe monster has targeted " + ally2.getName() + "!");
							monster.attack(ally2); // Monster attacks ally2
							i++;
						}

						else
						{
							heroSelect++;
							i++;
						}
					}

					if(heroSelect == 3)
					{
						if(ally3.getHp() > 0)
						{
							System.out.println("\tMonster's Turn");
							System.out.println("\nThe monster has targeted " + ally3.getName() + "!");
							monster.attack(ally3); // Monster attacks ally3
							i++;
						}

						else
						{
							i = 0;
						}
					}
				}

				// Player is Dead
				if(player.getHp() <= 0)
				{
					System.out.println("\t _____  ______ ______ ______       _______ \r\n" + 
					           		   "\t|  __ \\|  ____|  ____|  ____|   /\\|__   __|\r\n" + 
					           		   "\t| |  | | |__  | |__  | |__     /  \\  | |   \r\n" + 
					           		   "\t| |  | |  __| |  __| |  __|   / /\\ \\ | |   \r\n" + 
					           		   "\t| |__| | |____| |    | |____ / ____ \\| |   \r\n" + 
					                   "\t|_____/|______|_|    |______/_/    \\_\\_|   ");
					System.out.println("\nYou have been defeated. Game Over.\n");
					result = 0;
					break;
				}				
			}
			

			// Allies that are Dead
			if(ally1.getHp() <= 0 && ally1Dead == 0)
			{
				System.out.println(ally1.getName() + " has passed out!\n");
				ally1Dead = 1;
			}

			if(ally2.getHp() <= 0 && ally2Dead == 0)
			{
				System.out.println(ally2.getName() + " has passed out!\n");
				ally2Dead = 1;
			}

			if(ally3.getHp() <= 0 && ally3Dead == 0)
			{
				System.out.println(ally3.getName() + " has passed out!\n");
				ally3Dead = 1;
			}

			// Allies Attack
			if(ally1Dead != 1 || ally2Dead != 2 || ally3Dead != 3)
			{
				System.out.println("\tAlly's Turn");
			}
			
			if(ally1Dead == 0)
			{
				// Ally 1
				System.out.println();
				
				// Aqua
				if(ally1.getHeroType().equalsIgnoreCase("Goddess"))
				{
					Random r = new Random();
					int attack = r.nextInt(1);
					
					if(attack == 1)
					{
						Aqua temp = (Aqua) ally3; // Cast
						
						if(player.getHp() != player.getTotalHp())
						{
							temp.healAlly(player);
						}
						
						else if(ally1.getHp() != ally1.getTotalHp())
						{
							temp.healAlly(ally1);
						}
						
						else if(ally2.getHp() != ally2.getTotalHp())
						{
							temp.healAlly(ally2);
						}
						
						else if(ally3.getHp() != ally3.getTotalHp())
						{
							temp.healAlly(ally3);
						}
						
						else
						{
							attack = 0;
						}
					}
					
					if(attack == 0)
					{
						ally3.storyAttack(monster);
					}
				}
				
				// Megumin
				if(ally1.getHeroType().equalsIgnoreCase("Arch Wizard"))
				{
					if(ally1.getCanAttack() == true)
					{	
						ally1.storyAttack(monster);
						ally1.setCanAttack(false);
					}
					
					else
					{
						ally1.setAttackCounter(ally1.getAttackCounter() - 1);
						
						if(ally1.getAttackCounter() == 0)
						{
							ally1.setAttackCounter(5);
							ally1.setCanAttack(true);
							
							System.out.println("Megumin is finally ready to fight again!");
						}
						
						else
						{
							System.out.println("Megumin is still to tired to fight! She'll be ready in " + ally1.getAttackCounter() + " turns.");
						}
					}
				}
				
				else
					ally1.storyAttack(monster); // Ally attacks monster

				if(monster.getHp() < 0) // If monster dies, then battle is over
				{
					System.out.println("__      _______ _____ _______ ____  _______     __\r\n" + 
					           "\\ \\    / /_   _/ ____|__   __/ __ \\|  __ \\ \\   / /\r\n" + 
					           " \\ \\  / /  | || |       | | | |  | | |__) \\ \\_/ / \r\n" + 
					           "  \\ \\/ /   | || |       | | | |  | |  _  / \\   /  \r\n" + 
					           "   \\  /   _| || |____   | | | |__| | | \\ \\  | |   \r\n" + 
					           "    \\/   |_____\\_____|  |_|  \\____/|_|  \\_\\ |_|   \r\n" + 
						       "                                                  ");
					System.out.println("You have defeated the monster!\n");
					playerTurns = 0;
					result = 1;
					break;
				}
			}

			if(ally2Dead == 0)
			{
				// Ally 2
				System.out.println();
				
				// Aqua
				if(ally2.getHeroType().equalsIgnoreCase("Goddess"))
				{
					Random r = new Random();
					int attack = r.nextInt(1);
					
					if(attack == 1)
					{
						Aqua temp = (Aqua) ally3; // Cast
						
						if(player.getHp() != player.getTotalHp())
						{
							temp.healAlly(player);
						}
						
						else if(ally1.getHp() != ally1.getTotalHp())
						{
							temp.healAlly(ally1);
						}
						
						else if(ally2.getHp() != ally2.getTotalHp())
						{
							temp.healAlly(ally2);
						}
						
						else if(ally3.getHp() != ally3.getTotalHp())
						{
							temp.healAlly(ally3);
						}
						
						else
						{
							attack = 0;
						}
					}
					
					if(attack == 0)
					{
						ally3.storyAttack(monster);
					}
				}
				
				// Megumin
				if(ally2.getHeroType().equalsIgnoreCase("Arch Wizard"))
				{
					if(ally2.getCanAttack() == true)
					{	
						ally2.storyAttack(monster);
						ally2.setCanAttack(false);
					}
					
					else
					{
						ally2.setAttackCounter(ally2.getAttackCounter() - 1);
						
						if(ally2.getAttackCounter() == 0)
						{
							ally2.setAttackCounter(5);
							ally2.setCanAttack(true);
							
							System.out.println("Megumin is finally ready to fight again!");
						}
						
						else
						{
							System.out.println("Megumin is still to tired to fight! She'll be ready in " + ally2.getAttackCounter() + " turns.");
						}
					}
				}
				
				else
					ally2.storyAttack(monster); // Ally attacks monster

				if(monster.getHp() < 0) // If monster dies, then battle is over
				{
					System.out.println("__      _______ _____ _______ ____  _______     __\r\n" + 
					           "\\ \\    / /_   _/ ____|__   __/ __ \\|  __ \\ \\   / /\r\n" + 
					           " \\ \\  / /  | || |       | | | |  | | |__) \\ \\_/ / \r\n" + 
					           "  \\ \\/ /   | || |       | | | |  | |  _  / \\   /  \r\n" + 
					           "   \\  /   _| || |____   | | | |__| | | \\ \\  | |   \r\n" + 
					           "    \\/   |_____\\_____|  |_|  \\____/|_|  \\_\\ |_|   \r\n" + 
						       "                                                  ");
					System.out.println("You have defeated the monster!\n");
					playerTurns = 0;
					result = 1;
					break;
				}
			}

			if(ally3Dead == 0)
			{
				// Ally 3
				System.out.println();
				
				// Aqua
				if(ally3.getHeroType().equalsIgnoreCase("Goddess"))
				{
					Random r = new Random();
					int attack = r.nextInt(1);
					
					if(attack == 1)
					{
						Aqua temp = (Aqua) ally3; // Cast
						
						if(player.getHp() != player.getTotalHp())
						{
							temp.healAlly(player);
						}
						
						else if(ally1.getHp() != ally1.getTotalHp())
						{
							temp.healAlly(ally1);
						}
						
						else if(ally2.getHp() != ally2.getTotalHp())
						{
							temp.healAlly(ally2);
						}
						
						else if(ally3.getHp() != ally3.getTotalHp())
						{
							temp.healAlly(ally3);
						}
						
						else
						{
							attack = 0;
						}
					}
					
					if(attack == 0)
					{
						ally3.storyAttack(monster);
					}
					
				}
				
				// Megumin
				if(ally3.getHeroType().equalsIgnoreCase("Arch Wizard"))
				{
					if(ally3.getCanAttack() == true)
					{	
						ally3.storyAttack(monster);
						ally3.setCanAttack(false);
					}
					
					else
					{
						ally3.setAttackCounter(ally3.getAttackCounter() - 1);
						
						if(ally3.getAttackCounter() == 0)
						{
							ally3.setAttackCounter(5);
							ally3.setCanAttack(true);
							
							System.out.println("Megumin is finally ready to fight again!");
						}
						
						else
						{
							System.out.println("Megumin is still to tired to fight! She'll be ready in " + ally3.getAttackCounter() + " turns.");
						}
					}
				}
				
				else
					ally3.storyAttack(monster); // Ally attacks monster

				if(monster.getHp() < 0) // If monster dies, then battle is over
				{
					System.out.println("__      _______ _____ _______ ____  _______     __\r\n" + 
					           "\\ \\    / /_   _/ ____|__   __/ __ \\|  __ \\ \\   / /\r\n" + 
					           " \\ \\  / /  | || |       | | | |  | | |__) \\ \\_/ / \r\n" + 
					           "  \\ \\/ /   | || |       | | | |  | |  _  / \\   /  \r\n" + 
					           "   \\  /   _| || |____   | | | |__| | | \\ \\  | |   \r\n" + 
					           "    \\/   |_____\\_____|  |_|  \\____/|_|  \\_\\ |_|   \r\n" + 
						       "                                                  ");
					System.out.println("You have defeated the monster!\n");
					playerTurns = 0;
					result = 1;
					break;
				}
			}
			
			if(monster.getHp() > 0)
			{
				// Monster Regen
				System.out.println();
				monster.regenHealth();
				
				System.out.println("-------------------------------------------------------------");
				
				// Pause
				System.out.print("Hit enter to return to the menu:");
				kb.nextLine();
				System.out.println("-------------------------------------------------------------");
			}
		}
	
		return result;
	}
	
	// Events
	private static Integer[] event(Scanner kb, Integer[] ara, int doneEvent)
	{
		Random r = new Random();
		int diceRoll = r.nextInt(4);

		while(diceRoll == doneEvent)
		{
			diceRoll = r.nextInt(4);
		}

		ara[0] = diceRoll;


		if(diceRoll == 0) // Chickens
		{
			// Idea from the Legend of Zelda
			System.out.println("You and your group encounter a chicken in the middle of the road. You try to walk past it, but it keeps trying to get in your way.");
			System.out.println("\nWhat will you do?");
			System.out.print(
					"\t1. Continue to try and get past it\r\n" + 
					"\t2. Kill it\r\n");
			// Check if the input valid
			int option = 0;
			while(!(option == 1 || option == 2))
			{
				try
				{
					System.out.print("Choice -----> ");
					option = kb.nextInt(); // Choose Option
					kb.nextLine();  // Flush the buffer

					if(!(option == 1 || option == 2))
					{
						System.out.println("Invalid choice. Please type 1 or 2");
					}
				}

				catch(Exception e)
				{
					System.out.println("This is an invalid response. Please try again.");
					kb.nextLine();  // Flush the buffer
				}
			}

			if(option == 1)
			{
				System.out.println("\nAfter 10 mins of trying to out run a chicken, you finally got away and can continue on your journey.");
			}

			else
			{
				System.out.println("\nYou and your team spent 5 mins taking swings at the evasive chicken, but finally landed a hit. Unfortunately after getting hit, the chicken let out an earpiercing shriek. Moments later, an entire hoard of chickens came out of nowhere and attacked your team. You all fought valiently, but were quickly overtaken and killed.");
				System.out.println("Game Over");
				ara[1] = 0;
				return ara; // Dead
			}

			ara[1] = 1;
			return ara; // Alive
		}

		else if(diceRoll == 1) // Squirrels
		{
			// Idea from Rick & Morty and http://www.giantitp.com/forums/showthread.php?120333-Random-Event-(Not-encounter!)-List-Generator
			System.out.println("You and your group are traveling down the road, when you hear some noise in the trees. You notice that there is a squirrel in a tree looking down at you. Your team tells you to ignore it and keep walking.");
			System.out.println("\nWhat will you do?");
			System.out.print(
					"\t1. Continue walking the trail\r\n" + 
							"\t2. Turn around and find another to your next destination\r\n" +
					"\t3. Kill the squirrel\r\n");
			// Check if the input valid
			int option = 0;
			while(!(option == 1 || option == 2 || option == 3))
			{
				try
				{
					System.out.print("Choice -----> ");
					option = kb.nextInt(); // Choose Option
					kb.nextLine();  // Flush the buffer

					if(!(option == 1 || option == 2 || option == 3))
					{
						System.out.println("Invalid choice. Please type 1, 2, or 3");
					}
				}

				catch(Exception e)
				{
					System.out.println("This is an invalid response. Please try again.");
					kb.nextLine();  // Flush the buffer
				}
			}

			if(option == 1)
			{
				System.out.println("\nYou and your group keep walking down the trail. As you continue down the path you begin to hear more and more squirrels in the trees. \nYour teammates begin to notice the beady littles eyes peircing through the leaves and start to pick up the pace. It takes a while, but you all make it out of there ok.");
			}

			else if(option == 2)
			{
				System.out.println("\nYou are able to convince your team to turn back and head down another path. But, once you all turn around, you see hundreds of eyes staring into you from the sidelines. \nIt only took 2 minutes for there to be no evidence of your team every walking the trail.");
				System.out.println("Game Over");
				ara[1] = 0;
				return ara; // Dead
			}

			else //if(option == 3)
			{
				System.out.println("\nYou threw a dagger into the squirrel!");
				System.out.println("In that instant, you heard a massive shriek coming from behind you and your team. Without a second thought, you and your team bolted it like a bat out of hell and were able to escape whatever nightmare was behind you!");
			}

			ara[1] = 1;
			return ara; // Alive
		}

		else if(diceRoll == 2) // Stranger Good
		{
			System.out.println("As you and your group head down the road, you find an injured girl in the middle of the road. She is covered in cuts and isn't moving.");
			System.out.println("\nWhat will you do?");
			System.out.print(
					"\t1. Continue walking the trail\r\n" + 
							"\t2. Stop and help her\r\n" +
					"\t3. See if she has anything worth stealing\r\n");
			// Check if the input valid
			int option = 0;
			while(!(option == 1 || option == 2 || option == 3))
			{
				try
				{
					System.out.print("Choice -----> ");
					option = kb.nextInt(); // Choose Option
					kb.nextLine();  // Flush the buffer

					if(!(option == 1 || option == 2 || option == 3))
					{
						System.out.println("Invalid choice. Please type 1, 2, or 3");
					}
				}

				catch(Exception e)
				{
					System.out.println("This is an invalid response. Please try again.");
					kb.nextLine();  // Flush the buffer 
				}
			}

			if(option == 1)
			{
				System.out.println("\nYou and your group keep walking down the trail.");
			}

			else if(option == 3)
			{
				System.out.println("\nUnbeknownest to you, as you are checking to see if she has anything of value, her eyes open up. She pulls a dagger out and stabs you in the chest!");
				System.out.println("Game Over");
				ara[1] = 0;
				return ara; // Dead
			}

			else //if(option == 2)
			{
				System.out.println("\nYou walk over and use your potions to help her out.");
				System.out.println("After a few minutes, she wakes up and thanks you. As payment, she gives you a rare artifact that permanently raises your max hp!");
				ara[1] = 3;
				return ara; // Raise HP
			}

			ara[1] = 1;
			return ara; // Alive
		}

		else if(diceRoll == 3) // Stranger Bad
		{
			System.out.println("As you and your group head down the road, you find an passed out girl in the middle of the road. She isn't moving.");
			System.out.println("\nWhat will you do?");
			System.out.print(
					"\t1. Continue walking the trail\r\n" + 
							"\t2. Stop and help her\r\n" +
					"\t3. See if she has anything worth stealing\r\n");
			// Check if the input valid
			int option = 0;
			while(!(option == 1 || option == 2 || option == 3))
			{
				try
				{
					System.out.print("Choice -----> ");
					option = kb.nextInt(); // Choose Option
					kb.nextLine();  // Flush the buffer

					if(!(option == 1 || option == 2 || option == 3))
					{
						System.out.println("Invalid choice. Please type 1, 2, or 3");
					}
				}

				catch(Exception e)
				{
					System.out.println("This is an invalid response. Please try again.");
					kb.nextLine();  // Flush the buffer
				}
			}

			if(option == 1)
			{
				System.out.println("\nYou and your group keep walking down the trail.");
			}

			else if(option == 3)
			{
				System.out.println("\nYou look through her gear and find a strange artifact! \nAfter grabbing it, it lights up and you and your team begin to glow. This only lasts for 1 min, but now you all feel stronger for some reason.");
				ara[1] = 4;
				return ara; // Strength Up
			}

			else //if(option == 2)
			{
				System.out.println("\nYou walk over and use your potions to help her out.");
				System.out.println("After a few minutes, she wakes up and thanks you. Then out of nowhere, she blasts you and your team with a lightning bolt! She then loots your dead bodies with a smirk and walks away.");
				System.out.println("Game Over");
				ara[1] = 0;
				return ara; // Dead
			}

			ara[1] = 1;
			return ara; // Alive
		}

		ara[1] = 1;
		return ara; // Default
	}
	
	// Select an Item to Use
	private static int selectItem(Scanner kb, Hero hero)
	{
		int option = 0;
		
		System.out.println(
				  "\nYou have:"
				+ "\n1. Potions +25 hp: " + hero.getPotions()
				+ "\n2. Good Luck Charms +15 Chance to hit: " + hero.getCharm()
				+ "\n3. Strength Potions +25 Strength: " + hero.getStrPot()
				+ "\n4. Back to menu");
		
		while(!(option == 1 || option == 2 || option == 3 || option == 4))
		{
			try
			{
				System.out.print("Which would you like to use: ");
				option = kb.nextInt(); // Choose Option
				kb.nextLine();  // Flush the buffer

				if(!(option == 1 || option == 2 || option == 3 || option == 4))
				{
					System.out.println("Invalid choice. Please type 1, 2, 3, or 4.");
				}
				
				if(option == 1 && hero.getPotions() == 0)
				{
					System.out.println("\nYou don't have any of that left! PLease select another one.");
					option = 0;
				}
				
				if(option == 2 && hero.getCharm() == 0)
				{
					System.out.println("You don't have any of that left! PLease select another one.");
					option = 0;
				}
				
				if(option == 3 && hero.getStrPot() == 0)
				{
					System.out.println("You don't have any of that left! PLease select another one.");
					option = 0;
				}
			}

			catch(Exception e)
			{
				System.out.println("This is an invalid response. Please try again.");
				kb.nextLine();  // Flush the buffer
			}
		}
		
		return option;
	}
	
	// Select a Hero Class (Jokes)
	private static Hero selectHeroJokes(Scanner kb, int mode)
	{		
		Hero player = null;
		int heroChoice = 0;
		String temp = "";
		int seperate = 0;
		
		while (heroChoice != 1 && heroChoice != 2 && heroChoice != 3 && heroChoice != 4 && heroChoice != 5 && heroChoice != 6 && heroChoice != 7)
		{
			// Menu Options
			System.out.println("-------------------------------------------------------------");
			System.out.println("Character Creation:");
			System.out.println("Type \"info Number\" for more information on that class");
			System.out.println("\t1. Warrior"
					+ "\n\t2. Sorcer(ess/er)"
					+ "\n\t3. Thief"
					+ "\n\t4. Knight"
					+ "\n\t5. Battle Mage"
					+ "\n\t6. GM");
			if(mode == 0)
			{
				System.out.println("\t7. Quit");
			}
			
			else if(mode == 1)
			{
				System.out.println("\t7. Back to menu");
			}

			else if(mode == 2)
			{
				System.out.println("\t7. Cancel Team Selection");
			}
			
			// Check if the input valid
			while(!(heroChoice == 1 || heroChoice == 2 || heroChoice == 3 || heroChoice == 4 || heroChoice == 5 || heroChoice == 6 || heroChoice == 7))
			{
				try
				{
					System.out.print("Option -----> ");
					temp = kb.nextLine();
					
					seperate = temp.indexOf(" ");
					if(seperate != -1 && temp.substring(0, seperate).equalsIgnoreCase("info")) // They had a space
					{
						int heroInt = Integer.parseInt(temp.substring(seperate + 1));

						if(heroInt == 1 || heroInt == 2 || heroInt == 3 || heroInt == 4 || heroInt == 5 || heroInt == 6)
						{
							System.out.println("-------------------------------------------------------------");
							switch(heroInt)
							{
							case 1: // Warrior
								System.out.println("Warrior Stats:"
										+ "\n\tHp: 125"
										+ "\n\tAttack Speed: 4"
										+ "\n\tStandard Damage Min: 35"
										+ "\n\tStandard Damage Max: 60"
										+ "\n\tChance to Block: 20%"
										+ "\nWarrior's Attacks: "
										+ "\n\tA standard attack that has an 80% chance of hitting."
										+ "\n\tA special attack that has a 40% chance of hitting."
										+ "\nWarrior's Default Inventory:"
										+ "\n\t2 Potions & 3 Strength Potions");
								break;
							case 2: // Sorceress
								System.out.println("Socer(ess/er) Stats:"
										+ "\n\tHp: 75"
										+ "\n\tAttack Speed: 5"
										+ "\n\tStandard Damage Min: 25"
										+ "\n\tStandard Damage Max: 45"
										+ "\n\tChance to Block: 70%"
										+ "\nSocer(ess/er)'s Attacks: "
										+ "\n\tA standard attack that has a 70% chance of hitting."
										+ "\n\tA healing spell that has an 80% chance of working."
										+ "\nSocer(ess/er)'s Default Inventory:"
										+ "\n\t4 Potions & 1 Good Luck Charm");
								break;
							case 3: // Thief
								System.out.println("Thief Stats:"
										+ "\n\tHp: 75"
										+ "\n\tAttack Speed: 6"
										+ "\n\tStandard Damage Min: 20"
										+ "\n\tStandard Damage Max: 40"
										+ "\n\tChance to Block: 80%"
										+ "\nThief's Attacks: "
										+ "\n\tA standard attack that has an 80% chance of hitting."
										+ "\n\tA sneak attack (that can then be followed up by a standard attack) that has a 40% chance of hitting."
										+ "\nThief's Default Inventory:"
										+ "\n\t2 Potions & 3 Good Luck Charms");
								break;
							case 4: // Knight
								System.out.println("Knight Stats:"
										+ "\n\tHp: 200"
										+ "\n\tAttack Speed: 1"
										+ "\n\tStandard Damage Min: 20"
										+ "\n\tStandard Damage Max: 50"
										+ "\n\tChance to Block: 70%"
										+ "\nKnight's Attacks: "
										+ "\n\tA standard attack that has a 70% chance of hitting."
										+ "\n\tA shield bash that has a 50% chance of hitting."
										+ "\nKnight's Default Inventory:"
										+ "\n\t1 Potions, 1 Good Luck Charm, & 2 Strength Potions");
								break;
							case 5: // Battle Mage
								System.out.println("Battle Mage Stats:"
										+ "\n\tHp: 95"
										+ "\n\tAttack Speed: 3"
										+ "\n\tStandard Damage Min: 50"
										+ "\n\tStandard Damage Max: 70"
										+ "\n\tChance to Block: 20%"
										+ "\nBattle Mage's Attacks: "
										+ "\n\tA standard spell that has a 70% chance of hitting."
										+ "\n\tAn advanced spell that has a 50% chance of hitting."
										+ "\nBattle Mage's Default Inventory:"
										+ "\n\t2 Potions, 1 Good Luck Charm,  & 2 Strength Potions");
								break;
							case 6: // GM
								System.out.println("Game Master Stats:"
										+ "\n\tHp: 1000"
										+ "\n\tAttack Speed: 6"
										+ "\n\tStandard Damage Min: 100"
										+ "\n\tStandard Damage Max: 200"
										+ "\n\tChance to Block: 70%"
										+ "\nGame Master's Attacks: "
										+ "\n\tA powerful attack that has an 99% chance of hitting."
										+ "\n\tAn extremely powerful attack that has a 100% chance of hitting."
										+ "\nGame Master's Default Inventory:"
										+ "\n\t7 Potions, 7 Good Luck Charm,  & 7 Strength Potions");
								break;
							}
							

							System.out.print("\nHit enter to return to the menu:");
							kb.nextLine();
						}

						else
						{
							System.out.println("\nInvalid info search. Type something like \"info 1\"");
						}

						break;
					}
					
					else // They just typed in a value
					{
						heroChoice = Integer.parseInt(temp); // Choose Option
					}

					if(!(heroChoice == 1 || heroChoice == 2 || heroChoice == 3 || heroChoice == 4 || heroChoice == 5 || heroChoice == 6 || heroChoice == 7))
					{
						System.out.println("Invalid input. Please type any number from 1 to 7.");
					}
				}
				
				catch(NumberFormatException e) // Didn't type in a number after "info"
				{
					if(seperate != -1)
					{
						if(temp.substring(0, seperate).equalsIgnoreCase("info"))
						{
							System.out.println("You must type a valid number after \"info\". Type something like \"info 1\"");
						}
						
						else
						{
							System.out.println("Invalid choice. Try again.");
						}
					}
					
					else
					{
						if(temp.equalsIgnoreCase("info"))
						{
							System.out.println("You must type a valid number after \"info\". Type something like \"info 1\"");
						}
						
						else
						{
							System.out.println("Invalid choice. Try again.");
						}
					}
				}
				
				catch(Exception e)
				{
					System.out.println("Invalid choice. Try again.");
					kb.nextLine();  // Flush the buffer
				}
			}
		}
		
		switch (heroChoice)
		{
		case 1: // Warrior
			if(mode == 2)
			{
				player = new Warrior("");
			}
			
			else
				player = new Warrior();
			
			break;
		case 2: // Sorceress
			if(mode == 2)
			{
				player = new Sorceress("");
			}
			
			else
			{
				player = new Sorceress();
				
				// Aqua
				if(player.getName().equalsIgnoreCase("Aqua") && player.getGender().equalsIgnoreCase("Female"))
				{
					player = new Aqua("");
				}
			}
			
			break;
		case 3: // Thief
			if(mode == 2)
			{
				player = new Thief("");
			}
			
			else
			{
				player = new Thief();
				
				// Kazuma
				if(player.getName().equalsIgnoreCase("Kazuma") && player.getGender().equalsIgnoreCase("Male"))
				{
					player = new Kazuma("");
				}
			}
			
			break;
		case 4: // Knight
			if(mode == 2)
			{
				player = new Knight("");
			}
			
			else
			{
				player = new Knight();
				
				// Darkness
				if(player.getName().equalsIgnoreCase("Darkness") && player.getGender().equalsIgnoreCase("Crusader"))
				{
					player = new Darkness("");
				}
			}
			
			break;
		case 5: // Battle Mage
			if(mode == 2)
			{
				player = new BattleMage("");
			}
			
			else
			{
				player = new BattleMage();

				// Megumin
				if(player.getName().equalsIgnoreCase("Megumin") && player.getGender().equalsIgnoreCase("Female"))
				{
					player = new Megumin("");
				}
			}
			
			break;
		case 6: // GM
			if(mode == 2)
			{
				player = new GameMaster("");
			}
			
			else
				player = new GameMaster();
			
			break;
		case 7: // Quit
			player = null;
			break;
		}
		
		return player;
	}
	
	// Select a Hero Class (No Jokes)
	private static Hero selectHero(Scanner kb, int mode)
	{
		Hero player = null;
		int heroChoice = 0;
		String temp = "";
		int seperate = 0;

		while (heroChoice != 1 && heroChoice != 2 && heroChoice != 3 && heroChoice != 4 && heroChoice != 5 && heroChoice != 6)
		{
			// Menu Options
			System.out.println("-------------------------------------------------------------");
			System.out.println("Character Creation:");
			System.out.println("Type \"info Number\" for more information on that class");
			System.out.println("\t1. Warrior"
					+ "\n\t2. Sorcer(ess/er)"
					+ "\n\t3. Thief"
					+ "\n\t4. Knight"
					+ "\n\t5. Battle Mage");

			if(mode == 0)
			{
				System.out.println("\t6. Quit");
			}

			else if(mode == 1)
			{
				System.out.println("\t6. Back to menu");
			}

			else if(mode == 2)
			{
				System.out.println("\t6. Cancel Team Selection");
			}

			// Check if the input valid
			while(!(heroChoice == 1 || heroChoice == 2 || heroChoice == 3 || heroChoice == 4 || heroChoice == 5 || heroChoice == 6))
			{
				try
				{
					System.out.print("Option -----> ");
					temp = kb.nextLine();

					seperate = temp.indexOf(" ");
					if(seperate != -1 && temp.substring(0, seperate).equalsIgnoreCase("info")) // They had a space
					{
						int heroInt = Integer.parseInt(temp.substring(seperate + 1));

						if(heroInt == 1 || heroInt == 2 || heroInt == 3 || heroInt == 4 || heroInt == 5)
						{
							System.out.println("-------------------------------------------------------------");
							switch(heroInt)
							{
							case 1: // Warrior
								System.out.println("Warrior Stats:"
										+ "\n\tHp: 125"
										+ "\n\tAttack Speed: 4"
										+ "\n\tStandard Damage Min: 35"
										+ "\n\tStandard Damage Max: 60"
										+ "\n\tChance to Block: 20%"
										+ "\nWarrior's Attacks: "
										+ "\n\tA standard attack that has an 80% chance of hitting."
										+ "\n\tA special attack that has a 40% chance of hitting."
										+ "\nWarrior's Default Inventory:"
										+ "\n\t2 Potions & 3 Strength Potions");
								break;
							case 2: // Sorceress
								System.out.println("Socer(ess/er) Stats:"
										+ "\n\tHp: 75"
										+ "\n\tAttack Speed: 5"
										+ "\n\tStandard Damage Min: 25"
										+ "\n\tStandard Damage Max: 45"
										+ "\n\tChance to Block: 70%"
										+ "\nSocer(ess/er)'s Attacks: "
										+ "\n\tA standard attack that has a 70% chance of hitting."
										+ "\n\tA healing spell that has an 80% chance of working."
										+ "\nSocer(ess/er)'s Default Inventory:"
										+ "\n\t4 Potions & 1 Good Luck Charm");
								break;
							case 3: // Thief
								System.out.println("Thief Stats:"
										+ "\n\tHp: 75"
										+ "\n\tAttack Speed: 6"
										+ "\n\tStandard Damage Min: 20"
										+ "\n\tStandard Damage Max: 40"
										+ "\n\tChance to Block: 80%"
										+ "\nThief's Attacks: "
										+ "\n\tA standard attack that has an 80% chance of hitting."
										+ "\n\tA sneak attack (that can then be followed up by a standard attack) that has a 40% chance of hitting."
										+ "\nThief's Default Inventory:"
										+ "\n\t2 Potions & 3 Good Luck Charms");
								break;
							case 4: // Knight
								System.out.println("Knight Stats:"
										+ "\n\tHp: 200"
										+ "\n\tAttack Speed: 1"
										+ "\n\tStandard Damage Min: 20"
										+ "\n\tStandard Damage Max: 50"
										+ "\n\tChance to Block: 70%"
										+ "\nKnight's Attacks: "
										+ "\n\tA standard attack that has a 70% chance of hitting."
										+ "\n\tA shield bash that has a 50% chance of hitting."
										+ "\nKnight's Default Inventory:"
										+ "\n\t1 Potions, 1 Good Luck Charm, & 2 Strength Potions");
								break;
							case 5: // Battle Mage
								System.out.println("Battle Mage Stats:"
										+ "\n\tHp: 95"
										+ "\n\tAttack Speed: 3"
										+ "\n\tStandard Damage Min: 50"
										+ "\n\tStandard Damage Max: 70"
										+ "\n\tChance to Block: 20%"
										+ "\nBattle Mage's Attacks: "
										+ "\n\tA standard spell that has a 70% chance of hitting."
										+ "\n\tAn advanced spell that has a 50% chance of hitting."
										+ "\nBattle Mage's Default Inventory:"
										+ "\n\t2 Potions, 1 Good Luck Charm,  & 2 Strength Potions");
								break;
							}


							System.out.print("\nHit enter to return to the menu:");
							kb.nextLine();
						}

						else
						{
							System.out.println("\nInvalid info search. Type something like \"info 1\"");
						}

						break;
					}

					else // They just typed in a value
					{
						heroChoice = Integer.parseInt(temp); // Choose Option
					}

					if(!(heroChoice == 1 || heroChoice == 2 || heroChoice == 3 || heroChoice == 4 || heroChoice == 5 || heroChoice == 6))
					{
						System.out.println("Invalid input. Please type any number from 1 to 6.");
					}
				}

				catch(NumberFormatException e) // Didn't type in a number after "info"
				{
					if(seperate != -1)
					{
						if(temp.substring(0, seperate).equalsIgnoreCase("info"))
						{
							System.out.println("You must type a valid number after \"info\". Type something like \"info 1\"");
						}

						else
						{
							System.out.println("Invalid choice. Try again.");
						}
					}

					else
					{
						if(temp.equalsIgnoreCase("info"))
						{
							System.out.println("You must type a valid number after \"info\". Type something like \"info 1\"");
						}

						else
						{
							System.out.println("Invalid choice. Try again.");
						}
					}
				}

				catch(Exception e)
				{
					System.out.println("Invalid choice. Try again.");
					kb.nextLine();  // Flush the buffer
				}
			}
		}

		switch (heroChoice)
		{
		case 1: // Warrior
			if(mode == 2)
			{
				player = new Warrior("");
			}
			
			else
				player = new Warrior();
			
			break;
		case 2: // Sorceress
			if(mode == 2)
			{
				player = new Sorceress("");
			}
			
			else
				player = new Sorceress();
			
			break;
		case 3: // Thief
			if(mode == 2)
			{
				player = new Thief("");
			}
			
			else
				player = new Thief();
			
			break;
		case 4: // Knight
			if(mode == 2)
			{
				player = new Knight("");
			}
			
			else
				player = new Knight();
			
			break;
		case 5: // Battle Mage
			if(mode == 2)
			{
				player = new BattleMage("");
			}
			
			else
				player = new BattleMage();
			
			break;
		case 6: // Quit
			player = null;
			break;
		}

		return player;
	}
	
	// Save Game
	private static void saveGame(Scanner kb, Hero player)
	{
		// Found Here: https://stackoverflow.com/questions/2885173/how-do-i-create-a-file-and-write-to-it-in-java
		if(saveGame.exists()) // Overwriting the old save
		{
			String answer = "";
			System.out.println("Would you like to overwrite your current save?");
			while(!(answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("N") || answer.equalsIgnoreCase("Yes") || answer.equalsIgnoreCase("No")))
			{
				try
				{
					System.out.print("Yes or No -----> ");
					answer = kb.nextLine(); // Choose Option

					if(!(answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("N") || answer.equalsIgnoreCase("Yes") || answer.equalsIgnoreCase("No")))
					{
						System.out.println("Invalid choice. Please choose Yes or No.");
					}
				}

				catch(Exception e)
				{
					System.out.println("Invalid Input. Try again.");
				}
			}
			
			if(answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y"))
			{
				File backup = new File("SaveBackup.txt");
				// Backing up a file: https://stackoverflow.com/questions/22135156/how-to-backup-file-in-java
				// Getting the current directory: https://stackoverflow.com/questions/4871051/getting-the-current-working-directory-in-java
				Path dir = Paths.get("");
				Path source = Paths.get(dir.toAbsolutePath().toString() + "\\Save.txt");
			    Path target = Paths.get(dir.toAbsolutePath().toString() + "\\SaveBackup.txt");
			    try 
			    {
			    	if(backup.exists())
					{
			    		backup.delete(); // Delete Old Backup
					}
			        Files.copy(source, target);
			        System.out.println("\tBackup Completed");
			    } 
			    
			    catch (Exception e) 
			    {
			        System.out.println("Something went wrong when creating the backup file.");
			    }
				
			    // Writing the new save file
				try
				{
					PrintWriter printFile = new PrintWriter(saveGame);
					
					printFile.println(player.getName());
					printFile.println(player.getGender());
					printFile.println(player.getHeroType());
					printFile.close();
					
					System.out.println("\tSave Successful\n-------------------------------------------------------------\n");
				}
				
				catch(Exception e)
				{
					System.out.println("Something went wrong with saving the file.");
				}
			}
			
			else
			{
				System.out.println("\nFile didn't save. Going back to the menu.\n-------------------------------------------------------------\n");
			}
		}
		
		else // Create Save File
		{
			try
			{
				PrintWriter newFile = new PrintWriter("Save.txt", "UTF-8");
				newFile.println(player.getName());
				newFile.println(player.getGender());
				newFile.println(player.getHeroType());
				newFile.close();
			}
			
			catch(Exception e)
			{
				System.out.println("Something went wrong with createing a save file.");
			}
		}
	}
	
	// Settings
	private static void toggles(Scanner kb)
	{
		int choice = 0;
		
		while (choice != 3)
		{
			// Jokes on or off
			String joke = "Off";
			if(jokes == 1)
			{
				joke = "On";
			}
			
			// Hardcore on or off
			String hard = "Off";
			if(hardcore == 1)
			{
				hard = "On";
			}
			
			System.out.println("Options:\n"
						 + "\t1. Jokes & Easter Eggs: " + joke + "\n"
						 + "\t2. Hardcore Story Mode (Teammates Die instead of Fainting): " + hard + "\n"
						 + "\t3. Back");
		
			// Check if the input valid
			while(!(choice == 1 || choice == 2 || choice == 3))
			{
				try
				{
					System.out.print("Choice -----> ");
					choice = kb.nextInt(); // Choose Option
					kb.nextLine();  // Flush the buffer

					if(!(choice == 1 || choice == 2 || choice == 3))
					{
						System.out.println("Invalid choice. Please choose 1, 2, or 3.");
					}
				}

				catch(Exception e)
				{
					System.out.println("Invalid input. Please try again.");
					kb.nextLine();  // Flush the buffer
				}
			}
			
			if(choice == 1)
			{
				if(jokes == 1)
					jokes = 0;
				else
					jokes = 1;
				choice = 0;
				System.out.println("\n-------------------------------------------------------------");
			}
			
			else if(choice == 2)
			{
				if(hardcore == 1)
					hardcore = 0;
				else
					hardcore = 1;
				choice = 0;
				System.out.println("\n-------------------------------------------------------------");
			}
		}
		
		System.out.println("\n-------------------------------------------------------------");
	}
}
