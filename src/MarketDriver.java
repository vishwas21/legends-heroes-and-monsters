import java.io.IOException;

public class MarketDriver {

    private static Integer customerHero;

    public static void weaponDriver() throws IOException {

        // TODO: Check item level before allowing to buy
        System.out.println("Here are the list of weapons which you can buy from");
        Utils.displayWeapons(LegendsGameDriver.getMarketItemMap().get(ItemType.WEAPON));

        System.out.print("Please select a Weapon which you would like to buy: ");
        int itemNumber = Integer.parseInt(Utils.input.readLine()) - 1;

        if (itemNumber < 0 || itemNumber >= LegendsGameDriver.getMarketItemMap().get(ItemType.WEAPON).size()) {
            throw new IllegalStateException("Invalid Item selected");
        }

        if (((LegendsHero)(LegendsGameDriver.getTeamHero().getPawnAtIndex(customerHero))).getLevel() < LegendsGameDriver.getMarketItemMap().get(ItemType.WEAPON).get(itemNumber).getLevel()) {
            System.out.println("Hero level is not high enough to buy this item!! Please try again");
            return;
        }

        int weaponPrice = LegendsGameDriver.getMarketItemMap().get(ItemType.WEAPON).get(itemNumber).getPrice();

        if (((LegendsHero)(LegendsGameDriver.getTeamHero().getPawnAtIndex(customerHero))).getGold() < weaponPrice) {
            System.out.println("The hero you have chosen does not have enough gold to buy the weapon you have chosen!");
            return;
        }

        ((LegendsHero)(LegendsGameDriver.getTeamHero().getPawnAtIndex(customerHero))).setGold(((LegendsHero)(LegendsGameDriver.getTeamHero().getPawnAtIndex(customerHero))).getGold() - weaponPrice);
        ((LegendsHero)(LegendsGameDriver.getTeamHero().getPawnAtIndex(customerHero))).getItemInventory().get(ItemType.WEAPON).add(((Weapon)(LegendsGameDriver.getMarketItemMap().get(ItemType.WEAPON).get(itemNumber))).clone());
    }

    public static void armorDriver() throws IOException {
        System.out.println("Here are the list of Armors which you can buy from");
        Utils.displayWeapons(LegendsGameDriver.getMarketItemMap().get(ItemType.ARMOR));

        System.out.print("Please select a Armor which you would like to buy: ");
        int itemNumber = Integer.parseInt(Utils.input.readLine()) - 1;

        if (itemNumber < 0 || itemNumber >= LegendsGameDriver.getMarketItemMap().get(ItemType.ARMOR).size()) {
            throw new IllegalStateException("Invalid Item selected");
        }

        if (((LegendsHero)(LegendsGameDriver.getTeamHero().getPawnAtIndex(customerHero))).getLevel() < LegendsGameDriver.getMarketItemMap().get(ItemType.ARMOR).get(itemNumber).getLevel()) {
            System.out.println("Hero level is not high enough to buy this item!! Please try again");
            return;
        }

        int weaponPrice = LegendsGameDriver.getMarketItemMap().get(ItemType.ARMOR).get(itemNumber).getPrice();

        if (((LegendsHero)(LegendsGameDriver.getTeamHero().getPawnAtIndex(customerHero))).getGold() < weaponPrice) {
            System.out.println("The hero you have chosen does not have enough gold to buy the armor you have chosen!");
            return;
        }

        ((LegendsHero)(LegendsGameDriver.getTeamHero().getPawnAtIndex(customerHero))).setGold(((LegendsHero)(LegendsGameDriver.getTeamHero().getPawnAtIndex(customerHero))).getGold() - weaponPrice);
        ((LegendsHero)(LegendsGameDriver.getTeamHero().getPawnAtIndex(customerHero))).getItemInventory().get(ItemType.ARMOR).add(((Armor)(LegendsGameDriver.getMarketItemMap().get(ItemType.ARMOR).get(itemNumber))).clone());
    }

    public static void potionsDriver() throws IOException {
        System.out.println("Here are the list of potions which you can buy from");
        Utils.displayWeapons(LegendsGameDriver.getMarketItemMap().get(ItemType.POTION));

        System.out.print("Please select a potion which you would like to buy: ");
        int itemNumber = Integer.parseInt(Utils.input.readLine()) - 1;

        if (itemNumber < 0 || itemNumber >= LegendsGameDriver.getMarketItemMap().get(ItemType.POTION).size()) {
            throw new IllegalStateException("Invalid Item selected");
        }

        int weaponPrice = LegendsGameDriver.getMarketItemMap().get(ItemType.POTION).get(itemNumber).getPrice();

        if (((LegendsHero)(LegendsGameDriver.getTeamHero().getPawnAtIndex(customerHero))).getLevel() < LegendsGameDriver.getMarketItemMap().get(ItemType.POTION).get(itemNumber).getLevel()) {
            System.out.println("Hero level is not high enough to buy this item!! Please try again");
            return;
        }

        if (((LegendsHero)(LegendsGameDriver.getTeamHero().getPawnAtIndex(customerHero))).getGold() < weaponPrice) {
            System.out.println("The hero you have chosen does not have enough gold to buy the potion you have chosen!");
            return;
        }

        ((LegendsHero)(LegendsGameDriver.getTeamHero().getPawnAtIndex(customerHero))).setGold(((LegendsHero)(LegendsGameDriver.getTeamHero().getPawnAtIndex(customerHero))).getGold() - weaponPrice);
        ((LegendsHero)(LegendsGameDriver.getTeamHero().getPawnAtIndex(customerHero))).getItemInventory().get(ItemType.POTION).add(((Potion)(LegendsGameDriver.getMarketItemMap().get(ItemType.POTION).get(itemNumber))).clone());
    }

    public static void spellDriver() throws IOException {
        System.out.println("Here are the list of spells which you can buy from");
        Utils.displayWeapons(LegendsGameDriver.getMarketItemMap().get(ItemType.SPELL));

        System.out.print("Please select a spell which you would like to buy: ");
        int itemNumber = Integer.parseInt(Utils.input.readLine()) - 1;

        if (itemNumber < 0 || itemNumber >= LegendsGameDriver.getMarketItemMap().get(ItemType.SPELL).size()) {
            throw new IllegalStateException("Invalid Item selected");
        }

        if (((LegendsHero)(LegendsGameDriver.getTeamHero().getPawnAtIndex(customerHero))).getLevel() < LegendsGameDriver.getMarketItemMap().get(ItemType.SPELL).get(itemNumber).getLevel()) {
            System.out.println("Hero level is not high enough to buy this weapon!! Please try again");
            return;
        }

        int weaponPrice = LegendsGameDriver.getMarketItemMap().get(ItemType.SPELL).get(itemNumber).getPrice();

        if (((LegendsHero)(LegendsGameDriver.getTeamHero().getPawnAtIndex(customerHero))).getGold() < weaponPrice) {
            System.out.println("The hero you have chosen does not have enough gold to buy the spell you have chosen!");
            return;
        }

        ((LegendsHero)(LegendsGameDriver.getTeamHero().getPawnAtIndex(customerHero))).setGold(((LegendsHero)(LegendsGameDriver.getTeamHero().getPawnAtIndex(customerHero))).getGold() - weaponPrice);
        ((LegendsHero)(LegendsGameDriver.getTeamHero().getPawnAtIndex(customerHero))).getItemInventory().get(ItemType.SPELL).add(((Spell)(LegendsGameDriver.getMarketItemMap().get(ItemType.SPELL).get(itemNumber))).clone());
    }

    public static void enterMarket() throws IOException {
        while (true) {
            LegendsGameDriver.printChar(TextColors.PURPLE, '*', 160);
            System.out.println();
            System.out.println("Welcome to the market! Here are various items which you can use to fight the monsters!\nHappy shopping :D\n");
            BattleDriver.displayTeam(LegendsGameDriver.getTeamHero(), "Heroes");

            try {
                System.out.print("Please choose the hero who you want to buy the items : ");
                customerHero = Integer.parseInt(Utils.input.readLine()) - 1;

                if (customerHero < 0 || customerHero >= LegendsGameDriver.getTeamHero().getTeamSize()) {
                    throw new IllegalStateException("Invalid hero choice");
                }
                System.out.println();
                System.out.println("1. Weapons\n2. Armors\n3. Potions\n4. Spells");
                System.out.print("Please enter the number of the item which you would like to buy: ");
                int choice = Integer.parseInt(Utils.input.readLine());
                if (choice == 1) {
                    weaponDriver();
                } else if (choice == 2) {
                    armorDriver();
                } else if (choice == 3) {
                    potionsDriver();
                } else if (choice == 4) {
                    spellDriver();
                } else {
                    System.out.println("Hope you can choose the right option next time!" + Symbols.SUPRISED + " Please try again! :)");
                }
            } catch (Exception error) {
                System.out.println("Error Encountered while selected item : " + error.getMessage());
                System.out.println("Please Try again!!");
                continue;
            }
            System.out.print("Would you like to continue (Y/N): ");
            if ((Utils.input.readLine()).equalsIgnoreCase("N")) {
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        LegendsGameDriver.generateConstants();

        LegendsGameDriver.initTeamHero();
        LegendsGameDriver.initTeamMonster();

        LegendsGameDriver.generateConstants();
        LegendsGameDriver.getTeamHero().setTeamSize(3);
        LegendsGameDriver.buildHeroTeam(LegendsGameDriver.getTeamHero());

        enterMarket();
    }
}
