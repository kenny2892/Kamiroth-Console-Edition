package heroes;
import java.util.Random;
import main.*;

public class Megumin extends Hero{
	
	String menu = "\t1. EXPLOOOOOOOOSION!. (99% chance of success)\r\n" + 
				  "\t2. EXPLOOOOOOOOOOOOOOOOSION!!!. (100% chance of success)\r\n" + 
				  "\t3. Show stats.\r\n" + 
				  "\t4. Use Item.\r\n" + 
				  "\t5. Admit defeat. (Quit battle)\r\n";
	
	// DVC
	public Megumin()
	{
		this.setHp(50);
		this.setAtckSpd(2);
		this.setHitChnce(99);
		this.setDmgMin(999);
		this.setDmgMax(1000);
		this.setChance2Block(20);
		this.setPotions(0);
		this.setCharm(0);
		this.setStrPot(0);
		this.setName("Megumin");
		this.setMenu(menu);
		this.setHeroType("Arch Wizard");

		this.setTotalHp(hp);
		this.setHitChnceOG(hitChnce);
		this.setDmgMinOG(dmgMin);
		this.setDmgMaxOG(dmgMax);
	}
	
	// EVC (Load from a Save File)
	public Megumin(String name, String gender)
	{
		super(name);
		this.setHp(50);
		this.setAtckSpd(2);
		this.setHitChnce(99);
		this.setDmgMin(999);
		this.setDmgMax(1000);
		this.setChance2Block(20);
		this.setPotions(0);
		this.setCharm(0);
		this.setStrPot(0);
		this.setName(name);
		this.setGender(gender);
		this.setMenu(menu);
		this.setHeroType("Arch Wizard");

		this.setTotalHp(hp);
		this.setHitChnceOG(hitChnce);
		this.setDmgMinOG(dmgMin);
		this.setDmgMaxOG(dmgMax);
	}
	
	// EVC for Creation from Battle Mage
	public Megumin(String name)
	{
		super(name);
		this.setHp(50);
		this.setAtckSpd(2);
		this.setHitChnce(99);
		this.setDmgMin(999);
		this.setDmgMax(1000);
		this.setChance2Block(20);
		this.setPotions(0);
		this.setCharm(0);
		this.setStrPot(0);
		this.setGender("Female");
		this.setName("Megumin");
		this.setMenu(menu);
		this.setHeroType("Arch Wizard");

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
		
		this.setDmgMin(1000);
		this.setDmgMax(1000);
		this.setHitChnce(100);
		
		if(diceRoll <= this.hitChnce)
		{
			int dmg = r.nextInt(dmgMax - dmgMin + 1) + dmgMin; 
			that.setHp(that.getHp() - dmg);
			System.out.println("EXPLOOOOOOOOOOOOOSION!!!!!!" + " -" + dmg + " hp");
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
			System.out.println("EXPLOOOOOOOOOOOOOOOOOOSION!!!!!!!!" + " -" + dmg + " hp");
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
			System.out.println("Megumin Screamed \"EXPLOOOOOOOOOOOOOOOOOOSION!!!!!!!!\"" + " -" + dmg + " hp");
		}
		
		else if(diceRoll > this.hitChnce)
		{
			System.out.println("Megumin missed the monster!");
		}
	}
}
