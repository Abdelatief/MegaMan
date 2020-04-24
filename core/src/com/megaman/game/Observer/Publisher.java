package com.megaman.game.Observer;


import java.util.ArrayList;

public class Publisher
{
    private ArrayList<Subscriber> subscribers = new ArrayList<Subscriber>();

    public void register(Subscriber subscriber)
    {
        subscribers.add(subscriber);
    }

    public void remove(Subscriber subscriber)
    {
        subscribers.remove(subscriber);
    }

    public void NotifyAll()
    {

    }
}
