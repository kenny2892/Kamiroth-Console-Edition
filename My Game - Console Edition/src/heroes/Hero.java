package heroes;
import java.util.*;
import main.*;

public abstract class Hero extends DungeonCharacter{

	double chance2Block = 1.0;
	int roundTurns = 1;
	int potions = 0; // Health Potion: Boost Health, + 25 hp
	int charm = 0; // Good Luck Charm: Boost Chance to Hit, + 15%
	int strPot = 0; // Strength Potion: Boost Max & Min Damage, + 25 dmg
	String menu = "Default Text";
	String heroType = "Default Hero";
	
	// Just for Megumin
	protected static int attackCounter = 5;
	protected static boolean canAttack = true;
	
	// DVC
	public Hero()
	{
		this.chance2Block = 10.0;
		this.roundTurns = 1;
		inputValues();
	}
	
	// EVC (For NPC's & Save Loading)
	public Hero(String name)
	{
		this.name = name;
		this.gender = "Male";
	}
	
	public double getChance2Block() 
	{
		return chance2Block;
	}

	public void setChance2Block(double chance2Block) 
	{
		this.chance2Block = chance2Block;
	}

	public int getRoundTurns() 
	{
		return roundTurns;
	}

	public void setRoundTurns(int roundTurns) 
	{
		this.roundTurns = roundTurns;
	}

	public int getPotions() 
	{
		return potions;
	}

	public void setPotions(int potions)
	{
		this.potions = potions;
	}

	public int getCharm()
	{
		return charm;
	}

	public void setCharm(int charm) 
	{
		this.charm = charm;
	}

	public int getStrPot()
	{
		return strPot;
	}

	public void setStrPot(int strPot) 
	{
		this.strPot = strPot;
	}
	
	public String getMenu()
	{
		return menu;
	}

	public void setMenu(String menu) 
	{
		this.menu = menu;
	}
	
	public String getHeroType()
	{
		return heroType;
	}

	public void setHeroType(String heroType) 
	{
		this.heroType = heroType;
	}
	
	// For Megumin
	public int getAttackCounter() 
	{
		return attackCounter;
	}

	public void setAttackCounter(int attackCounter) 
	{
		Hero.attackCounter = attackCounter;
	}

	public boolean getCanAttack() 
	{
		return canAttack;
	}

	public void setCanAttack(boolean canAttack) 
	{
		Hero.canAttack = canAttack;
	}

	public void inputValues()
	{
		Scanner kb = new Scanner(System.in);
		System.out.print("What is your name: ");
		name = kb.nextLine();
		name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();		
		this.setName(name);
		
		while(gender.equals(""))
		{
			System.out.print("What is your gender (M, F, or Other): ");
			gender = kb.nextLine();
			
			if(gender.toUpperCase().equals("M") == false && gender.toUpperCase().equals("MALE") == false && gender.toUpperCase().equals("F") == false && gender.toUpperCase().equals("FEMALE") == false && gender.toUpperCase().equals("OTHER") == false && gender.toUpperCase().equals("O") == false)
			{
				System.out.println("Please select type in Male, Female, or Other.");
				gender = "";
			}
			
			else if(gender.toUpperCase().equals("M"))
			{
				gender = "Male";
			}
			
			else if(gender.toUpperCase().equals("F"))
			{
				gender = "Female";
			}
			
			else if(gender.toUpperCase().equals("O"))
			{
				gender = "Other";
			}
			
			else
			{
				gender = gender.substring(0,1).toUpperCase() + gender.substring(1).toLowerCase();
			}
		}
		this.setGender(gender);
	}
	
	public int calcTurns(DungeonCharacter that) // Do at the beginning of a game
	{
		int speed = this.atckSpd / that.getAtckSpd();
		if(speed <= 0)
		{
			speed = 1;
		}
		
		return speed;
	}
	
	@Override
	public void attack(DungeonCharacter that)
	{
		Random r = new Random(); // Random
		int diceRoll = r.nextInt(101); // Dice Roll
		
		if(diceRoll <= this.hitChnce)
		{
			int dmg = r.nextInt(dmgMax - dmgMin + 1) + dmgMin;
			that.setHp(that.getHp() - dmg);
			System.out.println("You have attacked the monster! You did: " + dmg + " damage");
		}
		
		else if(diceRoll > this.hitChnce)
		{
			System.out.println("You missed the monster!");
		}
	}
	
	@Override
	public void superAttack(DungeonCharacter that)
	{
		Random r = new Random(); // Random
		int diceRoll = r.nextInt(101); // Dice Roll
		
		if(diceRoll <= this.hitChnce)
		{
			int dmg = r.nextInt(dmgMax - dmgMin + 1) + dmgMin; 
			that.setHp(that.getHp() - dmg);
			String line2 = "You did";
			System.out.println(line2 + " -" + dmg + " hp");
		}
		
		else if(diceRoll > this.hitChnce)
		{
			System.out.println("Your attack missed!");
		}
	}
	
	public void storyAttack(DungeonCharacter that)
	{
		Random r = new Random(); // Random
		int diceRoll = r.nextInt(101); // Dice Roll
		
		if(diceRoll <= this.hitChnce)
		{
			int dmg = r.nextInt(dmgMax - dmgMin + 1) + dmgMin;
			that.setHp(that.getHp() - dmg);
			System.out.println(this.name + " hit the beast! -" + dmg + " hp");
		}
		
		else if(diceRoll > this.hitChnce)
		{
			System.out.println(this.name + " missed the monster!");
		}
	}
	
	@Override
	public void resetStats()
	{
		this.setHp(totalHp);
		this.setHitChnce(hitChnceOG);
		this.setDmgMin(dmgMinOG);
		this.setDmgMax(dmgMaxOG);
	}
}
