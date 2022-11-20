/**
 * BattleDriver class consists of all methods to run the battle
 */

/**
 * Battle Driver: This is the driver class for the whole battle module in this game which runs all the required functionalities and has all the common functions for battle
 *
 * @author Vishwas B
 * @version 1.0.0
 * @since November 5, 2022
 */

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class BattleDriver {

    private static Integer selectedHero;
    private static Integer selectedMonster;

    public static void displayTeam(Team team, String teamType) {
        Pawn pawn;
        if (teamType.equalsIgnoreCase("Heroes")) {
            LegendsGameDriver.printChar(TextColors.WHITE, '-', 152);
            System.out.println();
            LegendsHero.printHeaders();
            LegendsGameDriver.printChar(TextColors.WHITE, '-', 152);
            System.out.println();
        } else {
            LegendsGameDriver.printChar(TextColors.WHITE, '-', 116);
            System.out.println();
            LegendsMonster.printHeaders();
            LegendsGameDriver.printChar(TextColors.WHITE, '-', 116);
            System.out.println();
        }
        for (int index = 0; index < team.getTeamSize(); index++) {
            pawn = team.getPawnAtIndex(index);
            if (teamType.equalsIgnoreCase("Heroes")) {
                if (!((LegendsHero) pawn).getDidFaint()) {
                    ((LegendsHero) pawn).printData(index + 1);
                }
            } else {
                if (pawn != null) {
                    ((LegendsMonster) pawn).printData(index + 1);
                }
            }
        }
        if (teamType.equalsIgnoreCase("Heroes")) {
            LegendsGameDriver.printChar(TextColors.WHITE, '-', 152);
            System.out.println();
        } else {
            LegendsGameDriver.printChar(TextColors.WHITE, '-', 116);
            System.out.println();
        }
    }

    public static void selectRandomMonster() {
        try {
            selectedMonster = Utils.randomNumber.nextInt(LegendsGameDriver.getTeamMonster().getTeamSize());
            if (LegendsGameDriver.getTeamMonster().getPawnList()[selectedMonster] != null) {
                System.out.println("" + LegendsGameDriver.getTeamMonster().getPawnAtIndex(selectedMonster).getName() + " is ready to attack " + Symbols.SUPRISED);
                return;
            }
            selectRandomMonster();
        } catch (Exception error) {
            System.out.println("Internal Server Error");
        }
    }

    public static boolean chancesOfDodge(Integer value) {
        return ((Utils.randomNumber.nextInt(value) * 2) % 7) == 0;
    }

    public static void selectHero() throws IOException {
        try {
            System.out.println("Here is a look at your team. Please choose which hero you want to fight with!");
            displayTeam(LegendsGameDriver.getTeamHero(), "Heroes");

            System.out.print("Please select a hero with who you would like to fight with : ");
            selectedHero = (Integer.parseInt(Utils.input.readLine())) - 1;

            if ((selectedHero < 0 || selectedHero >= LegendsGameDriver.getTeamHero().getTeamSize()) || ((LegendsHero)(LegendsGameDriver.getTeamHero().getPawnList()[selectedHero])).getDidFaint()) {
                selectedHero = null;
                throw new Exception("Invalid Entry!!");
            }
            System.out.println("\nSuccessfully selected your hero!!\n");

        } catch (Exception error) {
            System.out.println("Invalid hero select entry! Please try again!");
            selectHero();
        }
    }

    public static void attackOfHero(Team teamHero, Team teamMonster) {
        LegendsMonster monster = (LegendsMonster) teamMonster.getPawnAtIndex(selectedMonster);
        if (!chancesOfDodge(monster.getDodge())) {
            LegendsHero hero = (LegendsHero) teamHero.getPawnAtIndex(selectedHero);
            int attackDamage = 0;

            if (hero.isItemEquipped(ItemType.WEAPON)) {
                attackDamage = (int) ((hero.getStrength() + ((Weapon) hero.getItemEquipped(ItemType.WEAPON)).getDamage()) * 0.05);
            } else {
                attackDamage = (int) (hero.getStrength() * 0.05);
            }

            System.out.println("" + hero.getName() + " successfully attacked " + monster.getName() + " for " + attackDamage + "HP!");

            if (monster.getCurrentHitPoints() <= attackDamage) {
                System.out.println("" + monster.getName() + " died!! Congratulations!");
                try {
                    File soundFile = new File("" + System.getProperty("user.dir") + "/src/sounds/" + "monster.wav");
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
                    clip.open(ais);
                    clip.start();
                } catch (Exception error) {

                }
                monster.setCurrentHitPoints(monster.getMaxHitPoints());
                teamMonster.getPawnList()[selectedMonster] = null;
                if (checkBattleStatus(teamHero, teamMonster) == -1) {
                    selectRandomMonster();
                }
            } else {
                monster.setCurrentHitPoints(monster.getCurrentHitPoints() - attackDamage);
            }
        } else {
            System.out.println("" + monster.getName() + " dodged the attack!!");
        }
    }

    public static void attackOfMonster(Team teamMonster, Team teamHero) throws IOException {
        LegendsHero hero = (LegendsHero) teamHero.getPawnAtIndex(selectedHero);
        if (!chancesOfDodge(hero.getAgility())) {
            LegendsMonster monster = (LegendsMonster) teamMonster.getPawnAtIndex(selectedMonster);
            int heroHP = hero.getCurrentHitPoints();
            int attackDamage = 0;

            if (hero.isItemEquipped(ItemType.ARMOR)) {
                attackDamage = (int) ((monster.getBaseDamage() * 0.02) - ((Armor) (hero.getItemEquipped(ItemType.ARMOR))).getDamageReduction() * 0.006);
            } else {
                attackDamage = (int) (monster.getBaseDamage() * 0.02);
            }

            System.out.println("" + monster.getName() + " successfully attacked " + hero.getName() + " for " + attackDamage + "HP!");

            if (hero.getCurrentHitPoints() <= attackDamage) {
                System.out.println("That was brutal!! Unfortunately " + hero.getName() + " has fainted! :(");
                hero.setCurrentHitPoints(0);
                hero.setFaint(true);
                System.out.println("You will have to select a new hero to be sent to battle!! \n");
                if (checkBattleStatus(teamHero, teamMonster) == -1) {
                    selectHero();
                }
            } else {
                hero.setCurrentHitPoints(hero.getCurrentHitPoints() - attackDamage);
            }
        } else {
            System.out.println("" + hero.getName() + " dodged the attack!!");
        }
    }

    public static boolean usePotion(Team teamHero) throws IOException {
        LegendsHero hero = (LegendsHero) teamHero.getPawnAtIndex(selectedHero);
        if (hero.getItemInventory().get(ItemType.POTION).size() != 0) {
            System.out.println("Here are the list of potions which your hero has : ");
            Utils.displayPotions(hero.getItemInventory().get(ItemType.POTION));
            System.out.print("\n Please choose a potion which you would like to use (Please note that they are exhaustible : )");
            int selectedPotion = Integer.parseInt(Utils.input.readLine()) - 1;

            if (selectedPotion < 0 || selectedPotion >= hero.getItemInventory().get(ItemType.POTION).size()) {
                System.out.println("Please choose a valid option!!");
                return false;
            }

            Potion potion = ((Potion) (hero.getItemInventory().get(ItemType.POTION).get(selectedPotion)));

            if (potion.getPotionType() == PotionType.STRENGTH) {
                hero.setStrength(hero.getStrength() + potion.getEffectAmount());
                System.out.println("" + hero.getName() + " used the " + potion.getName() + " increasing the hero's strength to " + hero.getStrength());
            } else if (potion.getPotionType() == PotionType.AGILITY) {
                hero.setAgility(hero.getAgility() + potion.getEffectAmount());
                System.out.println("" + hero.getName() + " used the " + potion.getName() + " increasing the hero's agility to " + hero.getAgility());
            } else if (potion.getPotionType() == PotionType.HEALTH) {
                hero.setCurrentHitPoints(hero.getCurrentHitPoints() + potion.getEffectAmount());
                System.out.println("" + hero.getName() + " used the " + potion.getName() + " increasing the hero's health to " + hero.getCurrentHitPoints());
            } else if (potion.getPotionType() == PotionType.MAGIC) {
                hero.setCurrentMagicPoints(hero.getCurrentMagicPoints() + potion.getEffectAmount());
                System.out.println("" + hero.getName() + " used the " + potion.getName() + " increasing the hero's mana to " + hero.getCurrentMagicPoints());
            } else if (potion.getPotionType() == PotionType.DEXTERITY) {
                hero.setDexterity(hero.getDexterity() + potion.getEffectAmount());
                System.out.println("" + hero.getName() + " used the " + potion.getName() + " increasing the hero's dexterity to " + hero.getDexterity());
            } else {
                hero.setStrength(hero.getStrength() + potion.getEffectAmount());
                hero.setAgility(hero.getAgility() + potion.getEffectAmount());
                hero.setCurrentHitPoints(hero.getCurrentHitPoints() + potion.getEffectAmount());
                hero.setCurrentMagicPoints(hero.getCurrentMagicPoints() + potion.getEffectAmount());
                hero.setDexterity(hero.getDexterity() + potion.getEffectAmount());
                System.out.println("" + hero.getName() + " used the " + potion.getName() + ". Effected attributes: \nStrength: " + hero.getStrength() + "\n" +
                        "Agility: " + hero.getAgility() + "\nHealth: " + hero.getCurrentHitPoints() + "\nMana: " + hero.getCurrentMagicPoints() + "\n" +
                        "Dexterity: " + hero.getDexterity());
            }

            hero.getItemInventory().get(ItemType.POTION).remove(selectedPotion);
            return true;
        } else {
            System.out.println("Uh oh! The Hero is out of potions!! Please buy some from the market to try again!!");
            return false;
        }
    }

    public static boolean useSpell(Team teamHero, Team teamMonster) throws IOException {
        LegendsHero hero = (LegendsHero) teamHero.getPawnAtIndex(selectedHero);
        if (hero.getItemInventory().get(ItemType.SPELL).size() != 0) {
            LegendsMonster monster = (LegendsMonster) teamMonster.getPawnAtIndex(selectedMonster);
            System.out.println("Here are the list of spell which your hero has : ");
            Utils.displayPotions(hero.getItemInventory().get(ItemType.SPELL));
            System.out.print("\n Please choose a spell which you would like to use (Please note that they are exhaustible : )");
            int selectedSpell = Integer.parseInt(Utils.input.readLine()) - 1;

            if (selectedSpell < 0 || selectedSpell >= hero.getItemInventory().get(ItemType.SPELL).size()) {
                System.out.println("Please choose a valid option!!");
                return false;
            }

            Spell spell = ((Spell) (hero.getItemInventory().get(ItemType.SPELL).get(selectedSpell)));

            if (spell.getMpCost() > hero.getCurrentMagicPoints()) {
                System.out.println("You do not have enough Mana to use this spell!");
                return false;
            }

            monster.setCurrentHitPoints(monster.getCurrentHitPoints() - spell.getDamage());

            if (spell.getSpellType() == SpellType.FIRE) {
                monster.setDefense((int)(Math.floor(monster.getDefense() * 0.9)));
                System.out.println("" + hero.getName() + " used " + spell.getName() + " spell on " + monster.getName() + "\nDecreasing the defense to " + monster.getDefense());
            } else if (spell.getSpellType() == SpellType.ICE) {
                monster.setBaseDamage((int)(Math.floor(monster.getBaseDamage() * 0.9)));
                System.out.println("" + hero.getName() + " used " + spell.getName() + " spell on " + monster.getName() + "\nDecreasing the Base Damage to " + monster.getDefense());
            } else {
                monster.setDodge((int)(Math.floor(monster.getDodge() * 0.9)));
                System.out.println("" + hero.getName() + " used " + spell.getName() + " spell on " + monster.getName() + "\nDecreasing the Dodge to " + monster.getDefense());
            }
            hero.setCurrentMagicPoints(hero.getCurrentMagicPoints() - spell.getMpCost());

            hero.getItemInventory().get(ItemType.SPELL).remove(selectedSpell);

            if (monster.getCurrentHitPoints() == 0) {
                System.out.println("" + monster.getName() + " died!! Congratulations!");
                monster.setCurrentHitPoints(monster.getMaxHitPoints());
                teamMonster.getPawnList()[selectedMonster] = null;
                if (checkBattleStatus(teamHero, teamMonster) == -1) {
                    selectRandomMonster();
                }
            }

            return true;
        } else {
            System.out.println("Uh oh! The Hero is out of spells!! Please buy some from the market to try again!!");
            return false;
        }
    }

    public static boolean equipWeaponArmor(Team teamHero, ItemType itemType) throws IOException {
        LegendsHero hero = (LegendsHero) teamHero.getPawnAtIndex(selectedHero);
        Item equipItem;
        if (hero.isItemEquipped(itemType)) {
            System.out.println("Your hero already has a " + itemType + " equipped, would you like to replace it? (No - N/Yes - any key)");
            if ((Utils.input.readLine()).equalsIgnoreCase("N")) {
                System.out.println("That sounds great!! Teleporting you back to the market :D");
                return false;
            }
            equipItem = hero.getItemEquipped(itemType);
            hero.getItemInventory().get(itemType).add(equipItem);
            hero.removeItemEquipped(itemType);
        }
        if (hero.getItemInventory().get(itemType).size() == 0) {
            System.out.println("Uh oh! The Hero is out of "+ itemType + "!! Please buy some from the market to try again!!");
            return false;
        }
        System.out.println("Here are the list of " + itemType + " which are there in the hero's inventory");
        if (itemType == ItemType.ARMOR) {
            Utils.displayArmors(hero.getItemInventory().get(itemType));
        } else {
            Utils.displayWeapons(hero.getItemInventory().get(itemType));
        }
        System.out.print("\n Please choose a " + itemType + " which you would like to equip : ");
        int selectedItem = Integer.parseInt(Utils.input.readLine()) - 1;

        if (selectedItem < 0 || selectedItem >= hero.getItemInventory().get(itemType).size()) {
            System.out.println("Please choose a valid option!!");
            return false;
        }

        equipItem = (hero.getItemInventory().get(itemType).get(selectedItem));
        hero.setItemsEquipped(itemType, equipItem);
        hero.getItemInventory().get(itemType).remove(selectedItem);

        System.out.println("" + hero.getName() + " is equipped with " + itemType + " " + equipItem.getName());
        return true;
    }

    public static Integer checkBattleStatus(Team teamHero, Team teamMonster) {
        int returnFlag = 1;
        for (Pawn hero: teamHero.getPawnList()) {
            if (!((LegendsHero) hero).getDidFaint()) {
                returnFlag = -1;
            }
        }

        if (returnFlag == 1) {
            return returnFlag;
        }

        for (int i = 0; i < teamMonster.getTeamSize(); i++) {
            if (teamMonster.getPawnAtIndex(i) != null) {
                return -1;
            }
        }
        return 2;
    }

    public static void checkHeroLevelUp(LegendsHero hero) {
        if (hero.getExperiencePoints() > (hero.getLevel() * 15)) {
            hero.setLevel(hero.getLevel() + 1);
            hero.setExperiencePoints(0);
            hero.setMaxMagicPoints((int)Math.floor(hero.getMaxMagicPoints() * 1.1));
            hero.setCurrentMagicPoints(hero.getMaxMagicPoints());
            hero.setMaxHitPoints((int)Math.floor(hero.getMaxHitPoints() * 1.1));
            hero.setCurrentHitPoints(hero.getMaxHitPoints());
            hero.setStrength((int)Math.floor(hero.getStrength() * 1.05));
            hero.setAgility((int)Math.floor(hero.getAgility() * 1.05));
            hero.setDexterity((int)Math.floor(hero.getDexterity() * 1.05));
        }
    }

    public static Integer startBattle() throws Exception {
        System.out.println("Welcome to the Thunderdome!! Let's Battle!");
        File soundFile = new File("" + System.getProperty("user.dir") + "/src/sounds/" + "battle.wav");
        Clip clip = AudioSystem.getClip();
        AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
        clip.open(ais);
        clip.start();

        LegendsGameDriver.initTeamMonster();

        System.out.println("Here are the monsters which have appeared. Better be prepared!!");
        LegendsGameDriver.buildMonsterTeam(LegendsGameDriver.getTeamMonster(), LegendsGameDriver.getTeamHero());
        displayTeam(LegendsGameDriver.getTeamMonster(), "Monsters");

        selectHero();
        selectRandomMonster();

        System.out.println();

        LegendsGameDriver.printChar(TextColors.PURPLE, '=', 160);
        System.out.println();
        System.out.println("Hope you come out of this unscathed!! ");

        int choice;
        int battleStatus = 2;

        while (true) {
            try {
                LegendsGameDriver.printChar(TextColors.BLUE, '-', 160);
                System.out.println("\n\nPlease choose among the following options to perform in the battle: \n1. Attack\n2. Equip Weapon\n3. Equip Armor\n4. Use Potion\n5. Use Spell\n6. Stats of heroes and monsters [You will not loose your turn for this!]");
                System.out.print("\nPlease choose: ");
                choice = Integer.parseInt(Utils.input.readLine());
                if (choice == 1) {
                    attackOfHero(LegendsGameDriver.getTeamHero(), LegendsGameDriver.getTeamMonster());
                    choice = -86;
                } else if (choice == 2 && equipWeaponArmor(LegendsGameDriver.getTeamHero(), ItemType.WEAPON)) {
                    choice = -86;
                } else if (choice == 3 && equipWeaponArmor(LegendsGameDriver.getTeamHero(), ItemType.ARMOR)) {
                    choice = -86;
                } else if (choice == 4 && usePotion(LegendsGameDriver.getTeamHero())) {
                    choice = -86;
                } else if (choice == 5 && useSpell(LegendsGameDriver.getTeamHero(), LegendsGameDriver.getTeamMonster())) {
                    choice = -86;
                } else if (choice == 6) {
                    System.out.println("\n\n Below are the stats of heroes in your team: ");
                    displayTeam(LegendsGameDriver.getTeamHero(), "heroes");
                    System.out.println("\n\n Below are the stats of monsters: ");
                    displayTeam(LegendsGameDriver.getTeamMonster(), "monster");
                }

                battleStatus = checkBattleStatus(LegendsGameDriver.getTeamHero(), LegendsGameDriver.getTeamMonster());
                if (battleStatus != -1) {
                    break;
                }

                if (choice == -86) {
                    System.out.println("The monster is gearing up for an attack!!  ");
                    attackOfMonster(LegendsGameDriver.getTeamMonster(), LegendsGameDriver.getTeamHero());
                }

                battleStatus = checkBattleStatus(LegendsGameDriver.getTeamHero(), LegendsGameDriver.getTeamMonster());
                if (battleStatus != -1) {
                    break;
                }
            } catch (Exception error) {
                System.out.println("Error Encountered: " + error.getMessage());
            }
        }
        if(battleStatus == 2) {
            LegendsGameDriver.printChar(TextColors.GREEN, '*', 50);
            System.out.println();
            System.out.println(TextColors.GREEN + "*** \t HEROES WON!!! \t ***" + TextColors.RESET);
            LegendsGameDriver.printChar(TextColors.GREEN, '*', 50);
            System.out.println();

            LegendsHero hero;

            for (int i = 0; i < LegendsGameDriver.getTeamHero().getTeamSize(); i ++) {
                hero = ((LegendsHero)(LegendsGameDriver.getTeamHero().getPawnAtIndex(i)));
                if (hero.getDidFaint()) {
                    hero.setFaint(false);
                    hero.setCurrentMagicPoints(hero.getMaxMagicPoints() / 2);
                    hero.setCurrentHitPoints(hero.getMaxHitPoints() / 2);
                } else {
                    hero.setCurrentHitPoints(hero.getMaxHitPoints());
                    hero.setCurrentMagicPoints(hero.getMaxMagicPoints());
                    hero.setExperiencePoints(hero.getExperiencePoints() + (LegendsGameDriver.getTeamMonster().getTeamSize() * 2));
                    hero.setGold(hero.getGold() + (LegendsGameDriver.getTeamMonster().getTeamSize() * 300));
                    checkHeroLevelUp(hero);
                }
            }
        }
        System.out.println("\n\n Below are the stats of heroes in your team: ");
        displayTeam(LegendsGameDriver.getTeamHero(), "heroes");
        return battleStatus;

    }
}
