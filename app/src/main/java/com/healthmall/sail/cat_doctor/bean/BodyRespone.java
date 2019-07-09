package com.healthmall.sail.cat_doctor.bean;

import com.healthmall.sail.cat_doctor.MyApplication;
import com.healthmall.sail.cat_doctor.R;
import com.healthmall.sail.cat_doctor.utils.FloatUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mai on 2017/12/21.
 */
public class BodyRespone implements Serializable{


    /**
     * waistToHipRatioResult : 梨型
     * bodyFatRang : [8.82,10.584]
     * bodyFatResult : 肥胖
     * muscleWeightRang : [17.64,19.992]
     * muscleWeightResult : 偏高
     * totalMoistureRang : [31.5168,33.516]
     * totalMoistureResult : 偏胖
     * proteinRang : [9.408,11.76]
     * proteinResult : 偏高
     * bodyFatRateRang : [0.15,0.18]
     * bodyFatRateResult : 肥胖
     * muscleRateRang : [0.3,0.34]
     * muscleRateResult : 偏高
     * bodyMoistureRateRang : [0.536,0.57]
     * bodyMoistureRateResult : 偏胖
     * bmiRang : [18.5,23.9]
     * bmiResult : 正常
     * upperLimbBalance : 不均衡
     * lowerLimbBalance : 均衡
     * upperLimbDeveloped : 发达
     * lowerLimbDeveloped : 发达
     */

    private String waistToHipRatioResult;
    private String bodyFatResult;
    private String muscleWeightResult;
    private String totalMoistureResult;
    private String proteinResult;
    private String bodyFatRateResult;
    private String muscleRateResult;
    private String bodyMoistureRateResult;
    private String bmiResult;
    private String upperLimbBalance;
    private String lowerLimbBalance;
    private String upperLimbDeveloped;
    private String lowerLimbDeveloped;

    private List<Double> bodyFatRang;
    private List<Double> muscleWeightRang;
    private List<Double> totalMoistureRang;
    private List<Double> proteinRang;
    private List<Double> bodyFatRateRang;
    private List<Double> muscleRateRang;
    private List<Double> bodyMoistureRateRang;
    private List<Double> bmiRang;

    private List<Range> allBmiRange;
    private List<Range> allMoistureRateRange;
    private List<Range> allFatRateRange;
    private List<Range> allMuscleRateRange;

    List<Float> bmiRangeList = new ArrayList<>();
    List<Float> moistureRangeList = new ArrayList<>();
    List<Float> fatRangeList = new ArrayList<>();
    List<Float> muscleRangeList = new ArrayList<>();

    public BodyRespone(int sex, int age) {

        bmiRangeList.add(18.5f);
        bmiRangeList.add(24f);
        bmiRangeList.add(28f);
        bmiRangeList.add(40f);

        muscleRangeList.add(17f);
        muscleRangeList.add(29f);

        if (sex == 1){ //男的
            fatRangeList.add(15f);
            fatRangeList.add(18f);

            if(age > 30){
                moistureRangeList.add(48.4f);
                moistureRangeList.add(52.3f);
                moistureRangeList.add(55.7f);
                moistureRangeList.add(59.1f);
            } else {
                moistureRangeList.add(50.2f);
                moistureRangeList.add(53.6f);
                moistureRangeList.add(57.1f);
                moistureRangeList.add(60.5f);
            }
        } else { //女的
            fatRangeList.add(25f);
            fatRangeList.add(28f);

            if(age > 30){
                moistureRangeList.add(44.7f);
                moistureRangeList.add(48.1f);
                moistureRangeList.add(51.6f);
                moistureRangeList.add(55f);
            } else {
                moistureRangeList.add(46.1f);
                moistureRangeList.add(49.5f);
                moistureRangeList.add(53f);
                moistureRangeList.add(56.4f);
            }

        }
    }

    public String getWaistToHipRatioResult() {
        return waistToHipRatioResult;
    }

    public void setWaistToHipRatioResult(int sex, float range) {
        if (sex == 1) {
            if (range > 1) {
                this.waistToHipRatioResult = "苹果型";
            } else if (range >= 0.8) {
                this.waistToHipRatioResult = "正常";
            } else {
                this.waistToHipRatioResult = "梨型";
            }
        } else {
            if (range > 0.9) {
                this.waistToHipRatioResult = "苹果型";
            } else if (range >= 0.7) {
                this.waistToHipRatioResult = "正常";
            } else {
                this.waistToHipRatioResult = "梨型";
            }
        }
    }

    public String getBodyFatResult() {
        return bodyFatResult;
    }

    public void setBodyFatResult(String bodyFatResult) {
        this.bodyFatResult = bodyFatResult;
    }

    public String getMuscleWeightResult() {
        return muscleWeightResult;
    }

    public void setMuscleWeightResult(String muscleWeightResult) {
        this.muscleWeightResult = muscleWeightResult;
    }

    public String getTotalMoistureResult() {
        return totalMoistureResult;
    }

    public void setTotalMoistureResult(String totalMoistureResult) {
        this.totalMoistureResult = totalMoistureResult;
    }

    public String getProteinResult() {
        return proteinResult;
    }

    public void setProteinResult(String proteinResult) {
        this.proteinResult = proteinResult;
    }

    public String getBodyFatRateResult() {
        return bodyFatRateResult;
    }

    public void setBodyFatRateResult(String bodyFatRateResult) {
        this.bodyFatRateResult = bodyFatRateResult;
    }

    public String getMuscleRateResult() {
        return muscleRateResult;
    }

    public void setMuscleRateResult(String muscleRateResult) {
        this.muscleRateResult = muscleRateResult;
    }

    public String getBodyMoistureRateResult() {
        return bodyMoistureRateResult;
    }

    public void setBodyMoistureRateResult(String bodyMoistureRateResult) {
        this.bodyMoistureRateResult = bodyMoistureRateResult;
    }

    public String getBmiResult() {
        return bmiResult;
    }

    public void setBmiResult(String bmiResult) {
        this.bmiResult = bmiResult;
    }

    public String getUpperLimbBalance() {
        return upperLimbBalance;
    }

    public void setUpperLimbBalance(float leftUp, float rightUp) {
        if (Math.abs(leftUp - rightUp) > 10) {
            this.upperLimbBalance = "不均衡";
        } else {
            this.upperLimbBalance = "均衡";
        }
//        this.upperLimbBalance = upperLimbBalance;
    }

    public String getLowerLimbBalance() {
        return lowerLimbBalance;
    }

    public void setLowerLimbBalance(float leftLower, float rightLower) {
        if (Math.abs(leftLower - rightLower) > 10) {
            this.lowerLimbBalance = "不均衡";
        } else {
            this.lowerLimbBalance = "均衡";
        }
//        this.lowerLimbBalance = lowerLimbBalance;
    }

    public String getUpperLimbDeveloped() {
        return upperLimbDeveloped;
    }

    public void setUpperLimbDeveloped(float leftDev, float rightDev) {
        if (leftDev > 2.2 && rightDev > 2.2) {
            this.upperLimbDeveloped = "发达";
        } else if (leftDev < 1.8 && rightDev < 1.8) {
            this.upperLimbDeveloped = "不发达";
        } else {
            this.upperLimbDeveloped = "正常";
        }
    }

    public String getLowerLimbDeveloped() {
        return lowerLimbDeveloped;
    }

    public void setLowerLimbDeveloped(float leftDev, float rightDev) {
        this.lowerLimbDeveloped = lowerLimbDeveloped;
        if (leftDev > 8.0 && rightDev > 8.0) {
            this.lowerLimbDeveloped = "发达";
        } else if (leftDev < 6.6 && rightDev < 6.6) {
            this.lowerLimbDeveloped = "不发达";
        } else {
            this.lowerLimbDeveloped = "正常";
        }
    }

    public List<Double> getBodyFatRang() {
        return bodyFatRang;
    }

    public void setBodyFatRang(List<Double> bodyFatRang) {
        this.bodyFatRang = bodyFatRang;
    }

    public List<Double> getMuscleWeightRang() {
        return muscleWeightRang;
    }

    public void setMuscleWeightRang(List<Double> muscleWeightRang) {
        this.muscleWeightRang = muscleWeightRang;
    }

    public List<Double> getTotalMoistureRang() {
        return totalMoistureRang;
    }

    public void setTotalMoistureRang(List<Double> totalMoistureRang) {
        this.totalMoistureRang = totalMoistureRang;
    }

    public List<Double> getProteinRang() {
        return proteinRang;
    }

    public void setProteinRang(List<Double> proteinRang) {
        this.proteinRang = proteinRang;
    }

    public List<Double> getBodyFatRateRang() {
        return bodyFatRateRang;
    }

    public void setBodyFatRateRang(List<Double> bodyFatRateRang) {
        this.bodyFatRateRang = bodyFatRateRang;
    }

    public List<Double> getMuscleRateRang() {
        return muscleRateRang;
    }

    public void setMuscleRateRang(List<Double> muscleRateRang) {
        this.muscleRateRang = muscleRateRang;
    }

    public List<Double> getBodyMoistureRateRang() {
        return bodyMoistureRateRang;
    }

    public void setBodyMoistureRateRang(List<Double> bodyMoistureRateRang) {
        this.bodyMoistureRateRang = bodyMoistureRateRang;
    }

    public List<Double> getBmiRang() {
        return bmiRang;
    }

    public void setBmiRang(List<Double> bmiRang) {
        this.bmiRang = bmiRang;
    }


    public int getBodyTypeImg() {
        User user = MyApplication.get().getCurrUser();
        switch (waistToHipRatioResult) {
            case "苹果型":
                if (user.getMemberSex() == 1)
                    return R.mipmap.body_info5_apple_man;
                return R.mipmap.body_info5_apple_woman;
            case "梨型":
                if (user.getMemberSex() == 1)
                    return R.mipmap.body_info5_pear_man;
                return R.mipmap.body_info5_pear_woman;
            default:
                if (user.getMemberSex() == 1)
                    return R.mipmap.body_info5_nomal_man;
                return R.mipmap.body_info5_noman_woman;
        }
    }

    public int getReportTypeImg() {
        User user = MyApplication.get().getCurrUser();
        switch (waistToHipRatioResult) {
            case "苹果型":
                if (user.getMemberSex() == 1)
                    return R.mipmap.report_body_5_apple;
                return R.mipmap.report_body_5_apple_f;
            case "梨型":
                if (user.getMemberSex() == 1)
                    return R.mipmap.report_body_5_bear;
                return R.mipmap.report_body_5_bear_f;
            default:
                if (user.getMemberSex() == 1)
                    return R.mipmap.report_body_5;
                return R.mipmap.report_body_5_f;
        }
    }

    public List<Range> getAllBmiRange() {
        return allBmiRange;
    }

    public void setAllBmiRange(List<Range> allBmiRange) {
        this.allBmiRange = allBmiRange;
    }

    public List<Range> getAllMoistureRateRange() {
        return allMoistureRateRange;
    }

    public void setAllMoistureRateRange(List<Range> allMoistureRateRange) {
        this.allMoistureRateRange = allMoistureRateRange;
    }

    public List<Range> getAllFatRateRange() {
        return allFatRateRange;
    }

    public void setAllFatRateRange(List<Range> allFatRateRange) {
        this.allFatRateRange = allFatRateRange;
    }

    public List<Range> getAllMuscleRateRange() {
        return allMuscleRateRange;
    }

    public void setAllMuscleRateRange(List<Range> allMuscleRateRange) {
        this.allMuscleRateRange = allMuscleRateRange;
    }

    public List<Float> getFatRate() {
       /* List<Float> rates = new ArrayList<>();
        Float rate1 = allFatRateRange.get(1).getRange().get(0) * 100;
        Float rate2 = allFatRateRange.get(2).getRange().get(0) * 100;
        rates.add(rate1);
        rates.add(rate2);*/
        /*List<Float> rates = new ArrayList<>();
        rates.add(30f);
        rates.add(60f);*/
        return fatRangeList;
    }

    public List<Float> getMuscleRate() {
        /*List<Float> rates = new ArrayList<>();
        Float rate1 = allMuscleRateRange.get(1).getRange().get(0) * 100;
        Float rate2 = allMuscleRateRange.get(2).getRange().get(0) * 100;
        rates.add(rate1);
        rates.add(rate2);*/
        /*List<Float> rates = new ArrayList<>();
        rates.add(25f);
        rates.add(45f);*/
        return muscleRangeList;
    }

    public List<Float> getBmiRate() {
       /* List<Float> rates = new ArrayList<>();
        Float rate1 = allBmiRange.get(1).getRange().get(0);
        Float rate2 = allBmiRange.get(2).getRange().get(0);
        Float rate3 = allBmiRange.get(3).getRange().get(0);
        Float rate4 = allBmiRange.get(4).getRange().get(0);
        rates.add(rate1);
        rates.add(rate2);
        rates.add(rate3);
        rates.add(rate4);*/
        return bmiRangeList;
    }

    public List<Float> getWaterRate() {
        /*List<Float> rates = new ArrayList<>();
        Float rate1 = allMoistureRateRange.get(4).getRange().get(0) * 100;
        Float rate2 = allMoistureRateRange.get(3).getRange().get(0) * 100;
        Float rate3 = allMoistureRateRange.get(2).getRange().get(0) * 100;
        Float rate4 = allMoistureRateRange.get(1).getRange().get(0) * 100;
        rates.add(rate1);
        rates.add(rate2);
        rates.add(rate3);
        rates.add(rate4);*/
        return moistureRangeList;
    }

    public static class Range {
        /**
         * diagnosisName : 偏瘦
         * rangeGroup : [[0,18.4]]
         */

        private String diagnosisName;
        private List<List<Float>> rangeGroup;

        public String getDiagnosisName() {
            return diagnosisName;
        }

        public void setDiagnosisName(String diagnosisName) {
            this.diagnosisName = diagnosisName;
        }

        public List<List<Float>> getRangeGroup() {
            return rangeGroup;
        }

        public void setRangeGroup(List<List<Float>> rangeGroup) {
            this.rangeGroup = rangeGroup;
        }

        public List<Float> getRange() {
            return rangeGroup.get(0);
        }
    }

}
