import javax.rmi.CORBA.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * LegendsDriver Class: This is the driver class for the Legends: Heroes and Monsters game. The Starting point for everything in this game.
 *
 * @author Vishwas B
 * @version 1.0.0
 * @since November 5, 2022
 */

public class LegendsGameDriver {

    private static HashMap<String, ArrayList<Pawn>> heroMap;
    private static ArrayList<Pawn> monsterList;
    private static HashMap<ItemType, ArrayList<Item>> marketItemMap;
    private static Team teamHero;
    private static Team teamMonster;

    public static HashMap<String, ArrayList<Pawn>> getHeroMap() {
        return heroMap;
    }

    public static ArrayList<Pawn> getMonsterList() {
        return monsterList;
    }

    public static HashMap<ItemType, ArrayList<Item>> getMarketItemMap() {
        return marketItemMap;
    }

    public static void initTeamHero() {
        teamHero = new Team();
    }

    public static void initTeamMonster() {
        teamMonster = new Team();
    }

    public static Team getTeamHero() {
        return teamHero;
    }

    public static Team getTeamMonster() {
        return teamMonster;
    }

    public static void generateConstants() {
        heroMap = new HashMap<>();
        try {
            heroMap.put("Paladins", NPCGenerator.generatePawn(NPCType.HERO, new ArrayList<>(), "Paladins.txt"));
            heroMap.put("Sorcerers", NPCGenerator.generatePawn(NPCType.HERO, new ArrayList<>(), "Sorcerers.txt"));
            heroMap.put("Warriors", NPCGenerator.generatePawn(NPCType.HERO, new ArrayList<>(), "Warriors.txt"));

            monsterList = NPCGenerator.generatePawn(NPCType.MONSTER, new ArrayList<>(), "Dragons.txt");
            monsterList = NPCGenerator.generatePawn(NPCType.MONSTER, monsterList, "Exoskeletons.txt");
            monsterList = NPCGenerator.generatePawn(NPCType.MONSTER, monsterList, "Spirits.txt");

            marketItemMap = new HashMap<>();
            marketItemMap.put(ItemType.WEAPON, ItemGenerator.generateItem(ItemType.WEAPON, new ArrayList<>(), "Weaponry.txt"));
            marketItemMap.put(ItemType.ARMOR, ItemGenerator.generateItem(ItemType.ARMOR, new ArrayList<>(), "Armory.txt"));
            marketItemMap.put(ItemType.POTION, ItemGenerator.generateItem(ItemType.POTION, new ArrayList<>(), "Potions.txt"));
            marketItemMap.put(ItemType.SPELL, ItemGenerator.generateItem(ItemType.SPELL, new ArrayList<>(), "FireSpells.txt"));
            marketItemMap.put(ItemType.SPELL, ItemGenerator.generateItem(ItemType.SPELL, marketItemMap.get(ItemType.SPELL), "IceSpells.txt"));
            marketItemMap.put(ItemType.SPELL, ItemGenerator.generateItem(ItemType.SPELL, marketItemMap.get(ItemType.SPELL), "LightningSpells.txt"));
        } catch (IOException error) {
            System.out.println("Internal Server Error : " + error);
            error.printStackTrace();
        }
    }

    public static void printHeroes(String heroType) {
        System.out.println("Here are the list of " + heroType + " and their stats. \nEnter the number associated with each hero to choose that hero in your team");
        printChar(TextColors.WHITE, '-', 152);
        System.out.println();
        LegendsHero.printHeaders();
        printChar(TextColors.WHITE, '-', 152);
        System.out.println();
        for (int index = 0; index < heroMap.get(heroType).size(); index ++) {
            ((LegendsHero)heroMap.get(heroType).get(index)).printData(index + 1);
        }
        printChar(TextColors.WHITE, '-', 152);
        System.out.println();
    }

    public static void buildHeroTeam(Team teamHero) {
        System.out.println("Let us build a team! \n There are multiple hero types. Please choose a number from the list shown");
        int heroChoice;
        String heroType;

        teamHero.initTeam();

        for (int i = 1; i <= teamHero.getTeamSize(); ) {
            System.out.println("Please choose a number from the list of hero types:\n1. Paladins\n2. Sorcerers\n3. Warriors");
            System.out.print("Input : ");
            try {
                heroChoice = Integer.parseInt(Utils.input.readLine());
                if (heroChoice == 1) {
                    heroType = "Paladins";
                } else if (heroChoice == 2) {
                    heroType = "Sorcerers";
                } else if (heroChoice == 3) {
                    heroType = "Warriors";
                } else {
                    System.out.println("Invalid Entry!! Please choose between the options provided!");
                    continue;
                }
                printHeroes(heroType);
                System.out.print("Please type the number of the hero : ");
                heroChoice = Integer.parseInt(Utils.input.readLine());
                teamHero.setPawnAtIndex(i - 1, heroMap.get(heroType).get(heroChoice - 1));
                heroMap.get(heroType).remove(heroChoice - 1);
                System.out.println("Hero added successfully to your team!! \n\n");
                i ++;
            } catch (Exception error) {
                System.out.println("Error Encountered! Stack Trace : ");
                error.printStackTrace();
            }
        }
    }

    public static void buildMonsterTeam(Team teamMonster, Team teamHero) {
        Integer maxMonsterLevel = 0, minMonsterLevel = 99;

        teamMonster.setTeamSize(teamHero.getTeamSize());
        teamMonster.initTeam();

        for (Pawn hero: teamHero.getPawnList()) {
            if (((LegendsHero)hero).getLevel() > maxMonsterLevel) {
                maxMonsterLevel = ((LegendsHero) hero).getLevel();
            }
            if (((LegendsHero)hero).getLevel() < minMonsterLevel) {
                minMonsterLevel = ((LegendsHero) hero).getLevel();
            }
        }

        Pawn selectedMonster;
        for (int index = 0; index < teamHero.getTeamSize(); ) {
            selectedMonster = monsterList.get(Utils.randomNumber.nextInt(monsterList.size()));
            if (((LegendsMonster) selectedMonster).getLevel() >= minMonsterLevel && ((LegendsMonster) selectedMonster).getLevel() <= maxMonsterLevel) {
                teamMonster.setPawnAtIndex(index, selectedMonster);
                index ++;
            }
        }
    }
    
    public static void printChar(String textColor, Character ch, Integer length) {
        System.out.print(textColor);
        for (int count = 0; count < length; count ++) {
            System.out.print(ch);
        }
        System.out.print(TextColors.RESET);
    }

    public static void playGame() throws IOException {
        do {
            try {
                printChar(TextColors.CYAN, '*', 60);
                System.out.print(TextColors.BLUE + " Legends: " + TextColors.PURPLE + "Heroes " + TextColors.GREEN + "and " + TextColors.RED + "Monsters " + TextColors.RESET + "");
                printChar(TextColors.CYAN, '*', 60);
                System.out.println();

                System.out.println("*************** \t Welcome to this magical world, it is was once a legendary world it was peaceful and everyone lived happily. " + Symbols.HAPPY + "\t ***************");
                System.out.println("************* \t  But one day a band of monsters called the Akatsuki, over took the world and laid waste to it, destroying everything. " + Symbols.SUPRISED + "\t *************");
                System.out.println("*********** \t A group of brave souls made an alliance and were planning to take back their world. Fight for it and also willing to give their lives for it");
                System.out.println("********* \t They fought and fought and while killing many monsters they gave their lives for their world. They were etched into the history as heroes who bought back peace.");
                System.out.println("******* \t Now many young people wanted to fight monsters and be called as heroes. They started looking for monsters in hiding and fighting them.");
                System.out.println("******* \t As times passed many weapons, armors, potions and spells were invented which aided the heroes to fight the monsters easily. These could be found in markets around the world");
                System.out.println("********* \t The monsters started to appear randomly and sometimes in groups, which made it difficult for heroes to fight them individually.");
                System.out.println("*********** \t You are the head of the group of brave heroes, guide the hero across your world, upgrade your inventory, find the monsters and kill them!");
                System.out.println("************* \t You will gain gold and experience points for every battle win. You can use the gold to buy new weapons or armors and experince to level up your heroes.");
                System.out.println("*************** \t Hope you can defeat all the monsters and enjoy the game!!");

                generateConstants();

                LegendsLayout lgLayout = new LegendsLayout(8);

                do {
                    lgLayout.initLayout();
                    lgLayout.displayLayout();

                    System.out.println("This is the layout of the map! Would you like to regenerate the map? (Y/N)");
                    System.out.print("Input: ");
                } while (Utils.input.readLine().equalsIgnoreCase("Y"));

                System.out.println("It would be great if you can have a team of heroes.\n In this game it would be preferable if you can have a team size of between 1 and 3");
                System.out.print("What would you like your team size to be? ");
                initTeamHero();
                teamHero.setTeamSize(Integer.parseInt(Utils.input.readLine()));
                buildHeroTeam(teamHero);

                lgLayout.getGameLayout()[teamHero.getPositionX()][teamHero.getPositionY()].placeTeam(teamHero);
                System.out.println("This is the latest state of the layout with the hero team present :)");
                lgLayout.displayLayout();

                String playedMove;
                System.out.println("\nAll the best\n");

                while (true) {
                    try {
                        System.out.println("\nFollowing are the rules for movements in the game!! Please press the right button to play the game!");
                        System.out.println("W - Move Up");
                        System.out.println("A - Move Left");
                        System.out.println("S - Move Down");
                        System.out.println("D - Move Right");
                        System.out.println("Q - Quit Game");
                        System.out.print("What would you like to do? ");
                        playedMove = Utils.input.readLine();
                        if (playedMove.equalsIgnoreCase("W")) {
                            if ((teamHero.getPositionX() - 1) < lgLayout.getLength()) {
                                throw new IllegalStateException("This in invalid move!! You cannot move out of the board!");
                            }

                            if (lgLayout.getCellType(getTeamHero().getPositionX() - 1, getTeamHero().getPositionY()) == CellSpace.INACCESSIBLE) {
                                throw new RuntimeException("Sorry!! That is an inaccessible space and your team cannot move there!!");
                            }

                            lgLayout.getCell(getTeamHero().getPositionX(), getTeamHero().getPositionY()).removeTeam();
                            getTeamHero().setPositionX(getTeamHero().getPositionX() - 1);
                            lgLayout.getCell(getTeamHero().getPositionX(), getTeamHero().getPositionY()).placeTeam(getTeamHero());
                            lgLayout.displayLayout();

                            if (lgLayout.getCellType(getTeamHero().getPositionX() , getTeamHero().getPositionY()) == CellSpace.MARKET) {
                                System.out.println("You have landed on a market spot. Would you like to shop? (Y/N) ");
                                System.out.print("Input: ");
                                if (Utils.input.readLine().equalsIgnoreCase("Y")) {
                                    MarketDriver.enterMarket();
                                }
                            } else {
                                System.out.println("Battle Start!!\n\n");
                                BattleDriver.startBattle();
                            }
                        } else if (playedMove.equalsIgnoreCase("A")) {
                            if ((teamHero.getPositionY() - 1) < lgLayout.getBreadth()) {
                                throw new IllegalStateException("This in invalid move!! You cannot move out of the board!");
                            }

                            if (lgLayout.getCellType(getTeamHero().getPositionX(), getTeamHero().getPositionY() - 1) == CellSpace.INACCESSIBLE) {
                                throw new RuntimeException("Sorry!! That is an inaccessible space and your team cannot move there!!");
                            }

                            lgLayout.getCell(getTeamHero().getPositionX(), getTeamHero().getPositionY()).removeTeam();
                            getTeamHero().setPositionY(getTeamHero().getPositionY() - 1);
                            lgLayout.getCell(getTeamHero().getPositionX(), getTeamHero().getPositionY()).placeTeam(getTeamHero());
                            lgLayout.displayLayout();
                            if (lgLayout.getCellType(getTeamHero().getPositionX() , getTeamHero().getPositionY()) == CellSpace.MARKET) {
                                System.out.println("You have landed on a market spot. Would you like to shop? (Y/N) ");
                                System.out.print("Input: ");
                                if (Utils.input.readLine().equalsIgnoreCase("Y")) {
                                    MarketDriver.enterMarket();
                                }
                            } else {
                                System.out.println("Battle Start!!\n\n");
                                BattleDriver.startBattle();
                            }
                        } else if (playedMove.equalsIgnoreCase("S")) {
                            if ((teamHero.getPositionX() + 1) > lgLayout.getLength()) {
                                throw new IllegalStateException("This in invalid move!! You cannot move out of the board!");
                            }

                            if (lgLayout.getCellType(getTeamHero().getPositionX() + 1, getTeamHero().getPositionY()) == CellSpace.INACCESSIBLE) {
                                throw new RuntimeException("Sorry!! That is an inaccessible space and your team cannot move there!!");
                            }

                            lgLayout.getCell(getTeamHero().getPositionX(), getTeamHero().getPositionY()).removeTeam();
                            getTeamHero().setPositionX(getTeamHero().getPositionX() + 1);
                            lgLayout.getCell(getTeamHero().getPositionX(), getTeamHero().getPositionY()).placeTeam(getTeamHero());
                            lgLayout.displayLayout();
                            if (lgLayout.getCellType(getTeamHero().getPositionX(), getTeamHero().getPositionY()) == CellSpace.MARKET) {
                                System.out.println("You have landed on a market spot. Would you like to shop? (Y/N) ");
                                System.out.print("Input: ");
                                if (Utils.input.readLine().equalsIgnoreCase("Y")) {
                                    MarketDriver.enterMarket();
                                }
                            } else {
                                System.out.println("Battle Start!!\n\n");
                                BattleDriver.startBattle();
                            }
                        } else if (playedMove.equalsIgnoreCase("D")) {
                            if ((teamHero.getPositionY() + 1) > lgLayout.getBreadth()) {
                                throw new IllegalStateException("This in invalid move!! You cannot move out of the board!");
                            }

                            if (lgLayout.getCellType(getTeamHero().getPositionX(), getTeamHero().getPositionY() + 1) == CellSpace.INACCESSIBLE) {
                                throw new RuntimeException("Sorry!! That is an inaccessible space and your team cannot move there!!");
                            }

                            lgLayout.getCell(getTeamHero().getPositionX(), getTeamHero().getPositionY()).removeTeam();
                            getTeamHero().setPositionY(getTeamHero().getPositionY() + 1);
                            lgLayout.getCell(getTeamHero().getPositionX(), getTeamHero().getPositionY()).placeTeam(getTeamHero());
                            lgLayout.displayLayout();
                            if (lgLayout.getCellType(getTeamHero().getPositionX(), getTeamHero().getPositionY()) == CellSpace.MARKET) {
                                System.out.println("You have landed on a market spot. Would you like to shop? (Y/N) ");
                                System.out.print("Input: ");
                                if (Utils.input.readLine().equalsIgnoreCase("Y")) {
                                    MarketDriver.enterMarket();
                                }
                            } else {
                                System.out.println("Battle Start!!\n\n");
                                BattleDriver.startBattle();
                            }
                        } else if (playedMove.equalsIgnoreCase("Q")) {
                            System.out.println("Qutting game!!");
                            break;
                        } else {
                            throw new IllegalStateException("Invalid Input");
                        }
                    } catch (Exception error) {
                        System.out.print(" " + error.getMessage());
                    }
                }
            } catch (Exception error) {
                System.out.println("Uh oh!! The game crashed! Apologise for the inconvenience!! \nError " + error.getMessage());
            }

        System.out.println("I hope you saved your world and had fun while doing it " + Symbols.HAPPY);
        System.out.print("Do you want to play again? (Y/N) ");
        } while (Utils.input.readLine().equalsIgnoreCase("Y"));

    }
}
