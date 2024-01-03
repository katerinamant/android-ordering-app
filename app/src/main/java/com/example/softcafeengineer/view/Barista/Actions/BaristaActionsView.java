package com.example.softcafeengineer.view.Barista.Actions;

public interface BaristaActionsView
{
    /**
     * Log user out when
     * day is over
     */
    void onClosedDay();

    /**
     * Start activity again
     * to load new orders
     */
    void onRefresh();
}
