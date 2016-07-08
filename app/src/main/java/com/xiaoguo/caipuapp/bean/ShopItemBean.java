package com.xiaoguo.caipuapp.bean;

import java.util.List;

/**
 * Created by lenovo on 2016/7/4.
 */
public class ShopItemBean {


    /**
     * cat_id : 292
     * name : 进口优选
     * short_name :
     * parent_id : 0
     * logo : http://www.hehe168.com/public/upload/images/201505/01/554328ef45dcc.jpg
     * cover_logo : http://www.hehe168.com/public/upload/images/201512/26/567e4a461d1a4.jpg
     * is_list : 0
     * subList : [{"cat_id":"292","name":"全部","short_name":"","logo":"http://www.hehe168.com/public/upload/images/201505/01/554328ef45dcc.jpg","parent_id":"0","cover_logo":"http://www.hehe168.com/public/upload/images/201512/26/567e4a461d1a4.jpg"},{"cat_id":"295","name":"进口零食","short_name":"","parent_id":"292","logo":"http://www.hehe168.com/public/upload/images/201504/02/551cea2b70e0c.jpg","is_list":"0"},{"cat_id":"298","name":"进口乳品","short_name":"","parent_id":"292","logo":"http://www.hehe168.com/public/upload/images/201508/23/55d960d5125a1.jpg","is_list":"0"},{"cat_id":"418","name":"进口生鲜","short_name":"jinkouchaye","parent_id":"292","logo":"http://www.hehe168.com/public/upload/images/201601/17/569af545b5dab.jpg","cover_logo":"http://www.hehe168.com/public/upload/images/201512/27/567f7fab9b6a8.png","is_list":"0"},{"cat_id":"297","name":"进口冲饮","short_name":"","parent_id":"292","logo":"http://www.hehe168.com/public/upload/images/201504/02/551cea3c730ad.jpg","is_list":"0"},{"cat_id":"294","name":"进口美酒","short_name":"","parent_id":"292","logo":"http://www.hehe168.com/public/upload/images/201512/18/56739f6b32f8c.jpg","is_list":"0"},{"cat_id":"293","name":"进口粮油","short_name":"","parent_id":"292","logo":"http://www.hehe168.com/public/upload/images/201504/02/551ce9dc03daa.jpg","is_list":"0"}]
     */

    private List<CatListBean> catList;

    public List<CatListBean> getCatList() {
        return catList;
    }

    public void setCatList(List<CatListBean> catList) {
        this.catList = catList;
    }

    public static class CatListBean {
        private String cat_id;
        private String name;
        private String short_name;
        private String parent_id;
        private String logo;
        private String cover_logo;
        private String is_list;
        /**
         * cat_id : 292
         * name : 全部
         * short_name :
         * logo : http://www.hehe168.com/public/upload/images/201505/01/554328ef45dcc.jpg
         * parent_id : 0
         * cover_logo : http://www.hehe168.com/public/upload/images/201512/26/567e4a461d1a4.jpg
         */

        private List<SubListBean> subList;

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShort_name() {
            return short_name;
        }

        public void setShort_name(String short_name) {
            this.short_name = short_name;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getCover_logo() {
            return cover_logo;
        }

        public void setCover_logo(String cover_logo) {
            this.cover_logo = cover_logo;
        }

        public String getIs_list() {
            return is_list;
        }

        public void setIs_list(String is_list) {
            this.is_list = is_list;
        }

        public List<SubListBean> getSubList() {
            return subList;
        }

        public void setSubList(List<SubListBean> subList) {
            this.subList = subList;
        }

        public static class SubListBean {
            private String cat_id;
            private String name;
            private String short_name;
            private String logo;
            private String parent_id;
            private String cover_logo;
            private List<ShareListBean> shareList;
            public String getCat_id() {
                return cat_id;
            }

            public void setCat_id(String cat_id) {
                this.cat_id = cat_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getShort_name() {
                return short_name;
            }

            public void setShort_name(String short_name) {
                this.short_name = short_name;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public String getCover_logo() {
                return cover_logo;
            }

            public void setCover_logo(String cover_logo) {
                this.cover_logo = cover_logo;
            }
            public List<ShareListBean> getShareList() {
                return shareList;
            }

            public void setShareList(List<ShareListBean> shareList) {
                this.shareList = shareList;
            }

            public static class ShareListBean {
                private String name;
                private String price;
                private int is_free_shipping;
                private String like;
                private String goods_url;
                private String source;
                private String icon;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public int getIs_free_shipping() {
                    return is_free_shipping;
                }

                public void setIs_free_shipping(int is_free_shipping) {
                    this.is_free_shipping = is_free_shipping;
                }

                public String getLike() {
                    return like;
                }

                public void setLike(String like) {
                    this.like = like;
                }

                public String getGoods_url() {
                    return goods_url;
                }

                public void setGoods_url(String goods_url) {
                    this.goods_url = goods_url;
                }

                public String getSource() {
                    return source;
                }

                public void setSource(String source) {
                    this.source = source;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }
            }
        }
    }
}
