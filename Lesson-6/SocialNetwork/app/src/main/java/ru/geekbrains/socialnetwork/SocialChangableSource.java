package ru.geekbrains.socialnetwork;

public interface SocialChangableSource extends SocialDataSource {
    void add();
    void delete();
}
