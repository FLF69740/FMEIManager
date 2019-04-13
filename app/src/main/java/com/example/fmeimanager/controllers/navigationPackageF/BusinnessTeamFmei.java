package com.example.fmeimanager.controllers.navigationPackageF;

import android.util.Log;

import com.example.fmeimanager.database.Participant;
import com.example.fmeimanager.database.TeamFmei;
import com.example.fmeimanager.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class BusinnessTeamFmei {

    //GET catalog build
    public static List<ArrayList<Participant>> getCatalog(ArrayList<Participant> participants){
        ArrayList<ArrayList<Participant>> catalog = new ArrayList<>();
        ArrayList<Participant> catalogPage = new ArrayList<>();
        int divider = 0;
        for (int i = 0 ; i < participants.size() ; i++){
            if (divider == 8){
                catalogPage.add(participants.get(i));
                catalog.add(catalogPage);
                catalogPage = new ArrayList<>();
                divider = 0;
            }else {
                catalogPage.add(participants.get(i));
                divider++;
            }
            if (i == participants.size()-1){
                catalog.add(catalogPage);
            }
        }

        return catalog;
    }

    //TEAM BUILDER FRAGMENT
    //RETURN mParticipantList AFTER tag
    public static ArrayList<String> participantListRealoaded(Participant participant, ArrayList<String> oldList, long teamLeaderId){
        ArrayList<String> newList;
        newList = oldList;

        if (participant.getId() != teamLeaderId) {

            boolean participantDetection = false;
            int trackDetection = 0;

            for (int i = 0; i < oldList.size(); i++) {
                if (String.valueOf(participant.getId()).equals(oldList.get(i))) {
                    participantDetection = true;
                    trackDetection = i;
                }
            }

            if (participantDetection) {
                newList.remove(trackDetection);
            } else {
                if (oldList.size() < 12) {
                    newList.add(String.valueOf(participant.getId()));
                }
            }

        }
        return newList;
    }

    //TEAM BUILDER FRAGMENT
    //RETURN mTeamLeaderId AFTER tag
    public static long teamLeaderIdRealoaded(Participant participant, ArrayList<String> oldList, long teamLeaderId){
        long newLeader = teamLeaderId;
        boolean newLeaderActed = false;

        for (int i = 0 ; i < oldList.size() ; i++){
            if (participant.getId() == Long.valueOf(oldList.get(i))){
                newLeader = participant.getId();
                newLeaderActed = true;
            }
        }


        if (!newLeaderActed){
            newLeader = 0;
        }


        return newLeader;
    }

    //RETURN a string with a long list
    public static String convertToListString(ArrayList<String> list, String title){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(title);
        for (int i = 0 ; i < list.size() ; i++){
            stringBuilder.append(list.get(i) + " - ");
        }
        return stringBuilder.toString();
    }

}
