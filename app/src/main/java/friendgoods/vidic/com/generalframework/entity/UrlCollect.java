package friendgoods.vidic.com.generalframework.entity;import android.os.Environment;import java.io.File;/** * Created by Administrator on 2016/10/12 0012. */public class UrlCollect {    public static final String HOST = "https://www.dt.pub";    //Log是否打印    public static final boolean isLog = true;    //图片Server    public static final String baseIamgeUrl = "http://doutui.oss-cn-beijing.aliyuncs.com/";    // 手机号注册    public static final String registers = HOST + "/shakeLeg/user/registers";    //  微信登录    public static final String register = HOST + "/shakeLeg/user/register";    //获取短信验证码    public static final String getSms = HOST + "/shakeLeg/user/getSms";    //手机验证码登录    public static final String smsLogen = HOST + "/shakeLeg/user/smsLogen";    // 绑定微信号    public static final String updateWeChat = HOST + "/shakeLeg/user/updateWeChat";    // 选择性别    public static final String updateSex = HOST + "/shakeLeg/user/updateSex";    // 好友列表    public static final String myFriends = HOST + "/shakeLeg/user/getFriend";    // 世界排行    public static final String worldRankings = HOST + "/shakeLeg/user/worldRankings";    // 个人世界排名  1（周排行榜）0  总排行    public static final String myWorldRankings = HOST + "/shakeLeg/user/myWorldRankings";    // 好友排行    public static final String getFriendp = HOST + "/shakeLeg/user/getFriendp";    // 粉丝列表    public static final String fansList = HOST + "/shakeLeg/user/getFans";    // 获取游戏历史记录    public static final String recordOfGame = HOST + "/shakeLeg/user/getRecord";    // 修改游戏设置    public static final String modifySettings = HOST + "/shakeLeg/user/updateSet";    //积分兑换礼物    public static final String convertGift = HOST + "/shakeLeg/user/convertGift";    // 查询可用积分    public static final String integrationAvailable = HOST + "/shakeLeg/user/getIntegral";    // 查询收到的礼物分数    public static final String getUserScore = HOST + "/shakeLeg/user/getUserScore";    public static final String addShare = HOST + "/shakeLeg/user/addShare";// 添加分享记录    public static final String updateSignDays = HOST + "/shakeLeg/user/updateSignDays";//签到    public static final String getSignGift = HOST + "/shakeLeg/admin/getSignGift";//七天签到礼物    public static final String getRecordTime = HOST + "/shakeLeg/user/getRecordTime";//最新签到日期    //////////////////////////////////////////////////////////////////////////////////////////////////////    // 送礼物    public static final String sendGift = HOST + "/shakeLeg/user/addSendGifts";    //个人公共墙    public static final String getPresentsWall = HOST + "/shakeLeg/user/getPresentsWall";    // 用户拥有礼物    public static final String myGifts = HOST + "/shakeLeg/user/getUserGift";    // 获取商品（礼物）列表    public static final String giftsListGift = HOST + "/shakeLeg/admin/getGift";    // 获取商品（推荐）列表    public static final String goodsListRecommmend = HOST + "/shakeLeg/admin/getGoods";    //添加地址    public static final String addAddress = HOST + "/shakeLeg/user/addAddress";    // 获取地址列表    public static final String addresses = HOST + "/shakeLeg/user/getAddress";    // 修改地址    public static final String modifyAddress = HOST + "/shakeLeg/user/updateAddress";    // 删除地址    public static final String deleteAddress = HOST + "/shakeLeg/user/deleteAddress";    // 设为默认地址    public static final String setForDefaultAddress = HOST + "/shakeLeg/user/updateStatus";    //修改礼物    public static final String updateGift = HOST + "/shakeLeg/admin/updateGift";    //保存礼物墙面    public static final String updatePresentsWall = HOST + "/shareLeg/user/updatePresentsWall";    //获取礼物    public static final String getGift = HOST + "/shakeLeg/user/getGift";    public static final String goodsDetail = HOST + "/shakeLeg/admin/getGoodsDetails"; //商品详情    public static final String addOrder = HOST + "/shakeLeg/user/addOrders";//添加订单    public static final String obtainOrderList = HOST + "/shakeLeg/user/getOrders"; //获取订单列表    public static final String orderDetail = HOST + "/shakeLeg/user/getOrdersDetails"; //订单详情    //修改订单状态    public static final String updateOrdersStatus = HOST + "/shakeLeg/admin/updateOrdersStatus";    //删除订单    public static final String delOrders = HOST + "/shakeLeg/user/delOrders";    //送礼物人的头像    public static final String getUserPhoto = HOST + "/shakeLeg/user/getUserPhoto";    //VIP礼物墙    public static final String vipWallReceived = HOST + "/shakeLeg/user/qSPresentsWall";    //查询送出的礼物墙VIP    public static final String vipWallSend = HOST + "/shakeLeg/user/qSPresentsWalls";    ////点击好友 进入礼物墙    public static final String wallOfFriend = HOST + "/shakeLeg/user/getSPresentWallall";    //    微信id    public static final String WXAppID = "wx880032906dd26b0f";    //    微信secret    public static final String WXsecret = "5e3c808cbda390fdbd6e8bf325e35956";    //    故事随机数    public static final String getStoryIMG = HOST + "/shakeLeg/user/getStoryIMG";    //    public static final String getChallengeMode = HOST + "/shakeLeg/admin/getChallengeMode";    //    public static final String getStoryModeSJ = HOST + "/shakeLeg/user/getStoryModeSJ";    //  增加游戏记录    public static final String addRecord = HOST + "/shakeLeg/user/addRecord";//    获取背景音乐    public static final String getMusic = HOST + "/shakeLeg/user/getMusic";   // 创建房间    public static final String addRoom = HOST + "/shakeLeg/user/addRoom";    //    查询pk排名    public static final String getUserPKRanking = HOST + "/shakeLeg/user/getUserPKRanking";    //    安卓微信openid获取用户信息    public static final String getUserByWeChatA = HOST + "/shakeLeg/user/getUserByWeChatA";//    public static final String inviteFriend = HOST + "/shakeLeg/user/inviteFriend";    //个人记录页面    public static final String getUserData = HOST + "/shakeLeg/user/getUserData";    //统一下单    public static final String prepay ="https://api.mch.weixin.qq.com/pay/unifiedorder";    public static final String wxpay ="";    public static final String getChallengeModeGift = HOST + "/shakeLeg/user/getChallengeModeGift";    public static final String addUserGift = HOST + "/shakeLeg/user/addUserGift";//商户号    public static final String mch_id  =  "1512369241";//    public static final String getPresentWallallScore  =  HOST + "/shakeLeg/user/getPresentWallallScore";    public static final String getIntegral =  HOST + "/shakeLeg/user/getIntegral";    public static final String getUsers =  HOST + "/shakeLeg/user/getUsers";    public static String IMAGEPATH=Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"trem";}