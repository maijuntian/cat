package com.healthmall.sail.cat_doctor.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.healthmall.sail.cat_doctor.MyApplication;
import com.healthmall.sail.cat_doctor.R;
import com.healthmall.sail.cat_doctor.activity.ExamineActivity;
import com.healthmall.sail.cat_doctor.base.BaseFragment;
import com.healthmall.sail.cat_doctor.base.MyThrowable;
import com.healthmall.sail.cat_doctor.bean.Question;
import com.healthmall.sail.cat_doctor.bean.Question2;
import com.healthmall.sail.cat_doctor.bean.QuestionReport;
import com.healthmall.sail.cat_doctor.delegate.QuestionDelegate;
import com.healthmall.sail.cat_doctor.delegate.QuestionDelegate2;
import com.healthmall.sail.cat_doctor.http.CatDoctorApi;
import com.healthmall.sail.cat_doctor.utils.CommonUtils;
import com.mai.xmai_fast_lib.basehttp.MParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.OnClick;
import butterknife.OnItemClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.functions.Action1;

/**
 * Created by mai on 2017/11/15.
 */
public class QuestionFragment2 extends BaseFragment<QuestionDelegate2> {

    private final String questionFemaleStr = "[{\"question\":\"您手脚发凉吗？\",\"questionAttrs\":[{\"type\":1,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您胃脘部、背部或腰膝部怕冷吗？\",\"questionAttrs\":[{\"type\":1,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您感到怕冷、衣服比别人穿得多吗？\",\"questionAttrs\":[{\"type\":1,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您比一般人耐受不了寒冷(冬天的寒冷，夏天的冷空调、电扇等)吗？\",\"questionAttrs\":[{\"type\":1,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您比别人容易患感冒吗？\",\"questionAttrs\":[{\"type\":1,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您吃(喝)凉的东西会感到不舒服或者怕吃(喝)凉的东西吗？\",\"questionAttrs\":[{\"type\":1,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您感到手脚心发热吗？\",\"questionAttrs\":[{\"type\":2,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您感觉身体、脸上发热吗？\",\"questionAttrs\":[{\"type\":2,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您皮肤或口唇干吗？\",\"questionAttrs\":[{\"type\":2,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您口唇的颜色比一般人红吗？\",\"questionAttrs\":[{\"type\":2,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您容易便秘或大便干燥吗？\",\"questionAttrs\":[{\"type\":2,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您面部两颧潮红或偏红吗？\",\"questionAttrs\":[{\"type\":2,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您感到眼睛干涩吗？\",\"questionAttrs\":[{\"type\":2,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您感到口干咽燥、总想喝水吗？\",\"questionAttrs\":[{\"type\":2,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您容易疲乏吗？\",\"questionAttrs\":[{\"type\":3,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您容易气短(呼吸短促，接不上气)吗？\",\"questionAttrs\":[{\"type\":3,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您容易心慌吗？\",\"questionAttrs\":[{\"type\":3,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您容易头晕或站起来晕眩吗？\",\"questionAttrs\":[{\"type\":3,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您比别人容易感冒吗？\",\"questionAttrs\":[{\"type\":3,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您喜欢安静、懒得说话吗？\",\"questionAttrs\":[{\"type\":3,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您说话声音低弱无力吗？\",\"questionAttrs\":[{\"type\":3,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您活动量稍大就容易出虚汗吗？\",\"questionAttrs\":[{\"type\":3,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您感到胸闷或腹部胀满吗？\",\"questionAttrs\":[{\"type\":4,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您感到身体沉重不轻松或不爽快吗？\",\"questionAttrs\":[{\"type\":4,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您腹部肥满松软吗？\",\"questionAttrs\":[{\"type\":4,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您有额部油脂分泌多的现象吗？\",\"questionAttrs\":[{\"type\":4,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您上眼睑比别人肿（上眼睑有轻微隆起的现象）吗？\",\"questionAttrs\":[{\"type\":4,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您嘴里有黏黏的感觉吗？\",\"questionAttrs\":[{\"type\":4,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"你平时痰多，特别是咽喉部总感到有痰堵着吗？\",\"questionAttrs\":[{\"type\":4,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您舌苔厚腻或有舌苔厚厚的感觉吗？\",\"questionAttrs\":[{\"type\":4,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您面部或鼻部有油腻感或者油亮发光吗？\",\"questionAttrs\":[{\"type\":5,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您易生痤疮或疮疖吗？\",\"questionAttrs\":[{\"type\":5,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您感到口苦或嘴里有异味吗？\",\"questionAttrs\":[{\"type\":5,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您大便粘滞不爽、有解不尽的感觉吗？\",\"questionAttrs\":[{\"type\":5,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您小便时尿道有发热感、尿色浓（深）吗？\",\"questionAttrs\":[{\"type\":5,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您带下色黄（白带颜色发黄）吗？(限女性回答)\",\"questionAttrs\":[{\"type\":5,\"isReverse\":1,\"sexLimit\":2}]},{\"question\":\"您的皮肤在不知不觉中会出现青瘀斑（皮下出血）吗？\",\"questionAttrs\":[{\"type\":6,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您两颧部有细微红丝吗？\",\"questionAttrs\":[{\"type\":6,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您身体上哪里疼痛吗？\",\"questionAttrs\":[{\"type\":6,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您面色晦暗或容易出现褐斑吗？\",\"questionAttrs\":[{\"type\":6,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您容易有黑眼圈吗？\",\"questionAttrs\":[{\"type\":6,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您容易忘事（健忘）吗？\",\"questionAttrs\":[{\"type\":6,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您口唇颜色偏黯吗？\",\"questionAttrs\":[{\"type\":6,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您没有感冒时也会打喷嚏吗？\",\"questionAttrs\":[{\"type\":7,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您没有感冒时也会鼻塞、流鼻涕吗？\",\"questionAttrs\":[{\"type\":7,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您有因季节变化、温度变化或异味等原因而咳喘的现象吗？\",\"questionAttrs\":[{\"type\":7,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您容易过敏（对药物、食物、气味、花粉或季节交替、气候变化时）吗？\",\"questionAttrs\":[{\"type\":7,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您的皮肤容易起荨麻疹（风团、风疹块、风疙瘩）吗？\",\"questionAttrs\":[{\"type\":7,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您的皮肤因过敏出现过紫癜(紫红色瘀点、瘀斑)吗？\",\"questionAttrs\":[{\"type\":7,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您的皮肤一抓就红，并出现抓痕吗？\",\"questionAttrs\":[{\"type\":7,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您感到闷闷不乐、情绪低沉吗？\",\"questionAttrs\":[{\"type\":8,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您容易精神紧张、焦虑不安吗？\",\"questionAttrs\":[{\"type\":8,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您多愁善感、感情脆弱吗？\",\"questionAttrs\":[{\"type\":8,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您容易感到害怕或受到惊吓吗？\",\"questionAttrs\":[{\"type\":8,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您的肋部或乳房胀痛吗？\",\"questionAttrs\":[{\"type\":8,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您无缘无故叹气吗？\",\"questionAttrs\":[{\"type\":8,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您咽喉部有异物感，且吐之不出、咽之不下吗?\",\"questionAttrs\":[{\"type\":8,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您精力充沛吗？\",\"questionAttrs\":[{\"type\":9,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您容易疲乏吗？\",\"questionAttrs\":[{\"type\":9,\"isReverse\":0,\"sexLimit\":0}]},{\"question\":\"您说话低弱无力吗？\",\"questionAttrs\":[{\"type\":9,\"isReverse\":0,\"sexLimit\":0}]},{\"question\":\"您感到闷闷不乐、情绪低沉吗？\",\"questionAttrs\":[{\"type\":9,\"isReverse\":0,\"sexLimit\":0}]},{\"question\":\"您比一般人耐受不了寒冷（冬天寒冷，夏天的冷空调、电扇等）吗？\",\"questionAttrs\":[{\"type\":9,\"isReverse\":0,\"sexLimit\":0}]},{\"question\":\"您能适应外界自然和社会环境的变化吗\",\"questionAttrs\":[{\"type\":9,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您容易失眠吗？\",\"questionAttrs\":[{\"type\":9,\"isReverse\":0,\"sexLimit\":0}]},{\"question\":\"您容易忘事（健忘）吗？\",\"questionAttrs\":[{\"type\":9,\"isReverse\":0,\"sexLimit\":0}]}]";

    private final String questionMaleStr = "[{\"question\":\"您手脚发凉吗？\",\"questionAttrs\":[{\"type\":1,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您胃脘部、背部或腰膝部怕冷吗？\",\"questionAttrs\":[{\"type\":1,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您感到怕冷、衣服比别人穿得多吗？\",\"questionAttrs\":[{\"type\":1,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您比一般人耐受不了寒冷(冬天的寒冷，夏天的冷空调、电扇等)吗？\",\"questionAttrs\":[{\"type\":1,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您比别人容易患感冒吗？\",\"questionAttrs\":[{\"type\":1,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您吃(喝)凉的东西会感到不舒服或者怕吃(喝)凉的东西吗？\",\"questionAttrs\":[{\"type\":1,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您感到手脚心发热吗？\",\"questionAttrs\":[{\"type\":2,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您感觉身体、脸上发热吗？\",\"questionAttrs\":[{\"type\":2,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您皮肤或口唇干吗？\",\"questionAttrs\":[{\"type\":2,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您口唇的颜色比一般人红吗？\",\"questionAttrs\":[{\"type\":2,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您容易便秘或大便干燥吗？\",\"questionAttrs\":[{\"type\":2,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您面部两颧潮红或偏红吗？\",\"questionAttrs\":[{\"type\":2,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您感到眼睛干涩吗？\",\"questionAttrs\":[{\"type\":2,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您感到口干咽燥、总想喝水吗？\",\"questionAttrs\":[{\"type\":2,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您容易疲乏吗？\",\"questionAttrs\":[{\"type\":3,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您容易气短(呼吸短促，接不上气)吗？\",\"questionAttrs\":[{\"type\":3,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您容易心慌吗？\",\"questionAttrs\":[{\"type\":3,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您容易头晕或站起来晕眩吗？\",\"questionAttrs\":[{\"type\":3,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您比别人容易感冒吗？\",\"questionAttrs\":[{\"type\":3,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您喜欢安静、懒得说话吗？\",\"questionAttrs\":[{\"type\":3,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您说话声音低弱无力吗？\",\"questionAttrs\":[{\"type\":3,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您活动量稍大就容易出虚汗吗？\",\"questionAttrs\":[{\"type\":3,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您感到胸闷或腹部胀满吗？\",\"questionAttrs\":[{\"type\":4,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您感到身体沉重不轻松或不爽快吗？\",\"questionAttrs\":[{\"type\":4,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您腹部肥满松软吗？\",\"questionAttrs\":[{\"type\":4,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您有额部油脂分泌多的现象吗？\",\"questionAttrs\":[{\"type\":4,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您上眼睑比别人肿（上眼睑有轻微隆起的现象）吗？\",\"questionAttrs\":[{\"type\":4,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您嘴里有黏黏的感觉吗？\",\"questionAttrs\":[{\"type\":4,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"你平时痰多，特别是咽喉部总感到有痰堵着吗？\",\"questionAttrs\":[{\"type\":4,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您舌苔厚腻或有舌苔厚厚的感觉吗？\",\"questionAttrs\":[{\"type\":4,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您面部或鼻部有油腻感或者油亮发光吗？\",\"questionAttrs\":[{\"type\":5,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您易生痤疮或疮疖吗？\",\"questionAttrs\":[{\"type\":5,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您感到口苦或嘴里有异味吗？\",\"questionAttrs\":[{\"type\":5,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您大便粘滞不爽、有解不尽的感觉吗？\",\"questionAttrs\":[{\"type\":5,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您小便时尿道有发热感、尿色浓（深）吗？\",\"questionAttrs\":[{\"type\":5,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您的阴囊部位潮湿吗？（限男性回答）\",\"questionAttrs\":[{\"type\":5,\"isReverse\":1,\"sexLimit\":1}]},{\"question\":\"您的皮肤在不知不觉中会出现青瘀斑（皮下出血）吗？\",\"questionAttrs\":[{\"type\":6,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您两颧部有细微红丝吗？\",\"questionAttrs\":[{\"type\":6,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您身体上哪里疼痛吗？\",\"questionAttrs\":[{\"type\":6,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您面色晦暗或容易出现褐斑吗？\",\"questionAttrs\":[{\"type\":6,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您容易有黑眼圈吗？\",\"questionAttrs\":[{\"type\":6,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您容易忘事（健忘）吗？\",\"questionAttrs\":[{\"type\":6,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您口唇颜色偏黯吗？\",\"questionAttrs\":[{\"type\":6,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您没有感冒时也会打喷嚏吗？\",\"questionAttrs\":[{\"type\":7,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您没有感冒时也会鼻塞、流鼻涕吗？\",\"questionAttrs\":[{\"type\":7,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您有因季节变化、温度变化或异味等原因而咳喘的现象吗？\",\"questionAttrs\":[{\"type\":7,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您容易过敏（对药物、食物、气味、花粉或季节交替、气候变化时）吗？\",\"questionAttrs\":[{\"type\":7,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您的皮肤容易起荨麻疹（风团、风疹块、风疙瘩）吗？\",\"questionAttrs\":[{\"type\":7,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您的皮肤因过敏出现过紫癜(紫红色瘀点、瘀斑)吗？\",\"questionAttrs\":[{\"type\":7,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您的皮肤一抓就红，并出现抓痕吗？\",\"questionAttrs\":[{\"type\":7,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您感到闷闷不乐、情绪低沉吗？\",\"questionAttrs\":[{\"type\":8,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您容易精神紧张、焦虑不安吗？\",\"questionAttrs\":[{\"type\":8,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您多愁善感、感情脆弱吗？\",\"questionAttrs\":[{\"type\":8,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您容易感到害怕或受到惊吓吗？\",\"questionAttrs\":[{\"type\":8,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您的肋部或乳房胀痛吗？\",\"questionAttrs\":[{\"type\":8,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您无缘无故叹气吗？\",\"questionAttrs\":[{\"type\":8,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您咽喉部有异物感，且吐之不出、咽之不下吗?\",\"questionAttrs\":[{\"type\":8,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您精力充沛吗？\",\"questionAttrs\":[{\"type\":9,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您容易疲乏吗？\",\"questionAttrs\":[{\"type\":9,\"isReverse\":0,\"sexLimit\":0}]},{\"question\":\"您说话低弱无力吗？\",\"questionAttrs\":[{\"type\":9,\"isReverse\":0,\"sexLimit\":0}]},{\"question\":\"您感到闷闷不乐、情绪低沉吗？\",\"questionAttrs\":[{\"type\":9,\"isReverse\":0,\"sexLimit\":0}]},{\"question\":\"您比一般人耐受不了寒冷（冬天寒冷，夏天的冷空调、电扇等）吗？\",\"questionAttrs\":[{\"type\":9,\"isReverse\":0,\"sexLimit\":0}]},{\"question\":\"您能适应外界自然和社会环境的变化吗\",\"questionAttrs\":[{\"type\":9,\"isReverse\":1,\"sexLimit\":0}]},{\"question\":\"您容易失眠吗？\",\"questionAttrs\":[{\"type\":9,\"isReverse\":0,\"sexLimit\":0}]},{\"question\":\"您容易忘事（健忘）吗？\",\"questionAttrs\":[{\"type\":9,\"isReverse\":0,\"sexLimit\":0}]}]";

    List<Question2> questionList = new ArrayList<>();

    QuestionReport currQuestionReport;

    private int currIndex;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        currQuestionReport = MyApplication.get().getCurrUserReport().getQuestionReport();

        start();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (hidden) {
            viewDelegate.hidePopWin();
        } else if (currQuestionReport.isFinish()) {
            viewDelegate.showReuslt(currQuestionReport, true);
        }
    }

    private void start() {
        if (currQuestionReport.isFinish()) {
            viewDelegate.showReuslt(currQuestionReport, true);
        } else {
            questionList = new Gson().fromJson(MyApplication.get().getCurrUser().getMemberSex() == 1 ? questionMaleStr : questionFemaleStr, new TypeToken<List<Question2>>() {
            }.getType());

            for (Question2 question2 : questionList) {
                List<Question2.Option> options = new ArrayList<>();
                if (question2.getQuestionAttrs().get(0).getIsReverse() == 1) { //不逆向
                    options.add(new Question2.Option("没有", false, 1));
                    options.add(new Question2.Option("很少", false, 2));
                    options.add(new Question2.Option("有时", false, 3));
                    options.add(new Question2.Option("经常", false, 4));
                    options.add(new Question2.Option("总是", false, 5));
                } else { //逆向
                    options.add(new Question2.Option("没有", false, 5));
                    options.add(new Question2.Option("很少", false, 4));
                    options.add(new Question2.Option("有时", false, 3));
                    options.add(new Question2.Option("经常", false, 2));
                    options.add(new Question2.Option("总是", false, 1));
                }
                question2.setOptionList(options);
            }
            getQuestion();
        }
    }

    private void commitQuestion() {


        try {
            Map<Integer, List<Integer>> scoreMap = new HashMap<>();

            for (Question2 question2 : questionList) {
                for (Question2.Option option : question2.getOptionList()) {
                    if (option.isSelect()) {
                        List<Integer> scores = scoreMap.get(question2.getQuestionAttrs().get(0).getType());
                        if (scores == null) {
                            scores = new ArrayList<>();
                            scoreMap.put(question2.getQuestionAttrs().get(0).getType(), scores);
                        }
                        scores.add(option.getScore());
                    }
                }
            }

            Map<Integer, Integer> changeScoreMap = new HashMap<>();

            for (Map.Entry<Integer, List<Integer>> entry : scoreMap.entrySet()) {
                changeScoreMap.put(entry.getKey(), changeScore(entry.getValue()));
            }

            Integer pinghe = changeScoreMap.remove(9); //平和质去掉

            Map.Entry<Integer, Integer> maxEntry = null;

            JSONArray jsonArray = new JSONArray();
            for (Map.Entry<Integer, Integer> entry : changeScoreMap.entrySet()) {

                if (entry.getValue() >= 40) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("rank", 1);
                    jsonObject.put("score", entry.getValue());
                    jsonObject.put("type", getResultName(entry.getKey()));
                    jsonArray.put(jsonObject);

                } else if (entry.getValue() >= 30) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("rank", 3);
                    jsonObject.put("score", entry.getValue());
                    jsonObject.put("type", getResultName(entry.getKey()));
                    jsonArray.put(jsonObject);
                }

                if (maxEntry == null)
                    maxEntry = entry;
                else if (entry.getValue() > maxEntry.getValue())
                    maxEntry = entry;
            }

            if (pinghe >= 60) {
                if (maxEntry.getValue() < 30) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("rank", 1);
                    jsonObject.put("score", pinghe);
                    jsonObject.put("type", "平和质");
                    jsonArray.put(jsonObject);
                } else if (maxEntry.getValue() < 40) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("rank", 2);
                    jsonObject.put("score", pinghe);
                    jsonObject.put("type", "平和质");
                    jsonArray.put(jsonObject);
                }
            }
//        1：阳虚质
//        2：阴虚质
//        3：气虚质
//        4：痰湿质
//        5：湿热质
//        6：血瘀质
//        7：特禀质
//        8：气郁质
//        9：平和质


//        1：是，2：基本是，3：倾向于


            if (maxEntry.getValue() >= 30) {
                currQuestionReport.setQuestionResultName(getResultName(maxEntry.getKey()));
            } else {
                currQuestionReport.setQuestionResultName("平和质");
            }


            viewDelegate.showReuslt(currQuestionReport, false);

            JSONObject param = new JSONObject();
            param.put("physical", jsonArray);
            CatDoctorApi.getInstance().physicalResult(RequestBody.create(MediaType.parse("application/json"), param.toString()), getActivity())
                    .subscribe(new Action1<Object>() {
                        @Override
                        public void call(Object o) {
                            log("上传成功...");
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getResultName(int type) {
        switch (type) {
            case 1:
                return "阳虚质";
            case 2:
                return "阴虚质";
            case 3:
                return "气虚质";
            case 4:
                return "痰湿质";
            case 5:
                return "湿热质";
            case 6:
                return "血瘀质";
            case 7:
                return "特禀质";
            case 8:
                return "气郁质";
            case 9:
                return "平和质";
        }
        return "";
    }

    private int changeScore(List<Integer> scores) {
        int account = 0;
        for (Integer score : scores) {
            account += score;
        }
        return (account - scores.size()) * 100 / (scores.size() * 4);
    }

    /**
     * 获取上一道题
     */
    private void getPreQuestion() {

        currIndex--;
        viewDelegate.initQuestion(questionList.get(currIndex), questionList.size(), currIndex);
    }

    /**
     * 获取下一道题
     */
    private void nextQuestion() {

        currIndex++;
        viewDelegate.initQuestion(questionList.get(currIndex), questionList.size(), currIndex);
    }

    /**
     * 获取第一道题
     */
    private void getQuestion() {
        currIndex = 0;
        viewDelegate.showQuestion();
        viewDelegate.initQuestion(questionList.get(currIndex), questionList.size(), currIndex);
    }

    /**
     * 获取问卷结果
     *
     * @param questionAnswerId
     */
    private void getResult(final String questionAnswerId) {
        CatDoctorApi.getInstance().questionResult(new MParams().add("questionAnswerId", questionAnswerId), getActivity()).subscribe(new Action1<QuestionReport>() {
            @Override
            public void call(QuestionReport questionReport) {
                questionReport.setQuestionAnswerId(questionAnswerId);

                MyApplication.get().getCurrUserReport().setQuestionReport(questionReport);
                currQuestionReport = MyApplication.get().getCurrUserReport().getQuestionReport();

                ((ExamineActivity) getActivity()).notifyMenu();
                viewDelegate.showReuslt(currQuestionReport, false);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    @OnItemClick(R.id.lv_answers)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        viewDelegate.setCheckQuestion(position);

        if (currIndex == (questionList.size() - 1)) {
            //最后一题
            commitQuestion();
        } else {
            nextQuestion();
        }
    }

    @OnClick(R.id.iv_left)
    public void tvLeftClick() {
        getPreQuestion();
    }

    public void reExamine() {

        currQuestionReport.setQuestionAnswerId("");
        currQuestionReport.setQuestionResultName("");
        ((ExamineActivity) getActivity()).notifyMenu();
        start();
    }

    @OnClick(R.id.tv_commit)
    public void tv_commitClick() {


       /* if (viewDelegate.etAnswer.length() == 0) {
            showToast("提交内容不能为空！");
            return;
        }

        if (viewDelegate.currQuestion.getSubjectDTO().getTotalSubjectNum() == viewDelegate.currQuestion.getSubjectDTO().getSubjectIndex()) {
            //最后一题
            commitQuestion(viewDelegate.currQuestion.getSubjectDTO().getSubjectId(), viewDelegate.currQuestion.getQuestionAnswerId(), viewDelegate.etAnswer.getText().toString());
        } else {
            nextQuestion(viewDelegate.currQuestion.getSubjectDTO().getSubjectId(), viewDelegate.currQuestion.getQuestionAnswerId(), viewDelegate.etAnswer.getText().toString());
        }*/
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {
            case R.id.tv_reexamine:
                reExamine();
                break;
            case R.id.tv_next:
                ((ExamineActivity) getActivity()).showNextExamine();
                break;
            case R.id.tv_report:
                ((ExamineActivity) getActivity()).showReport();
                break;
        }
    }

    @Override
    public void serialPortCallBack(String msg) {
        super.serialPortCallBack(msg);

        switch (msg) { //语音
            case "AT+ASRCXBS": //重新辨识
                if (viewDelegate.currStep == 2) {
                    reExamine();
                }
                return;
            case "AT+ASRCLXYX": //测量下一项
                if (viewDelegate.currStep == 2) {
                    ((ExamineActivity) getActivity()).showNextExamine();
                }
                return;
            case "AT+ASRSCBG": //生成报告
                if (viewDelegate.currStep == 2) {
                    ((ExamineActivity) getActivity()).showReport();
                }
                return;
        }
    }
}
