package heroes;
import java.util.Random;
import main.*;

public class Darkness extends Hero{
	
	String menu = "\t1. Use your sword. (0% chance of success)\r\n" + 
				  "\t2. Endure the hits till the enemy passes out from exhaustion. (90% chance of success)\r\n" + 
				  "\t3. Show stats.\r\n" + 
				  "\t4. Use Item.\r\n" + 
				  "\t5. Admit defeat. (Quit battle)\r\n";
	
	// DVC
	public Darkness()
	{
		this.setHp(400);
		this.setAtckSpd(1);
		this.setHitChnce(0);
		this.setDmgMin(10);
		this.setDmgMax(40);
		this.setChance2Block(70);
		this.setPotions(0);
		this.setCharm(0);
		this.setStrPot(0);
		this.setMenu(menu);
		this.setName("Darkness");
		this.setHeroType("Crusader");
		this.setGender("Female");

		this.setTotalHp(hp);
		this.setHitChnceOG(hitChnce);
		this.setDmgMinOG(dmgMin);
		this.setDmgMaxOG(dmgMax);
	}
	
	// EVC (Load from a Save File)
	public Darkness(String name, String gender)
	{
		super(name);
		this.setHp(200);
		this.setAtckSpd(1);
		this.setHitChnce(70);
		this.setDmgMin(10);
		this.setDmgMax(40);
		this.setChance2Block(70);
		this.setPotions(0);
		this.setCharm(0);
		this.setStrPot(0);
		this.setGender("Female");
		this.setMenu(menu);
		this.setName("Darkness");
		this.setHeroType("Crusader");

		this.setTotalHp(hp);
		this.setHitChnceOG(hitChnce);
		this.setDmgMinOG(dmgMin);
		this.setDmgMaxOG(dmgMax);
	}
	
	// EVC for NPC's
	public Darkness(String name)
	{
		super(name);		
		this.setHp(200);
		this.setAtckSpd(1);
		this.setHitChnce(70);
		this.setDmgMin(10);
		this.setDmgMax(40);
		this.setChance2Block(70);
		this.setPotions(0);
		this.setCharm(0);
		this.setStrPot(0);
		this.setMenu(menu);
		this.setName("Darkness");
		this.setHeroType("Crusader");
		this.setGender("Female");

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
		
		this.setHitChnce(90);
		
		if(diceRoll <= this.hitChnce)
		{
			int dmg = r.nextInt(dmgMax - dmgMin + 1) + dmgMin; 
			that.setHp(that.getHp() - dmg);
			String line2 = that.getName() + " is getting tired.";
			System.out.println(line2 + " -" + dmg + " hp");
		}
		
		else if(diceRoll > this.hitChnce)
		{
			System.out.println(that.getName() + " didn't get tired.");
		}
		
		this.setHitChnce(0);
	}
	
	@Override
	public void attack(DungeonCharacter that)
	{
		// .. why would I program an attack that can never hit.
	}
	
	@Override
	public void storyAttack(DungeonCharacter that)
	{
		Random r = new Random(); // Random
		int diceRoll = r.nextInt(101); // Dice Roll
		
		this.setHitChnce(90);
		
		if(diceRoll <= this.hitChnce)
		{
			int dmg = r.nextInt(dmgMax - dmgMin + 1) + dmgMin; 
			that.setHp(that.getHp() - dmg);
			String line2 = "Darkness' wierdness is tiring out " + that.getName();
			System.out.println(line2 + " -" + dmg + " hp");
		}
		
		else if(diceRoll > this.hitChnce)
		{
			System.out.println(that.getName() + " didn't get tired.");
		}
		
		this.setHitChnce(0);
	}
}
