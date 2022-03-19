package com.atguigu.statisticsservice.service.impl;

import com.atguigu.commonutils.R;
import com.atguigu.servicebase.exception.GuliException;
import com.atguigu.statisticsservice.entity.StatisticsDaily;
import com.atguigu.statisticsservice.entity.vo.StatisticsDailyVo;
import com.atguigu.statisticsservice.feign.MemberFeignService;
import com.atguigu.statisticsservice.mapper.StatisticsDailyMapper;
import com.atguigu.statisticsservice.service.StatisticsDailyService;
import com.atguigu.statisticsservice.utils.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-03-18
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    private MemberFeignService memberFeignService;

    @Override
    public void createStatisticsDailyByDay(String day) {
        if (StringUtils.isEmpty(day)) {
            throw new GuliException(20001, "生成单天的统计数据失败，时间不能为空");
        }

        R registerCountR = memberFeignService.getRegisterCountByDay(day);

        Integer registerCount = (Integer) registerCountR.getData().get("registerCount");

        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated", day);
        baseMapper.delete(wrapper);

        StatisticsDaily statisticsDaily = new StatisticsDaily();
        // 注册人数
        statisticsDaily.setRegisterNum(registerCount);
        // 统计日期
        statisticsDaily.setDateCalculated(day);
        statisticsDaily.setVideoViewNum(RandomUtils.nextInt(100,200));
        statisticsDaily.setLoginNum(RandomUtils.nextInt(100,200));
        statisticsDaily.setCourseNum(RandomUtils.nextInt(100,200));

        baseMapper.insert(statisticsDaily);
    }

    @Override
    public Map<String, Object> getStatisticsDailyData(StatisticsDailyVo statisticsDailyVo) {

        if (statisticsDailyVo == null || StringUtils.isEmpty(statisticsDailyVo.getType())) {
            throw new GuliException(20001, "请选择维度");
        }

        String defaultBegin = DateUtil.formatDate(DateUtil.addDays(new Date(), -7));
        String defaultEnd = DateUtil.formatDate(new Date());

        if (StringUtils.isEmpty(statisticsDailyVo.getBegin())) {
            statisticsDailyVo.setBegin(defaultBegin);
        }
        if (StringUtils.isEmpty(statisticsDailyVo.getEnd())) {
            statisticsDailyVo.setEnd(defaultEnd);
        }

        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("date_calculated", statisticsDailyVo.getBegin(), statisticsDailyVo.getEnd());
        queryWrapper.select("date_calculated", statisticsDailyVo.getType());
        queryWrapper.orderByAsc("date_calculated");

        List<StatisticsDaily> dailyList = baseMapper.selectList(queryWrapper);

        List<String> dateCalculatedList = new ArrayList<>();
        List<Integer> dataList = new ArrayList<>();

        for (StatisticsDaily daily : dailyList) {
            dateCalculatedList.add(daily.getDateCalculated());

            switch (statisticsDailyVo.getType()) {
                case "login_num" :
                    dataList.add(daily.getLoginNum());
                    break;
                case "register_num" :
                    dataList.add(daily.getRegisterNum());
                    break;
                case "video_view_num" :
                    dataList.add(daily.getVideoViewNum());
                    break;
                case "course_num" :
                    dataList.add(daily.getCourseNum());
                    break;
                default:
                    break;
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("dateCalculatedList", dateCalculatedList);
        map.put("dataList", dataList);
        return map;
    }
}
