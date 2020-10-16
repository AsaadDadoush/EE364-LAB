package ee364.hajj.group;

import ee364.hajj.Priority;

public class Campaign implements PermitedForHaj {

    private int UID;
    private int workers;
    private int numberOfPeople;
    private String district;
    private String name;
    private boolean local;

    private Pilgrim[] pilgrims;

    public Campaign(int numberOfPeople){
        /*
        Make an array of pilgrims based on number of people with
        a set ration pilgrims:workers
        Assume not local
         */
    }

    public Campaign(Pilgrim[] pilgrims){
        /*
        Calculate number of workers based on number of pilgrims (pilgrims.length;)
        Assume not local
         */
    }
    @Override
    public int hasUID() {
        return UID;
    }

    @Override
    public boolean isLocal() {
        return local;
    }

    public int getNumberofCars(){
        //Assume each car holds 4 workers
        return (int)workers/4;
    }

    public int getNumberOfBusses(){
        int highPriorityPilgrims = 0;
        int totalBusses = 0;
        for (Pilgrim pilgrim : pilgrims){
            if (pilgrim.getPriority() == Priority.HIGH) highPriorityPilgrims++;
        }
        totalBusses += highPriorityPilgrims/20; //Assume pilgims with physical disability
        totalBusses += (pilgrims.length - highPriorityPilgrims)/40; //Remaining pilgrims fill busses
        return totalBusses;
    }
}
