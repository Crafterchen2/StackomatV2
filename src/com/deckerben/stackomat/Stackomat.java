package com.deckerben.stackomat;

import java.util.HashMap;

public class Stackomat{

    //Felder
    /**
     * Der ausgewählte Einheitentyp.
     * */
    private ItemUnitEnum selectedItemUnit;

    /**
     * Speichert die Anzahl an gefüllten Containern des entsprechenden Typs in Form einer Map.
     * Der Containertyp ist der Schlüssel.
     */
    private HashMap<ContainerUnitEnum,Integer> inputContainer;

    /**
     * Weißt den zugewiesenen Containertypen die Anzahl zu, wie viele Benötigt werden.
     * Wenn nicht vom vorher Festgelegt, ist jeder Containertyp vertreten.
     * Der Containertyp ist der Schlüssel.
     * Vorherige Werte werden überschrieben.
     */
    private final HashMap<ContainerUnitEnum,Integer> outputContainer = new HashMap<>(3);;

    /**
     * Die rohe Anzahl Items.
     */
    private int inputItemNum;

    /**
     * Die gesamtanzahl an gefüllten Stacks.
     */
    private int outputFullSlotNum;

    /**
     * Die Anzahl Items, die nicht ausreichen, um einen Stack zu füllen.
     */
    private int overflowNum;

    private int occupiedSlots;

    private double fillPercent;

    private boolean autoCalcEnabled = false;
    private boolean clearOnChange = true;

    //Konstruktoren

    /**
     * Erzeugt eine neue Stackomat Instanz.
     * Bei Änderungen der Eingabewerte wird keine erneute Berechnung
     * der Werte durchgeführt, allerdings bisherige Werte gelöscht.
     */
    public Stackomat(){
        this(false,true);
    }

    /**
     * Erzeugt eine neue Stackomat Instanz mit konfiguriertem AutoCalc Verhalten.
     * @param autoCalcEnabled
     * Legt fest, ob bei jeder Änderung der Eingabewerte die Berechnung neu durchgeführt werden soll.
     * @param clearOnChange
     * Legt fest, ob der von getOutputContainer() zurückzugebender Wert bei jeder Änderung der Eingabewerte gelöscht werden soll.
     * Falls autoCalcEnabled == true wird dieser Wert ignoriert.
     */
    public Stackomat(boolean autoCalcEnabled, boolean clearOnChange){
        clearAll();
        configureAutoCalc(autoCalcEnabled, clearOnChange);
    }

    //Methoden
    public void calcOutput(){
        //Berechne Slotwerte
        outputFullSlotNum = inputItemNum / selectedItemUnit.getAmount();
        inputContainer.forEach((containerUnitEnum, integer) -> outputFullSlotNum += integer*containerUnitEnum.getAmount());
        overflowNum = inputItemNum % selectedItemUnit.getAmount();
        //Verteile auf Container
        int slotNum = outputFullSlotNum + Math.min(overflowNum,1);
        ContainerUnitEnum[] units = ContainerUnitEnum.values();
        int smallestUnitIndex = 0;
        for (int i = units.length-1; i >= 0; i--) {
            if (outputContainer.containsKey(units[i])) {
                outputContainer.replace(units[i],slotNum / units[i].getAmount());
                slotNum -= alreadySortedSlots(units[i]);
                smallestUnitIndex = Integer.valueOf(i);
                occupiedSlots += units[i].getAmount() * outputContainer.get(units[i]);
            }
        }
        if (slotNum > 0) {
            outputContainer.replace(units[smallestUnitIndex], outputContainer.get(units[smallestUnitIndex]) + 1);
            occupiedSlots += units[smallestUnitIndex].getAmount();
        }
        fillPercent = (double) (outputFullSlotNum + Math.min(overflowNum,1)) / (double) occupiedSlots;
    }

    private void autoCalc(){
        if (clearOnChange) {
            clearOutput();
        }
        if (autoCalcEnabled) calcOutput();
    }

    public void clearAll(){
        clearOutput();
        clearInput();
    }

    public void clearInput() {
        setInputContainer(null);
        setSelectedItemUnit(null);
        setEnabledContainer(ContainerUnitEnum.values());
        setInputItemNum(0);
    }

    public void clearOutput() {
        outputContainer.forEach((containerUnitEnum, integer) -> outputContainer.replace(containerUnitEnum,0));
        outputFullSlotNum = 0;
        overflowNum = 0;
        fillPercent = 0.0;
        occupiedSlots = 0;
    }

    private int alreadySortedSlots(ContainerUnitEnum unit){
        return unit.getAmount()*outputContainer.get(unit);
    }

    /**
     * Legt fest, wie sich Verhalten werden soll, wenn Eingabewerte verändert werden.
     * @param autoCalcEnabled
     * Legt fest, ob bei jeder Änderung der Eingabewerte die Berechnung neu durchgeführt werden soll.
     * @param clearOutputOnChange
     * Legt fest, ob der von getOutputContainer() zurückzugebender Wert bei jeder Änderung der Eingabewerte gelöscht werden soll.
     * Falls autoCalcEnabled == true wird dieser Wert ignoriert.
     */
    public void configureAutoCalc(boolean autoCalcEnabled, boolean clearOutputOnChange) {
        this.autoCalcEnabled = autoCalcEnabled;
        this.clearOnChange = autoCalcEnabled || clearOutputOnChange;
    }

    //Getter
    public ItemUnitEnum getSelectedItemUnit() {
        return selectedItemUnit;
    }

    public HashMap<ContainerUnitEnum, Integer> getInputContainer() {
        return inputContainer;
    }

    public HashMap<ContainerUnitEnum, Integer> getOutputContainer() {
        return outputContainer;
    }

    public int getInputItemNum() {
        return inputItemNum;
    }

    public int getTotalItemAmount(){
        return overflowNum+(outputFullSlotNum*selectedItemUnit.getAmount());
    }

    public int getFullSlotNum() {
        return outputFullSlotNum;
    }

    public int getOverflowNum() {
        return overflowNum;
    }

    public int getOccupiedSlots() {
        return occupiedSlots;
    }

    public double getFillPercent() {
        return fillPercent;
    }

    public boolean isAutoCalcEnabled() {
        return autoCalcEnabled;
    }

    public boolean isClearOnChange() {
        return clearOnChange;
    }

    //Setter
    public void setSelectedItemUnit(ItemUnitEnum selectedItemUnit) {
        this.selectedItemUnit = (selectedItemUnit == null)? ItemUnitEnum.FULL_STACK : selectedItemUnit;
        autoCalc();
    }

    public void setInputContainer(HashMap<ContainerUnitEnum, Integer> inputContainer) {
        if (inputContainer == null) {
            this.inputContainer = new HashMap<>(4);
            this.inputContainer.put(ContainerUnitEnum.SLOTS,0);
            this.inputContainer.put(ContainerUnitEnum.SHULKER,0);
            this.inputContainer.put(ContainerUnitEnum.DOUBLE_CHEST,0);
            this.inputContainer.put(ContainerUnitEnum.SHULKER_IN_DOUBLE_CHEST,0);
        } else {
            this.inputContainer = inputContainer;
        }
        autoCalc();
    }

    public void setInputItemNum(int inputItemNum) {
        this.inputItemNum = inputItemNum;
        autoCalc();
    }

    /**
     * Gibt an, welche Containertypen bei der Verteilung berücksichtigt werden.
     * Ignoriert den Wert von isClearOnChange(), es werden immer alle bisherigen Werte gelöscht.
     * @param enabledContainer
     * Die Containertypen. Doppelte Eintragungen werden ignoriert.
     */
    public void setEnabledContainer(ContainerUnitEnum... enabledContainer){
        outputContainer.clear();
        for (ContainerUnitEnum unit: enabledContainer) {
            outputContainer.put(unit,0);
        }
        autoCalc();
    }
}
