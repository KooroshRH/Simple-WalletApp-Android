package com.example.mywallet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.fragment.app.ListFragment;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.query.QueryBuilder;

public class ReceiptFragment extends ListFragment {
    private TextView capacity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.receipt_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        capacity = getActivity().findViewById(R.id.yourWallet);
        capacity.setText("" + ObjectBox.getBoxStore().boxFor(Wallet.class).query().build().findFirst().getCapacity());
        List<Transaction> transactionBox = ObjectBox.getBoxStore().boxFor(Transaction.class).query().order(Transaction_.id, QueryBuilder.DESCENDING).build().find();
        ArrayList<String> textList = new ArrayList<>();
        for (Transaction transaction : transactionBox){
            if (transaction.getAmountType() == 1) {
                textList.add("+" + transaction.getAmount());
            } else {
                textList.add("-" + transaction.getAmount());
            }
        }
        setListAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, textList));
    }
}
