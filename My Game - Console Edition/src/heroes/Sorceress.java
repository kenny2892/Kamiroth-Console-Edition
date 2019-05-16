package heroes;
import java.util.Random;
import main.*;

public class Sorceress extends Hero{
	
	String menu = "\t1. Perform a standard attack. (70% chance of success)\r\n" + 
				  "\t2. Perform a healing spell. (80% chance of success)\r\n" + 
				  "\t3. Show stats.\r\n" + 
				  "\t4. Use Item.\r\n" +
				  "\t5. Admit defeat. (Quit battle)\r\n";
	
	// DVC
	public Sorceress()
	{
		this.setHp(75);
		this.setAtckSpd(5);
		this.setHitChnce(70);
		this.setDmgMin(25);
		this.setDmgMax(45);
		this.setChance2Block(30);
		this.setPotions(4);
		this.setCharm(1);
		this.setStrPot(0);
		if(gender.toUpperCase().equals("MALE"))
		{
			this.setHeroType("Sorcerer");
		}
		else
		{
			this.setHeroType("Sorceress");
		}
		
		this.setMenu(menu);
		
		this.setTotalHp(hp);
		this.setHitChnceOG(hitChnce);
		this.setDmgMinOG(dmgMin);
		this.setDmgMaxOG(dmgMax);
	}
	
	// EVC (Loading from a Save File)
	public Sorceress(String name, String gender)
	{
		super(name);
		if(gender.equalsIgnoreCase("male"))
		{
			this.setGender("Male");
			this.setName(name);
			this.setHeroType("Sorcerer");
		}

		else
		{
			this.setGender("Female");
			this.setName(name);
			this.setHeroType("Sorceress");
		}

		this.setHp(75);
		this.setAtckSpd(5);
		this.setHitChnce(70);
		this.setDmgMin(25);
		this.setDmgMax(45);
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
	public Sorceress(String name)
	{
		super(name);
		Random gen = new Random();
		int gender = gen.nextInt(1);
		
		if(gender == 0)
		{
			this.setGender("Male");
			this.setName("Sorcerer " + nameGenMale());
		}
		
		else
		{
			this.setGender("Female");
			this.setName("Sorceress " + nameGenFemale());
		}
		
		this.setHp(75);
		this.setAtckSpd(5);
		this.setHitChnce(70);
		this.setDmgMin(25);
		this.setDmgMax(45);
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

		this.setDmgMin(20); // Min heals
		this.setDmgMax(75); // Max heals
		this.setHitChnce(80); // Chance to heal

		if(diceRoll <= this.hitChnce)
		{
			int hp = r.nextInt(dmgMax - dmgMin + 1) + dmgMin;
			int heals = this.hp + hp;
			
			if(heals > this.hp) // To ensure that the hp doesn't go over the maximum limit
			{
				this.hp = 75;
			}
			
			else
			{
				this.hp = heals;
			}

			System.out.println("You restored " + hp + " hp!");
		}

		else if(diceRoll > this.hitChnce)
		{
			System.out.println("Your heal spell failed.");
		}
	}
	
	@Override
	public void attack(DungeonCharacter that)
	{
		this.setHitChnce(70);
		this.setDmgMin(25);
		this.setDmgMax(45);
		
		Random r = new Random(); // Random
		int diceRoll = r.nextInt(101); // Dice Roll
		
		if(diceRoll <= this.hitChnce)
		{
			int dmg = r.nextInt(dmgMax - dmgMin + 1) + dmgMin;
			that.setHp(that.getHp() - dmg);
			String line = linesGen();
			System.out.println(line + " -" + dmg + " hp");
		}
		
		else if(diceRoll > this.hitChnce)
		{
			System.out.println("You missed the monster!");
		}
	}
	
	private static String linesGen()
	{
		String[] attackLines = new String[5];
		Random r = new Random(); // Random
		int diceRoll = r.nextInt(5); // Dice Roll
		
		attackLines[0] = "You jabbed at the monster with a dagger!";
		attackLines[1] = "You lunged towards the monster!";
		attackLines[2] = "You launched lightning at the beast!";
		attackLines[3] = "You shot fireballs at the beast!";
		attackLines[4] = "You sliced at the monster's ankles!";
		
		return attackLines[diceRoll];
	}
	
	// Name Generator - Male
	private static String nameGenMale()
	{
		String[] names = new String[10];
		Random r = new Random();
		int diceRoll = r.nextInt(9);		
		names[0] = "Adam";
		names[1] = "Clyde";
		names[2] = "Larry";
		names[3] = "Ben";
		names[4] = "Jonas";
		names[5] = "Jack";
		names[6] = "James";
		names[7] = "Matt";
		names[8] = "Mark";
		names[9] = "Wade";

		return names[diceRoll];
	}
	
	// Name Generator - Female
	private static String nameGenFemale()
	{
		String[] names = new String[10];
		Random r = new Random();
		int diceRoll = r.nextInt(9);		
		names[0] = "Stephanie";
		names[1] = "Jaiden";
		names[2] = "Emily";
		names[3] = "Haley";
		names[4] = "Caroline";
		names[5] = "Jodi";
		names[6] = "Jas";
		names[7] = "Robin";
		names[8] = "Pam";
		names[9] = "Sofia";

		return names[diceRoll];
	}
}
