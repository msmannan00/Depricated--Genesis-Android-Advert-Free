package com.darkweb.genesissearchengine.noads.helperManager;

import com.darkweb.genesissearchengine.noads.constants.enums;

import java.util.List;

public class eventObserver
{
    public interface eventListener
    {
        void invokeObserver(List<Object> data, enums.etype event_type);
    }
}
