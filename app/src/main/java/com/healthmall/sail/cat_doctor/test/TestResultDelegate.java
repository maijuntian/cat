package com.healthmall.sail.cat_doctor.test;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.healthmall.sail.cat_doctor.R;
import com.healthmall.sail.cat_doctor.bean.BodyReport;
import com.mai.xmai_fast_lib.baseadapter.BaseListViewAdapter;
import com.mai.xmai_fast_lib.baseadapter.BaseRecyclerViewAdapter;
import com.mai.xmai_fast_lib.baseadapter.BaseViewHolder;
import com.mai.xmai_fast_lib.baseadapter.BaseViewHolderImpl;
import com.mai.xmai_fast_lib.mvvm.view.AppDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by mai on 2018/10/25.
 */
public class TestResultDelegate extends AppDelegate {
    @Bind(R.id.rv_result)
    RecyclerView rvResult;

    List<TestResult> showList = new ArrayList<>();

    List<TestResult> results = new ArrayList<>();

    int page;

    final int size = 4;
    @Bind(R.id.tv_height)
    TextView tvHeight;
    @Bind(R.id.tv_weight)
    TextView tvWeight;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.tv_age)
    TextView tvAge;
    @Bind(R.id.bt_pre)
    Button btPre;
    @Bind(R.id.bt_next)
    Button btNext;

    BaseRecyclerViewAdapter<TestResult> adapter;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_test_result;
    }

    @Override
    public void initWidget() {
        super.initWidget();

        rvResult.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

    public void showResult(BodyReport bodyReport) {

        tvHeight.setText("身高：" + bodyReport.getBm_height() + "cm");
        tvWeight.setText("体重：" + bodyReport.getBm_weight() + "kg");
        tvAge.setText("年龄：" + bodyReport.getAge());
        tvSex.setText("性别：" + (bodyReport.getSex() == 0 ? "女" : "男"));


        results.clear();
        results.add(new TestResult("身体年龄", bodyReport.getBm_bf_ba(), ""));
        results.add(new TestResult("身体评分", bodyReport.getBm_bf_cs(), ""));
        results.add(new TestResult("身体水分", bodyReport.getBm_bf_tm(), bodyReport.getBm_bf_tmRange()));
        results.add(new TestResult("蛋白质", bodyReport.getBm_bf_protein(), ""));
        results.add(new TestResult("体脂重", bodyReport.getBm_bf_bf(), bodyReport.getBm_bf_bfRange()));
        results.add(new TestResult("肌肉重", bodyReport.getBm_bf_mw(), ""));
        results.add(new TestResult("骨骼肌重", bodyReport.getBm_bf_sm(), bodyReport.getBm_bf_smRange()));
        results.add(new TestResult("骨盐量", bodyReport.getBm_bf_bonysalts(), bodyReport.getBm_bf_bonysaltsRange()));

        results.add(new TestResult("体脂率", bodyReport.getBm_bf_bfr(), bodyReport.getBm_bf_bfrRange()));
        results.add(new TestResult("含水比率", bodyReport.getBm_bf_bmr(), bodyReport.getBm_bf_bmrRange()));
        results.add(new TestResult("躯干脂肪率", bodyReport.getBm_bf_tfr(), ""));
        results.add(new TestResult("右手脂肪率", bodyReport.getBm_bf_rhfr(), ""));
        results.add(new TestResult("左手脂肪率", bodyReport.getBm_bf_lhfr(), ""));
        results.add(new TestResult("右腿脂肪率", bodyReport.getBm_bf_rlfr(), ""));
        results.add(new TestResult("左脚脂肪率", bodyReport.getBm_bf_lffr(), ""));
        results.add(new TestResult("躯干肌肉量", bodyReport.getBm_bf_tmm(), ""));
        results.add(new TestResult("右手肌肉量", bodyReport.getBm_bf_rhmm(), ""));
        results.add(new TestResult("左手肌肉量", bodyReport.getBm_bf_lhmm(), ""));
        results.add(new TestResult("右腿肌肉量", bodyReport.getBm_bf_rlmm(), ""));
        results.add(new TestResult("左腿肌肉量", bodyReport.getBm_bf_llmm(), ""));
        results.add(new TestResult("标准肌肉", bodyReport.getBm_bf_sdm(), ""));
        results.add(new TestResult("BMI", bodyReport.getBm_bf_bmi(), "18.5kg/m2～23.9kg/m2"));
        results.add(new TestResult("基础代谢", bodyReport.getBm_bf_bm(), ""));
        results.add(new TestResult("标准体重", bodyReport.getBm_bf_sw(), ""));
        results.add(new TestResult("内脏脂肪等级", bodyReport.getBm_bf_vfl(), ""));
        results.add(new TestResult("肥胖度", bodyReport.getBm_bf_doo(), ""));
        results.add(new TestResult("肌肉控制", bodyReport.getBm_bf_mc(), ""));
        results.add(new TestResult("体重控制", bodyReport.getBm_bf_wc(), ""));
        results.add(new TestResult("脂肪控制", bodyReport.getBm_bf_fc(), ""));
        results.add(new TestResult("腰臀比", bodyReport.getBm_bf_rac(), bodyReport.getBm_bf_racRange()));

        showPage();
    }

    @OnClick(R.id.bt_pre)
    public void bt_preClick() {
        page--;
        showPage();
    }

    @OnClick(R.id.bt_next)
    public void bt_nextClick() {
        page++;
        showPage();
    }


    public void showPage() {

        showList.clear();

        int lastIndex = (page + 1) * size;
        if (lastIndex > results.size())
            lastIndex = results.size();

        showList.addAll(results.subList(page * size, lastIndex));

        if (adapter == null) {
            adapter = new BaseRecyclerViewAdapter<TestResult>(showList) {
                @Override
                protected int bindLayoutId(int position) {
                    return R.layout.item_result;
                }

                @Override
                protected void initView(TestResult data, BaseViewHolderImpl viewHolder) {

                    viewHolder.setText(R.id.tv_name, data.name)
                            .setText(R.id.tv_value, data.value)
                            .setText(R.id.tv_range, data.range);
                }

            };
            rvResult.setAdapter(adapter);

            View view = LayoutInflater.from(mContext).inflate(R.layout.item_result_top, null);
            adapter.addHeaderView(view);
        } else {
            adapter.notifyDataSetChanged();
        }


        if (page == 0)
            btPre.setVisibility(View.INVISIBLE);
        else
            btPre.setVisibility(View.VISIBLE);

        if (lastIndex == results.size())
            btNext.setVisibility(View.INVISIBLE);
        else
            btNext.setVisibility(View.VISIBLE);
    }
}
