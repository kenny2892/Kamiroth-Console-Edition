package monsters;
import java.util.ArrayList;
import java.util.Random;
import heroes.*;

public class FinalBoss extends Monster{

	private ArrayList<String> attackLines = new ArrayList<String>(5);
	
	// DVC
	public FinalBoss()
	{
		this.setName(nameGen() + " the Dragon");
		this.setHp(800);
		this.setAtckSpd(3);
		this.setHitChnce(90);
		this.setDmgMin(50);
		this.setDmgMax(73);
		this.setChance2Heal(70);
		this.setHealMin(70);
		this.setHealMax(100);
		this.setMstrType("Final Boss");
		
		attackLines.add(name + " blasted your team with fire!");
		attackLines.add(name + " launched a frost breath at your team!");
		attackLines.add(name + " shot lightning out of its mouth!");
		attackLines.add(name + " launched a barrage of wind at your team!");
		attackLines.add(name + " rained meteors from the sky!");
		
		this.setTotalHp(hp);
		this.setHitChnceOG(hitChnce);
		this.setDmgMinOG(dmgMin);
		this.setDmgMaxOG(dmgMax);
	}
	
	// EVC for specific bosses
	public FinalBoss(int mode)
	{
		// Demon King
		if(mode == 1)
		{
			this.setName("The Demon King");
			this.setHp(3000);
			this.setAtckSpd(8);
			this.setHitChnce(90);
			this.setDmgMin(70);
			this.setDmgMax(74);
			this.setChance2Heal(80);
			this.setHealMin(100);
			this.setHealMax(1000);
			this.setMstrType("Demon Lord");
			
			attackLines.add(name + " stared at your team!");
			attackLines.add(name + " launched an army of demons at your team!");
			attackLines.add(name + " threw demon fire at your team!");
			attackLines.add(name + " questioned why you actually thought you could win?!");
			attackLines.add("Your team was consumed by a cloud of poisones smoke!");
		}
		
		// Bowsette
		else if(mode == 2)
		{
			this.setName("Bowsette");
			this.setHp(1000);
			this.setAtckSpd(3);
			this.setHitChnce(70);
			this.setDmgMin(50);
			this.setDmgMax(70);
			this.setChance2Heal(60);
			this.setHealMin(50);
			this.setHealMax(100);
			this.setMstrType("Koopa Queen");
			
			attackLines.add(name + " blasted your team with her fire breath!");
			attackLines.add(name + " launched launched balls of fire!");
			attackLines.add(name + " rained fiery meteors from the sky!");
			attackLines.add(name + " launched a volley of hammers at your team!");
			attackLines.add(name + " transformed into Giga Bowsette and swung a punch at your team!");
		}
		
		// Ganon
		else if(mode == 3)
		{
			this.setName("Ganon");
			this.setHp(2000);
			this.setAtckSpd(2);
			this.setHitChnce(70);
			this.setDmgMin(60);
			this.setDmgMax(70);
			this.setChance2Heal(70);
			this.setHealMin(100);
			this.setHealMax(500);
			this.setMstrType("Former Gerudo King");
			
			attackLines.add(name + " blasted your team with a dark energy blast!");
			attackLines.add(name + " slashed at your team with his twin blades!");
			attackLines.add(name + " launched a wave of dark magic at your team!");
			attackLines.add(name + " launched a volley of lightning bolts at your team!");
			attackLines.add(name + " sent an army of monsters to attack your team!");
		}

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
		that.setChance2Block(that.getChance2Block() - 30); // Make it harder to block
		
		if(diceRoll2 <= 20) // Special Attack
		{
			this.dmgMin = 60;
			this.dmgMax = 74;
			int dmg = r.nextInt(dmgMax - dmgMin + 1) + dmgMin;
			
			if(r.nextInt(101) > that.getChance2Block()) // If block was unsuccessful
			{
				that.setHp(that.getHp() - dmg);
				System.out.println("\n" + this.getName() + " successfuly landed a special attack!");
				System.out.println(this.attackLines.get(4));
				
				System.out.println(that.getName() + " took " + dmg + " damage\n");
			}
			
			else // Successful Block
			{
				System.out.println("The attack was successfully blocked!\n");
			}
		}
		
		else if(diceRoll <= this.hitChnce) // Normal Attack
		{
			int dmg = r.nextInt(dmgMax - dmgMin + 1) + dmgMin;
			
			if(r.nextInt(101) > that.getChance2Block()) // If block was unsuccessful
			{
				that.setHp(that.getHp() - dmg);
				
				int strSelect = r.nextInt(3);
				System.out.println(attackLines.get(strSelect));
				
				System.out.println(that.getName() + " took " + dmg + " damage\n");
			}

			else // Successful Block
			{
				System.out.println("The attack was successfully blocked!\n");
			}
		}
		
		else if(diceRoll > this.hitChnce)
		{
			System.out.println("The attack was dodged!\n");
		}
	}
	
	// Dragon Name Generator
	private static String nameGen()
	{
		// Got some names from: Skyrim & http://www.fantasynamegenerators.com/dragon_names.php
		String[] names = new String[10];
		Random r = new Random();
		int diceRoll = r.nextInt(9);		
		names[0] = "Vulthuryol";
		names[1] = "Spike";
		names[2] = "Alduin";
		names[3] = "Paarthurnax";
		names[4] = "Sahrotaar";
		names[5] = "Nahagliiv";
		names[6] = "Durnehviir";
		names[7] = "Zarvait";
		names[8] = "Mylreoth";
		names[9] = "Giandal";

		return names[diceRoll];
	}
}
