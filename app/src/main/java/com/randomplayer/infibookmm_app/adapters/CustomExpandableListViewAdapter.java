package com.randomplayer.infibookmm_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.randomplayer.infibookmm_app.R;
import com.randomplayer.infibookmm_app.activities.TrackingListActivity;
import com.randomplayer.infibookmm_app.activities.WatcherActivity;
import com.randomplayer.infibookmm_app.models.Transaction;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> StatusGroup;
    private ArrayList<ArrayList<Transaction>> TransactionGroup;

    public CustomExpandableListViewAdapter(){

    }

    public CustomExpandableListViewAdapter(Context ccontext, List<String> statusGroup, ArrayList<ArrayList<Transaction>> transactionGroup){
        this.context = ccontext;
        this.StatusGroup = statusGroup;
        this.TransactionGroup = transactionGroup;
    }

    @Override
    public int getGroupCount() {
        return StatusGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return TransactionGroup.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return TransactionGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return TransactionGroup.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.status_view, null);

        TextView status = convertView.findViewById(R.id.status);
        ImageView statusImage = convertView.findViewById(R.id.status_image);

        status.setText(StatusGroup.get(groupPosition) + ": (" + String.valueOf(getChildrenCount(groupPosition)) + ")" );
        switch (groupPosition){
            case 0:
                statusImage.setImageResource(R.drawable.green);
                break;
            case 1:
                statusImage.setImageResource(R.drawable.yello);
                break;
            case 2:
                statusImage.setImageResource(R.drawable.red);
                break;
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.transaction_view, null);

        TextView lender = convertView.findViewById(R.id.lender);
        TextView borrower = convertView.findViewById(R.id.borrower);
        TextView book = convertView.findViewById(R.id.book_name);
        TextView deadline = convertView.findViewById(R.id.date);

        Transaction transc = (Transaction)getChild(groupPosition, childPosition);
        lender.setText(transc.getLender().getEmail());
        borrower.setText(transc.getBorrower().getEmail());
        book.setText(transc.getBook());

        SimpleDateFormat simpleDate =  new SimpleDateFormat("dd/MM/yyyy");
        String time = simpleDate.format(transc.getTime());
        deadline.setText(time);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
