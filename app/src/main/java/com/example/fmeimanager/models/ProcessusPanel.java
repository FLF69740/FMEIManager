package com.example.fmeimanager.models;

public class ProcessusPanel {

    private boolean mIsATittle;
    private long mProcessusId;
    private int mProcessusStep;
    private String mProcessusName;
    private boolean mProcessusVisible;
    private int mRiskId;
    private String mTitleRisk;
    private String mCreationDateRisk;
    private long mResponsableRiskId;
    private String mResponsableRisk;
    private int mIPR;
    private int mGravivity;
    private int mFrequencies;
    private int mDetectability;
    private int mCorrectiveIndicator;
    private boolean mProcessusEditFull;
    private boolean mRiskEditFull;
    private boolean mCorrectiveEditFull;
    private boolean mParticipantEditFull;

    public ProcessusPanel(long processusId, int processusStep, String processusName, boolean isATittle) {
        mProcessusId = processusId;
        mProcessusStep = processusStep;
        mProcessusName = processusName;
        mIsATittle = isATittle;
        mCorrectiveIndicator = 0;
        mProcessusEditFull = false;
        mRiskEditFull = false;
        mCorrectiveEditFull = false;
        mParticipantEditFull = false;
    }

    //GETTER
    public boolean isATittle() {return mIsATittle;}
    public long getProcessusId() {return mProcessusId;}
    public int getProcessusStep() {return mProcessusStep;}
    public boolean isProcessusVisible() {return mProcessusVisible;}
    public int getRiskId() {return mRiskId;}
    public String getTitleRisk() {return mTitleRisk;}
    public String getCreationDateRisk() {return mCreationDateRisk;}
    public String getResponsableRisk() {return mResponsableRisk;}
    public int getIPR() {return mIPR;}
    public int getGravivity() {return mGravivity;}
    public int getFrequencies() {return mFrequencies;}
    public int getDetectability() {return mDetectability;}
    public int getCorrectiveIndicator() {return mCorrectiveIndicator;}
    public String getProcessusName() {return mProcessusName;}
    public long getResponsableRiskId() {return mResponsableRiskId;}
    public boolean isProcessusEditFull() {return mProcessusEditFull;}
    public boolean isRiskEditFull() {return mRiskEditFull;}
    public boolean isCorrectiveEditFull() {return mCorrectiveEditFull;}
    public boolean isParticipantEditFull() {return mParticipantEditFull;}

    //SETTER
    public void setATittle(boolean ATittle) {mIsATittle = ATittle;}
    public void setProcessusStep(int processusStep) {mProcessusStep = processusStep;}
    public void setProcessusVisible(boolean processusVisible) {mProcessusVisible = processusVisible;}
    public void setRiskId(int riskId) {mRiskId = riskId;}
    public void setTitleRisk(String titleRisk) {mTitleRisk = titleRisk;}
    public void setCreationDateRisk(String creationDateRisk) {mCreationDateRisk = creationDateRisk;}
    public void setResponsableRisk(String responsableRisk) {mResponsableRisk = responsableRisk;}
    public void setIPR(int IPR) {mIPR = IPR;}
    public void setGravivity(int gravivity) {mGravivity = gravivity;}
    public void setFrequencies(int frequencies) {mFrequencies = frequencies;}
    public void setDetectability(int detectability) {mDetectability = detectability;}
    public void setCorrectiveIndicator(int correctiveIndicator) {mCorrectiveIndicator = correctiveIndicator;}
    public void setProcessusName(String processusName) {mProcessusName = processusName;}
    public void setResponsableRiskId(long responsableRiskId) {mResponsableRiskId = responsableRiskId;}
    public void setProcessusEditFull(boolean processusEditFull) {mProcessusEditFull = processusEditFull;}
    public void setRiskEditFull(boolean riskEditFull) {mRiskEditFull = riskEditFull;}
    public void setCorrectiveEditFull(boolean correctiveEditFull) {mCorrectiveEditFull = correctiveEditFull;}
    public void setParticipantEditFull(boolean participantEditFull) {mParticipantEditFull = participantEditFull;}
}
