package com.owulia.score;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {


    private final static String A_TEAM_SCORE = "A_TEAM_SCORE";
    private final static String B_TEAM_SCORE = "B_TEAM_SCORE";
    private final static String A_BACK = "A_BACK";
    private final static String B_BACK = "B_BACK";

//    private MutableLiveData<Integer> aTeamScore;
//    private MutableLiveData<Integer> bTeamScore;

    private SavedStateHandle handle;

    public MyViewModel(SavedStateHandle handle) {
        this.handle = handle;
    }

    private int aBack, bBack;

    public MutableLiveData<Integer> getaTeamScore() {

        if (!handle.contains(A_TEAM_SCORE)) {
            handle.set(A_TEAM_SCORE, 0);
        }
        return handle.getLiveData(A_TEAM_SCORE);
//        if (aTeamScore == null) {
//            aTeamScore = new MutableLiveData<>();
//            aTeamScore.setValue(0);
//        }
//        return aTeamScore;
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

//        if (bTeamScore == null) {
//            bTeamScore = new MutableLiveData<>();
//            bTeamScore.setValue(0);
//        }
//        return bTeamScore;
    }

    public void aTeamAdd (int p) {
        getaBack().setValue(getaTeamScore().getValue());
        getbBack().setValue(getbTeamScore().getValue());
//        aBack = aTeamScore.getValue();
//        bBack = bTeamScore.getValue();
//        aTeamScore.setValue(aTeamScore.getValue() + p);
        getaTeamScore().setValue(getaTeamScore().getValue() + p);
    }

    public void bTeamAdd (int p) {
        getaBack().setValue(getaTeamScore().getValue());
        getbBack().setValue(getbTeamScore().getValue());
//        aBack = aTeamScore.getValue();
//        bBack = bTeamScore.getValue();
//        bTeamScore.setValue(bTeamScore.getValue() + p);
        getbTeamScore().setValue(getbTeamScore().getValue() + p);
    }

    public void reset () {
//        aBack = aTeamScore.getValue();
//        bBack = bTeamScore.getValue();
//        aTeamScore.setValue(0);
//        bTeamScore.setValue(0);
        getaBack().setValue(getaTeamScore().getValue());
        getbBack().setValue(getbTeamScore().getValue());
        getaTeamScore().setValue(0);
        getbTeamScore().setValue(0);
    }

    public void undo () {
//        aTeamScore.setValue(aBack);
//        bTeamScore.setValue(bBack);
        getaTeamScore().setValue(getaBack().getValue());
        getbTeamScore().setValue(getbBack().getValue());
    }

}
