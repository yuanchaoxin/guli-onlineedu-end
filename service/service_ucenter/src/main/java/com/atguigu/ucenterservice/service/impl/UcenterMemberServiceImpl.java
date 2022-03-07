package com.atguigu.ucenterservice.service.impl;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.MD5;
import com.atguigu.servicebase.exception.GuliException;
import com.atguigu.ucenterservice.entity.UcenterMember;
import com.atguigu.ucenterservice.entity.vo.RegisterVo;
import com.atguigu.ucenterservice.mapper.UcenterMemberMapper;
import com.atguigu.ucenterservice.service.UcenterMemberService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-03-06
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public String login(UcenterMember ucenterMember) {

        if (ucenterMember == null) {
            throw new GuliException(20001, "登录失败");
        }

        String mobile = ucenterMember.getMobile();
        String password = ucenterMember.getPassword();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new GuliException(20001, "登录失败，手机号和密码不能为空");
        }

        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        UcenterMember member = baseMapper.selectOne(queryWrapper);
        if (member == null) {
            throw new GuliException(20001, "登录失败，用户不存在");
        }

        if (!MD5.encrypt(password).equals(member.getPassword())) {
            throw new GuliException(20001, "登录失败，用户名或密码不正确");
        }

        if (member.getIsDisabled()) {
            throw new GuliException(20001, "登录失败，用户已禁用");
        }

        String token = JwtUtils.getJwtToken(member.getId(), member.getNickname());
        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {

        if (registerVo == null) {
            throw new GuliException(20001, "注册失败");
        }

        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();

        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(mobile)
                || StringUtils.isEmpty(nickname) || StringUtils.isEmpty(password)) {
            throw new GuliException(20001, "注册失败");
        }

        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        UcenterMember member = baseMapper.selectOne(queryWrapper);
        if (member != null) {
            throw new GuliException(20001, "注册失败，用户已存在");
        }

        String redisCode = String.valueOf(redisTemplate.opsForValue().get(mobile));
        if (!code.equals(redisCode)) {
            throw new GuliException(20001, "注册失败，验证码已失效，请重新获取");
        }

        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setIsDisabled(false);
        ucenterMember.setMobile(mobile);
        ucenterMember.setPassword(MD5.encrypt(password));
        ucenterMember.setNickname(nickname);
        ucenterMember.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        baseMapper.insert(ucenterMember);
    }

    @Override
    public UcenterMember getMemberInfo(HttpServletRequest httpRequest) {

        String memberId = JwtUtils.getMemberIdByJwtToken(httpRequest);
        UcenterMember ucenterMember = baseMapper.selectById(memberId);
        return ucenterMember;
    }
}
