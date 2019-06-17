package ru.geekbrains.socialnetwork;

public class SocChangableSource implements SocialChangableSource {

    private int count;
    private SocialDataSource dataSource;

    public SocChangableSource(SocialDataSource dataSource) {
        count = 0;
        this.dataSource = dataSource;
    }

    @Override
    public void add() {
        if (count < dataSource.size()){
            count ++;
        }
    }

    @Override
    public void delete() {
        if (count > 0){
            count--;
        }
    }

    @Override
    public Soc getSoc(int position) {
        return dataSource.getSoc(position);
    }

    @Override
    public int size() {
        return count;
    }
}
