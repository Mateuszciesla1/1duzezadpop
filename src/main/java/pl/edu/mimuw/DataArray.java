package pl.edu.mimuw;
//A class helpful to print all data
//only used in simulation readData
public class DataArray {
    private final int[] firstArray;
    private final String[] secondArray;

    public DataArray(int getSize){
        firstArray = new int[getSize];
        secondArray = new String[getSize];
    }
    public void setIndex(int index, int value){
        firstArray[index] = value;
    }
    public void setIndex(int index, String value){
        secondArray[index] = value;
    }
    public int getIntIndex(int index){
        return firstArray[index];
    }
    public String getStringIndex(int index){
        return secondArray[index];
    }
}
