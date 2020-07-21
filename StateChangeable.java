package edu.umgc.cmis242;

public interface StateChangeable<T extends Enum<T>>
{
    abstract void changeState(Status T);
}
