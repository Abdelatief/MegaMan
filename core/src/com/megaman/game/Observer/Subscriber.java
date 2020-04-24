package com.megaman.game.Observer;

abstract class Subscriber
{
    private Publisher publisher;
    public Subscriber(Publisher publisher)
    {
        this.publisher = publisher;
    }

    public abstract void update();

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
