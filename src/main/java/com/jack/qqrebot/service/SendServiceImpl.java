package com.jack.qqrebot.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jack.qqrebot.service.articles.ArticlesService;
import com.jack.qqrebot.service.baiduyundisk.BaiduDiskSearchService;
import com.jack.qqrebot.service.codercalendar.CodeCalendarService;
import com.jack.qqrebot.service.constellation.ConstellationService;
import com.jack.qqrebot.service.dashang.DashangService;
import com.jack.qqrebot.service.duyan.DuyanService;
import com.jack.qqrebot.service.emoticonpackage.EmoticonPackageService;
import com.jack.qqrebot.service.erciyuna.PicService;
import com.jack.qqrebot.service.gankService.GankeService;
import com.jack.qqrebot.service.historyontoday.HistoryOnTodayService;
import com.jack.qqrebot.service.leetcode.LeetCodeService;
import com.jack.qqrebot.service.meitu.MeituService;
import com.jack.qqrebot.service.menu.MenuService;
import com.jack.qqrebot.service.music.MusicService;
import com.jack.qqrebot.service.news.NewsService;
import com.jack.qqrebot.service.notice.NoticeService;
import com.jack.qqrebot.service.poetry.PoetryService;
import com.jack.qqrebot.service.ranking.RankingService;
import com.jack.qqrebot.service.satin.SatinService;
import com.jack.qqrebot.service.saylove.SayLoveService;
import com.jack.qqrebot.service.snh.SNHMembersService;
import com.jack.qqrebot.service.tuling.TulingService;
import com.jack.qqrebot.service.v2ex.V2exService;
import com.jack.qqrebot.service.weather.WeatherService;
import com.jack.qqrebot.service.weibo.WeiboService;
import com.jack.qqrebot.utils.SendMsgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;



@Service("sendService")
public class SendServiceImpl implements SendServiceI {

    private final CodeCalendarService codeCalendarService;
    private final ConstellationService constellationService;
    private final MeituService meituService;
    private final MenuService menuService;
    private final MusicService musicService;
    private final PoetryService poetryService;
    private final RankingService rankingService;
    private final SatinService satinService;
    private final TulingService tulingService;
    private final WeatherService weatherService;
    private final WeiboService weiboService;
    private final NewsService newsService;
    private final DuyanService duyanService;
    private final ArticlesService articlesService;
    private final HistoryOnTodayService historyOnTodayService;
    private final EmoticonPackageService emoticonPackageService;
    private final BaiduDiskSearchService baiduDiskSearchService;
    private final GankeService gankeService;
    private final V2exService v2exService;
    private final LeetCodeService leetCodeService;
    private final SayLoveService sayLoveService;
    private final PicService picService;
    private final SNHMembersService snhMembersService;
    private final DashangService dashangService;
    private final NoticeService noticeService;

    @Autowired
    public SendServiceImpl(CodeCalendarService codeCalendarService, ConstellationService constellationService, SayLoveService sayLoveService, PicService picService, MeituService meituService, MenuService menuService, MusicService musicService, PoetryService poetryService, NewsService newsService, ArticlesService articlesService, SNHMembersService snhMembersService, RankingService rankingService, HistoryOnTodayService historyOnTodayService, LeetCodeService leetCodeService, DuyanService duyanService, SatinService satinService, TulingService tulingService, NoticeService noticeService, GankeService gankeService, V2exService v2exService, WeatherService weatherService, DashangService dashangService, WeiboService weiboService, BaiduDiskSearchService baiduDiskSearchService, EmoticonPackageService emoticonPackageService) {
        this.codeCalendarService = codeCalendarService;
        this.constellationService = constellationService;
        this.sayLoveService = sayLoveService;
        this.picService = picService;
        this.meituService = meituService;
        this.menuService = menuService;
        this.musicService = musicService;
        this.poetryService = poetryService;
        this.newsService = newsService;
        this.articlesService = articlesService;
        this.snhMembersService = snhMembersService;
        this.rankingService = rankingService;
        this.historyOnTodayService = historyOnTodayService;
        this.leetCodeService = leetCodeService;
        this.duyanService = duyanService;
        this.satinService = satinService;
        this.tulingService = tulingService;
        this.noticeService = noticeService;
        this.gankeService = gankeService;
        this.v2exService = v2exService;
        this.weatherService = weatherService;
        this.dashangService = dashangService;
        this.weiboService = weiboService;
        this.baiduDiskSearchService = baiduDiskSearchService;
        this.emoticonPackageService = emoticonPackageService;
    }

    @Override
    public void dealGroupMsg(String message) throws UnsupportedEncodingException {
        JSONObject jsonObject = JSON.parseObject(message);
        String result = "";
        message = jsonObject.getString("message");
        Integer group_id = jsonObject.getInteger("group_id");
        if (message.contains("[CQ:at,qq=1244623542]")) {
            message = message.replace("[CQ:at,qq=1244623542]", "").trim();
            if (!StringUtils.isEmpty(message) && message.contains("诗")) {
                result = poetryService.getPoetryByKeyWord(message.replace("诗", "").replace(" ", ""));
            } else if (!StringUtils.isEmpty(message) && message.contains("新闻")) {
                result = newsService.getNewsByRandom();
            } else if (!StringUtils.isEmpty(message) && (message.contains("段子") || message.contains("笑话"))) {
                result = satinService.getSatinByRandom();
            }else if(!StringUtils.isEmpty(message) && (message.contains("二次元美图"))){
                result = picService.getRandomPic();
            } else if (!StringUtils.isEmpty(message) && message.contains("美图")) {
                result = meituService.getImageByRandom();
            } else if (!StringUtils.isEmpty(message) && message.contains("音乐")) {
                result = musicService.getMusicByName(message.replace("音乐", "").replace(" ", ""));
            } else if (!StringUtils.isEmpty(message) && message.contains("天气")) {
                result = weatherService.getWeatherByCity(message.replace("天气", "").replace(" ", ""));
            } else if (!StringUtils.isEmpty(message) && (message.contains("微博") || (message.contains("热搜")))) {
                result = weiboService.getWeiboHot();
            } else if (!StringUtils.isEmpty(message) && (message.contains("菜单"))) {
                result = menuService.getMenus();
            } else if (!StringUtils.isEmpty(message) && (message.contains("运势") || (message.contains("星座")))) {
                result = constellationService.getMsgByConstellationName(message.replace("运势", "").replace("星座", "").replace(" ", ""));
            } else if (!StringUtils.isEmpty(message) && (message.contains("黄历"))) {
                result = codeCalendarService.getTodayCoderCalendar();
            } else if (!StringUtils.isEmpty(message) && (message.contains("毒鸡汤"))) {
                result = duyanService.getDuyanRandom();
            } else if (!StringUtils.isEmpty(message) && (message.contains("排行榜"))) {
                result = rankingService.updateRanking(message);
            } else if (!StringUtils.isEmpty(message) && (message.contains("文章"))) {
                result = articlesService.getArticleByRandom();
            } else if (!StringUtils.isEmpty(message) && (message.contains("历史上的今天"))) {
                result = historyOnTodayService.getHistory();
            } else if (!StringUtils.isEmpty(message) && (message.contains("表情包"))) {
                result = emoticonPackageService.getEmoticonPackageByKeyWord(message.replace("表情包*",""));
            }else if(!StringUtils.isEmpty(message) && (message.contains("资源"))){
                result = baiduDiskSearchService.SearchByKeyWords(message.replace("资源","").replace(" ",""));
            } else if(!StringUtils.isEmpty(message) && (message.equals("干货"))){
                result = gankeService.getToadyData();
            } else if(!StringUtils.isEmpty(message) && (message.contains("干货今日"))){
                result = gankeService.getTodayDataByCategory(message.replace("干货今日","").replace(" ",""));
            } else if(!StringUtils.isEmpty(message) && (message.contains("干货日报类型"))){
                result = gankeService.reportType();
            }else if(!StringUtils.isEmpty(message) && (message.contains("干货日报"))){
                result = gankeService.report(message.replace("干货日报","").replace(" ",""));
            }  else if(!StringUtils.isEmpty(message) && (message.contains("干货"))){
                result = gankeService.getDataByCategory(message.replace("干货","").replace(" ",""));
            }else if(!StringUtils.isEmpty(message) && (message.contains("v2ex"))){
                result = v2exService.hotTopics();
            } else if(!StringUtils.isEmpty(message) && (message.contains("leetcode"))){
                result = leetCodeService.randomProblem();
            } else if(!StringUtils.isEmpty(message) && (message.contains("情话"))){
                result = sayLoveService.getLoveRandom();
            }else if(!StringUtils.isEmpty(message) && (message.contains("menhera"))){
                result = picService.getRandomMenhera();
            }else if(!StringUtils.isEmpty(message) && (message.toLowerCase().contains("snh"))){
                result = snhMembersService.getRandomMember();
            }else if(!StringUtils.isEmpty(message) && (message.contains("买女装排行"))){
                result = dashangService.getRank();
            }else if(!StringUtils.isEmpty(message) && (message.contains("支持买女装"))){
                result = dashangService.updateRank(message.replace("支持买女装","").trim());
            }else if(!StringUtils.isEmpty(message) && (message.contains("买女装"))){
                result = dashangService.getUlr();
            }else {
                result = tulingService.getMsgByMsg(message);
            }
            SendMsgUtils.sendGroupMsg(group_id, result);
        }
    }

    @Override
    public void dealNotice(String message) {
        JSONObject jsonObject = JSON.parseObject(message);
        String result= "";
        String noticeType = jsonObject.getString("notice_type");
        Integer group_id = jsonObject.getInteger("group_id");
        if(noticeType.equals("group_increase")){
            String user_id = jsonObject.getString("user_id");
            result = noticeService.groupIncrease(user_id);
        }else{
            String user_id = jsonObject.getString("user_id");
            result = noticeService.groupDecrease(user_id);
        }
        SendMsgUtils.sendGroupMsg(group_id, result);
    }
}
