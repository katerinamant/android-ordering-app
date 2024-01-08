package com.example.softcafeengineer.view.Barista.Actions;

public interface BaristaActionsView {
    /**
     * Log user out and redirect them
     * to the WelcomeScreenActivity
     */
    void onLogOut();

    /**
     * Start activity again
     * to load new orders
     */
    void onRefresh();
}
