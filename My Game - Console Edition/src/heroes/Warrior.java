package heroes;
import java.util.Random;
import main.*;

public class Warrior extends Hero{

	String menu = "\t1. Perform a standard attack. (80% chance of success)\r\n" + 
				  "\t2. Perform a crushing blow. (40% chance of success)\r\n" + 
				  "\t3. Show stats.\r\n" + 
				  "\t4. Use Item.\r\n" + 
				  "\t5. Admit defeat. (Quit battle)\r\n";
	
	// DVC
	public Warrior()
	{
		this.setHp(125);
		this.setAtckSpd(4);
		this.setHitChnce(80);
		this.setDmgMin(35);
		this.setDmgMax(60);
		this.setChance2Block(20);
		this.setPotions(2);
		this.setCharm(0);
		this.setStrPot(3);
		this.setMenu(menu);
		this.setHeroType("Warrior");
		
		this.setTotalHp(hp);
		this.setHitChnceOG(hitChnce);
		this.setDmgMinOG(dmgMin);
		this.setDmgMaxOG(dmgMax);
	}
	
	// EVC (Loading from a Save File)
	public Warrior(String name, String gender)
	{
		super(name);
		this.setHp(125);
		this.setAtckSpd(4);
		this.setHitChnce(80);
		this.setDmgMin(35);
		this.setDmgMax(60);
		this.setChance2Block(20);
		this.setPotions(2);
		this.setCharm(0);
		this.setStrPot(3);
		this.setName(name);
		this.setGender(gender);
		this.setMenu(menu);
		this.setHeroType("Warrior");
		
		this.setTotalHp(hp);
		this.setHitChnceOG(hitChnce);
		this.setDmgMinOG(dmgMin);
		this.setDmgMaxOG(dmgMax);
	}
	
	// EVC for NPC's
	public Warrior(String name)
	{
		super(name);
		Random gen = new Random();
		int gender = gen.nextInt(1);
		
		if(gender == 0)
		{
			this.setGender("Male");
			this.setName("Warrior " + nameGenMale());
		}
		
		else
		{
			this.setGender("Female");
			this.setName("Warrior " + nameGenFemale());
		}
		
		this.setHp(125);
		this.setAtckSpd(4);
		this.setHitChnce(80);
		this.setDmgMin(35);
		this.setDmgMax(60);
		this.setChance2Block(20);
		this.setPotions(2);
		this.setCharm(0);
		this.setStrPot(3);

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
		
		this.setDmgMin(75);
		this.setDmgMax(175);
		this.setHitChnce(40);
		
		if(diceRoll <= this.hitChnce)
		{
			int dmg = r.nextInt(dmgMax - dmgMin + 1) + dmgMin; 
			that.setHp(that.getHp() - dmg);
			String line2 = lines2Gen();
			System.out.println(line2 + " -" + dmg + " hp");
		}
		
		else if(diceRoll > this.hitChnce)
		{
			System.out.println("Your attack missed!");
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
		
		attack2Lines[0] = "You delivered a crushing blow to the enemy's head!";
		attack2Lines[1] = "You lunged at the beast and slashed at their with lightning speed!";
		attack2Lines[2] = "You delivered a crushing blow to the enemy's head!";
		attack2Lines[3] = "You kicked up some dirt to blind the beast, and then swung your sword at its chest!";
		attack2Lines[4] = "You charged at the monster and released a flury of attacks!";
		
		return attack2Lines[diceRoll];
	}
	
	// Name Generator - Male
	private static String nameGenMale()
	{
		String[] names = new String[10];
		Random r = new Random();
		int diceRoll = r.nextInt(9);		
		names[0] = "Andrew";
		names[1] = "Gabe";
		names[2] = "Josh";
		names[3] = "Henry";
		names[4] = "Nick";
		names[5] = "Peter";
		names[6] = "Stan";
		names[7] = "Kyle";
		names[8] = "Kenny";
		names[9] = "Eric";

		return names[diceRoll];
	}
	
	// Name Generator - Female
	private static String nameGenFemale()
	{
		String[] names = new String[10];
		Random r = new Random();
		int diceRoll = r.nextInt(9);		
		names[0] = "Katie";
		names[1] = "Suzy";
		names[2] = "Wendy";
		names[3] = "Lucy";
		names[4] = "Rose";
		names[5] = "Daisy";
		names[6] = "Alexis";
		names[7] = "Sabrina";
		names[8] = "Penny";
		names[9] = "Leah";

		return names[diceRoll];
	}
}
