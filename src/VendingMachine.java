import org.jetbrains.annotations.NotNull;

public class VendingMachine {
    private Inventory[] inventories;
    private int fund;

    public static void main(String[] args) {

    }

    private void initiateInventory() {
        inventories = new Inventory[]
                {
                        new Inventory(new Item("Coke", 0.55), 10),
                        new Inventory(new Item("Chips", 0.70), 10),
                        new Inventory(new Item("RedBull", 0.75), 10)
                };
        resetFund();
    }

    private void resetFund() {
        fund = 0;
    }

    private void addFund(@NotNull CoinEnum coin) {
        switch (coin) {
            case Nickel:
                fund += 0.05;
                break;
            case Dime:
                fund += 0.10;
                break;
            case Quarter:
                fund += 0.25;
                break;
        }
    }

    private String chooseItem(String itemName)
    {
        String msg;
        for (Inventory inventory: inventories) {
            if(inventory.item.name == itemName){
                if(inventory.availability == 0){
                    msg = "The item does not have sufficient inventory! Returning " + fund;
                }
                else if(fund < inventory.item.price){
                    msg = "You do not have sufficient fund! Returning " + fund;
                }
                else{
                    //Successfully delivered the item
                    inventory.availability--;
                    fund -= inventory.item.price;

                    msg = "Enjoy your " + itemName + "!";
                    if(fund > 0 ){
                        msg += " Returning " + fund;
                    }
                }

                resetFund();
                return msg;
            }
        }
        resetFund();
        return "The item: " + itemName + " does not exist";

    }
}
