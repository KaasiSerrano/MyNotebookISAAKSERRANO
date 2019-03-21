package com.example.notifications.sync;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class WaterReminderFirebaseJobService extends JobService {

    private AsyncTask mBackgroundTask;

    @Override
    public boolean onStartJob(@NonNull JobParameters job) {
        mBackgroundTask = new AsyncTaskThreads(this,job);

        mBackgroundTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(@NonNull JobParameters job) {

       if(mBackgroundTask!=null)mBackgroundTask.cancel(true);


        return true;
    }

    // Completed (3) WaterReminderFirebaseJobService should extend from JobService

    // Completed (4) Override onStartJob
    // TODO (5) By default, jobs are executed on the main thread, so make an anonymous class extending
    //  AsyncTask called mBackgroundTask.
    class AsyncTaskThreads extends AsyncTask<Object,Integer,Object>
    {
        JobParameters job;
        Context context;

        public AsyncTaskThreads(Context context, JobParameters job) {
            this.context = context;
            this.job = job;
        }

        @Override
        protected Object doInBackground(Object[] params) {
            // TODO (7) Use ReminderTasks to execute the new charging reminder task you made, use


            ReminderTasks.executeTask(context,ReminderTasks.ACTION_CHARGING_REMINDER);
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {


            jobFinished(job, false);
        }
    }
    // TODO (6) Override doInBackground
    // this service as the context (WaterReminderFirebaseJobService.this) and return null
    // when finished.
    // TODO (8) Override onPostExecute and call jobFinished. Pass the job parameters
    // and false to jobFinished. This will inform the JobManager that your job is done
    // and that you do not want to reschedule the job.

    // TODO (9) Execute the AsyncTask
    // TODO (10) Return true

    // Completed (11) Override onStopJob
    // TODO (12) If mBackgroundTask is valid, cancel it
    // TODO (13) Return true to signify the job should be retried

}
