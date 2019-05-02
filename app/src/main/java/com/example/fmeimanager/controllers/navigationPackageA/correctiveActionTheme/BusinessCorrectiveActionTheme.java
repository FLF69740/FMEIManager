package com.example.fmeimanager.controllers.navigationPackageA.correctiveActionTheme;


import android.content.Context;

import com.example.fmeimanager.R;

public class BusinessCorrectiveActionTheme {

    public static String getStringRequest(Context context, long fmeaId, String riskName, String deadLine){
        StringBuilder builder = new StringBuilder();

        builder.append(context.getString(R.string.Risk_file_mail_message_line_one)).append(" ").append(fmeaId).append("\n")
            .append(context.getString(R.string.Risk_file_mail_message_line_two)).append(" ").append(riskName).append("\n\n")
            .append(context.getString(R.string.Risk_file_mail_message_line_three)).append("\n\n")
            .append(context.getString(R.string.Risk_file_mail_message_line_four)).append(" ").append(deadLine).append("\n")
            .append(context.getString(R.string.Risk_file_mail_message_line_five)).append("\n\n")
            .append(context.getString(R.string.Risk_file_mail_message_line_six)).append("\n")
            .append(context.getString(R.string.Risk_file_mail_message_line_seven)).append("\n")
            .append(context.getString(R.string.Risk_file_mail_message_line_height)).append("\n\n")
            .append(context.getString(R.string.Risk_file_mail_message_line_nine)).append("\n\n\n")
            .append(context.getString(R.string.Risk_file_mail_message_line_ten));

        return builder.toString();
    }




}
