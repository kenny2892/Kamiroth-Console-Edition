package heroes;
import java.util.Random;
import main.*;

public class Aqua extends Hero{
	
	String menu = "\t1. Use God Requiem. (1% chance of success)\r\n" + 
				  "\t2. Perform a healing spell. (80% chance of success)\r\n" + 
				  "\t3. Show stats.\r\n" + 
				  "\t4. Use Item.\r\n" +
				  "\t5. Admit defeat. (Quit battle)\r\n";
	
	// DVC
	public Aqua()
	{
		this.setHp(75);
		this.setAtckSpd(5);
		this.setHitChnce(1);
		this.setDmgMin(1000);
		this.setDmgMax(2000);
		this.setChance2Block(30);
		this.setPotions(4);
		this.setCharm(1);
		this.setStrPot(0);
		this.setGender("Female");
		this.setName("Aqua");
		this.setHeroType("Goddess");
		this.setMenu(menu);
		
		this.setTotalHp(hp);
		this.setHitChnceOG(hitChnce);
		this.setDmgMinOG(dmgMin);
		this.setDmgMaxOG(dmgMax);
	}
	
	// EVC (Loading from a Save File)
	public Aqua(String name, String gender)
	{
		super(name);
		this.setGender("Female");
		this.setName("Aqua");
		this.setHeroType("Goddess");

		this.setHp(75);
		this.setAtckSpd(5);
		this.setHitChnce(1);
		this.setDmgMin(1000);
		this.setDmgMax(2000);
		this.setChance2Block(30);
		this.setPotions(4);
		this.setCharm(1);
		this.setStrPot(0);
		this.setMenu(menu);
		
		this.setTotalHp(hp);
		this.setHitChnceOG(hitChnce);
		this.setDmgMinOG(dmgMin);
		this.setDmgMaxOG(dmgMax);
	}
	
	// EVC for NPC's
	public Aqua(String name)
	{
		super(name);
		this.setGender("Female");
		this.setName("Aqua");
		this.setHeroType("Goddess");
		
		this.setHp(75);
		this.setAtckSpd(5);
		this.setHitChnce(1);
		this.setDmgMin(1000);
		this.setDmgMax(2000);
		this.setChance2Block(30);
		this.setPotions(4);
		this.setCharm(1);
		this.setStrPot(0);
		this.setMenu(menu);
		
		this.setTotalHp(hp);
		this.setHitChnceOG(hitChnce);
		this.setDmgMinOG(dmgMin);
		this.setDmgMaxOG(dmgMax);
	}

	@Override
	public void superAttack(DungeonCharacter that)
	{
		Random r = new Random(); // Random
		int diceRoll = r.nextInt(101); // Dice Roll

		this.setDmgMin(90); // Min heals
		this.setDmgMax(200); // Max heals
		this.setHitChnce(80); // Chance to heal

		if(diceRoll <= this.hitChnce)
		{
			int hp = r.nextInt(dmgMax - dmgMin + 1) + dmgMin;
			int heals = this.hp + hp;
			
			if(heals > this.hp) // To ensure that the hp doesn't go over the maximum limit
			{
				this.hp = 75;
				System.out.println("You restored all of your hp!");
			}
			
			else
			{
				this.hp = heals;
				System.out.println("You restored " + hp + " hp!");
			}
		}

		else if(diceRoll > this.hitChnce)
		{
			System.out.println("Your heal spell failed.");
		}
	}
	
	public void healAlly(DungeonCharacter that)
	{
		Random r = new Random(); // Random
		int diceRoll = r.nextInt(101); // Dice Roll

		this.setDmgMin(90); // Min heals
		this.setDmgMax(200); // Max heals
		this.setHitChnce(80); // Chance to heal

		if(diceRoll <= this.hitChnce)
		{
			int hp = r.nextInt(dmgMax - dmgMin + 1) + dmgMin;
			int heals = that.getHp() + hp;
			
			if(heals > that.getTotalHp()) // To ensure that the hp doesn't go over the maximum limit
			{
				that.setHp(that.getTotalHp());
				System.out.println("Aqua restored all of " + that.getName() + "'s hp!");
			}
			
			else
			{
				that.setHp(heals);
				System.out.println("Aqua restored " + hp + " of " + that.getName() + "'s hp!");
			}
		}

		else if(diceRoll > this.hitChnce)
		{
			System.out.println("Your heal spell failed.");
		}
	}
	
	@Override
	public void attack(DungeonCharacter that)
	{
		this.setHitChnce(1);
		this.setDmgMin(1000);
		this.setDmgMax(2000);
		
		Random r = new Random(); // Random
		int diceRoll = r.nextInt(101); // Dice Roll
		
		if(diceRoll <= this.hitChnce)
		{
			int dmg = r.nextInt(dmgMax - dmgMin + 1) + dmgMin;
			that.setHp(that.getHp() - dmg);
			String line = "You actually landed a hit!";
			System.out.println(line + " -" + dmg + " hp");
		}
		
		else if(diceRoll > this.hitChnce)
		{
			System.out.println("You missed the monster!");
		}
	}
	
	@Override
	public void storyAttack(DungeonCharacter that)
	{
		this.setHitChnce(1);
		this.setDmgMin(1000);
		this.setDmgMax(2000);
		
		Random r = new Random(); // Random
		int diceRoll = r.nextInt(101); // Dice Roll
		
		if(diceRoll <= this.hitChnce)
		{
			int dmg = r.nextInt(dmgMax - dmgMin + 1) + dmgMin;
			that.setHp(that.getHp() - dmg);
			String line = "Aqua actually landed her attack!";
			System.out.println(line + " -" + dmg + " hp");
		}
		
		else if(diceRoll > this.hitChnce)
		{
			System.out.println("Aqua was completely useless!");
		}
	}
}
