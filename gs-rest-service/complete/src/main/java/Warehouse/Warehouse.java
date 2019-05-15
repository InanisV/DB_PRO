package Warehouse;

public class Warehouse {
    private String warehouseName;
    private String warehouseAddress;
    private float warehouseLongitude;
    private float warehouseLatitude;
    private double warehouseCapacity;

    public Warehouse(String name, String address, double capacity){
        this.warehouseName=name;
        this.warehouseAddress=address;
        this.warehouseCapacity =capacity;
    }

    public Warehouse(){}

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public float getWarehouseLongitude() {
        return warehouseLongitude;
    }

    public void setWarehouseLongitude(float warehouseLongitude) {
        this.warehouseLongitude = warehouseLongitude;
    }

    public float getWarehouseLatitude() {
        return warehouseLatitude;
    }

    public void setWarehouseLatitude(float warehouseLatitude) {
        this.warehouseLatitude = warehouseLatitude;
    }

    public double getWarehouseCapacity() {
        return warehouseCapacity;
    }

    public void setWarehouseCapacity(double warehouseCapacity) {
        this.warehouseCapacity = warehouseCapacity;
    }

}
