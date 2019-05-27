package com.example.fmeimanager.models;

import java.util.ArrayList;
import java.util.List;

public class FmeiPanelCreator {

    private List<FmeiPanel> mFmeiPanels;

    public FmeiPanelCreator() {
        mFmeiPanels = new ArrayList<>();
    }

    public void setFmeiPanelsWithFmeiLivedata(List<FmeiPanel> fmeiPanels){
        if (fmeiPanels != null){
            mFmeiPanels.clear();
            mFmeiPanels.addAll(fmeiPanels);
        }
    }

    //GET fmeis number
    public int getFmeiListSize(){
        return mFmeiPanels.size();
    }

    public List<FmeiPanel> getFmeiPanels(){
        return mFmeiPanels;
    }

    public void clear(){mFmeiPanels.clear();}

}
