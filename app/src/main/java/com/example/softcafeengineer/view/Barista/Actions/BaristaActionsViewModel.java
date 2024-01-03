package com.example.softcafeengineer.view.Barista.Actions;

import androidx.lifecycle.ViewModel;

public class BaristaActionsViewModel extends ViewModel {
    private BaristaActionsPresenter presenter;

    public BaristaActionsViewModel() {
        this.presenter = new BaristaActionsPresenter();
    }

    public BaristaActionsPresenter getPresenter() { return this.presenter; }

    @Override
    protected void onCleared() {
        super.onCleared();
        // release resources
    }
}
