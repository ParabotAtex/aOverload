package aOverload;

import org.parabot.core.Context;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Bank;
import org.rev317.min.api.methods.Inventory;

public class Action implements Strategy  {
	int idOverload = 15334; 	//1
	int idSuperStrength = 158; 	//2
	int idSuperAttack = 146; 	//3
	int idSuperDefence = 164; 	//4
	int idRange = 170; 			//5
	int idMagic = 3043; 		//6
	int idDwarfWeed = 268; 		//7
	int idAvantoe = 262; 		//8
	int idLantadyme = 2482; 	//9
	int idSpikes = 12540; 		//10
	int idGroundRunes = 9595; 	//11
	int idTorstol = 270; 		//12
	
	int idExtremeStrength = 15314; 	//13
	int idExtremeAttack = 15310; 	//14
	int idExtremeDefence = 15318; 	//15
	int idExtremeRange = 15326; 	//16
	int idExtremeMagic = 15322; 	//17
	
	int[] item = {17, idOverload, idSuperStrength, idSuperAttack, idSuperDefence, idRange, idMagic,
			idDwarfWeed, idAvantoe, idLantadyme, idSpikes, idGroundRunes, idTorstol,
			idExtremeStrength, idExtremeAttack, idExtremeDefence, idExtremeRange, idExtremeMagic};
	
	int bankSleep = 200;
	int withDrawSleep = 10;
	int interactSleep = 250;
	@Override
	public boolean activate() {
		return true;
	}
	@Override
	public void execute() {
		if(status() == -1) {
			System.out.println("Depositing items");
			Bank.open(Bank.getBank());
			Time.sleep(new SleepCondition() {
				@Override
				public boolean isValid() {
					return Bank.isOpen();
				}
			}, 1000);
			if(Bank.isOpen()) {
				if(Inventory.getItem(idOverload) != null) {
					Main.ovlCount += Inventory.getCount(idOverload);
				}
				Bank.depositAllExcept(idSpikes);
				if(Bank.getItem(idSpikes) != null) {
					Bank.withdraw(idSpikes, Bank.getCount(idSpikes),500);
				}
				Time.sleep(500);
			}
		}
		if(status() == 0) {
			System.out.println("Withdrawing supplies");
			Bank.open(Bank.getBank());
			Time.sleep(new SleepCondition() {
				@Override
				public boolean isValid() {
					return Bank.isOpen();
				}
			}, 2000);
			if(Bank.isOpen()) {
				Time.sleep(bankSleep);
				for(int i = 2;i<13;i++) {
					if(i != 10) {
						if(Bank.getItem(item[i]) == null) {
							System.out.println("Out of item: "+item[i]);
							Context.getInstance().getRunningScript().setState(Script.STATE_STOPPED);
						} else if(Bank.getCount(item[i]) > 1){
							Bank.withdraw(item[i], 2, withDrawSleep);
							Time.sleep(bankSleep);
						} else {
							System.out.println("Out of item: "+item[i]);
							Context.getInstance().getRunningScript().setState(Script.STATE_STOPPED);
						}
					}
				}
			}
		}
		if(status() == 1) {
			System.out.println("Preparing potions");
			for(int i = 2; i<7;i++) {
				for(int a = 0; a <2;a++) {
					Inventory.combine(item[i], item[i+5]);
					Time.sleep(interactSleep);
				}
			}
			
			//Dwarf weed with Super Strength
			//Avantoe with Super Attack
			//Lantadyme with Super Defence
			//Spikes with Range
			//Ground runes with Magic
		}
		if(status() == 2) {
			System.out.println("Making overloads");
			for(int i = 0; i<2;i++) {
				Inventory.combine(item[12], item[13]);
				Time.sleep(interactSleep);
			}
		}
	}
	public int status() {
		int status = -1;
		if(Inventory.getItems(idSpikes) != null && Inventory.getCount(true, idSpikes) > 9 && Inventory.getCount() == 1) {
			status = 0;
		} else if(Inventory.getCount(idSuperStrength) == 2 && Inventory.getCount(idSuperAttack) == 2
				&& Inventory.getCount(idSuperDefence) == 2 && Inventory.getCount(idRange) == 2
				&& Inventory.getCount(idMagic) == 2 && Inventory.getCount(idGroundRunes) == 2
				&& Inventory.getCount(idDwarfWeed) == 2 && Inventory.getCount(idAvantoe) == 2
				&& Inventory.getCount(idLantadyme) == 2 && Inventory.getCount(idTorstol) == 2
				&& Inventory.getCount(true, idSpikes) > 9) {
			status = 1;
		} else if(Inventory.getCount(item[13]) == 2 && Inventory.getCount(item[14]) == 2
				&& Inventory.getCount(item[15]) == 2 && Inventory.getCount(item[16]) == 2 && Inventory.getCount(item[17]) == 2
				&& Inventory.getCount(item[12]) == 2) {
			status = 2;
		}
		return status;
	}
}
