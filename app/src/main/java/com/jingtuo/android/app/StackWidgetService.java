package com.jingtuo.android.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

/**
 * Created by jingtuo on 2016/11/12.
 */

public class StackWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(getApplicationContext(), intent);
    }

    class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

        private Context context;

        private Intent intent;

        private int count;

        public StackRemoteViewsFactory(Context context, Intent intent) {
            this.context = context;
            this.intent = intent;
        }

        @Override
        public void onCreate() {
            // In onCreate() you setup any connections / cursors to your data source. Heavy lifting,
            // for example downloading or creating content etc, should be deferred to onDataSetChanged()
            // or getViewAt(). Taking more than 20 seconds in this call will result in an ANR.
            count = 10;

        }

        @Override
        public void onDataSetChanged() {

        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public RemoteViews getViewAt(int i) {
            RemoteViews views = new RemoteViews(getPackageName(), R.layout.example_app_widget_item);
            views.setTextViewText(R.id.widget_item_text, "" + (i + 1));

            // Next, set a fill-intent, which will be used to fill in the pending intent template
            // that is set on the collection view in StackWidgetProvider.
            Bundle extras = new Bundle();
            extras.putInt(ExampleAppWidgetProvider.EXTRA_ITEM, i);
            Intent fillInIntent = new Intent();
            fillInIntent.putExtras(extras);
            // Make it possible to distinguish the individual on-click
            // action of a given item
            views.setOnClickFillInIntent(R.id.widget_item, fillInIntent);
            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            RemoteViews views = new RemoteViews(getPackageName(), R.layout.example_app_wdiget_loading);
            return views;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}
