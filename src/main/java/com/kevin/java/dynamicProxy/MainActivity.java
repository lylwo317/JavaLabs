package com.kevin.java.dynamicProxy;

public class MainActivity {
    private final IService iService;

    public MainActivity(IService iService) {
        this.iService = iService;
    }

    public void runServiceFun() {
        iService.fun();
    }
}
