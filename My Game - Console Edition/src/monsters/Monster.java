package monsters;
import java.util.Random;
import heroes.*;
import main.*;

public abstract class Monster extends DungeonCharacter{
	
	double chance2Heal = 10.0;
	int healMin = 1;
	int healMax = 30;
	String mstrType = "Default Monster";
	
	//DVC
	public Monster()
	{
		this.chance2Heal = 10.0;
		this.healMin = 1;
		this.healMax = 10;
	}
	
	// EVC
	public Monster(double chance, int healMin, int healMax)
	{
		this.chance2Heal = chance;
		this.healMin = healMin;
		this.healMax = healMax;
	}

	public double getChance2Heal() 
	{
		return chance2Heal;
	}

	public void setChance2Heal(double chance2Heal) 
	{
		this.chance2Heal = chance2Heal;
	}

	public int getHealMin()
	{
		return healMin;
	}

	public void setHealMin(int healMin)
	{
		this.healMin = healMin;
	}

	public int getHealMax()
	{
		return healMax;
	}

	public void setHealMax(int healMax) 
	{
		this.healMax = healMax;
	}
	
	public String getMstrType() 
	{
		return mstrType;
	}

	public void setMstrType(String mstrType) 
	{
		this.mstrType = mstrType;
	}

	public abstract void regenHealth();
	
	public void attack(Hero that)
	{
		Random r = new Random(); // Random
		int diceRoll = r.nextInt(101); // Dice Roll
		
		if(diceRoll <= this.hitChnce)
		{
			int dmg = r.nextInt(dmgMax - dmgMin + 1) + dmgMin;
			
			if(r.nextInt(101) > that.getChance2Block()) // If block was unsuccessful
			{
				that.setHp(that.getHp() - dmg);
				System.out.println("The monster has attacked you! It did: " + dmg + " damage to you\n");
			}
			
			else // Successful Block
			{
				System.out.println("You successfully blocked the monster's attack!");
			}
		}
		
		else if(diceRoll > this.hitChnce)
		{
			System.out.println("You dodged the monster's blow!\n");
		}
	}
	
	public void storyAttack(Hero that)
	{
		Random r = new Random(); // Random
		int diceRoll = r.nextInt(101); // Dice Roll
		
		if(diceRoll <= this.hitChnce)
		{
			int dmg = r.nextInt(dmgMax - dmgMin + 1) + dmgMin;
			
			if(r.nextInt(101) > that.getChance2Block()) // If block was unsuccessful
			{
				that.setHp(that.getHp() - dmg);
				System.out.println("The monster has attacked " + that.getName() + "! It did: " + dmg + " damage\n");
			}
			
			else // Successful Block
			{
				System.out.println(that.getName() + " successfully blocked the monster's attack!\n");
			}
		}
		
		else if(diceRoll > this.hitChnce)
		{
			System.out.println(that.getName() + " dodged the monster's blow!\n");
		}
	}
	
}
