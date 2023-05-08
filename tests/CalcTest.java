import com.deckerben.stackomat.ContainerUnitEnum;
import com.deckerben.stackomat.ItemUnitEnum;
import com.deckerben.stackomat.Stackomat;

import java.util.HashMap;

public class CalcTest {

    //Felder

    //Listener

    //Konstruktoren

    //Methoden
    public static void main(String[] args) {
        Stackomat stackomat = new Stackomat(true, true);
        stackomat.setSelectedItemUnit(ItemUnitEnum.FULL_STACK);
        stackomat.setEnabledContainer(
                //ContainerUnitEnum.SLOTS,
                ContainerUnitEnum.SHULKER,
                ContainerUnitEnum.DOUBLE_CHEST,
                ContainerUnitEnum.SHULKER_IN_DOUBLE_CHEST);
        stackomat.setInputItemNum(128);
        HashMap<ContainerUnitEnum, Integer> inputContainer = new HashMap<>();
        inputContainer.put(ContainerUnitEnum.SLOTS,0);
        inputContainer.put(ContainerUnitEnum.SHULKER,0);
        inputContainer.put(ContainerUnitEnum.DOUBLE_CHEST,0);
        inputContainer.put(ContainerUnitEnum.SHULKER_IN_DOUBLE_CHEST,0);
        stackomat.setInputContainer(inputContainer);
        stackomat.calcOutput();
        printOutput(stackomat);

        stackomat.setInputItemNum(170);
        printOutput(stackomat);

        stackomat.configureAutoCalc(true,false);

        stackomat.setInputItemNum(200);
        printOutput(stackomat);

        stackomat.setInputItemNum(300);
        printOutput(stackomat);


        stackomat.setInputItemNum(1);
        inputContainer.put(ContainerUnitEnum.SHULKER,0);
        inputContainer.put(ContainerUnitEnum.SHULKER_IN_DOUBLE_CHEST,1);
        stackomat.setInputContainer(inputContainer);
        printOutput(stackomat);
    }

    private static void printOutput(Stackomat stackomat) {
        System.out.println(stackomat.getFullSlotNum()+" + "+ stackomat.getOverflowNum());
        System.out.println(stackomat.getOccupiedSlots()+" @ "+ stackomat.getFillPercent());
        HashMap<ContainerUnitEnum,Integer> outputContainer = stackomat.getOutputContainer();
        outputContainer.forEach((containerUnitEnum, integer) -> System.out.println(containerUnitEnum.name()+": "+integer));
    }

    //Getter

    //Setter

    //Maker

    //Overrides aus
    ////<Oberklasse>

    //Interne Klassen
    ////Klasse "<Klassenname>"
}
