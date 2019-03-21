package com.example.notifications.sync;

import android.content.Context;

import com.example.notifications.utilities.NotificaitonUtils;
import com.example.notifications.utilities.PreferenceUtilities;

// TODO create a  static string ACTION_CHARGING_REMINDER defining the charging action

public class ReminderTasks{
    public static final String ACTION_DISMISS_NOTIFICAITON="dismiss-notification";
    public static final String ACTION_INCREMENT_WATER_COUNT="Action_water_increment";
    static final String ACTION_CHARGING_REMINDER = "Charging_Reminder";

    public static void executeTask(Context context, String action){

        if(ACTION_INCREMENT_WATER_COUNT.contentEquals(action)){

            IncrementWaterCount(context);

        }
        else if(ACTION_DISMISS_NOTIFICAITON.contentEquals(action)){

            NotificaitonUtils.clearNotifications(context);

        } else if (ACTION_CHARGING_REMINDER.contentEquals(action))
        {
            issueChargingReminder(context);


        }
        //TODO include the else condition to check if the action was ACTION_CHARGING_REMINDER and call the method you created below for charging reminder notification
    }

// completed (2) Create a public static constant String called ACTION_INCREMENT_WATER_COUNT
// completed (6) Create a public static void method called executeTask

// completed (7) Add a Context called context and String parameter called action to the parameter list

// completed (8) If the action equals ACTION_INCREMENT_WATER_COUNT, call this class's incrementWaterCount


// completed (3) Create a private static void method called incrementWaterCount

// completed (4) Add a Context called context to the argument list
// completed (5) From incrementWaterCount, call the PreferenceUtility method that will ultimately update the water count

    private static void IncrementWaterCount(Context context){
        PreferenceUtilities.incrementWaterCount(context);
        NotificaitonUtils.clearNotifications(context);


    }


    // TODO (2) Create an additional task for issuing a charging reminder notification.
    // This should be done in a similar way to how you have an action for incrementingWaterCount
    // and dismissing notifications. This task should both create a notification AND
    // increment the charging reminder count (hint: there is a method for this in PreferenceUtilities)
    // When finished, you should be able to call executeTask with the correct parameters to execute
    // this task. Don't forget to add the code to executeTask which actually calls your new task!


    private static void issueChargingReminder(Context context)
    {
        PreferenceUtilities.incrementChargingReminderCount(context);
        NotificaitonUtils.remindUserBecauseCharging(context);
    }
}

