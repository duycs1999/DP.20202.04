package entity.shipping;

import org.example.DistanceCalculator;

import common.interfaces.Distance;

public class AdapterDistance implements Distance{
    private Distance distanceInterface;
        public AdapterDistance(Distance distanceInterface){
        this.distanceInterface = distanceInterface;
    }
    @Override
    public int calculateDistance(String address, String province) {
        return this.distanceInterface.calculateDistance(address, province);
    }
}
