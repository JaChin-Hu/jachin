package com.jachin.blog.utils;

import com.jachin.blog.mapper.LoginInfoMapper;
import com.jachin.blog.mapper.OpeLogMapper;
import com.jachin.blog.pojo.entity.LoginInfoEntity;
import com.jachin.blog.pojo.entity.OpeLogEntity;
import com.jachin.common.constant.Constants;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * @author JaChin
 * @version 1.0
 * @date 2022/8/26 14:37
 */
public class AsyncFactory {
    private static final Logger logger = LoggerFactory.getLogger(AsyncFactory.class);

    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息
     * @param args     列表
     * @return 任务task
     */
    public static TimerTask recordLoginInfo(final String username, final String status, final String message,
                                            final Object... args) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = IpUtils.getIpAddress(ServletUtils.getRequest());
        return new TimerTask() {
            @Override
            public void run() {
                String address = IpUtils.getRealAddressByIp(ip);
                String s = getBlock(ip) +
                        address +
                        getBlock(username) +
                        getBlock(status) +
                        getBlock(message);
                // 打印信息到日志
                logger.info(s, args);
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
                LoginInfoEntity loginInfo = new LoginInfoEntity();
                loginInfo.setUsername(username);
                loginInfo.setIp(ip);
                loginInfo.setLocation(address);
                loginInfo.setBrowser(browser);
                loginInfo.setOs(os);
                loginInfo.setMsg(message);
                // 日志状态
                if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER)) {
                    loginInfo.setStatus(Constants.SUCCESS);
                } else if (Constants.LOGIN_FAIL.equals(status)) {
                    loginInfo.setStatus(Constants.FAIL);
                }
                // 插入数据
                SpringUtils.getBean(LoginInfoMapper.class).insert(loginInfo);
            }
        };
    }

    /**
     * 操作日志记录
     *
     * @param opeLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordLog(final OpeLogEntity opeLog) {
        return new TimerTask() {
            @Override
            public void run() {
                // 远程查询操作地点
                opeLog.setLocation(IpUtils.getRealAddressByIp(opeLog.getIp()));
                SpringUtils.getBean(OpeLogMapper.class).insert(opeLog);
            }
        };
    }

    public static String getBlock(Object msg) {
        if (msg == null) {
            msg = "";
        }
        return "[" + msg + "]";
    }
}
