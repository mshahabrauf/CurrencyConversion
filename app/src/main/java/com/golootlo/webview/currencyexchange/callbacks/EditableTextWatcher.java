package com.golootlo.webview.currencyexchange.callbacks;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * An extension of TextWatcher which stops further callbacks being called as 
 * a result of a change happening within the callbacks themselves.
 */
public abstract class EditableTextWatcher implements TextWatcher {

    private boolean editing;

    @Override
    public final void beforeTextChanged(CharSequence s, int start,
                                        int count, int after) {
        // No usage
    }


    @Override
    public final void onTextChanged(CharSequence s, int start,
                                    int before, int count) {
        // No usage
    }

    @Override
    public final void afterTextChanged(Editable s) {
        if (editing)
            return;

        editing = true;
        try {
            afterTextChange(s);
        } finally {
            editing = false;
        }
    }

    public boolean isEditing() {
        return editing;
    }

    protected abstract void afterTextChange(Editable s);
}
