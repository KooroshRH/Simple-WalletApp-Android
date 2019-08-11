package com.example.mywallet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class InputFragment extends Fragment {

    private BoxStore boxStore;
    private EditText amountEdit, typeEdit, descriptionEdit;
    private RadioGroup radioGroup;
    private Wallet wallet;
    private Button add, receipt;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.input_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        boxStore = ObjectBox.getBoxStore();
        amountEdit = getActivity().findViewById(R.id.incomeEdit);
        typeEdit = getActivity().findViewById(R.id.typeEdit);
        descriptionEdit = getActivity().findViewById(R.id.descriptionEdit);
        radioGroup = getActivity().findViewById(R.id.radioGroup);
        add = getActivity().findViewById(R.id.addIncome);
        receipt = getActivity().findViewById(R.id.receiptButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int amountType;
                Box<Transaction> transactionBox = boxStore.boxFor(Transaction.class);
                Box<Wallet> walletBox = boxStore.boxFor(Wallet.class);
                if (walletBox.query().build().findFirst() == null){
                    wallet = new Wallet();
                } else {
                    wallet = walletBox.query().build().findFirst();
                }
                if (radioGroup.getCheckedRadioButtonId() == R.id.incomeRadio){
                    amountType = 1;
                } else {
                    amountType = -1;
                }
                Transaction transaction = new Transaction(Integer.parseInt(amountEdit.getText().toString()), amountType, typeEdit.getText().toString(), descriptionEdit.getText().toString());
                wallet.setCapacity(wallet.getCapacity() + (transaction.getAmount() * transaction.getAmountType()));
                transactionBox.put(transaction);
                walletBox.put(wallet);
                descriptionEdit.setText("");
                typeEdit.setText("");
                amountEdit.setText("");
                Toast.makeText(getActivity(), "Transaction Added", Toast.LENGTH_LONG).show();
            }
        });
        receipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, new ReceiptFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }
}
