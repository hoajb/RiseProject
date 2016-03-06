package vn.com.rise.project.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import vn.com.rise.project.R;
import vn.com.rise.project.Utils.Constants;
import vn.com.rise.project.iohelper.IOHelper;
import vn.com.rise.project.model.InfoData;

/**
 * Created by hoanguyen on 3/5/16.
 */
public class PriceFromActivity extends BaseActivity implements View.OnClickListener {
    public static final int ERR_MISSING_NAME = 0;

    private EditText mEditTextName;
    private Spinner mSpinnerAge;
    private CheckBox mCheckBoxEngYes;
    private CheckBox mCheckBoxEngNo;
    private EditText mEditTextNameFa;
    private EditText mEditTextNameMo;
    private EditText mEditTextPhoneFa;
    private EditText mEditTextPhoneMo;
    private EditText mEditTextEmailFa;
    private EditText mEditTextEmailMo;
    private EditText mEditTextAddress;
    private EditText mEditTextHour;

    private EditText mEditTextMoneyPerHour;
    private EditText mEditTextDiscount;
    private EditText mEditTextDiscountTime;
    private EditText mEditTextDiscountBro;
    //    private TextView mTextViewDiscount;
    //    private TextView mTextViewDiscountTime;
//    private TextView mTextViewDiscountBro;
    private TextView mTextViewErrorAge;

    private Spinner mSpinnerTime;
    private Spinner mSpinnerBro;
    private TextView mTextViewTotal;
    private Button mButtonCal;
    private Button mButtonSave;
    private CheckBox mCheckBoxBackpack;
    private CheckBox mCheckBoxShirt;
    private CheckBox mCheckBoxVoucher;
    private EditText mEditTextOther;
    private EditText mEditTextNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_form);
        initView();
        setData();
    }

    private void initView() {
        mEditTextName = (EditText) findViewById(R.id.edtName);
        mEditTextNameFa = (EditText) findViewById(R.id.edtNameFa);
        mEditTextNameMo = (EditText) findViewById(R.id.edtNameMo);
        mEditTextPhoneFa = (EditText) findViewById(R.id.edtPhoneFa);
        mEditTextPhoneMo = (EditText) findViewById(R.id.edtPhoneMo);
        mEditTextEmailFa = (EditText) findViewById(R.id.edtEmailFa);
        mEditTextEmailMo = (EditText) findViewById(R.id.edtEmailMo);
        mEditTextAddress = (EditText) findViewById(R.id.edtAddress);
        mEditTextHour = (EditText) findViewById(R.id.edtHour);
        mEditTextOther = (EditText) findViewById(R.id.edtOther);
        mEditTextNote = (EditText) findViewById(R.id.edtNote);

        mEditTextMoneyPerHour = (EditText) findViewById(R.id.edtPricePerHour);
        mEditTextDiscount = (EditText) findViewById(R.id.edtDiscount);
        mEditTextDiscountTime = (EditText) findViewById(R.id.edtDiscountTime);
        mEditTextDiscountBro = (EditText) findViewById(R.id.edtDiscountBro);

        mSpinnerAge = (Spinner) findViewById(R.id.spinnerAge);
        mSpinnerTime = (Spinner) findViewById(R.id.spinnerDiscountTime);
        mSpinnerBro = (Spinner) findViewById(R.id.spinnerDiscountBro);

//        mTextViewDiscount = (TextView) findViewById(R.id.txtDiscount);
//        mTextViewDiscountTime = (TextView) findViewById(R.id.txtDiscountTime);
//        mTextViewDiscountBro = (TextView) findViewById(R.id.txtDiscountBro);
        mTextViewErrorAge = (TextView) findViewById(R.id.errorAge);
        mTextViewTotal = (TextView) findViewById(R.id.txtTotal);

        mCheckBoxEngYes = (CheckBox) findViewById(R.id.checkboxEngYes);
        mCheckBoxEngNo = (CheckBox) findViewById(R.id.checkboxEngNo);
        mCheckBoxBackpack = (CheckBox) findViewById(R.id.checkboxBackpack);
        mCheckBoxShirt = (CheckBox) findViewById(R.id.checkboxShirt);
        mCheckBoxVoucher = (CheckBox) findViewById(R.id.checkboxVoucher);

        mButtonCal = (Button) findViewById(R.id.buttonCal);
        mButtonSave = (Button) findViewById(R.id.buttonSave);

        mButtonCal.setOnClickListener(this);
        mButtonSave.setOnClickListener(this);

        mCheckBoxEngYes.setOnCheckedChangeListener(mCheckedChangeListener);
        mCheckBoxEngNo.setOnCheckedChangeListener(mCheckedChangeListener);
    }

    private void setData() {
        ArrayAdapter<Integer> ageAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Constants.getListAge());
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerAge.setAdapter(ageAdapter);

        ArrayAdapter<String> disTimeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Constants.getListDiscountTime(this));
        disTimeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerTime.setAdapter(disTimeAdapter);
        mSpinnerTime.setSelection(0);

        ArrayAdapter<String> disBroAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Constants.getListDiscountBro(this));
        disBroAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerBro.setAdapter(disBroAdapter);
        mSpinnerTime.setSelection(0);
    }

    private CompoundButton.OnCheckedChangeListener mCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (buttonView.getId() == R.id.checkboxEngYes) {
                mCheckBoxEngNo.setChecked(!isChecked);
            } else {
                mCheckBoxEngYes.setChecked(!isChecked);
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonCal:
                int hour = 0;
                try {
                    hour = Integer.valueOf(mEditTextHour.getText().toString());
                } catch (NumberFormatException e) {
                    mEditTextHour.setError("");
                    return;
                }

                int moneyPerHour = 0;
                try {
                    moneyPerHour = Integer.valueOf(mEditTextMoneyPerHour.getText().toString());
                } catch (NumberFormatException e) {
                    mEditTextMoneyPerHour.setError("");
                    return;
                }


                int discount = 0;
                try {
                    discount = Integer.valueOf(mEditTextDiscount.getText().toString());
                } catch (NumberFormatException e) {
//                    mEditTextDiscount.setError("");
                }


                float discountTime = 0;
                try {
                    discountTime = Integer.valueOf(mEditTextDiscountTime.getText().toString());
                } catch (NumberFormatException e) {
//                    mEditTextDiscountTime.setError("");
                }

                float discountBro = 0;
                try {
                    discountBro = Integer.valueOf(mEditTextDiscountBro.getText().toString());
                } catch (NumberFormatException e) {
//                    mEditTextDiscountBro.setError("");
                }

                int total = (int) ((hour * moneyPerHour - discount) * (1 - discountTime / 100) * (1 - discountBro / 100));

                mTextViewTotal.setText(String.valueOf(total));
                mTextViewTotal.setError(null);
                Toast.makeText(PriceFromActivity.this, "Calculating...", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonSave:
                if (!validateInput()) {
                    return;
                }
                Toast.makeText(PriceFromActivity.this, "Saving", Toast.LENGTH_SHORT).show();
                InfoData data = new InfoData();
                data.setName(mEditTextName.getText().toString());
                data.setAge((Integer.valueOf((String) mSpinnerAge.getSelectedItem())));
                data.setNameFa(mEditTextNameFa.getText().toString());
                data.setPhoneFa(mEditTextPhoneFa.getText().toString());
                data.setEmailFa(mEditTextEmailFa.getText().toString());
                data.setNameMo(mEditTextNameMo.getText().toString());
                data.setPhoneMo(mEditTextPhoneMo.getText().toString());
                data.setEmailMo(mEditTextEmailMo.getText().toString());
                data.setAddress(mEditTextAddress.getText().toString());

                try {
                    data.setHour(Integer.valueOf(mEditTextHour.getText().toString()));
                } catch (NumberFormatException e) {
                    data.setHour(0);
                }

                try {
                    data.setMoneyPerHour(Integer.valueOf(mEditTextMoneyPerHour.getText().toString()));
                } catch (NumberFormatException e) {
                    data.setMoneyPerHour(0);
                }

                try {
                    data.setDiscount(Integer.valueOf(mEditTextDiscount.getText().toString()));
                } catch (NumberFormatException e) {
                    data.setDiscount(0);
                }

                data.setDiscountTimeLabel((String) mSpinnerTime.getSelectedItem());
                data.setDiscountBroLabel((String) mSpinnerBro.getSelectedItem());
                try {
                    data.setDiscountTime(Integer.valueOf(mEditTextDiscountTime.getText().toString()));
                } catch (NumberFormatException e) {
                    data.setDiscountTime(0);
                }

                try {
                    data.setDiscountBro(Integer.valueOf(mEditTextDiscountBro.getText().toString()));
                } catch (NumberFormatException e) {
                    data.setDiscountBro(0);
                }

                try {
                    data.setTotal(Integer.valueOf(mTextViewTotal.getText().toString()));
                } catch (NumberFormatException e) {
                    data.setTotal(0);
                }

                data.setIsStudyEng(mCheckBoxEngYes.isChecked());
                data.setGiftBackpack(mCheckBoxBackpack.isChecked());
                data.setGiftShirt(mCheckBoxShirt.isChecked());
                data.setGiftVoucher(mCheckBoxVoucher.isChecked());

                data.setOther(mEditTextOther.getText().toString());
                data.setNote(mEditTextNote.getText().toString());


                IOHelper.instance().saveExcelFile(this, data);

                break;
        }
    }

    private boolean validateInput() {
        if (TextUtils.isEmpty(mEditTextName.getText())) {
            mEditTextName.setError("");
            mEditTextName.requestFocus();
            return false;
        }
        mEditTextName.setError(null);

        if (mSpinnerAge.getSelectedItemPosition() < 0) {
            mTextViewErrorAge.setError("");
            mTextViewErrorAge.requestFocus();
        }

        mTextViewErrorAge.setError(null);

        if (TextUtils.isEmpty(mEditTextNameFa.getText())) {
            mEditTextNameFa.setError("");
            mEditTextNameFa.requestFocus();
            return false;
        }
        mEditTextNameFa.setError(null);

        if (TextUtils.isEmpty(mEditTextPhoneFa.getText())) {
            mEditTextPhoneFa.setError("");
            mEditTextPhoneFa.requestFocus();
            return false;
        }
        mEditTextPhoneFa.setError(null);

        if (TextUtils.isEmpty(mEditTextEmailFa.getText())) {
            mEditTextEmailFa.setError("");
            mEditTextEmailFa.requestFocus();
            return false;
        }
        mEditTextEmailFa.setError(null);

        if (TextUtils.isEmpty(mEditTextNameMo.getText())) {
            mEditTextNameMo.setError("");
            mEditTextNameMo.requestFocus();
            return false;
        }
        mEditTextNameMo.setError(null);

        if (TextUtils.isEmpty(mEditTextPhoneMo.getText())) {
            mEditTextPhoneMo.setError("");
            mEditTextPhoneMo.requestFocus();
            return false;
        }
        mEditTextPhoneMo.setError(null);

        if (TextUtils.isEmpty(mEditTextEmailMo.getText())) {
            mEditTextEmailMo.setError("");
            mEditTextEmailMo.requestFocus();
            return false;
        }
        mEditTextEmailMo.setError(null);

        if (TextUtils.isEmpty(mEditTextAddress.getText())) {
            mEditTextAddress.setError("");
            mEditTextAddress.requestFocus();
            return false;
        }
        mEditTextAddress.setError(null);

        //----
        if (TextUtils.isEmpty(mEditTextHour.getText())) {
            mEditTextHour.setError("");
            mEditTextHour.requestFocus();
            return false;
        }
        mEditTextHour.setError(null);

        if (TextUtils.isEmpty(mEditTextMoneyPerHour.getText())) {
            mEditTextMoneyPerHour.setError("");
            mEditTextMoneyPerHour.requestFocus();
            return false;
        }
        mEditTextMoneyPerHour.setError(null);

        if (TextUtils.isEmpty(mTextViewTotal.getText())) {
            mTextViewTotal.setError("");
            mTextViewTotal.requestFocus();
            return false;
        }
        mTextViewTotal.setError(null);

        return true;
    }

//    private void showDialogWarning(int type) {
//        new MaterialDialog.Builder(this)
//                .title(R.string.title)
//                .content(R.string.content)
//                .positiveText(R.string.agree)
//                .negativeText(R.string.disagree)
//                .show();
//    }

//    new MaterialDialog.Builder(this)
//            .title(R.string.title)
//    .content(R.string.content)
//    .positiveText(R.string.agree)
//    .negativeText(R.string.disagree)
//    .show();
}
