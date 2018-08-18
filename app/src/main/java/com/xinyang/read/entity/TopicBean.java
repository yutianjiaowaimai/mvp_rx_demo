package com.xinyang.read.entity;


import java.io.Serializable;
import java.util.List;

public class TopicBean implements Serializable{

    /**
     * id : 1BLdBQDBZVU
     * createdAt : 2018-08-17T09:35:43.837Z
     * nelData : {}
     * eventData : null
     * newsArray : []
     * order : 60951
     * publishDate : 2018-08-17T09:35:43.889Z
     * summary : @英雄联盟赛事发博称，他们之前已经收到了来自@iG电子竞技俱乐部递交的新选手注册的相关材料，这名选手自然就是昨日iG昨日才官宣过的“新选手”王思聪了 ... @英雄联盟赛事还在博文中强调了LPL注册选手的相关步骤和要求，其中就包括了经过个人信息验证后的个人帐号必须达到峡谷之巅钻石三或以上一条，有网友看到后惊讶道，王思聪居然有钻三 ... 最后@英雄联盟赛事表示目前正在对审核资料，如通过审核，新注册的选手将有资格代表俱乐部参加LPL的相关比赛。
     * title : 王思聪已开始注册 欲成《英雄联盟》职业选手
     * updatedAt : 2018-08-17T09:52:18.490Z
     * timeline : null
     * extra : {"instantView":true}
     */

    private String id;
    private String createdAt;
    private NelDataBean nelData;
    private Object eventData;
    private int order;
    private String publishDate;
    private String summary;
    private String title;
    private String updatedAt;
    private Object timeline;
    private ExtraBean extra;
    private List<TopicNewsBean> newsArray;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public NelDataBean getNelData() {
        return nelData;
    }

    public void setNelData(NelDataBean nelData) {
        this.nelData = nelData;
    }

    public Object getEventData() {
        return eventData;
    }

    public void setEventData(Object eventData) {
        this.eventData = eventData;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getTimeline() {
        return timeline;
    }

    public void setTimeline(Object timeline) {
        this.timeline = timeline;
    }

    public ExtraBean getExtra() {
        return extra;
    }

    public void setExtra(ExtraBean extra) {
        this.extra = extra;
    }

    public List<TopicNewsBean> getNewsArray() {
        return newsArray;
    }

    public void setNewsArray(List<TopicNewsBean> newsArray) {
        this.newsArray = newsArray;
    }

    public static class NelDataBean {

        /**
         * state : true
         * result : [{"weight":1,"inTitle":true,"nerName":"王思聪","entityId":424,"entityName":"王思聪","entityType":"person","entityUniqueId":"baike_57581"},{"weight":0.49920663237571716,"inTitle":true,"nerName":"《英雄联盟》","entityId":1247,"entityName":"英雄联盟","entityType":"product","fromAlgorithm":true,"entityUniqueId":"baike_4615671"}]
         */

        private boolean state;
        private List<ResultBean> result;

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class ResultBean {
            /**
             * weight : 1
             * inTitle : true
             * nerName : 王思聪
             * entityId : 424
             * entityName : 王思聪
             * entityType : person
             * entityUniqueId : baike_57581
             * fromAlgorithm : true
             */

            private double weight;
            private boolean inTitle;
            private String nerName;
            private int entityId;
            private String entityName;
            private String entityType;
            private String entityUniqueId;
            private boolean fromAlgorithm;

            public double getWeight() {
                return weight;
            }

            public void setWeight(double weight) {
                this.weight = weight;
            }

            public boolean isInTitle() {
                return inTitle;
            }

            public void setInTitle(boolean inTitle) {
                this.inTitle = inTitle;
            }

            public String getNerName() {
                return nerName;
            }

            public void setNerName(String nerName) {
                this.nerName = nerName;
            }

            public int getEntityId() {
                return entityId;
            }

            public void setEntityId(int entityId) {
                this.entityId = entityId;
            }

            public String getEntityName() {
                return entityName;
            }

            public void setEntityName(String entityName) {
                this.entityName = entityName;
            }

            public String getEntityType() {
                return entityType;
            }

            public void setEntityType(String entityType) {
                this.entityType = entityType;
            }

            public String getEntityUniqueId() {
                return entityUniqueId;
            }

            public void setEntityUniqueId(String entityUniqueId) {
                this.entityUniqueId = entityUniqueId;
            }

            public boolean isFromAlgorithm() {
                return fromAlgorithm;
            }

            public void setFromAlgorithm(boolean fromAlgorithm) {
                this.fromAlgorithm = fromAlgorithm;
            }
        }
    }

    public static class ExtraBean {
        /**
         * instantView : true
         */

        private boolean instantView;

        public boolean isInstantView() {
            return instantView;
        }

        public void setInstantView(boolean instantView) {
            this.instantView = instantView;
        }
    }
}
