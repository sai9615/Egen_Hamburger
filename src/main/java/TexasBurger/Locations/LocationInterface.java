package TexasBurger.Locations;

import Util.Results;

public interface LocationInterface {
    public void createLocation();
    public void updateLocation();
    public void readLocation();
    public void deleteLocation();
    public void storeLocations(Results res);
}
