package com.xmjd.info.commons.mail;

import com.xmjd.info.commons.util.ConfigUtil;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.util.List;
import java.util.Properties;

/**
 *  * 简单邮件发送器，可单发，群发。
 *  *
 *  * @author shenyuting
 *  *
 *  
 */
public class SimpleMailSender {
    /**
     * 发送邮件的props文件
     */
    private final transient Properties props = System.getProperties();
    /**
     * 邮件服务器登录验证
     */
    private transient MailAuthenticator authenticator;

    /**
     * 邮箱session
     */
    private transient Session session;

    /**
     * 初始化
     */
    public SimpleMailSender() {
        // 初始化props
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", ConfigUtil.emailSMTP());
        // 验证
        authenticator = new MailAuthenticator(ConfigUtil.emailName(), ConfigUtil.emailPwd());
        // 创建session
        session = Session.getInstance(props, authenticator);
    }

    /**
     * 发送邮件
     *
     * @param recipient   收件人邮箱地址
     * @param subject     邮件主题
     * @param content     邮件内容
     * @throws AddressException
     * @throws MessagingException   
     */
    public void send(String recipient, String subject, Object content) throws AddressException, MessagingException {
        // 创建mime类型邮件
        final MimeMessage message = new MimeMessage(session);
        // 设置发信人
        message.setFrom(new InternetAddress(authenticator.getUsername()));
        // 设置收件人
        message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
        // 设置主题
        message.setSubject(subject);
        // 设置邮件内容
        message.setContent(content.toString(), "text/html;charset=utf-8");
        // 发送
        Transport.send(message);
    }

    /**
     * 群发邮件
     *
     * @param recipients   收件人们
     * @param subject      主题
     * @param content      内容
     * @throws AddressException
     * @throws MessagingException   
     */
    public void send(List<String> recipients, String subject, Object content) throws AddressException, MessagingException {
        // 创建mime类型邮件
        final MimeMessage message = new MimeMessage(session);
        // 设置发信人
        message.setFrom(new InternetAddress(authenticator.getUsername()));
        // 设置收件人们
        final int num = recipients.size();
        InternetAddress[] addresses = new InternetAddress[num];
        for (int i = 0; i < num; i++) {
            addresses[i] = new InternetAddress(recipients.get(i));
        }
        message.setRecipients(RecipientType.TO, addresses);
        // 设置主题
        message.setSubject(subject);
        // 设置邮件内容
        message.setContent(content.toString(), "text/html;charset=utf-8");
        // 发送
        Transport.send(message);
    }

    public static void main(String args[]) throws MessagingException {
        //TEST
        SimpleMailSender sender = new SimpleMailSender();
        sender.send("362159172@qq.com", "您的账户登录密码已重置成功", "您的新密码为：32131232");
    }

}