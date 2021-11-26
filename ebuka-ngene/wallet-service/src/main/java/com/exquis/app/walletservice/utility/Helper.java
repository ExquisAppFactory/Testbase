package com.exquis.app.walletservice.utility;

public class Helper {
    public static String getNumeric(int size)
    {
        String numbersToPickFrom = "1234567890";
        StringBuffer stringBuffer = new StringBuffer(size);
        for(int i=0; i<size; i++)
        {
            int index = (int)(numbersToPickFrom.length() * Math.random());
            stringBuffer.append(numbersToPickFrom.charAt(index));
        }
        return stringBuffer.toString();
    }
}
