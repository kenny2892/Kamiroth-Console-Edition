package monsters;
import java.util.Random;
import heroes.*;

public class Skeleton extends Monster{

	// DVC
	public Skeleton()
	{
		this.setName(nameGen() + " the Skeleton");
		this.setHp(100);
		this.setAtckSpd(3);
		this.setHitChnce(80);
		this.setDmgMin(30);
		this.setDmgMax(50);
		this.setChance2Heal(30);
		this.setHealMin(30);
		this.setHealMax(50);
		this.setMstrType("Skeleton");
		
		this.setTotalHp(hp);
		this.setHitChnceOG(hitChnce);
		this.setDmgMinOG(dmgMin);
		this.setDmgMaxOG(dmgMax);
	}
	
	@Override
	public void regenHealth()
	{
		Random r = new Random(); // Random
		int diceRoll = r.nextInt(101); // Dice Roll

		if(diceRoll <= this.chance2Heal)
		{
			int heal = r.nextInt(healMax - healMin + 1) + healMin;
			int newHp = this.hp + heal;

			if(newHp > totalHp)
			{
				this.hp = totalHp;
				System.out.println("The monster regained all of its hp back after the fight!\n");
			}
			
			else
			{
				this.hp = newHp;
				System.out.println("The monster regained " + heal + " hp back after the fight!\n");
			}			
		}
		
		else if(diceRoll > this.chance2Heal)
		{
			System.out.println("The monster didn't gain any health back.\n");
		}
	}
	
	@Override
	public void attack(Hero that)
	{
		Random r = new Random(); // Random
		int diceRoll = r.nextInt(101); // Dice Roll
		
		int diceRoll2 = r.nextInt();
		
		if(diceRoll2 <= 30) // Special Attack
		{
			this.dmgMin = 40;
			this.dmgMax = 60;
			int dmg = r.nextInt(dmgMax - dmgMin + 1) + dmgMin;

			if(r.nextInt(101) > that.getChance2Block()) // If block was unsuccessful
			{
				that.setHp(that.getHp() - dmg);
				System.out.println(this.getName() + " successfuly landed a special attack!");
				System.out.println("The skeleton ripped off its arm and smacked you across the face with it! -" + dmg + " hp\n");
			}
			
			else // Successful Block
			{
				System.out.println("\nYou successfully blocked the monster's attack!");
			}
		}
		
		else if(diceRoll <= this.hitChnce) // Normal Attack
		{
			int dmg = r.nextInt(dmgMax - dmgMin + 1) + dmgMin;

			if(r.nextInt(101) > that.getChance2Block()) // If block was unsuccessful
			{
				that.setHp(that.getHp() - dmg);
				System.out.println("The skeleton swung a sword at your ribs! -" + dmg + " hp\n");
			}
			
			else // Successful Block
			{
				System.out.println("\nYou successfully blocked the monster's attack!");
			}
		}
		
		else if(diceRoll > this.hitChnce)
		{
			System.out.println("\nYou dodged the monster's blow!\n");
		}
	}
	
	// Name Generator
	private static String nameGen()
	{
		// Got some names from: https://www.bungie.net/en-US/Forums/Post/3486040
		String[] names = new String[10];
		Random r = new Random();
		int diceRoll = r.nextInt(9);		
		names[0] = "Sans";
		names[1] = "Papyrus";
		names[2] = "Gaster";
		names[3] = "McRibs";
		names[4] = "Cal C. Iam";
		names[5] = "Skeletor";
		names[6] = "Jack Marrow";
		names[7] = "Jack Skellington";
		names[8] = "Brook";
		names[9] = "Grim";
				
		return names[diceRoll];
	}
}
