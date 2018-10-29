package friendgoods.vidic.com.generalframework.activity.bean;

import java.util.List;

public class StoryModelBean  {

    /**
     * data : {"random":[124,343,742,968,1228,1707,1915,2335,2455,2708,3073,3586,3816,4150,4283,4550,4885,5134,5635,5822],"storyIMG":[{"img":"man1.png","width":131,"height":234},{"img":"man1.png","width":131,"height":234},{"img":"man1.png","width":131,"height":234}]}
     * message : 请求成功
     * state : 1
     */

    private DataBean data;
    private String message;
    private int state;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public static class DataBean {
        private List<Integer> random;
        private List<StoryIMGBean> storyIMG;

        public List<Integer> getRandom() {
            return random;
        }

        public void setRandom(List<Integer> random) {
            this.random = random;
        }

        public List<StoryIMGBean> getStoryIMG() {
            return storyIMG;
        }

        public void setStoryIMG(List<StoryIMGBean> storyIMG) {
            this.storyIMG = storyIMG;
        }

        public static class StoryIMGBean {
            /**
             * img : man1.png
             * width : 131
             * height : 234
             */

            private String img;
            private int width;
            private int height;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }
        }
    }
}
