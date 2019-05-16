package monsters;
import java.util.Random;
import heroes.*;

public class Gremlin extends Monster{

	// DVC
	public Gremlin()
	{
		this.setName(nameGen() + " the Gremlin");
		this.setHp(70);
		this.setAtckSpd(5);
		this.setHitChnce(80);
		this.setDmgMin(15);
		this.setDmgMax(30);
		this.setChance2Heal(40);
		this.setHealMin(20);
		this.setHealMax(40);
		this.setMstrType("Gremlin");
		
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
			this.dmgMin = 20;
			this.dmgMax = 40;
			int dmg = r.nextInt(dmgMax - dmgMin + 1) + dmgMin;

			if(r.nextInt(101) > that.getChance2Block()) // If block was unsuccessful
			{
				that.setHp(that.getHp() - dmg);
				System.out.println(this.getName() + " successfuly landed a special attack!");
				System.out.println("The gremlin slashed at your face! -" + dmg + " hp\n");
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
				System.out.println("The gremlin jumped up and attacked! -" + dmg + " hp\n");
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
		// Got names from: https://www.fantasynamegenerators.com/ogre-names.php
		String[] names = new String[10];
		Random r = new Random();
		int diceRoll = r.nextInt(9);		
		names[0] = "Zozar";
		names[1] = "Eezur";
		names[2] = "Zigrot";
		names[3] = "Nugut";
		names[4] = "Urek";
		names[5] = "Akor";
		names[6] = "Brokeg";
		names[7] = "Yazir";
		names[8] = "Glenk";
		names[9] = "Vurok";
				
		return names[diceRoll];
	}
}
