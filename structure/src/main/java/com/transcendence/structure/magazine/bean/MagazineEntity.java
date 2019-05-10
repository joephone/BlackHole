package com.transcendence.structure.magazine.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ziyu on 2016/10/24.
 */

public class MagazineEntity implements Serializable {


    /**
     * has_next : 1
     * articles : [{"author":{"username":"苏潜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/a6743869c424b81b9a7faf5c283203db_225x226.jpg","id":44171,"sign":"设计故纸堆里的考古学徒"},"title":"我想，我是忘记了长大","sub_title":"长不大的设计师，做出了俏皮的童话首饰","favor_user_num":13,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/17/462f6a13-b875-4958-9602-d0dcf1832b90_800x800.jpeg","publish_at":1477065600000,"id":91},{"author":{"username":"黎曦","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/068b70d77304e910f9bbfcc732e2d774_463x450.jpg","id":46473,"sign":"贪恋一抹胭脂香气的败家子"},"title":"为动物的自由而战","sub_title":"素食主义者的设计之路","favor_user_num":9,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/17/2f720e4b-98bf-4222-a069-66fad8007d99_809x809.jpeg","publish_at":1476979200000,"id":90},{"author":{"username":"鱼烬","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/24/cb72799c716b347da480261217298b56_224x224.jpg","id":44168,"sign":"能在烤鱼身上研究烟熏妆的时尚骨头"},"title":"当你不再试图从一个包中确定自己","sub_title":"KikaNY：还原一个包最应该有的简单状态","favor_user_num":10,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/17/7ac855a9-5680-4368-8ded-68cccf1bd2e3_800x800.jpeg","publish_at":1476892800000,"id":89},{"author":{"username":"Phoebe","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/09/19/bda1c2ac9234397c10982618f55562fe_199x200.jpg","id":36075,"sign":"在设计的世界撒欢儿的野马"},"title":"为阿尔法女度身定制的时尚","sub_title":"时尚是每个女人的事， 即使你选择同你的事业结婚","favor_user_num":7,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/17/30f07a18-c4d3-434f-9d90-8833f7f86f94_505x505.jpeg","publish_at":1476720000000,"id":88},{"author":{"username":"狐萝卜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/d17e6a982c677b1e4b35726af80d6a50_254x255.jpg","id":30,"sign":"在光怪陆离的世界里记录时尚的梦旅人"},"title":"领带的文艺时代","sub_title":"在别人正襟危坐时，我偏要玩世不恭","favor_user_num":23,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/13/74bea20e-c2f8-4031-9fcf-6eb7d304eef5_800x800.jpeg","publish_at":1476460800000,"id":86},{"author":{"username":"鱼烬","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/24/cb72799c716b347da480261217298b56_224x224.jpg","id":44168,"sign":"能在烤鱼身上研究烟熏妆的时尚骨头"},"title":"从小在祖父的地毯上捡黄金的女孩，用金子去描绘山峦温柔的曲线","sub_title":"她把爬过的山，都做成了戒指","favor_user_num":18,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/12/ecae22a0-cf65-4843-b6a5-1ff50049294a_750x750.jpeg","publish_at":1476288000000,"id":87},{"author":{"username":"Phoebe","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/09/19/bda1c2ac9234397c10982618f55562fe_199x200.jpg","id":36075,"sign":"在设计的世界撒欢儿的野马"},"title":"Donatella Versace：她为大牌续写着神话","sub_title":"她披上了时尚的铠甲，并让全世界都记住了她","favor_user_num":12,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/10/99162390-bb9a-41b9-86b5-9d13c5cff320_800x602.jpeg","publish_at":1476115200000,"id":85},{"author":{"username":"夏漱香菜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/5a069cd6278626c9a638bf46a8010837_450x450.jpg","id":41625,"sign":"迷失风向却沉浸在时尚海洋里的游鱼"},"title":"不愿涂脂抹粉，只愿扎根泥土","sub_title":"把朴素的生活过成一幅画","favor_user_num":38,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/3/babb2f3b-d5f1-491b-9019-a7da864cd788_800x800.jpeg","publish_at":1475856000000,"id":84},{"author":{"username":"川枝","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/e49c5fa883f8475d55523b73e141a4e8_300x300.jpg","id":41383,"sign":"梦游也在和设计师唠嗑的拜访者 Z"},"title":"送给你，这一季的忧郁和花香","sub_title":"把这个夏天的潮热和焦灼，风干在玻璃球里","favor_user_num":72,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/3/fc60867b-ac3e-4928-a7a1-640c290cf091_570x380.jpeg","publish_at":1475683200000,"id":83},{"author":{"username":"鱼烬","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/24/cb72799c716b347da480261217298b56_224x224.jpg","id":44168,"sign":"能在烤鱼身上研究烟熏妆的时尚骨头"},"title":"一个男孩要走多少路，才能称得上是一个男人","sub_title":"在这个女性化的年代，找寻男子汉失去的荣光","favor_user_num":34,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/3/bb4a7e22-8a29-4943-a3de-504a1e55b660_800x800.jpeg","publish_at":1475510400000,"id":82},{"author":{"username":"苏潜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/a6743869c424b81b9a7faf5c283203db_225x226.jpg","id":44171,"sign":"设计故纸堆里的考古学徒"},"title":"文学少女、珠宝设计和女巫的魔法首饰","sub_title":"她把「冰与火」和「五十度灰」，都做成了首饰","favor_user_num":75,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/9/29/d0e52773-ad0e-40a6-a6bc-4f61cca9514b_800x800.jpeg","publish_at":1475251200000,"id":81},{"author":{"username":"Mana Bean","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/9/24/ac99fa28-3e08-4067-b618-b9d63c0df7a6.jpg","id":16653,"sign":"一颗从金融学土壤中萌发的时尚之豆"},"title":"让光影自由起舞","sub_title":"建筑师的跨界设计之旅","favor_user_num":53,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/9/28/8dfdc050-e5e4-44ac-a287-b47eefa6ea6b_667x667.jpeg","publish_at":1475078400000,"id":80},{"author":{"username":"狐萝卜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/d17e6a982c677b1e4b35726af80d6a50_254x255.jpg","id":30,"sign":"在光怪陆离的世界里记录时尚的梦旅人"},"title":"新时代的左轮手枪，用细腻柔情直击你的心脏","sub_title":"你愿意和这样的男人去探险吗","favor_user_num":25,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/9/21/2658da4c-67a7-48c8-8c20-3da602b90bd7_800x800.jpeg","publish_at":1474905600000,"id":77},{"author":{"username":"川枝","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/e49c5fa883f8475d55523b73e141a4e8_300x300.jpg","id":41383,"sign":"梦游也在和设计师唠嗑的拜访者 Z"},"title":"让黑暗世界的人们，触摸到时间的流逝","sub_title":"设计的终极关怀是让所有人都更有尊严地生活","favor_user_num":61,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/9/21/8c185195-3317-4ee0-97f6-18d3e1df22dc_800x800.jpeg","publish_at":1474646400000,"id":79},{"author":{"username":"Phoebe","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/09/19/bda1c2ac9234397c10982618f55562fe_199x200.jpg","id":36075,"sign":"在设计的世界撒欢儿的野马"},"title":"Jonathan Anderson：这个小哥有着开了挂的人生","sub_title":"挑战无限，乐趣无穷","favor_user_num":15,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/9/21/15a6b49f-e17d-4ab4-a387-e45e4af3977d_548x548.jpeg","publish_at":1474387200000,"id":78},{"author":{"username":"Phoebe","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/09/19/bda1c2ac9234397c10982618f55562fe_199x200.jpg","id":36075,"sign":"在设计的世界撒欢儿的野马"},"title":"不想做设计师的演员不是好双生","sub_title":"看 Olsen 双生姐妹花玩转角色转换","favor_user_num":16,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/9/19/ae5960b2-7faa-4fa2-9c84-ac789d8d75f9_660x440.jpeg","publish_at":1474300800000,"id":76},{"author":{"username":"苏潜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/a6743869c424b81b9a7faf5c283203db_225x226.jpg","id":44171,"sign":"设计故纸堆里的考古学徒"},"title":"戴着你的许愿骨去流浪，流浪远方","sub_title":"旅途里都是对远方的祷告","favor_user_num":38,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/9/14/a34b59fb-a17e-488c-b04f-c27321ca8028_684x684.jpeg","publish_at":1474041600000,"id":75},{"author":{"username":"黎曦","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/068b70d77304e910f9bbfcc732e2d774_463x450.jpg","id":46473,"sign":"贪恋一抹胭脂香气的败家子"},"title":"月华如水，锆石和身体共婵娟的 N 种可能","sub_title":"我们爱着的，就是那个性感而优雅的世界","favor_user_num":22,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/9/14/a7f046c8-6f01-4d77-b8ca-28ed8d4e3b4c_800x800.jpeg","publish_at":1473868800000,"id":74},{"author":{"username":"川枝","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/e49c5fa883f8475d55523b73e141a4e8_300x300.jpg","id":41383,"sign":"梦游也在和设计师唠嗑的拜访者 Z"},"title":"在树叶的光影变换中，她发现了珠宝的秘密","sub_title":"当一个学习人类学的女孩爱上了设计","favor_user_num":20,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/9/12/4e6b131f-4f13-4d06-bc1f-52df4ca1c447_600x600.jpeg","publish_at":1473681780000,"id":73},{"author":{"username":"鱼烬","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/24/cb72799c716b347da480261217298b56_224x224.jpg","id":44168,"sign":"能在烤鱼身上研究烟熏妆的时尚骨头"},"title":"他做的每一副墨镜都是一首歌，哪一首你想唱给最爱的那个人听？","sub_title":"如果你想拥有一件时尚单品，不妨从一副墨镜开始","favor_user_num":28,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/9/10/1bacf1f0-118c-48b0-9f3a-cccf0ce8faea_520x520.jpeg","publish_at":1473390000000,"id":72},{"author":{"username":"小婧的梦想","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/9/24/b07ab371-fc42-4fa0-99b9-3a0cedb0ca0d.jpg","id":48122,"sign":"为臭美孜孜向学的艺术系在读生"},"title":"忍把浮华，换了素衣如织","sub_title":"最美人物「有物设计说」之婧篇","favor_user_num":46,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/9/6/b6103f61-a23a-4c5f-a75a-eb120127b92c_812x812.jpeg","publish_at":1473306120000,"id":69},{"author":{"username":"夏漱香菜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/5a069cd6278626c9a638bf46a8010837_450x450.jpg","id":41625,"sign":"迷失风向却沉浸在时尚海洋里的游鱼"},"title":"告别奢侈大牌，过自由自在的人生","sub_title":"人生亦如鞋子，舒适与否唯有自知","favor_user_num":22,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/9/5/8b4e9412-3407-47f1-a322-d78eb1abcd8a_853x853.jpeg","publish_at":1473079620000,"id":71},{"author":{"username":"小虎","avatar_url":"http://wx.qlogo.cn/mmopen/SXryYH6DzyY62B6gJJwz1TQAmIDlrLRJb5wO5bkULLm2ibCmesViaJto9B3q5cZStZOwJdwwkYFCtpiaKkJaDfFVw/0","id":980,"sign":"Tigerhood 太格有物 创始人"},"title":"Red Wing Icon：Tigerhood 创始人小虎的生活态度","sub_title":"最美人物「有物设计说」之小虎篇","favor_user_num":25,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/9/1/8bd9cd51-0ccd-47af-b336-06ad0da61c7d_635x635.jpeg","publish_at":1472701680000,"id":70},{"author":{"username":"狐萝卜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/d17e6a982c677b1e4b35726af80d6a50_254x255.jpg","id":30,"sign":"在光怪陆离的世界里记录时尚的梦旅人"},"title":"Kate Poole: 一个制作精油的「炼金术师」","sub_title":"万物的精髓就是平衡","favor_user_num":50,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/8/31/813cf0ce-0770-4a8e-b69a-fc50a0ad9db4_839x839.jpeg","publish_at":1472614440000,"id":68},{"author":{"username":"Cheryl","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/29/3d611df24cd321ec8f2b41ed11c75afb_337x339.jpg","id":1228,"sign":"Whoever said money doesnt buy happiness didn't know where to shop"},"title":"纽约客 Cheryl 的 Miansai 航海之旅","sub_title":"最美人物「有物设计说」之 Cheryl 篇","favor_user_num":53,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/8/29/cd5fd892-5a9f-4af2-be47-6cafdd244915_800x800.jpeg","publish_at":1472470080000,"id":67},{"author":{"username":"RainbowMe","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/29/0ce86625189bc5c071bdd4fc8ccabae9_170x170.jpg","id":29,"sign":"最美有物 创始人"},"title":"Amanda Pearl，在美学设计领域率性起舞的女人","sub_title":"从此小饰物们也学会了摇曳裙角和踮起脚尖","favor_user_num":58,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/8/24/179adfe9-a5f8-4af3-aa84-8b7f7ea695d0_400x400.jpeg","publish_at":1472023680000,"id":66},{"author":{"username":"夏漱香菜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/5a069cd6278626c9a638bf46a8010837_450x450.jpg","id":41625,"sign":"迷失风向却沉浸在时尚海洋里的游鱼"},"title":"她活出了从女人到母亲最「酷」的姿态","sub_title":"用色彩和奇思构建属于自己的墨镜世界","favor_user_num":17,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/8/24/07335ad6-206f-46ee-ab80-196e3c15f431_800x800.jpeg","publish_at":1472022240000,"id":64},{"author":{"username":"黎曦","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/068b70d77304e910f9bbfcc732e2d774_463x450.jpg","id":46473,"sign":"贪恋一抹胭脂香气的败家子"},"title":"Beltology：把腰带做成一门学问","sub_title":"只为了藏在腰间的那份优雅","favor_user_num":75,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/8/22/d264d850-6996-46f6-ae0c-31467fadde73_700x700.jpeg","publish_at":1471869660000,"id":63},{"author":{"username":"狐萝卜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/d17e6a982c677b1e4b35726af80d6a50_254x255.jpg","id":30,"sign":"在光怪陆离的世界里记录时尚的梦旅人"},"title":"戴上公主的冠冕华丽出嫁","sub_title":"如不能美丽一世，就惊艳一时","favor_user_num":221,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/8/17/400afc55-3bb9-4401-a76f-4722c88de242_600x600.jpeg","publish_at":1471405500000,"id":62},{"author":{"username":"夏漱香菜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/5a069cd6278626c9a638bf46a8010837_450x450.jpg","id":41625,"sign":"迷失风向却沉浸在时尚海洋里的游鱼"},"title":"造梦者与现实的相逢","sub_title":"隐藏在咖啡桌下的城市风浪","favor_user_num":173,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/9/13/045cd44d-85d3-4137-9083-9764fbb5bc31_800x800.jpeg","publish_at":1471404600000,"id":61}]
     */

    private DataBean data;
    /**
     * data : {"has_next":1,"articles":[{"author":{"username":"苏潜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/a6743869c424b81b9a7faf5c283203db_225x226.jpg","id":44171,"sign":"设计故纸堆里的考古学徒"},"title":"我想，我是忘记了长大","sub_title":"长不大的设计师，做出了俏皮的童话首饰","favor_user_num":13,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/17/462f6a13-b875-4958-9602-d0dcf1832b90_800x800.jpeg","publish_at":1477065600000,"id":91},{"author":{"username":"黎曦","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/068b70d77304e910f9bbfcc732e2d774_463x450.jpg","id":46473,"sign":"贪恋一抹胭脂香气的败家子"},"title":"为动物的自由而战","sub_title":"素食主义者的设计之路","favor_user_num":9,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/17/2f720e4b-98bf-4222-a069-66fad8007d99_809x809.jpeg","publish_at":1476979200000,"id":90},{"author":{"username":"鱼烬","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/24/cb72799c716b347da480261217298b56_224x224.jpg","id":44168,"sign":"能在烤鱼身上研究烟熏妆的时尚骨头"},"title":"当你不再试图从一个包中确定自己","sub_title":"KikaNY：还原一个包最应该有的简单状态","favor_user_num":10,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/17/7ac855a9-5680-4368-8ded-68cccf1bd2e3_800x800.jpeg","publish_at":1476892800000,"id":89},{"author":{"username":"Phoebe","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/09/19/bda1c2ac9234397c10982618f55562fe_199x200.jpg","id":36075,"sign":"在设计的世界撒欢儿的野马"},"title":"为阿尔法女度身定制的时尚","sub_title":"时尚是每个女人的事， 即使你选择同你的事业结婚","favor_user_num":7,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/17/30f07a18-c4d3-434f-9d90-8833f7f86f94_505x505.jpeg","publish_at":1476720000000,"id":88},{"author":{"username":"狐萝卜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/d17e6a982c677b1e4b35726af80d6a50_254x255.jpg","id":30,"sign":"在光怪陆离的世界里记录时尚的梦旅人"},"title":"领带的文艺时代","sub_title":"在别人正襟危坐时，我偏要玩世不恭","favor_user_num":23,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/13/74bea20e-c2f8-4031-9fcf-6eb7d304eef5_800x800.jpeg","publish_at":1476460800000,"id":86},{"author":{"username":"鱼烬","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/24/cb72799c716b347da480261217298b56_224x224.jpg","id":44168,"sign":"能在烤鱼身上研究烟熏妆的时尚骨头"},"title":"从小在祖父的地毯上捡黄金的女孩，用金子去描绘山峦温柔的曲线","sub_title":"她把爬过的山，都做成了戒指","favor_user_num":18,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/12/ecae22a0-cf65-4843-b6a5-1ff50049294a_750x750.jpeg","publish_at":1476288000000,"id":87},{"author":{"username":"Phoebe","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/09/19/bda1c2ac9234397c10982618f55562fe_199x200.jpg","id":36075,"sign":"在设计的世界撒欢儿的野马"},"title":"Donatella Versace：她为大牌续写着神话","sub_title":"她披上了时尚的铠甲，并让全世界都记住了她","favor_user_num":12,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/10/99162390-bb9a-41b9-86b5-9d13c5cff320_800x602.jpeg","publish_at":1476115200000,"id":85},{"author":{"username":"夏漱香菜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/5a069cd6278626c9a638bf46a8010837_450x450.jpg","id":41625,"sign":"迷失风向却沉浸在时尚海洋里的游鱼"},"title":"不愿涂脂抹粉，只愿扎根泥土","sub_title":"把朴素的生活过成一幅画","favor_user_num":38,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/3/babb2f3b-d5f1-491b-9019-a7da864cd788_800x800.jpeg","publish_at":1475856000000,"id":84},{"author":{"username":"川枝","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/e49c5fa883f8475d55523b73e141a4e8_300x300.jpg","id":41383,"sign":"梦游也在和设计师唠嗑的拜访者 Z"},"title":"送给你，这一季的忧郁和花香","sub_title":"把这个夏天的潮热和焦灼，风干在玻璃球里","favor_user_num":72,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/3/fc60867b-ac3e-4928-a7a1-640c290cf091_570x380.jpeg","publish_at":1475683200000,"id":83},{"author":{"username":"鱼烬","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/24/cb72799c716b347da480261217298b56_224x224.jpg","id":44168,"sign":"能在烤鱼身上研究烟熏妆的时尚骨头"},"title":"一个男孩要走多少路，才能称得上是一个男人","sub_title":"在这个女性化的年代，找寻男子汉失去的荣光","favor_user_num":34,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/10/3/bb4a7e22-8a29-4943-a3de-504a1e55b660_800x800.jpeg","publish_at":1475510400000,"id":82},{"author":{"username":"苏潜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/a6743869c424b81b9a7faf5c283203db_225x226.jpg","id":44171,"sign":"设计故纸堆里的考古学徒"},"title":"文学少女、珠宝设计和女巫的魔法首饰","sub_title":"她把「冰与火」和「五十度灰」，都做成了首饰","favor_user_num":75,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/9/29/d0e52773-ad0e-40a6-a6bc-4f61cca9514b_800x800.jpeg","publish_at":1475251200000,"id":81},{"author":{"username":"Mana Bean","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/9/24/ac99fa28-3e08-4067-b618-b9d63c0df7a6.jpg","id":16653,"sign":"一颗从金融学土壤中萌发的时尚之豆"},"title":"让光影自由起舞","sub_title":"建筑师的跨界设计之旅","favor_user_num":53,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/9/28/8dfdc050-e5e4-44ac-a287-b47eefa6ea6b_667x667.jpeg","publish_at":1475078400000,"id":80},{"author":{"username":"狐萝卜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/d17e6a982c677b1e4b35726af80d6a50_254x255.jpg","id":30,"sign":"在光怪陆离的世界里记录时尚的梦旅人"},"title":"新时代的左轮手枪，用细腻柔情直击你的心脏","sub_title":"你愿意和这样的男人去探险吗","favor_user_num":25,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/9/21/2658da4c-67a7-48c8-8c20-3da602b90bd7_800x800.jpeg","publish_at":1474905600000,"id":77},{"author":{"username":"川枝","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/e49c5fa883f8475d55523b73e141a4e8_300x300.jpg","id":41383,"sign":"梦游也在和设计师唠嗑的拜访者 Z"},"title":"让黑暗世界的人们，触摸到时间的流逝","sub_title":"设计的终极关怀是让所有人都更有尊严地生活","favor_user_num":61,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/9/21/8c185195-3317-4ee0-97f6-18d3e1df22dc_800x800.jpeg","publish_at":1474646400000,"id":79},{"author":{"username":"Phoebe","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/09/19/bda1c2ac9234397c10982618f55562fe_199x200.jpg","id":36075,"sign":"在设计的世界撒欢儿的野马"},"title":"Jonathan Anderson：这个小哥有着开了挂的人生","sub_title":"挑战无限，乐趣无穷","favor_user_num":15,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/9/21/15a6b49f-e17d-4ab4-a387-e45e4af3977d_548x548.jpeg","publish_at":1474387200000,"id":78},{"author":{"username":"Phoebe","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/09/19/bda1c2ac9234397c10982618f55562fe_199x200.jpg","id":36075,"sign":"在设计的世界撒欢儿的野马"},"title":"不想做设计师的演员不是好双生","sub_title":"看 Olsen 双生姐妹花玩转角色转换","favor_user_num":16,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/9/19/ae5960b2-7faa-4fa2-9c84-ac789d8d75f9_660x440.jpeg","publish_at":1474300800000,"id":76},{"author":{"username":"苏潜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/a6743869c424b81b9a7faf5c283203db_225x226.jpg","id":44171,"sign":"设计故纸堆里的考古学徒"},"title":"戴着你的许愿骨去流浪，流浪远方","sub_title":"旅途里都是对远方的祷告","favor_user_num":38,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/9/14/a34b59fb-a17e-488c-b04f-c27321ca8028_684x684.jpeg","publish_at":1474041600000,"id":75},{"author":{"username":"黎曦","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/068b70d77304e910f9bbfcc732e2d774_463x450.jpg","id":46473,"sign":"贪恋一抹胭脂香气的败家子"},"title":"月华如水，锆石和身体共婵娟的 N 种可能","sub_title":"我们爱着的，就是那个性感而优雅的世界","favor_user_num":22,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/9/14/a7f046c8-6f01-4d77-b8ca-28ed8d4e3b4c_800x800.jpeg","publish_at":1473868800000,"id":74},{"author":{"username":"川枝","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/e49c5fa883f8475d55523b73e141a4e8_300x300.jpg","id":41383,"sign":"梦游也在和设计师唠嗑的拜访者 Z"},"title":"在树叶的光影变换中，她发现了珠宝的秘密","sub_title":"当一个学习人类学的女孩爱上了设计","favor_user_num":20,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/9/12/4e6b131f-4f13-4d06-bc1f-52df4ca1c447_600x600.jpeg","publish_at":1473681780000,"id":73},{"author":{"username":"鱼烬","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/24/cb72799c716b347da480261217298b56_224x224.jpg","id":44168,"sign":"能在烤鱼身上研究烟熏妆的时尚骨头"},"title":"他做的每一副墨镜都是一首歌，哪一首你想唱给最爱的那个人听？","sub_title":"如果你想拥有一件时尚单品，不妨从一副墨镜开始","favor_user_num":28,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/9/10/1bacf1f0-118c-48b0-9f3a-cccf0ce8faea_520x520.jpeg","publish_at":1473390000000,"id":72},{"author":{"username":"小婧的梦想","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/9/24/b07ab371-fc42-4fa0-99b9-3a0cedb0ca0d.jpg","id":48122,"sign":"为臭美孜孜向学的艺术系在读生"},"title":"忍把浮华，换了素衣如织","sub_title":"最美人物「有物设计说」之婧篇","favor_user_num":46,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/9/6/b6103f61-a23a-4c5f-a75a-eb120127b92c_812x812.jpeg","publish_at":1473306120000,"id":69},{"author":{"username":"夏漱香菜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/5a069cd6278626c9a638bf46a8010837_450x450.jpg","id":41625,"sign":"迷失风向却沉浸在时尚海洋里的游鱼"},"title":"告别奢侈大牌，过自由自在的人生","sub_title":"人生亦如鞋子，舒适与否唯有自知","favor_user_num":22,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/9/5/8b4e9412-3407-47f1-a322-d78eb1abcd8a_853x853.jpeg","publish_at":1473079620000,"id":71},{"author":{"username":"小虎","avatar_url":"http://wx.qlogo.cn/mmopen/SXryYH6DzyY62B6gJJwz1TQAmIDlrLRJb5wO5bkULLm2ibCmesViaJto9B3q5cZStZOwJdwwkYFCtpiaKkJaDfFVw/0","id":980,"sign":"Tigerhood 太格有物 创始人"},"title":"Red Wing Icon：Tigerhood 创始人小虎的生活态度","sub_title":"最美人物「有物设计说」之小虎篇","favor_user_num":25,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/9/1/8bd9cd51-0ccd-47af-b336-06ad0da61c7d_635x635.jpeg","publish_at":1472701680000,"id":70},{"author":{"username":"狐萝卜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/d17e6a982c677b1e4b35726af80d6a50_254x255.jpg","id":30,"sign":"在光怪陆离的世界里记录时尚的梦旅人"},"title":"Kate Poole: 一个制作精油的「炼金术师」","sub_title":"万物的精髓就是平衡","favor_user_num":50,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/8/31/813cf0ce-0770-4a8e-b69a-fc50a0ad9db4_839x839.jpeg","publish_at":1472614440000,"id":68},{"author":{"username":"Cheryl","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/29/3d611df24cd321ec8f2b41ed11c75afb_337x339.jpg","id":1228,"sign":"Whoever said money doesnt buy happiness didn't know where to shop"},"title":"纽约客 Cheryl 的 Miansai 航海之旅","sub_title":"最美人物「有物设计说」之 Cheryl 篇","favor_user_num":53,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/8/29/cd5fd892-5a9f-4af2-be47-6cafdd244915_800x800.jpeg","publish_at":1472470080000,"id":67},{"author":{"username":"RainbowMe","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/29/0ce86625189bc5c071bdd4fc8ccabae9_170x170.jpg","id":29,"sign":"最美有物 创始人"},"title":"Amanda Pearl，在美学设计领域率性起舞的女人","sub_title":"从此小饰物们也学会了摇曳裙角和踮起脚尖","favor_user_num":58,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/8/24/179adfe9-a5f8-4af3-aa84-8b7f7ea695d0_400x400.jpeg","publish_at":1472023680000,"id":66},{"author":{"username":"夏漱香菜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/5a069cd6278626c9a638bf46a8010837_450x450.jpg","id":41625,"sign":"迷失风向却沉浸在时尚海洋里的游鱼"},"title":"她活出了从女人到母亲最「酷」的姿态","sub_title":"用色彩和奇思构建属于自己的墨镜世界","favor_user_num":17,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/8/24/07335ad6-206f-46ee-ab80-196e3c15f431_800x800.jpeg","publish_at":1472022240000,"id":64},{"author":{"username":"黎曦","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/068b70d77304e910f9bbfcc732e2d774_463x450.jpg","id":46473,"sign":"贪恋一抹胭脂香气的败家子"},"title":"Beltology：把腰带做成一门学问","sub_title":"只为了藏在腰间的那份优雅","favor_user_num":75,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/8/22/d264d850-6996-46f6-ae0c-31467fadde73_700x700.jpeg","publish_at":1471869660000,"id":63},{"author":{"username":"狐萝卜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/d17e6a982c677b1e4b35726af80d6a50_254x255.jpg","id":30,"sign":"在光怪陆离的世界里记录时尚的梦旅人"},"title":"戴上公主的冠冕华丽出嫁","sub_title":"如不能美丽一世，就惊艳一时","favor_user_num":221,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/8/17/400afc55-3bb9-4401-a76f-4722c88de242_600x600.jpeg","publish_at":1471405500000,"id":62},{"author":{"username":"夏漱香菜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/5a069cd6278626c9a638bf46a8010837_450x450.jpg","id":41625,"sign":"迷失风向却沉浸在时尚海洋里的游鱼"},"title":"造梦者与现实的相逢","sub_title":"隐藏在咖啡桌下的城市风浪","favor_user_num":173,"web_url":"","image_url":"http://dstatic.zuimeia.com/common/image/2016/9/13/045cd44d-85d3-4137-9083-9764fbb5bc31_800x800.jpeg","publish_at":1471404600000,"id":61}]}
     * result : 1
     */

    private int result;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public static class DataBean {
        private int has_next;
        /**
         * author : {"username":"苏潜","avatar_url":"http://dstatic.zuimeia.com/user/avatar/2016/08/27/a6743869c424b81b9a7faf5c283203db_225x226.jpg","id":44171,"sign":"设计故纸堆里的考古学徒"}
         * title : 我想，我是忘记了长大
         * sub_title : 长不大的设计师，做出了俏皮的童话首饰
         * favor_user_num : 13
         * web_url :
         * image_url : http://dstatic.zuimeia.com/common/image/2016/10/17/462f6a13-b875-4958-9602-d0dcf1832b90_800x800.jpeg
         * publish_at : 1477065600000
         * id : 91
         */

        private List<ArticlesBean> articles;

        public int getHas_next() {
            return has_next;
        }

        public void setHas_next(int has_next) {
            this.has_next = has_next;
        }

        public List<ArticlesBean> getArticles() {
            return articles;
        }

        public void setArticles(List<ArticlesBean> articles) {
            this.articles = articles;
        }

        public static class ArticlesBean implements Serializable {
            @Override
            public String toString() {
                return "ArticlesBean{" +
                        "author=" + author +
                        ", title='" + title + '\'' +
                        ", sub_title='" + sub_title + '\'' +
                        ", favor_user_num=" + favor_user_num +
                        ", web_url='" + web_url + '\'' +
                        ", image_url='" + image_url + '\'' +
                        ", publish_at=" + publish_at +
                        ", id=" + id +
                        '}';
            }

            /**
             * username : 苏潜
             * avatar_url : http://dstatic.zuimeia.com/user/avatar/2016/08/27/a6743869c424b81b9a7faf5c283203db_225x226.jpg
             * id : 44171
             * sign : 设计故纸堆里的考古学徒
             */



            private AuthorBean author;
            private String title;
            private String sub_title;
            private int favor_user_num;
            private String web_url;
            private String image_url;
            private long publish_at;
            private int id;
            private int resId;
            private String authorName;

            public AuthorBean getAuthor() {
                return author;
            }

            public void setAuthor(AuthorBean author) {
                this.author = author;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSub_title() {
                return sub_title;
            }

            public void setSub_title(String sub_title) {
                this.sub_title = sub_title;
            }

            public int getFavor_user_num() {
                return favor_user_num;
            }

            public void setFavor_user_num(int favor_user_num) {
                this.favor_user_num = favor_user_num;
            }

            public String getWeb_url() {
                return web_url;
            }

            public void setWeb_url(String web_url) {
                this.web_url = web_url;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public long getPublish_at() {
                return publish_at;
            }

            public void setPublish_at(long publish_at) {
                this.publish_at = publish_at;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getResId() {
                return resId;
            }

            public void setResId(int resId) {
                this.resId = resId;
            }

            public String getAuthorName() {
                return authorName;
            }

            public void setAuthorName(String authorName) {
                this.authorName = authorName;
            }

            public static class AuthorBean implements Serializable {
                private String username;
                private String avatar_url;
                private int id;
                private String sign;

                @Override
                public String toString() {
                    return "AuthorBean{" +
                            "username='" + username + '\'' +
                            ", avatar_url='" + avatar_url + '\'' +
                            ", id=" + id +
                            ", sign='" + sign + '\'' +
                            '}';
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getAvatar_url() {
                    return avatar_url;
                }

                public void setAvatar_url(String avatar_url) {
                    this.avatar_url = avatar_url;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getSign() {
                    return sign;
                }

                public void setSign(String sign) {
                    this.sign = sign;
                }


            }
        }
    }
}
