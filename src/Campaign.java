public class Campaign {

    private int UID;
    private int workers;
    private int numberOfPeople;
    private String district;
    private String name;
    private boolean local;


    public Campaign(int numberOfPeople){
        /*
        Make an array of pilgrims based on number of people with
        a set ration pilgrims:workers
        Assume not local
         */
    }

//    public Campaign(Pilgrim[] pilgrims){
//        /*
//        Calculate number of workers based on number of pilgrims (pilgrims.length;)
//        Assume not local
//         */
//    }

    public int getNumberofCars(){
        //Assume each car holds 4 workers
        return (int)workers/4;
    }

    public int getNumberOfBusses(){
        return 0;//TODO: calc buses?
    }
}
