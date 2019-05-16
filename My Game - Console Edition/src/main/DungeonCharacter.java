package main;
import java.util.*;

public abstract class DungeonCharacter {
	
	protected String name = ""; // Name
	protected String gender = ""; // Gender
	protected int hp = 1; // Hit Points
	protected int atckSpd = 1; // Attack Speed
	protected int dmgMin = 1; // Damage Minimum
	protected int dmgMax = 1; // Damage Maximum
	protected double hitChnce = 1.0;
	protected int totalHp = hp;
	protected int dmgMinOG = dmgMin; // Original Damage Minimum
	protected int dmgMaxOG = dmgMax; // Original Damage Maximum
	protected double hitChnceOG = hitChnce; // Original Hit Chance
	
	// DVC
	public DungeonCharacter()
	{
		name = "Bob Jones";
		gender = "";
		hp = 100;
		atckSpd = 1;
		dmgMin = 1;
		dmgMax = 1;
		hitChnce = 10.0;
	}
	
	// EVC
	public DungeonCharacter(String name, String gender, int hp, int attckSpd, int dmgMin, int dmgMax, double hitChnce)
	{
		this.name = name;
		this.gender = gender;
		this.hp = hp;
		this.atckSpd = attckSpd;
		this.dmgMin = dmgMin;
		this.dmgMax = dmgMax;
		this.hitChnce = hitChnce;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) 
	{
		this.gender = gender;
	}

	public int getHp() 
	{
		return hp;
	}

	public void setHp(int hp)
	{
		this.hp = hp;
	}

	public int getAtckSpd() 
	{
		return atckSpd;
	}

	public void setAtckSpd(int atckSpd)
	{
		this.atckSpd = atckSpd;
	}

	public int getDmgMin() 
	{
		return dmgMin;
	}

	public void setDmgMin(int dmgMin) 
	{
		this.dmgMin = dmgMin;
	}

	public int getDmgMax() 
	{
		return dmgMax;
	}

	public void setDmgMax(int dmgMax) 
	{
		this.dmgMax = dmgMax;
	}

	public double getHitChnce() 
	{
		return hitChnce;
	}

	public void setHitChnce(double hitChnce) 
	{
		this.hitChnce = hitChnce;
	}
	
	public int getTotalHp() 
	{
		return totalHp;
	}

	public void setTotalHp(int totalHp)
	{
		this.totalHp = totalHp;
	}
	
	public int getDmgMinOG() 
	{
		return dmgMinOG;
	}

	public void setDmgMinOG(int dmgMinOG)
	{
		this.dmgMinOG = dmgMinOG;
	}

	public int getDmgMaxOG() 
	{
		return dmgMaxOG;
	}

	public void setDmgMaxOG(int dmgMaxOG) 
	{
		this.dmgMaxOG = dmgMaxOG;
	}

	public double getHitChnceOG() 
	{
		return hitChnceOG;
	}

	public void setHitChnceOG(double hitChnceOG) 
	{
		this.hitChnceOG = hitChnceOG;
	}

	public void resetStats()
	{
		this.setHitChnce(hitChnceOG);
		this.setDmgMin(dmgMinOG);
		this.setDmgMax(dmgMaxOG);
	}
	
	public void attack(DungeonCharacter that)
	{
		Random r = new Random(); // Random
		int diceRoll = r.nextInt(101); // Dice Roll
		
		if(diceRoll <= this.hitChnce)
		{
			int dmg = r.nextInt(dmgMax - dmgMin + 1) + dmgMin;
			that.hp = that.hp - dmg;
			System.out.println("Attack Successfull");
		}
		
		else if(diceRoll > this.hitChnce)
		{
			System.out.println("The attack missed.");
		}
	}
	
	public void superAttack(DungeonCharacter that)
	{
		Random r = new Random(); // Random
		int diceRoll = r.nextInt(101); // Dice Roll
		
		if(diceRoll <= this.hitChnce)
		{
			int dmg = r.nextInt(dmgMax - dmgMin + 1) + dmgMin;
			that.hp = that.hp - dmg;
			System.out.println("Attack Successfull");
		}
		
		else if(diceRoll > this.hitChnce)
		{
			System.out.println("The attack missed.");
		}
	}
	
}
