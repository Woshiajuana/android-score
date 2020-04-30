package com.owulia.score;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

public class MyViewModel extends AndroidViewModel {

    // 定义存储的文件名称
    private final static String SHP_SCORE = "SHP_SCORE";

    // 两队的分数
    private final static String A_TEAM_SCORE = "A_TEAM_SCORE";
    private final static String B_TEAM_SCORE = "B_TEAM_SCORE";

    // 上一次的分数
    private final static String A_BACK = "A_BACK";
    private final static String B_BACK = "B_BACK";

    private SavedStateHandle handle;

    public MyViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);
        this.handle = handle;
    }

    private SharedPreferences getShp() {
        return getApplication().getSharedPreferences(SHP_SCORE, Context.MODE_PRIVATE);
    }

    public void save () {
        SharedPreferences.Editor editor = getShp().edit();
        editor.putInt(A_TEAM_SCORE, getaTeamScore().getValue());
        editor.putInt(B_TEAM_SCORE, getbTeamScore().getValue());
        editor.putInt(A_BACK, getaBack().getValue());
        editor.putInt(B_BACK, getbBack().getValue());
        editor.apply();
    }

    public MutableLiveData<Integer> getaTeamScore() {
        if (!handle.contains(A_TEAM_SCORE)) {
            handle.set(A_TEAM_SCORE, getShp().getInt(A_TEAM_SCORE, 0));
        }
        return handle.getLiveData(A_TEAM_SCORE);
    }

    public MutableLiveData<Integer> getbTeamScore() {
        if (!handle.contains(B_TEAM_SCORE)) {
            handle.set(B_TEAM_SCORE, getShp().getInt(B_TEAM_SCORE, 0));
        }
        return handle.getLiveData(B_TEAM_SCORE);
    }

    private MutableLiveData<Integer> getaBack() {
        if (!handle.contains(A_BACK)) {
            handle.set(A_BACK, getShp().getInt(A_BACK, 0));
        }
        return handle.getLiveData(A_BACK);
    }

    private MutableLiveData<Integer> getbBack() {
        if (!handle.contains(B_BACK)) {
            handle.set(B_BACK, getShp().getInt(B_BACK, 0));
        }
        return handle.getLiveData(B_BACK);
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
