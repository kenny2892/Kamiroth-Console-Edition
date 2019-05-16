package heroes;
import java.util.Random;
import main.*;

public class Thief extends Hero{
	
	String menu = "\t1. Perform a standard attack. (80% chance of success)\r\n" + 
				  "\t2. Perform a sneak attack & a standard attack. (40% chance of success)\r\n" + 
				  "\t3. Show stats.\r\n" + 
				  "\t4. Use Item\r\n" + 
				  "\t5. Admit defeat. (Quit battle)\r\n";
	
	// DVC
	public Thief()
	{
		this.setHp(75);
		this.setAtckSpd(6);
		this.setHitChnce(80);
		this.setDmgMin(20);
		this.setDmgMax(40);
		this.setChance2Block(40);
		this.setPotions(2);
		this.setCharm(3);
		this.setStrPot(0);
		this.setMenu(menu);
		this.setHeroType("Thief");

		this.setTotalHp(hp);
		this.setHitChnceOG(hitChnce);
		this.setDmgMinOG(dmgMin);
		this.setDmgMaxOG(dmgMax);
	}
	
	// EVC (Load from a Save File)
	public Thief(String name, String gender)
	{
		super(name);
		this.setHp(75);
		this.setAtckSpd(6);
		this.setHitChnce(80);
		this.setDmgMin(20);
		this.setDmgMax(40);
		this.setChance2Block(40);
		this.setPotions(2);
		this.setCharm(3);
		this.setStrPot(0);
		this.setName(name);
		this.setGender(gender);
		this.setMenu(menu);
		this.setHeroType("Thief");

		this.setTotalHp(hp);
		this.setHitChnceOG(hitChnce);
		this.setDmgMinOG(dmgMin);
		this.setDmgMaxOG(dmgMax);
	}
	
	// EVC for NPC's
	public Thief(String name)
	{
		super(name);
		Random gen = new Random();
		int gender = gen.nextInt(1);
		
		if(gender == 0)
		{
			this.setGender("Male");
			this.setName("Thief " + nameGenMale());
		}
		
		else
		{
			this.setGender("Female");
			this.setName("Thief " + nameGenFemale());
		}
		
		this.setHp(75);
		this.setAtckSpd(6);
		this.setHitChnce(80);
		this.setDmgMin(20);
		this.setDmgMax(40);
		this.setChance2Block(40);
		this.setPotions(2);
		this.setCharm(3);
		this.setStrPot(0);
		this.setMenu(menu);
		this.setHeroType("Thief");

		this.setTotalHp(hp);
		this.setHitChnceOG(hitChnce);
		this.setDmgMinOG(dmgMin);
		this.setDmgMaxOG(dmgMax);
	}
	
	@Override
	public void superAttack(DungeonCharacter that)
	{
		this.setHitChnce(40);
		
		Random r = new Random(); // Random
		int diceRoll = r.nextInt(101); // Dice Roll
		
		if(diceRoll <= 80 && diceRoll > 40) // Surprise Attack
		{
			Random r2 = new Random(); // Random

			int dmg = r2.nextInt(dmgMax - dmgMin + 1) + dmgMin; // Amount of damage the sneak attack will do
			that.setHp(that.getHp() - dmg);
			System.out.println("Sneak Attack Successful!");
			String line2 = lines2Gen();
			System.out.println(line2 + " -" + dmg + " hp");		

			this.attack(that); // Extra Attack
		}
		
		else if(diceRoll <= 40) // Just normal attack
		{
			System.out.println("You were unable to peform a sneak attack, but may be able to get in a normal attack.");
			this.attack(that);
		}
		
		else
		{
			System.out.println("You were caught before you could attack.");
		}
	}
	
	@Override
	public void attack(DungeonCharacter that)
	{
		this.setHitChnce(80);
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
		
		attack2Lines[0] = "You snuck up on the monster and went for the neck!";
		attack2Lines[1] = "You crawled behind the beast and cut its ankles!";
		attack2Lines[2] = "You crept in the shadow of the monster and stabbed it in the back!";
		attack2Lines[3] = "You evaded the beast's line of sight and got a sneak attack in!";
		attack2Lines[4] = "You hid in a nearby bush and ambushed the beast when it passed by!";
		
		return attack2Lines[diceRoll];
	}
	
	// Name Generator - Male
	private static String nameGenMale()
	{
		String[] names = new String[10];
		Random r = new Random();
		int diceRoll = r.nextInt(9);		
		names[0] = "Craig";
		names[1] = "Evan";
		names[2] = "Brian";
		names[3] = "Marcel";
		names[4] = "Brock";
		names[5] = "Scotty";
		names[6] = "Jordan";
		names[7] = "Arin";
		names[8] = "Dan";
		names[9] = "Jacob";

		return names[diceRoll];
	}
	
	// Name Generator - Female
	private static String nameGenFemale()
	{
		String[] names = new String[10];
		Random r = new Random();
		int diceRoll = r.nextInt(9);		
		names[0] = "Lola";
		names[1] = "Lena";
		names[2] = "Angela";
		names[3] = "Ana";
		names[4] = "Sarah";
		names[5] = "Ellie";
		names[6] = "Abby";
		names[7] = "Rebecca";
		names[8] = "Hannah";
		names[9] = "Selena";

		return names[diceRoll];
	}
}
