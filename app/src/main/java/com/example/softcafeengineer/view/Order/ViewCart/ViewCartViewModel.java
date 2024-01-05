package com.example.softcafeengineer.view.Order.ViewCart;

import androidx.lifecycle.ViewModel;

public class ViewCartViewModel extends ViewModel
{
    private ViewCartPresenter presenter;

    public ViewCartViewModel() {
        this.presenter = new ViewCartPresenter();
    }

    public ViewCartPresenter getPresenter() { return this.presenter; }

    @Override
    protected void onCleared() {
        super.onCleared();
        // release resources
    }
}
