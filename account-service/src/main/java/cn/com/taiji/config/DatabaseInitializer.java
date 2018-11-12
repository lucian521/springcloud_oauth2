package cn.com.taiji.config;

import cn.com.taiji.account.Account;
import cn.com.taiji.account.AccountRepository;
import cn.com.taiji.address.Address;
import cn.com.taiji.address.AddressType;
import cn.com.taiji.creditcard.CreditCard;
import cn.com.taiji.creditcard.CreditCardType;
import cn.com.taiji.customer.Customer;
import cn.com.taiji.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * 填充数据
 *
 * @author zhuohao
 * @create 2018-08-05 11:24
 */
@Service
@Profile("dev")
public class DatabaseInitializer {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public void populate() {
        // 清除已有数据
        customerRepository.deleteAll(); // Customer有一个外键引用Account，因此需要先删除Customer，然后才删除Account
        accountRepository.deleteAll();

        Account account = new Account("user", "12345");
        account.setDefaultAccount(true);

        Set<Address> addresses = new HashSet<>();
        Address address = new Address("中国", "吉林省", "吉林市", "丰满区", "street1", "street2", 000000, AddressType.SHIPPING);
        addresses.add(address);
        account.setAddresses(addresses);

        Set<CreditCard> creditCards = new HashSet<>();
        CreditCard creditCard = new CreditCard("6666666666666666", CreditCardType.VISA);
        creditCards.add(creditCard);
        account.setCreditCards(creditCards);

        //account = accountRepository.save(account); // spring data save方法的jpa实现是 EntityManager.persist方法，该方法不能persist detached entity, EntityManager.merge方法可以，但spring data jpa 未采用，所以 不单独保存，而是放到保存customer时使用级联保存。

        // 创建一个customer
        Customer customer = new Customer("Hao", "Zhuo", "zhuohao@mail.taiji.com.cn", account);
        customerRepository.save(customer);
    }

}
