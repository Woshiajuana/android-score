package com.owulia.score;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private final static String A_TEAM_SCORE = "A_TEAM_SCORE";
    private final static String B_TEAM_SCORE = "B_TEAM_SCORE";
    private final static String A_BACK = "A_BACK";
    private final static String B_BACK = "B_BACK";
    private SavedStateHandle handle;

    public MyViewModel(SavedStateHandle handle) {
        this.handle = handle;
    }

    public MutableLiveData<Integer> getaTeamScore() {
        if (!handle.contains(A_TEAM_SCORE)) {
            handle.set(A_TEAM_SCORE, 0);
        }
        return handle.getLiveData(A_TEAM_SCORE);
    }

    private MutableLiveData<Integer> getaBack() {
        if (!handle.contains(A_BACK)) {
            handle.set(A_BACK, 0);
        }
        return handle.getLiveData(A_BACK);
    }

    private MutableLiveData<Integer> getbBack() {
        if (!handle.contains(B_BACK)) {
            handle.set(B_BACK, 0);
        }
        return handle.getLiveData(B_BACK);
    }

    public MutableLiveData<Integer> getbTeamScore() {
        if (!handle.contains(B_TEAM_SCORE)) {
            handle.set(B_TEAM_SCORE, 0);
        }
        return handle.getLiveData(B_TEAM_SCORE);
    }

    public void aTeamAdd(int p) {
        getaBack().setValue(getaTeamScore().getValue());
        getbBack().setValue(getbTeamScore().getValue());
        getaTeamScore().setValue(getaTeamScore().getValue() + p);
    }

    public void bTeamAdd(int p) {
        getaBack().setValue(getaTeamScore().getValue());
        getbBack().setValue(getbTeamScore().getValue());
        getbTeamScore().setValue(getbTeamScore().getValue() + p);
    }

    public void reset() {
        getaBack().setValue(getaTeamScore().getValue());
        getbBack().setValue(getbTeamScore().getValue());
        getaTeamScore().setValue(0);
        getbTeamScore().setValue(0);
    }

    public void undo() {
        getaTeamScore().setValue(getaBack().getValue());
        getbTeamScore().setValue(getbBack().getValue());
    }

}
