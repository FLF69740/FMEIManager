package com.example.fmeimanager.controllers.navigationPackageA.fmeiTheme;

import java.util.List;

public class BusinessFmeaTheme {

    //GET IPR average for a panel
    public static double getRiskAverageIPR(List<Integer> IPRs){
        double result = 0;
        if (IPRs.size() != 0) {
            for (int i = 0; i < IPRs.size(); i++) {
                result += IPRs.get(i);
            }
            return result / IPRs.size();
        }else {
            return 0;
        }
    }

    //GET IPR MAX for a panel
    public static int getIprMax(int max, int dataProposition){
        return max > dataProposition ? max : dataProposition;
    }

    //GET Number of risk high level
    public static int getHighRiskLevelAmount(List<Integer> IPRs, int highRateRiskLevel){
        int result = 0;
        if (IPRs.size() != 0) {
            for (int i = 0; i < IPRs.size(); i++) {
                if (IPRs.get(i) >= highRateRiskLevel) {
                    result++;
                }
            }
            return result;
        }else {
            return 0;
        }
    }

    //GET Number of risk high level
    public static int getMediumRiskLevelAmount(List<Integer> IPRs, int highRateRiskLevel, int mediumRateRiskLevel){
        int result = 0;
        if (IPRs.size() != 0) {
            for (int i = 0; i < IPRs.size(); i++) {
                if (IPRs.get(i) < highRateRiskLevel && IPRs.get(i) >= mediumRateRiskLevel) {
                    result++;
                }
            }
            return result;
        }else {
            return 0;
        }
    }

    //GET Number of risk high level
    public static int getLowRiskLevelAmount(List<Integer> IPRs, int mediumRateRiskLevel, int lowRateRiskLevel){
        int result = 0;
        if (IPRs.size() != 0) {
            for (int i = 0; i < IPRs.size(); i++) {
                if (IPRs.get(i) < mediumRateRiskLevel && IPRs.get(i) >= lowRateRiskLevel) {
                    result++;
                }
            }
            return result;
        }else {
            return 0;
        }
    }

}
