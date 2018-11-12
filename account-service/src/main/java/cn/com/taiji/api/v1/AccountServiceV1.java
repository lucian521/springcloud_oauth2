package cn.com.taiji.api.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import cn.com.taiji.account.Account;
import cn.com.taiji.account.AccountRepository;
import cn.com.taiji.catalog.CatalogInfo;
import cn.com.taiji.user.User;

/**
 * @author zhuohao
 */
@Service
public class AccountServiceV1 {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private OAuth2RestTemplate oAuth2RestTemplate;

    public List<Account> getUserAccount() {
        List<Account> accounts = null;
        User user = oAuth2RestTemplate.getForObject("http://user-service/auth/v1/me", User.class);
        if(user != null) {
            accounts = accountRepository.findAccountsByUserId(user.getUsername()); // 用户名作为userId是合理的，因为登录时使用的用户名是唯一的
        }

        // 掩盖信用卡除最后四位以外的数字
        if(accounts != null) {
            accounts.forEach(account -> account.getCreditCards()
                    .forEach(creditCard -> creditCard.setNumber(creditCard.getNumber().replaceAll("[\\d]{4}(?!$)", "****-"))));
        }
        //微服务之间调用
        CatalogInfo catalogInfo = getCatalog(user.getId().toString());
        return accounts;
    }
    
  //根据用户id获取所属目录
    public CatalogInfo getCatalog(String userid) {
    	CatalogInfo catalogInfo = oAuth2RestTemplate.getForObject(String.format("http://catalog-service/v1/catalogbyuser/%s", userid), CatalogInfo.class);
        return catalogInfo;
    }
}
