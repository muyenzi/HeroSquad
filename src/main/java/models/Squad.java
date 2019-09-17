package models;
import java.util.List;
import java.util.ArrayList;

public class Squad {
    private String mName;
    private String mDescription;
    private int mNumber;
    private static List<Squad> instances = new ArrayList<Squad>();
    private int mId;
    private List<Hero> mHeroes;
    private boolean published; //i’m new



    public Squad(String name, String description, int number) {
        mName = name;
        mDescription = description;
        mNumber=number;
        instances.add(this);
        mId = instances.size();
        mHeroes = new ArrayList<Hero>();
        this.published = false; //also new
    }


    public List<Hero> getHeroes(){
        return mHeroes;
    }

    public String getName(){
        return mName;
    }

    public String getDescription(){
        return mDescription;
    }
    public int getmNumber() {
        return mNumber;
    }

    public static List<Squad> all(){
        return instances;
    }

    public static void clear(){
        instances.clear();
    }

    public int getId(){
        return mId;
    }

    public static Squad find(int id){
        return instances.get(id - 1);
    }

    public boolean getPublished(){ //new too
        return this.published;
    }



    public void addHero(Hero hero){
        if(mHeroes.size() < 4) {
            mHeroes.add(hero);
        } else {
            // do nothing
        }
        //mHeroes.add(hero);
    }
}
