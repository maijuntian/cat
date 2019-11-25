package com.healthmall.sail.cat_doctor.bean;

import java.util.List;

/**
 * Created by mai on 2019-10-07.
 */
public class Question2 {



    /**
     * question : 您容易失眠吗？
     * questionAttrs : [{"type":9,"isReverse":0,"sexLimit":0}]
     */

    private String question;
    private List<QuestionAttrs> questionAttrs;

    private List<Option> OptionList;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<QuestionAttrs> getQuestionAttrs() {
        return questionAttrs;
    }

    public void setQuestionAttrs(List<QuestionAttrs> questionAttrs) {
        this.questionAttrs = questionAttrs;
    }

    public List<Option> getOptionList() {
        return OptionList;
    }

    public void setOptionList(List<Option> optionList) {
        OptionList = optionList;
    }

    public static class QuestionAttrs {
        /**
         * type : 9
         * isReverse : 0
         * sexLimit : 0
         */

        private int type;
        private int isReverse;
        private int sexLimit;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getIsReverse() {
            return isReverse;
        }

        public void setIsReverse(int isReverse) {
            this.isReverse = isReverse;
        }

        public int getSexLimit() {
            return sexLimit;
        }

        public void setSexLimit(int sexLimit) {
            this.sexLimit = sexLimit;
        }
    }

    public static class Option {
        private String content;
        private boolean isSelect;
        private int score;

        public Option(String content, boolean isSelect, int score) {
            this.content = content;
            this.isSelect = isSelect;
            this.score = score;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        @Override
        public String toString() {
            return "Option{" +
                    "content='" + content + '\'' +
                    ", isSelect=" + isSelect +
                    ", score=" + score +
                    '}';
        }
    }
}
