package mindmap.selestar.com.mindmap.fragments;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import mindmap.selestar.com.mindmap.NewElementListener;
import mindmap.selestar.com.mindmap.R;
import mindmap.selestar.com.mindmap.models.MapListElement;

/**
 * Created by ASTER-NOTUS on 05.11.2015.
 */
public class DialogNewElement extends DialogFragment
{
    private NewElementListener newElementListener;

    private Button dialog_btn_submit, dialog_btn_cancel;
    private EditText dialog_et_name, dialog_et_description;
    private TextInputLayout dialog_til_name, dialog_til_description;

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);

        newElementListener = (NewElementListener) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle)
    {
        View v = inflater.inflate(R.layout.dialog_new_element, null);


        dialog_et_name = (EditText) v.findViewById(R.id.dialog_et_name);
        dialog_til_name = (TextInputLayout) v.findViewById(R.id.dialog_til_name);
        dialog_til_name.setHint(getString(R.string.name));

        dialog_et_description = (EditText) v.findViewById(R.id.dialog_et_decription);
        dialog_til_description = (TextInputLayout) v.findViewById(R.id.dialog_til_decription);
        dialog_til_description.setHint(getString(R.string.hint_description));

        dialog_btn_submit = (Button) v.findViewById(R.id.dialog_btn_submit);
        dialog_btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newElementListener.addNewElement(new MapListElement(dialog_et_name.getText().toString(), dialog_et_description.getText().toString(), android.R.drawable.ic_dialog_map));
                dialog_et_name.setText("");
                dismiss();
            }
        });

        dialog_btn_cancel = (Button) v.findViewById(R.id.dialog_btn_cancel);
        dialog_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        getDialog().setTitle(getString(R.string.add_new_map));

        return v;
    }
}
