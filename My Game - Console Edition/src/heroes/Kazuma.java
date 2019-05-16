package heroes;
import java.util.Random;
import main.*;

public class Kazuma extends Hero{
	
	String menu = "\t1. Come up with a plan of attack. (95% chance of success)\r\n" + 
				  "\t2. Use your skills. (90% chance of success)\r\n" + 
				  "\t3. Show stats.\r\n" + 
				  "\t4. Use Item\r\n" + 
				  "\t5. Admit defeat. (Quit battle)\r\n";
	
	// DVC
	public Kazuma()
	{
		this.setHp(75);
		this.setAtckSpd(4);
		this.setHitChnce(95);
		this.setDmgMin(20);
		this.setDmgMax(40);
		this.setChance2Block(40);
		this.setPotions(2);
		this.setCharm(0);
		this.setStrPot(3);
		this.setGender("Male");
		this.setName("Satou Kazuma");
		this.setMenu(menu);
		this.setHeroType("Adventurer");

		this.setTotalHp(hp);
		this.setHitChnceOG(hitChnce);
		this.setDmgMinOG(dmgMin);
		this.setDmgMaxOG(dmgMax);
	}
	
	// EVC (Load from a Save File)
	public Kazuma(String name, String gender)
	{
		super(name);
		this.setHp(75);
		this.setAtckSpd(4);
		this.setHitChnce(95);
		this.setDmgMin(20);
		this.setDmgMax(40);
		this.setChance2Block(40);
		this.setPotions(2);
		this.setCharm(0);
		this.setStrPot(3);
		this.setGender("Male");
		this.setName("Satou Kazuma");
		this.setMenu(menu);
		this.setHeroType("Adventurer");

		this.setTotalHp(hp);
		this.setHitChnceOG(hitChnce);
		this.setDmgMinOG(dmgMin);
		this.setDmgMaxOG(dmgMax);
	}
	
	// EVC for NPC's
	public Kazuma(String name)
	{
		super(name);
		this.setHp(75);
		this.setAtckSpd(4);
		this.setHitChnce(95);
		this.setDmgMin(20);
		this.setDmgMax(40);
		this.setChance2Block(40);
		this.setPotions(2);
		this.setCharm(0);
		this.setStrPot(3);
		this.setGender("Male");
		this.setName("Satou Kazuma");
		this.setMenu(menu);
		this.setHeroType("Adventurer");

		this.setTotalHp(hp);
		this.setHitChnceOG(hitChnce);
		this.setDmgMinOG(dmgMin);
		this.setDmgMaxOG(dmgMax);
	}
	
	@Override
	public void superAttack(DungeonCharacter that)
	{
		this.setHitChnce(90);
		
		Random r = new Random(); // Random
		int diceRoll = r.nextInt(101); // Dice Roll
		
		if(diceRoll <= this.hitChnce)
		{
			int dmg = r.nextInt(dmgMax - dmgMin + 1) + dmgMin; 
			that.setHp(that.getHp() - dmg);
			String line2 = lines2Gen();
			System.out.println(line2 + " -" + dmg + " hp");
		}
		
		else
		{
			System.out.println("Your shot at the beast, but you missed.");
		}
	}
	
	@Override
	public void attack(DungeonCharacter that)
	{
		this.setHitChnce(95);
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
			System.out.println("Your plan failed!");
		}
	}
	
	@Override
	public void storyAttack(DungeonCharacter that)
	{
		this.setHitChnce(95);
		Random r = new Random(); // Random
		int diceRoll = r.nextInt(101); // Dice Roll
		
		if(diceRoll <= this.hitChnce)
		{
			int dmg = r.nextInt(dmgMax - dmgMin + 1) + dmgMin; 
			that.setHp(that.getHp() - dmg);
			String line = "Kazuma shot at the beast!";
			System.out.println(line + " -" + dmg + " hp");
		}
		
		else if(diceRoll > this.hitChnce)
		{
			System.out.println("Kazuma's plan failed!");
		}
	}
	
	private static String linesGen()
	{
		String[] attackLines = new String[10];
		Random r = new Random(); // Random
		int diceRoll = r.nextInt(10); // Dice Roll
		
		attackLines[0] = "You jabbed at the monster!";
		attackLines[1] = "You lunged towards the monster!";
		attackLines[2] = "You fought the monster!";
		attackLines[3] = "You jabbed at the monster!";
		attackLines[4] = "You sliced at the monster!";
		attackLines[5] = "You waited for your moment to strike, and then attacked the monster!";
		attackLines[6] = "You dashed towards the beast, pushed through their defences and landed a slash with your sword!";
		attackLines[7] = "You saw an oppening in the beast's defences and struck!";
		attackLines[8] = "The monster got distracted by a sound out in the distance. You took this moment of confusion to deal a painful blow!";
		attackLines[9] = "You closed the distance between you and the monster and took a swing at its head!";
		
		return attackLines[diceRoll];
	}
	
	private static String lines2Gen()
	{
		String[] attack2Lines = new String[5];
		Random r = new Random(); // Random
		int diceRoll = r.nextInt(5); // Dice Roll
		
		attack2Lines[0] = "You snuck up to the monster and used Drain Touch on them!";
		attack2Lines[1] = "You Stole the monster's weapon and hit them over the head with it!";
		attack2Lines[2] = "You used Create Earth and Wind Breathe to blow dirt into the beast's eyes!";
		attack2Lines[3] = "You shot at the beast from the top of a tree!";
		attack2Lines[4] = "You hid in a nearby bush and ambushed the beast when it passed by!";
		
		return attack2Lines[diceRoll];
	}
}
