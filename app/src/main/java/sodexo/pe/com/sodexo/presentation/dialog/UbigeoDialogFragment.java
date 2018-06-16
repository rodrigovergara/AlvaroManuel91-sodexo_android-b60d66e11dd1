package sodexo.pe.com.sodexo.presentation.dialog;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.data.datasource.assets.UbigeoDataStore;
import sodexo.pe.com.sodexo.data.model.UbigeoEntityData;
import sodexo.pe.com.sodexo.presentation.adapter.UbigeoAdapter;
import sodexo.pe.com.sodexo.presentation.interfaces.MainFView;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;

public class UbigeoDialogFragment extends DialogFragment {


    @BindView(R.id.sp_department)
    Spinner spDepartment;
    @BindView(R.id.sp_provincie)
    Spinner spProvincie;
    @BindView(R.id.sp_district)
    Spinner spDistrict;
    private UbigeoEntityData ubigeoData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ubigeo_dialog, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return dialog;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        populateDepartment();
    }

    private void populateDepartment() {
        UbigeoAdapter adapter = new UbigeoAdapter(getActivity());
        adapter.addUbigeo(UbigeoDataStore.getAllDepartments());
        spDepartment.setAdapter(adapter);
        spDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                populateProvince(UbigeoDataStore.getAllDepartments().get(i).getDepartment());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void populateProvince(final String department) {
        UbigeoAdapter adapter = new UbigeoAdapter(getActivity());
        adapter.addUbigeo(UbigeoDataStore.getAllProvinces(department));
        spProvincie.setAdapter(adapter);
        spProvincie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                populateDistrict(department, UbigeoDataStore.getAllProvinces(department).get(i).getProvince());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void populateDistrict(final String department, final String province) {
        UbigeoAdapter adapter = new UbigeoAdapter(getActivity());
        adapter.addUbigeo(UbigeoDataStore.getAllDistricts(department, province));
        spDistrict.setAdapter(adapter);
        spDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ubigeoData = UbigeoDataStore.getAllDistricts(department, province).get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    @OnClick(R.id.btn_search)
    public void search() {
        Intent intent = new Intent();
        intent.putExtra("ubigeo", ubigeoData);
        getTargetFragment().onActivityResult(
                getTargetRequestCode(), Activity.RESULT_OK, intent);
        dismissAllowingStateLoss();
    }
}
