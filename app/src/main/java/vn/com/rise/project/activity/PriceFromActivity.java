package vn.com.rise.project.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import vn.com.rise.project.R;
import vn.com.rise.project.iohelper.IOHelper;
import vn.com.rise.project.model.InfoData;

/**
 * Created by hoanguyen on 3/5/16.
 */
public class PriceFromActivity extends BaseActivity implements View.OnClickListener {
    public static final int ERR_MISSING_NAME = 0;

    private EditText mEditTextName;
    private Spinner mSpinnerAge;
    private CheckBox mCheckBoxEng;
    private EditText mEditTextNameFa;
    private EditText mEditTextNameMo;
    private EditText mEditTextPhoneFa;
    private EditText mEditTextPhoneMo;
    private EditText mEditTextEmailFa;
    private EditText mEditTextEmailMo;
    private EditText mEditTextAddress;
    private EditText mEditTextHour;
    private TextView mTextViewDiscount;
    private TextView mTextViewDiscountTime;
    private TextView mTextViewDiscountBro;
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

        mSpinnerAge = (Spinner) findViewById(R.id.spinnerAge);
        mSpinnerTime = (Spinner) findViewById(R.id.spinnerDiscountTime);
        mSpinnerBro = (Spinner) findViewById(R.id.spinnerDiscountBro);

        mTextViewDiscount = (TextView) findViewById(R.id.txtDiscount);
        mTextViewDiscountTime = (TextView) findViewById(R.id.txtDiscountTime);
        mTextViewDiscountBro = (TextView) findViewById(R.id.txtDiscountBro);
        mTextViewTotal = (TextView) findViewById(R.id.txtTotal);

        mCheckBoxEng = (CheckBox) findViewById(R.id.checkboxEng);
        mCheckBoxBackpack = (CheckBox) findViewById(R.id.checkboxBackpack);
        mCheckBoxShirt = (CheckBox) findViewById(R.id.checkboxShirt);
        mCheckBoxVoucher = (CheckBox) findViewById(R.id.checkboxVoucher);

        mButtonCal = (Button) findViewById(R.id.buttonCal);
        mButtonSave = (Button) findViewById(R.id.buttonSave);

        mButtonCal.setOnClickListener(this);
        mButtonSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonCal:
                Toast.makeText(PriceFromActivity.this, "Cal click", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonSave:
                Toast.makeText(PriceFromActivity.this, "Save click", Toast.LENGTH_SHORT).show();
                InfoData data = new InfoData();
                data.setName(mEditTextName.getText().toString());
                IOHelper.instance().saveExcelFile(this,data);
                break;
        }
    }

    private boolean validateInput() {
        boolean isValid = true;

        return isValid;
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
