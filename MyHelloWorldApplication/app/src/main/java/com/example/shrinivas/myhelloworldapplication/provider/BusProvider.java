package com.example.shrinivas.myhelloworldapplication.provider;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class BusProvider {
    public static Bus bus=null;

    public static Bus instantiationBus()
    {
        if(bus==null)
        {
            bus = new Bus(ThreadEnforcer.MAIN);
            return bus;
        }
        return bus;
    }
}