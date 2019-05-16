package heroes;
import java.util.Random;
import main.*;

public class BattleMage extends Hero{
	
	String menu = "\t1. Perform a standard attack. (70% chance of success)\r\n" + 
			 	  "\t2. Perform an advanced spell. (50% chance of success)\r\n" + 
			 	  "\t3. Show stats.\r\n" + 
			 	  "\t4. Use Item.\r\n" +
			 	  "\t5. Admit defeat. (Quit battle)\r\n";
	
	// DVC
	public BattleMage()
	{
		this.setHp(95);
		this.setAtckSpd(3);
		this.setHitChnce(70);
		this.setDmgMin(50);
		this.setDmgMax(70);
		this.setChance2Block(20);
		this.setPotions(2);
		this.setCharm(1);
		this.setStrPot(2);
		this.setMenu(menu);
		this.setHeroType("Battle Mage");
		
		this.setTotalHp(hp);
		this.setHitChnceOG(hitChnce);
		this.setDmgMinOG(dmgMin);
		this.setDmgMaxOG(dmgMax);
	}
	
	// EVC (Load from a Save File)
	public BattleMage(String name, String gender)
	{
		super(name);
		this.setHp(95);
		this.setAtckSpd(3);
		this.setHitChnce(70);
		this.setDmgMin(50);
		this.setDmgMax(70);
		this.setChance2Block(20);
		this.setPotions(2);
		this.setCharm(1);
		this.setStrPot(2);
		this.setName(name);
		this.setGender(gender);
		this.setMenu(menu);
		this.setHeroType("Battle Mage");

		this.setTotalHp(hp);
		this.setHitChnceOG(hitChnce);
		this.setDmgMinOG(dmgMin);
		this.setDmgMaxOG(dmgMax);
	}

	// EVC for NPC's
	public BattleMage(String name)
	{
		super(name);
		Random gen = new Random();
		int gender = gen.nextInt(1);

		if(gender == 0)
		{
			this.setGender("Male");
			this.setName("Battle Mage " + nameGenMale());
		}

		else
		{
			this.setGender("Female");
			this.setName("Battle Mage " + nameGenFemale());
		}

		this.setHp(95);
		this.setAtckSpd(3);
		this.setHitChnce(70);
		this.setDmgMin(50);
		this.setDmgMax(70);
		this.setChance2Block(20);
		this.setPotions(2);
		this.setCharm(1);
		this.setStrPot(2);
		this.setMenu(menu);
		this.setHeroType("Battle Mage");

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
		
		this.setHitChnce(50);
		this.setDmgMin(75);
		this.setDmgMax(90);
		
		if(diceRoll <= this.hitChnce)
		{
			int dmg = r.nextInt(dmgMax - dmgMin + 1) + dmgMin;
			that.setHp(that.getHp() - dmg);
			String line = lines2Gen();
			System.out.println(line + " -" + dmg + " hp");
		}
		
		else if(diceRoll > this.hitChnce)
		{
			System.out.println("Your spell missed!");
		}
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
			String line = linesGen();
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
		Random r = new Random(); // Random
		int diceRoll = r.nextInt(101); // Dice Roll
		
		if(diceRoll <= this.hitChnce)
		{
			int dmg = r.nextInt(dmgMax - dmgMin + 1) + dmgMin;
			that.setHp(that.getHp() - dmg);
			String line = this.getName() + " launched a fire ball at the beast!";
			System.out.println(line + " -" + dmg + " hp");
		}
		
		else if(diceRoll > this.hitChnce)
		{
			System.out.println(this.getName() + " missed the monster!");
		}
	}
	
	private static String linesGen()
	{
		String[] attackLines = new String[5];
		Random r = new Random(); // Random
		int diceRoll = r.nextInt(5); // Dice Roll
		
		attackLines[0] = "You shot lightning at the beast!";
		attackLines[1] = "You launched fireballs at the monster!";
		attackLines[2] = "You used a frost breath attack!";
		attackLines[3] = "You summoned a cloud of poison to engulf your enemy!";
		attackLines[4] = "You blasted the monster away with water!";
		
		return attackLines[diceRoll];
	}
	
	private static String lines2Gen()
	{
		String[] attack2Lines = new String[5];
		Random r = new Random(); // Random
		int diceRoll = r.nextInt(5); // Dice Roll
		
		attack2Lines[0] = "You summoned a familiar to fight the beast!";
		attack2Lines[1] = "You made it rain hell fire from the sky!";
		attack2Lines[2] = "You launched a massive beam of magic directly into the monster!";
		attack2Lines[3] = "You created a mini blizzard and sent it towards the beast!";
		attack2Lines[4] = "You generated a storm cloud right over the monsters head!";
		
		return attack2Lines[diceRoll];
	}
	
	// Name Generator - Male
	private static String nameGenMale()
	{
		String[] names = new String[10];
		Random r = new Random();
		int diceRoll = r.nextInt(9);		
		names[0] = "Anthony";
		names[1] = "Ian";
		names[2] = "Ray";
		names[3] = "Fred";
		names[4] = "Gary";
		names[5] = "Cade";
		names[6] = "Dillion";
		names[7] = "Shaun";
		names[8] = "Phil";
		names[9] = "Tony";

		return names[diceRoll];
	}

	// Name Generator - Female
	private static String nameGenFemale()
	{
		String[] names = new String[10];
		Random r = new Random();
		int diceRoll = r.nextInt(9);		
		names[0] = "Tamara";
		names[1] = "Jeanie";
		names[2] = "Sonja";
		names[3] = "Ruby";
		names[4] = "Zoe";
		names[5] = "Natasha";
		names[6] = "Daniela";
		names[7] = "Paige";
		names[8] = "Alice";
		names[9] = "Rachel";

		return names[diceRoll];
	}
}
